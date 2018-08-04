package javassist;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

public class JavassistCompiler<T> {
    private static final ConcurrentHashMap<ClassLoader, ClassPool> CLASS_POOL = new ConcurrentHashMap<ClassLoader, ClassPool>();

    public Class<?> doCompile(T t) throws Exception {
        if (t == null)
            throw new RuntimeException("传入对象不能为空");
        Class clazz = t.getClass();
        // ctClass对象容器
        ClassPool classPool = getClassPool(getClass().getClassLoader());
        classPool.importPackage("package" + clazz.getPackage().getName());
        // 生成一个public的类
        CtClass ctClass = classPool.makeClass(clazz.getName()+"$Proxy");
        // 添加构造方法
        // CtConstructor constructor = new CtConstructor(new CtClass[]{}, ctClass);
        // 添加函数体
        // StringBuffer stringBuffer = new StringBuffer();

        // 添加属性
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            try {
                CtField ctField = new CtField(classPool.get(field.getType().getName()),
                        field.getName(), ctClass);
                ctField.setModifiers(Modifier.PRIVATE);
                ctClass.addField(ctField);
                String name = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                ctClass.addMethod(CtNewMethod.getter("get" + name, ctField));
                ctClass.addMethod(CtNewMethod.setter("set" + name, ctField));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 添加方法
        StringBuffer buffer = new StringBuffer();

        for (Method method : clazz.getDeclaredMethods()) {
            try {
                if (method.getName().startsWith("get"))
                    continue;
                if (method.getName().startsWith("set"))
                    continue;
                CtMethod ctMethod = new CtMethod(CtClass.voidType, method.getName(),
                        new CtClass[]{}, ctClass);
                // 为自定义方法设置修饰符
                ctMethod.setModifiers(Modifier.PUBLIC);
                // 函数体
                buffer.append("{\n")
                        .append(" System.out.println(\"我是一年级二班的小傻逼\");")
                        .append(" System.out.println(\"我是一年级二班的小傻逼22\");")
                        .append("\n}");

                ctMethod.setBody(buffer.toString());
                ctClass.addMethod(ctMethod);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ctClass.toClass(getClass().getClassLoader(),JavassistCompiler.class.getProtectionDomain());

    }


    public static ClassPool getClassPool(ClassLoader classLoader) {
        if (classLoader == null) {
            return ClassPool.getDefault();
        } else {
            ClassPool pool = CLASS_POOL.get(classLoader);
            if (pool == null) {
                ClassPool classPool = new ClassPool(true);
                classPool.appendClassPath(new LoaderClassPath(classLoader));
                CLASS_POOL.putIfAbsent(classLoader, classPool);
            }
            return CLASS_POOL.get(classLoader);
        }

    }
}
