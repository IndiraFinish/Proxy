package springaop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;

import java.lang.reflect.Method;

/**
 * @author wang.xw
 * @date 2018/7/26 10:37.
 */
@Aspect
public class ProxyTarget1 implements Ordered {
    /**
     * 匹配 springaop.subject.Subject类所有的public方法
     */
    @Pointcut("execution(public * springaop.subject.Traget.*(..))")
    public void proxy() {
    }

    @Around("proxy()")
    public Object MyArround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("!!!环绕通知--前置1");
        System.out.println("-------");
        Object object = pjp.proceed();
        System.out.println("!!!环绕通知--后置1");
        System.out.println("-------");
        return object;
    }

    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
