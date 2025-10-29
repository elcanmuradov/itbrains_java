public class Main {
    public static void main(String[] args) {
        Base base = new Base();
        Derived d1 = new Derived();

        d1.calculate();
        d1.calculate1();
        base.calculate();
        base.calculate1();
    }
}
