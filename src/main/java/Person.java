/**
 * @title: Person
 * @Author eddie
 * @Date: 2021/3/4 13:43
 * @Version 1.0
 */
public class Person {
    public String name;
    public int age;
    public Person(){
        super();
    }
    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }
    public String showInfo() {
        return "name=" + name + ", age=" + age;
    }
}
