package springaop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import springaop.annotation.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author wang.xw
 * @date 2018/7/26 11:22.
 */
@Aspect
public class AnnoProxy {


    @Pointcut("@annotation(springaop.annotation.Test)")
    public void clinet() {

    }

    @Around("clinet()")
    public void clientInterceptorContextMethod(ProceedingJoinPoint pjp) {
        Object[] args = pjp.getArgs();
        for(Object arg : args){
            System.out.println(arg.getClass());
        }
        System.out.println("-------");
        System.out.println(pjp.getSignature().toLongString());
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        Test annotation = method.getAnnotation(Test.class);
        System.out.println(annotation);

        System.out.println(method);
        System.out.println("-------");
        try {
            method = pjp.getTarget().getClass().getMethod(method.getName(), method.getParameterTypes());
            annotation = method.getAnnotation(Test.class);
            System.out.println(annotation);
            System.out.println(annotation.desc());
            System.out.println(method);
            System.out.println("-----");
            Object result = pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }
}
