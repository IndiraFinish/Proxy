package designPattern.Interceptingfilter;

public class Target {
    public void execute(String request) {
        System.out.println("execute : " + request);
    }
}
