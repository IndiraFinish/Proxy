package springaop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import springaop.subject.AnnoTarget;
import springaop.subject.Traget;

/**
 * @author wang.xw
 * @date 2018/7/19 10:49.
 */
@Configuration
@EnableAspectJAutoProxy
public class Application {
    @Bean
    public ProxyTarget proxyTarget() {
        return new ProxyTarget();
    }
    @Bean
    public ProxyTarget1 proxyTarget1() {
        return new ProxyTarget1();
    }
    @Bean
    public AnnoProxy annoProxy() {
        return new AnnoProxy();
    }
    @Bean
    public Traget traget() {
        return new Traget();
    }
    @Bean
    public AnnoTarget annoTarget() {
        return new AnnoTarget();
    }
}
