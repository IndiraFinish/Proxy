package designPattern.dubbofilter;

public interface Filter {
    /**
     * @param invoker 请求者
     */
    Object invoke(Invoker invoker,String request);
}
