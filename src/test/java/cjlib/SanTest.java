package cjlib;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.Method;

/**
 * @author wang.xw
 * @date 2018/7/19 16:04.
 */
@RunWith(JUnit4.class)
public class SanTest {
    @Test
    public void test() throws Exception {
        Method method = Class.forName("cjlib.San").getDeclaredMethod("name", null);
        method.setAccessible(true);
        System.out.println(method.getName());

    }

}

