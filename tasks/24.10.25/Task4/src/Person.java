import java.util.Objects;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public boolean equals(Person p) {
        return this.age == p.age && this.name.equals(p.name);
    }

    public int hashCode() {
        return Objects.hash(name, age);
    }
    public String toString() {
        return "name=" + name  +", age=" + age ;
    }
}