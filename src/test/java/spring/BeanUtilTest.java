package spring;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import springaop.annotation.Test;

import static org.junit.Assert.*;

/**
 * @author wang.xw
 * @date 2018/8/7 10:15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationContext.class)
public class BeanUtilTest {
}