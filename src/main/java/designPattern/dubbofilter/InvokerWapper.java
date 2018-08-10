package designPattern.dubbofilter;

public class InvokerWapper implements Invoker {
    private Filter filter;
    private Invoker next;

    InvokerWapper(Invoker next, Filter filter) {
        this.next = next;
        this.filter = filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }


    public Object inovke(String request) {
        if (filter == null) {
            return next.inovke(request);
        }
        return filter.invoke(next, request);
    }
}
