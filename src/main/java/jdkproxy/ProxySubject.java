package jdkproxy;

import jdkproxy.exception.NoInterfaceException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxySubject implements InvocationHandler {
    /**
     * 被代理对象
     */
    private Object target;

    public ProxySubject(Object target) {
        this.target = target;
    }

    /**
     * 获取代理对象
     **/
    public Object getProxy() {
        Class<?>[] interfaces = target.getClass().getInterfaces();
        if (interfaces == null || interfaces.length == 0) {
            throw new NoInterfaceException(target.getClass().getSimpleName() + "not have interfaces");
        }
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), interfaces, this);
    }

    /**
     * 执行目标对象的方法
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("前置通知*****");

        Object invoke = method.invoke(target, args);

        System.out.println("后置通知*****");
        return invoke;
    }
}
