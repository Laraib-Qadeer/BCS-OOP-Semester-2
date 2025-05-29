package models;

public class Police extends User {
    public Police(String id, String name, String email, String password) {
        super(id, name, email, password, "police");
    }

    @Override
    public void displayMenu() {
        System.out.println("\n=== Police Menu ===");
        System.out.println("1. View All Reports");
        System.out.println("2. Update Report Status");
        System.out.println("3. Export Reports");
        System.out.println("0. Exit");
    }
}
