package jdkproxy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ProxySubjectTest {
    @Test
    public void test() {
        SubjectInterface subject = new Subject();

        ProxySubject proxySubject = new ProxySubject(subject);
        SubjectInterface proxy = (SubjectInterface) proxySubject.getProxy();

        proxy.age();

    }

}
