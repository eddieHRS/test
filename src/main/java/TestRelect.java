import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @title: TestRelect
 * @Author eddie
 * @Date: 2021/3/4 13:47
 * @Version 1.0
 */
public class TestRelect {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class student = null;
        try {
            student = Class.forName("Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("获取对象的所有共有属性---------------------");

        Field[] fields = student.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }

        System.out.println("获取对象的所有属性，不包括继承的---------------------");
        Field[] declaredFields = student.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println(field);
        }

        System.out.println("获取对象的所有公共方法---------------------");
        Method[] methods = student.getMethods();
        for(Method method:methods){
            System.out.println(method);
        }

        System.out.println("获取对象的所有公共方法,不包括继承的---------------------");
        Method[] declaredMethods = student.getDeclaredMethods();
        for(Method method:declaredMethods){
            System.out.println(method);
        }
        System.out.println("---------------------");

        Student s = (Student)student.getDeclaredConstructor().newInstance();
        s.setAddress("sadsda");
        System.out.println(s.toString());
    }
}
