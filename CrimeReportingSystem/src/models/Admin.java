package models;

public class Admin extends User {
    public Admin(String id, String name, String email, String password) {
        super(id, name, email, password, "admin");
    }

    @Override
    public void displayMenu() {
        System.out.println("\n=== Admin Menu ===");
        System.out.println("1. View All Reports");
        System.out.println("2. Update Report Status");
        System.out.println("3. Export Reports");
        System.out.println("4. Create New Police Account");
        System.out.println("0. Exit");

    }
}
