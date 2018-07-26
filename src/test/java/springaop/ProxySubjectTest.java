package springaop;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springaop.subject.AnnoTargetInterface;
import springaop.subject.TragertInterface;
import springaop.subject.Traget;

/**
 * @author wang.xw
 * @date 2018/7/19 10:31.
 */
@RunWith(JUnit4.class)
public class ProxySubjectTest {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Application.class);
    @Test
    public void test() {
        TragertInterface target = ctx.getBean(TragertInterface.class);
        target.name();
    }
    @Test
    public void testAnno() {
        AnnoTargetInterface target = ctx.getBean(AnnoTargetInterface.class);
        target.name("111",new Traget());
    }
}
