package services;

import com.google.gson.reflect.TypeToken;
import models.User;
import utils.FileStorage;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AuthServices {
    private List<User> users;
    private final String USER_FILE = "src/db/users.json";

    public AuthServices() {
        // Load users from file when AuthService is created
        Type userListType = new TypeToken<List<User>>() {}.getType();
        users = FileStorage.loadFromFile(USER_FILE, userListType);
        if (users == null) {
            users = new ArrayList<>();
        }
    }

    // Register a new user
    public User register(String name, String email, String password, String role) {
        String id = UUID.randomUUID().toString();
        User newUser = new User(id, name, email, password, role);
        users.add(newUser);
        saveUsers(); // Save to file
        return newUser;
    }

    // Login
    public User login(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email) && user.checkPassword(password)) {
                return user;
            }
        }
        return null;
    }

    // Save user list to file
    private void saveUsers() {
        FileStorage.saveToFile(users, USER_FILE);
    }

    public List<User> getAllUsers() { //bcz users are private
        return users;
    }
}
