package designPattern.Interceptingfilter;


public class ErrorFilter implements Filter {

    public void execute(String request) {
        System.out.println("Error : " + request);
    }
}
