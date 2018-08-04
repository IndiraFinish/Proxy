package javassist;

public class Student {

    private String name;
    private Integer age;

    public void sayHello() {
        System.out.println("我是一年级二班的小傻逼");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
