package designPattern.responsibilityfilter;

public class ErrorFilter extends Filter {
    protected String invoke(String str) {
        System.out.println("Error ：" + str);
        return str;
    }
}
