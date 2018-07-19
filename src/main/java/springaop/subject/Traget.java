package springaop.subject;


/**
 * @author wang.xw
 * @date 2018/7/19 10:20.
 */
public class Traget implements TragertInterface {

    public String name() {
        System.out.println("my name is hisoka.");
        return "Chinese name is wang";
    }

    public void age() {
        System.out.println("my age is 23.");
    }

    public static void sex() {
        System.out.println("这是一个静态方法");
    }
}
