package validator;

import users.User;
import java.io.Console;

public class UserManager {
    public static void login() {
        Console console = System.console();
        
        if (console == null) {
            System.out.println("No console available. Run in terminal.");
            return;
        }

        System.out.println("Welcome to the authentication system.");
        String username = console.readLine("Enter username: ");
        char[] passwordChars = console.readPassword("Enter password: ");
        String password = new String(passwordChars);

        // Using the static inner class for login handling
        LoginHandler loginHandler = new LoginHandler();
        if (loginHandler.authenticate(username, password)) {
            System.out.println("Access Granted!");
        } else {
            System.out.println("Invalid credentials. Access Denied.");
        }
    }

    // Static inner class for login handling
    private static class LoginHandler {
        private static User user = new User();

        public boolean authenticate(String username, String password) {
            return user.validateCredentials(username, password);
        }
    }
}
