package designPattern.dubbofilter;


public class ErrorFilter implements Filter {
    public Object invoke(Invoker invoker, String request) {
        System.out.println("Error Filter");
        return invoker.inovke(request);
    }
}
