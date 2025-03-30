public class Teacher extends Person {
    private String subject;
    private String teacherID;

    public Teacher(String name, int age, String subject, String teacherID) {
        super(name, age);
        this.subject = subject;
        this.teacherID = teacherID;
    }

    public String getSubject() {
        return subject;
    }

    public String getTeacherID() {
        return teacherID;
    }

    @Override
    public String toString() {
        return "Teacher - " + super.toString() + ", Subject: " + subject + ", ID: " + teacherID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Teacher) {
            Teacher other = (Teacher) obj;
            return this.teacherID.equals(other.teacherID);
        }
        return false;
    }
}
