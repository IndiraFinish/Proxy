package wapper;

import org.junit.Test;


public class DecorateImplTest {

    @Test
    public void decorate() {
        DecorateImpl decorate = new DecorateImpl();
        StubDecorate stubDecorate = new StubDecorate(decorate);
        stubDecorate.decorate();
    }

    @Test
    public void reflectDecorate() throws Exception {
        DecorateImpl decorate = new DecorateImpl();
        Object obj = Class.forName("wapper.StubDecorate").getConstructor(Decorate.class).newInstance(decorate);
        obj.getClass().getDeclaredMethod("decorate", new Class[]{}).invoke(obj, new Class[]{});
    }

}