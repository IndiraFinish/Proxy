package designPattern.Interceptingfilter;

import java.util.ArrayList;
import java.util.List;

public class FilterChain {
    private static final List<Filter> filters = new ArrayList<Filter>();
    private Target target;

    public void setTarget(Target target) {
        this.target = target;
    }


    public boolean addFilter(Filter filter) {
        filters.add(filter);
        return true;
    }

    public void execute(String request) {
        for (Filter filter : filters) {
            filter.execute(request);
        }
        target.execute(request);
    }
}
