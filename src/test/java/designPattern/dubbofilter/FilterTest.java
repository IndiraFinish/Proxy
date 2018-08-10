package designPattern.dubbofilter;


import org.junit.Before;
import org.junit.Test;


public class FilterTest {
    Invoker invoker;
    FilterChain filterChain;

    @Before
    public void setUp() {
        invoker = new Invoker() {

            public Object inovke(String request) {
                System.out.println("invoker");
                return null;
            }
        };

        filterChain = new FilterChain();
        filterChain.addFilter(new ErrorFilter());
        filterChain.addFilter(new DebugFilter());
    }

    @Test
    public void test() {

        invoker = filterChain.buildFilterChain(invoker);
        invoker.inovke("request");

    }

    @Test
    public void testWithWapper() {

        invoker = filterChain.buildFilterChainWithWapper(invoker);
        invoker.inovke("request");

    }

}