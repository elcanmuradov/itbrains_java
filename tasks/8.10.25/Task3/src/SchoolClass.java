public class SchoolClass {
    private static int id = 1;
    private String className;
    private String teacherName;
    private int classId;

    public SchoolClass(String className, String teacherName) {
        this.className = className;
        this.teacherName = teacherName;
        this.classId = id;
        id++;
    }

    public String getClassName() {
        return className;
    }
}
