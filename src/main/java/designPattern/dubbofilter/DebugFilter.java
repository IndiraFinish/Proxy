package designPattern.dubbofilter;


public class DebugFilter implements Filter {

    public Object invoke(Invoker invoker, String request) {
        System.out.println("Debug Filter");
        return invoker.inovke(request);
    }
}
