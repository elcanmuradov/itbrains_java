import java.util.ArrayList;

public class Main {
   static ArrayList<Student> students = new ArrayList<>();
    {
        System.out.println("School system initialized.");
    }
    public static void main(String[] args) {
        SchoolClass class10A = new SchoolClass("10A", "Ali");
        SchoolClass class9B = new SchoolClass("9B", "Narmin");
        SchoolClass class11C = new SchoolClass("11C", "Aylin");

        students.add(createStudent("Elcan",15,class10A));
        students.add(createStudent("Ayxan",15,class10A));
        students.add(createStudent("Subhan",15,class10A));
        students.add(createStudent("Senan",14,class9B));
        students.add(createStudent("Sahin",14,class9B));
        students.add(createStudent("Revan",14,class9B));
        students.add(createStudent("Ali",17,class11C));
        students.add(createStudent("Mehemmed",17,class11C));
        students.add(createStudent("Azer",17,class11C));
        students.add(createStudent("Kenan",17,class11C));



        findStudentsByClass("10A");
        findStudentsByClass("9B");
        findStudentsByClass("11C");
        findStudentsByClass("11A");

    }
    static void findStudentsByClass(String className){
        int count = 0;
        for (Student student : students) {
            if (student.getClassName().equals(className)){
                student.toStringStudent();
                count++;
            }
        }
        System.out.println();
        System.out.println("The number of students in the class "+ className  +": " + count);
    }


    static Student createStudent(String name, int age, SchoolClass schoolClass) {
        Student student = new Student(name, age, schoolClass);
        return student;
    }
}
