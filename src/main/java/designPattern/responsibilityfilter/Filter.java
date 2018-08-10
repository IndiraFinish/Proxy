package designPattern.responsibilityfilter;

public abstract class Filter {
    private Filter next;

    public void setNext(Filter next) {
        this.next = next;
    }

    public void doInvoke(String str) {
        invoke(str);
        if (next != null) {
            next.doInvoke(str);
        }

    }

    abstract protected String invoke(String str);
}
