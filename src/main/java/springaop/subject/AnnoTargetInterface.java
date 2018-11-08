package springaop.subject;


import springaop.annotation.Test;

/**
 * @author wang.xw
 * @date 2018/7/26 11:21.
 */
public interface AnnoTargetInterface {
    @Test(desc = "123")
    void name(String str, Traget traget);
}
