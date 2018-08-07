package spring;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author wang.xw
 * @date 2018/8/7 10:17.
 */
@SpringBootApplication
public class ApplicationContext {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ApplicationContext.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.setWebApplicationType(WebApplicationType.NONE);
        ConfigurableApplicationContext context = application.run(args);
        System.out.println("====>" + context);
    }
}
