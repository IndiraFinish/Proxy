package spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

/**
 * @author wang.xw
 * @date 2018/8/7 10:08.
 */
@Component
public class BeanUtil implements DisposableBean, InitializingBean, ApplicationContextAware, ApplicationListener, BeanNameAware {
    private static ApplicationContext applicationContext;
    private String beanName;

    public void setBeanName(String beanName) {
        System.out.println("====> BeanName");
        this.beanName = beanName;
    }

    public void destroy() throws Exception {
        System.out.println("====> 我被销毁了");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("====> 我被初始化了");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (applicationContext != null) {
            this.applicationContext = applicationContext;
        }
        System.out.println("====> setApplicationContext");
    }

    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (ContextRefreshedEvent.class == applicationEvent.getClass()) {
            System.out.println("====> refersh");
        }
    }
}
