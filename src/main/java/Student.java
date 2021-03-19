/**
 * @title: Student
 * @Author eddie
 * @Date: 2021/3/4 13:45
 * @Version 1.0
 */
public class Student extends Person{
    public String className;
    private String address;
    public Student() {
        super();
    }
    public Student(String name, int age, String className, String address) {
        super(name, age);
        this.className = className;
        this.address = address;
    }
    public Student(String className) {
        this.className = className;
    }
    public String toString() {
        return "姓名" + name + ",年龄" + age + ",班级" + className + ",֘住址" + address;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
