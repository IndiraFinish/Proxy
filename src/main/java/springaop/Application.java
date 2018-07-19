package springaop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
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
    public Traget traget() {
        return new Traget();
    }
}
