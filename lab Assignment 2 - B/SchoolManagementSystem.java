import java.util.*;

class Person {
    String name;
    int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class Principal extends Person {
    public Principal(String name, int age) {
        super(name, age);
    }
}

class Teacher extends Person {
    public Teacher(String name, int age) {
        super(name, age);
    }
}

class Student extends Person {
    int rollNumber;
    
    public Student(String name, int age, int rollNumber) {
        super(name, age);
        this.rollNumber = rollNumber;
    }
}

class ClassRoom {
    String name;
    Teacher teacher;
    List<Student> students = new ArrayList<>();
    
    public ClassRoom(String name, Teacher teacher) {
        this.name = name;
        this.teacher = teacher;
    }
    
    public void addStudent(Student student) {
        students.add(student);
    }
}

class School {
    String name;
    Principal principal;
    List<ClassRoom> classrooms = new ArrayList<>();
    
    public School(String name, Principal principal) {
        this.name = name;
        this.principal = principal;
    }
    
    public void addClassRoom(ClassRoom classRoom) {
        classrooms.add(classRoom);
    }
}

public class SchoolManagementSystem {
    public static void main(String[] args) {
        Principal principal = new Principal("Dr. John", 50);
        School school = new School("Springfield High", principal);
        
        Teacher teacher = new Teacher("Alice Brown", 30);
        ClassRoom classRoom = new ClassRoom("Grade 10", teacher);
        school.addClassRoom(classRoom);
        
        classRoom.addStudent(new Student("Charlie", 15, 201));
        classRoom.addStudent(new Student("David", 16, 202));
        
        System.out.println("School: " + school.name);
        System.out.println("Principal: " + school.principal.name);
        for (ClassRoom cr : school.classrooms) {
            System.out.println("Class: " + cr.name + ", Teacher: " + cr.teacher.name);
            for (Student s : cr.students) {
                System.out.println("Student: " + s.name + ", Roll No: " + s.rollNumber);
            }
        }
    }
}
