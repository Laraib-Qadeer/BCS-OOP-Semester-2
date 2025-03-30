public class Main {
    public static void main(String[] args) {
        Principal principal = new Principal("Dr. Ayesha", 50, 22);

        School school = new School("Zayup International School", "101 Main St", principal);

        Teacher t1 = new Teacher("Mr. Bilal", 38, "Math", "T202");
        Teacher t2 = new Teacher("Ms. Sana", 34, "Biology", "T203");

        ClassRoom class10 = new ClassRoom("Grade 10", "G10", t1);
        ClassRoom class9 = new ClassRoom("Grade 9", "G9", t2);

        school.addClassRoom(class10);
        school.addClassRoom(class9);

        class10.addStudent(new Student("Ali", 15, "S001"));
        class10.addStudent(new Student("Sara", 14, "S002"));
        class10.addStudent(new Student("Zain", 15, "S003"));
        class10.addStudent(new Student("Fatima", 14, "S004"));
        class10.addStudent(new Student("Usman", 15, "S005"));
        class10.addStudent(new Student("Extra", 14, "S006")); 

        class9.addStudent(new Student("Hina", 13, "S101"));
        class9.addStudent(new Student("Omar", 13, "S102"));

        System.out.println(school);

        Student s1 = new Student("Ali", 15, "S001");
        Student s2 = new Student("Ali", 15, "S009");
        System.out.println("s1 equals s2? " + s1.equals(s2)); 

        Teacher tCompare = new Teacher("Mr. Bilal", 38, "Math", "T202");
        System.out.println("t1 equals tCompare? " + t1.equals(tCompare));
    }
}
