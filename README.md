# AOP

> AOP根据对源代码修改的时机不同分为两种:一是以AspectJ为代表的在编译期修改源代码的静态AOP。二是以SpringAop为代表的在运行时期对源代码进行修改的动态AOP。

## SpringAop
SpringAop提供了强大的动态AOP，当目标类有实现接口的情况下默认使用JDK动态代理，当目标类没有接口时使用CJLIB动态代理，当目标类实现接口的情况下也可强制使用CHLIB动态代理。

> **注意：**
1. JDK动态代理是基于接口的，CJLIB动态代理是基于类继承的，所以在使用CJLIB代理的时候目标类的final方法不能被增强(建议目标类实现接口)。还有一点需要注意的是当目标类内部自我调用的时候将无法实现切面的增强(@Transactional)
2. Spring提供AspectJ的支持，但是在没有特殊情况下基本上不会使用AspectJ，因为SpringAop已经足够强大。SpringAop切入点是方法，如果要对属性增加应该使用AspectJ。
SpringAop和AspectJ是AOP的两种不同的实现方式，是互补的而不是竞争关系。

## AOP术语
- Target(目标类)：需要被代理的类。
- Joinpoint(连接点): 所谓连接点是指那些可能被拦截到的方法。 
- PointCut (切入点)：已经被增强的连接点。 
- Advice(通知/增强): 对连接点进行增强代码。
- Weaving(织入): 是指把增强Advice应用到目标对象Target来创建新的代理Proxy的过程。
- Aspect(切面): 是切入点Pointcut和通知Advice的结合，一个切入点和一个通知，组成成一个特殊的面。
## 切入点表达式
### 通配符
```
*  匹配任意字符，只匹配一个元素。
.. 匹配任意字符，可以匹配多个元素 ，在表示类时，必须和*联合使用。
+  表示按照类型匹配指定类的所有类，必须跟在类名后面，如 com.cad.Car+ ,表示继承该类的所有子类包括本身。
```
### 常用的语法
- execution : 用来匹配执行方法的连接点
```
execution(<修饰符><返回类型><包.类.方法(参数)><异常>) 
```
- @annotation(*) : 匹配指定注解

[切入点表达式简述](https://www.jianshu.com/p/dadc7d730489)

## 通知类型
- Before : 前置通知，一般用于各种校验，在方法执行前执行，如果通知抛出异常，则阻止方法运行。  

- AfterReturning : 后置通知，方法正常返回后执行，如果方法中抛出异常，通知无法执行。必须在方法执行后才执行，所以可以获得方法的返回值。  
- Around : 环绕通知，功能比较强大。方法执行前后分别执行，可以阻止方法的执行必须手动执行目标方法。
- AfterThrowingA : 抛出异常通知，一般用于包装异常信息。方法抛出异常后执行，如果方法没有抛出异常，无法执行 
- After : 最终通知，用于清理现场。无论方法是否抛出异常都执行。

### 示例代码
使用springboot全注解实现AOP。

## JDK动态代理原理
### Proxy
```
Proxy{
    private static final Class<?>[] constructorParams = {InvocationHandler.class };

    private static final WeakCache<ClassLoader, Class<?>[], Class<?>> 
             proxyClassCache = new WeakCache<>(new KeyFactory(), new ProxyClassFactory());
       
    protected InvocationHandler h;

    // 私有构造方法
    private Proxy() {
    }  
  
    protected Proxy(InvocationHandler h) {
        Objects.requireNonNull(h);
        this.h = h;
    }

    public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces,InvocationHandler h)
                  throws IllegalArgumentException                       
    {
        Objects.requireNonNull(h);
        // 1.拷贝接口
        final Class<?>[] intfs = interfaces.clone();
        // 检查执行权限
        final SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            checkProxyAccess(Reflection.getCallerClass(), loader, intfs);
        }

        // 2.生成 proxy class
        Class<?> cl = getProxyClass0(loader, intfs);
  
        try {
            if (sm != null) {
                checkNewProxyPermission(Reflection.getCallerClass(), cl);
            }
            // 3.反射获得构造
            final Constructor<?> cons = cl.getConstructor(constructorParams);
            final InvocationHandler ih = h;
            // 检查修饰类型
            if (!Modifier.isPublic(cl.getModifiers())) {
                AccessController.doPrivileged(new PrivilegedAction<Void>() {
                    public Void run() {
                        cons.setAccessible(true);
                        return null;
                    }
                });
            }
            // 4.返回代理对象
            return cons.newInstance(new Object[]{h});
        } catch (IllegalAccessException|InstantiationException e) {
            throw new InternalError(e.toString(), e);
        } catch (InvocationTargetException e) {
          // ....
        } catch (NoSuchMethodException e) {
            throw new InternalError(e.toString(), e);
        }
    }
}
   private static Class<?> getProxyClass0(ClassLoader loader, Class<?>... interfaces) {
        // 继承这么多接口...傻逼吧！                                 
        if (interfaces.length > 65535) {
            throw new IllegalArgumentException("interface limit exceeded");
        }

        // 如果给定接口的代理类存在则返回缓存的代理类,不存在通过ProxyClassFactory创建
        return proxyClassCache.get(loader, interfaces);
    } 
```
### ProxyClassFactory
```
private static final class ProxyClassFactory  implements BiFunction<ClassLoader, Class<?>[], Class<?>>{
 
    // prefix for all proxy class names
    private static final String proxyClassNamePrefix = "$Proxy";
 
     @Override
     public Class<?> apply(ClassLoader loader, Class<?>[] interfaces) {
        // 这个map我还是头一次见,和hashMap还是有点小区别的
        Map<Class<?>, Boolean> interfaceSet = new IdentityHashMap<>(interfaces.length);
        for (Class<?> intf : interfaces) {
                // 验证类加载器是否解析过这个接口
                Class<?> interfaceClass = null;
                try {
                // false 不执行静态代码块的内容... 
                    interfaceClass = Class.forName(intf.getName(), false, loader);
                } catch (ClassNotFoundException e) {
                }
                if (interfaceClass != intf) {
                    throw new IllegalArgumentException(
                        intf + " is not visible from class loader");
                }
     
                if (!interfaceClass.isInterface()) {
                    throw new IllegalArgumentException(
                        interfaceClass.getName() + " is not an interface");
                }
                // 已经实现的接口不再解析了... 怎么不在外面判断？
                if (interfaceSet.put(interfaceClass, Boolean.TRUE) != null) {
                    throw new IllegalArgumentException(
                        "repeated interface: " + interfaceClass.getName());
                }
            }
            // 拼接包名等...
           
            // 生成字节码文件
            byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
                proxyName, interfaces, accessFlags);
            try {
                // 返回代理class
                return defineClass0(loader, proxyName,
                                    proxyClassFile, 0, proxyClassFile.length);
            } catch (ClassFormatError e) {
               
                throw new IllegalArgumentException(e.toString());
            }
        }
}
       
```

### 实现自己的动态代理

示例代码:myJdkProxy

## Javassist

- package:javassist

Javassist是一个开源的分析、编辑和创建Java字节码的类库。可以对类直接进行增强和修改实现"AOP"。相对于反射性能要高出很多。
[动态代理性能方案对比](http://javatar.iteye.com/blog/814426)

## SpringBoot 
- package:srping

SpringBoot创建非web应用可以只引入spring-boot-starter依赖。默认实现了context.registerShutdownHook()方法用于容器的正常销毁。
无需再自己实现registerShutdownHook

ApplicationContextAware：通过实现该类获取spring容器的Bean

ApplicationListener : [Spring事件驱动模型](http://jinnianshTilongnian.iteye.com/blog/1902886)非常赞的文章
实际上也是发布-订阅模式

## 设计模式

- package:designPattern

责任链模式：多用于过滤器，请求者和接收者解耦(dubbo的filter实现比较特殊)
装饰模式：wapper类试用,用于功能增强
适配器模式：用于功能的分发？？
单例模式：线程安全,个人感觉单例模式才是最难的，易写难精
发布-订阅模式：spring的监听，用于广播

## other
个人感觉抽象类有好使的地方，但是不易读，可读性稍微差一些。

## 优秀博客

[dubbo作者梁飞](http://javatar.iteye.com/)
[京东张开涛](http://jinnianshilongnian.iteye.com/)

