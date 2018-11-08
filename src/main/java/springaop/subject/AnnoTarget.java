package springaop.subject;

import springaop.annotation.Test;

/**
 * @author wang.xw
 * @date 2018/7/26 11:20.
 */
public class AnnoTarget implements AnnoTargetInterface {
    @Test(desc = "123")
    public void name(String str, Traget traget) {
        System.out.println("注解式代理");
    }
}
