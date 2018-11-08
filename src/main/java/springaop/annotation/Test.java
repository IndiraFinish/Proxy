package springaop.annotation;

import java.lang.annotation.*;

/**
 * @author wang.xw
 * @date 2018/7/26 11:19.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Inherited
@Documented
public @interface Test {
    String desc();
}
