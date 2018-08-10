package designPattern.responsibilityfilter;

public class DebugFilter extends Filter {

    protected String invoke(String str) {
        System.out.println("Debug ：" + str);
        return str;
    }
}
