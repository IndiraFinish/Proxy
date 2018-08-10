package designPattern.Interabstract;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractProxyInvokerTest {
    private static String str = "复写的doInvoke";

    @Test
    public void doInvoke() {

        AbstractProxyInvoker invoker = new AbstractProxyInvoker("wxw", "this is abstract test") {
            @Override
            protected String doInvoke(String str) {
                return str;

            }
        };

        Assert.assertEquals(str, invoker.doInvoke(str));
        Assert.assertEquals(str, invoker.invoke(str));
        Assert.assertEquals("this is abstract test",invoker.getUrl());
    }
}