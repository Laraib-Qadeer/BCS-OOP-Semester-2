public class Student extends Person {
    private String rollNumber;

    public Student(String name, int age, String rollNumber) {
        super(name, age);
        this.rollNumber = rollNumber;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    @Override
    public String toString() {
        return "Student - " + super.toString() + ", Roll#: " + rollNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student) {
            Student other = (Student) obj;
            return this.rollNumber.equals(other.rollNumber);
        }
        return false;
    }
}
