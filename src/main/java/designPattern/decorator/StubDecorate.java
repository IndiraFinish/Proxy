package designPattern.decorator;

public class StubDecorate implements Decorate {
    private Decorate decorate;

    public StubDecorate(Decorate decorate) {
        this.decorate = decorate;
    }

    public void decorate() {
        System.out.println("this is stub decorate");
        decorate.decorate();
    }
}
