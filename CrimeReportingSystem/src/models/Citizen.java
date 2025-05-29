package models;

public class Citizen extends User {
    public Citizen(String id, String name, String email, String password) {
        super(id, name, email, password, "citizen");
    }

    @Override
    public void displayMenu() {
        System.out.println("\n=== Citizen Menu ===");
        System.out.println("1. Submit Crime Report");
        System.out.println("2. View My Reports");
        System.out.println("3. Emergency Assistance");
        System.out.println("4. Export My Reports");
        System.out.println("0. Exit");
    }
}
