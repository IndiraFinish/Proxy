package javassist;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class JavassistCompilerTest {

    @Test
    public void doCompile() throws Exception{
        JavassistCompiler<Student> compiler = new JavassistCompiler<Student>();
        Student student = new Student();
        Class<?> clazz = compiler.doCompile(student);
        //反射 执行方法
        Object obj = clazz.newInstance();
        obj.getClass().getDeclaredMethod("sayHello",new Class[]{})
               .invoke(obj,new Object[]{});
    }
}