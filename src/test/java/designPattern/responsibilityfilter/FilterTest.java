package designPattern.responsibilityfilter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FilterTest {
    private Filter filterChain;

    @Before
    public void setUp() {
        Filter debugFilter = new DebugFilter();
        Filter errorFilter = new ErrorFilter();
        errorFilter.setNext(debugFilter);
        filterChain = errorFilter;
    }

    @Test
    public void test() {
      filterChain.doInvoke("打印日志");

    }
}