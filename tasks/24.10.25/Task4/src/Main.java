
public class Main {
    public static void main(String[] args) {
        Person person1 = new Person("Elcan", 17);
        Person person2 = new Person("Elcan", 17);

        System.out.println("person1: " + person1.toString());
        System.out.println("person2: " + person2.toString());
        System.out.println("equals(): " + person1.equals(person2));
        System.out.println("hashCode() person1: " + person1.hashCode());
        System.out.println("hashCode() person2: " + person2.hashCode());
        System.out.println("person1 == person2: " + (person1.hashCode() == person2.hashCode()));


    }
}