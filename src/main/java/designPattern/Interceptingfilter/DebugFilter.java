package designPattern.Interceptingfilter;


public class DebugFilter implements Filter {

    public void execute(String request) {
        System.out.println("Debug : " + request);
    }
}
