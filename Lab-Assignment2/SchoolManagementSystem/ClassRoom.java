public class ClassRoom {
    private String className;
    private String classCode;
    private Teacher teacher;
    private Student[] students;
    private int studentCount;

    public ClassRoom(String className, String classCode, Teacher teacher) {
        this.className = className;
        this.classCode = classCode;
        this.teacher = teacher;
        this.students = new Student[5];
        this.studentCount = 0;
    }

    public boolean addStudent(Student student) {
        if (studentCount >= 5) {
            System.out.println("Cannot add student: Class " + className + " is full.");
            return false;
        }
        students[studentCount++] = student;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ClassRoom: ").append(className).append(" (").append(classCode).append(")\n");
        sb.append(teacher.toString()).append("\n");
        sb.append("Students:\n");
        for (int i = 0; i < studentCount; i++) {
            sb.append(" - ").append(students[i].toString()).append("\n");
        }
        return sb.toString();
    }
}
