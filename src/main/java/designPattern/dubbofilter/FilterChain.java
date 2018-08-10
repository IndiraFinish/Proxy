package designPattern.dubbofilter;

import java.util.ArrayList;
import java.util.List;

public class FilterChain {
    private static final List<Filter> filters = new ArrayList<Filter>();

    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    public Invoker buildFilterChainWithWapper(Invoker invoker) {
        Invoker last = new InvokerWapper(invoker,null);
        for (int i = filters.size() - 1; i >= 0; i--) {
            final Filter filter = filters.get(i);
            final Invoker next = last;
//            last = new Invoker() {
//                public Object inovke(String request) {
//                    return filter.invoke(next, request);
//                }
//            };
            last = new InvokerWapper(next,filter);
        }
        return last;
    }

    public Invoker buildFilterChain(Invoker invoker) {
        Invoker last = invoker;
        for (int i = filters.size() - 1; i >= 0; i--) {
            final Filter filter = filters.get(i);
            final Invoker next = last;
            last = new Invoker() {
                public Object inovke(String request) {
                    return filter.invoke(next, request);
                }
            };
        }
        return last;
    }

}
