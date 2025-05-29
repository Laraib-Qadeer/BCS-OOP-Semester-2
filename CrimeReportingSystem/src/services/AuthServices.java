package services;

import com.google.gson.reflect.TypeToken;
import models.*;
import utils.FileStorage;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AuthServices {
    private List<User> users;
    private final String USER_FILE = "db/users.json";
    private final String USER_LOG_FILE = "db/user_log.txt";

    public AuthServices() {
        Type userListType = new TypeToken<List<User>>() {}.getType();
        users = FileStorage.loadFromFile(USER_FILE, userListType);
        if (users == null) {
            users = new ArrayList<>();
        }
        ensureValidUserRoles();
    }

    private void ensureValidUserRoles() {
        boolean changed = false;

        if (users == null || users.isEmpty()) {
            System.out.println(" No users found. Adding default admin and police...");
            users = new ArrayList<>();
            users.add(new Admin("admin-001", "Admin", "admin@dept.com", "admin123"));
            users.add(new Police("police-001", "Officer", "police@police.com", "police123"));
            saveUsers();
            return;
        }

        List<User> fixed = new ArrayList<>();
        for (User u : users) {
            if (u.getRole() == null || u.getRole().isBlank()) {
                String email = u.getEmail().toLowerCase();
                if (email.contains("@police.com")) {
                    u = new Police(u.getId(), u.getName(), u.getEmail(), u.getPassword());
                } else if (email.contains("@dept.com")) {
                    u = new Admin(u.getId(), u.getName(), u.getEmail(), u.getPassword());
                } else {
                    u = new Citizen(u.getId(), u.getName(), u.getEmail(), u.getPassword());
                }
                changed = true;
            }
            fixed.add(u);
        }

        users = fixed;

        if (changed) {
            saveUsers();
            System.out.println(" Fixed invalid roles and saved users.");
        } else {
            System.out.println(" All user roles already valid. No save needed.");
        }
    }

    public User register(String name, String email, String password, String role) {
        if (!role.equalsIgnoreCase("citizen")) {
            System.out.println(" Only citizens can register. Admins and police accounts are department-issued.");
            return null;
        }

        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                System.out.println(" Email already registered.");
                return null;
            }
        }

        String id = UUID.randomUUID().toString();
        Citizen newUser = new Citizen(id, name, email, password);
        users.add(newUser);
        System.out.println(" Registering new user: " + newUser.getEmail());
        saveUsers();
        System.out.println(" saveUsers() called.");

        String logLine = " User registered: " + name + " (citizen), Email: " + email + "\n";
        FileStorage.writeToFile(new File(USER_LOG_FILE), logLine);

        return newUser;
    }

    public User login(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email) && user.checkPassword(password)) {
                switch (user.getRole().toLowerCase()) {
                    case "admin" -> {
                        return new Admin(user.getId(), user.getName(), user.getEmail(), user.getPassword());
                    }
                    case "police" -> {
                        return new Police(user.getId(), user.getName(), user.getEmail(), user.getPassword());
                    }
                    case "citizen" -> {
                        return new Citizen(user.getId(), user.getName(), user.getEmail(), user.getPassword());
                    }
                }
            }
        }
        return null;
    }

    public void saveUsers() {
        System.out.println(" Saving users: " + users.size());
        FileStorage.saveToFile(users, USER_FILE);
        System.out.println(" Users saved to: " + USER_FILE);
    }

    public List<User> getAllUsers() {
        return users;
    }
    public boolean deleteUserByEmail(String email) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equalsIgnoreCase(email)) {
                users.remove(i);
                saveUsers();
                return true;
            }
        }
        return false;
    }
    public boolean removeUser(String email) {
        return users.removeIf(user -> user.getEmail().equalsIgnoreCase(email));
    }
}
