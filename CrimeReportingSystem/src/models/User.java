package models;

public abstract class User {
    protected String id;
    protected String name;
    protected String email;
    protected String password;
    protected String role;

    public User(String id, String name, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    @Override
    public String toString() {
        return "[" + role.toUpperCase() + "] " + name + " <" + email + ">";
    }


    public abstract void displayMenu();
}
