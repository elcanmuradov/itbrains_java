public class Main {
    public static void main(String[] args) {
        Parent p = new Child();
        Child c = new Child();

        p.display();  // Parent class has been displayed
        c.display();  // Child class has been displayed

        // because of reference types are different
    }
}
