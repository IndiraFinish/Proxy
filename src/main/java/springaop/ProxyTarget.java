package springaop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @author wang.xw
 * @date 2018/7/19 10:19.
 */
@Aspect
public class ProxyTarget {
    /**
     * 匹配 springaop.subject.Traget类所有的public方法
     */
    @Pointcut("execution(public * springaop.subject.Traget.*(..))")
    public void proxy() {
    }

    @Before("proxy()")
    public void before(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        System.out.println("前置通知--方法名: " + methodName);
        System.out.println("-------");
    }

    @AfterReturning(pointcut = "proxy()", returning = "ret")
    public void afterReturning(JoinPoint jp, Object ret) {
        String methodName = jp.getSignature().getName();
        System.out.println("后置通知--方法名:" + methodName);
        System.out.println(ret);
        System.out.println("-------");
    }

    @After("proxy()")
    public void after(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        System.out.println("最终通知--方法名:" + methodName);
        System.out.println("-------");
    }

    @Around("proxy()")
    public Object MyArround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕通知--前置");
        System.out.println("-------");
        Object object = pjp.proceed();
        System.out.println("环绕通知--后置");
        System.out.println("-------");
        return object;
    }

    @AfterThrowing(value = "proxy()", throwing = "e")
    public void MyAfterThrowable(JoinPoint jp, Throwable e) {
        String name = jp.getSignature().getName();
        System.out.println("异常：" + name + "..." + e.getMessage());

    }
}
