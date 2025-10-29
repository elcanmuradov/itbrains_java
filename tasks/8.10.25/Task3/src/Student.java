public class Student {
    private static int id = 1;
    private String name;
    private int age;
    private int studenId;
    SchoolClass schoolClass;
    public Student(String name, int age, SchoolClass schoolClass) {
        this.name = name;
        this.age = age;
        this.schoolClass = schoolClass;
        this.studenId = id;
        id++;
    }
    public String getClassName() {
        return schoolClass.getClassName();
    }
    public void toStringStudent(){
        System.out.println("\n-------------------");
        System.out.println("Student ID: " + studenId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("School Class: " + schoolClass.getClassName());
        System.out.println("-------------------");
    }
}
