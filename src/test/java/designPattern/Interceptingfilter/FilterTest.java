package designPattern.Interceptingfilter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FilterTest {
    FilterManager manager;

    @Before
    public void setUp() {
        manager = new FilterManager(new Target());
        manager.setFilter(new ErrorFilter());
        manager.setFilter(new DebugFilter());
    }

    @Test
    public void execute() {
        manager.filterRequest("request....");
    }
}