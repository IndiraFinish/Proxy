package designPattern.Interabstract;

public abstract class AbstractProxyInvoker {
    private String name;

    public String getUrl() {
        return url;
    }

    private String url;

    public AbstractProxyInvoker(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String invoke(String str) {
        System.out.println(" ===> 调用invoke");
        String invoke = doInvoke(str);
        return invoke;
    }

    protected abstract String doInvoke(String str);
}
