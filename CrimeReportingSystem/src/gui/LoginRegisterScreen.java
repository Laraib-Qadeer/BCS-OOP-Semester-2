package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import models.User;
import services.AuthServices;
import session.Session;

public class LoginRegisterScreen {
    private final String role;
    private final String mode;

    public LoginRegisterScreen(String role, String mode) {
        this.role = role.toLowerCase();
        this.mode = mode.toLowerCase();
    }

    public void start(Stage stage) {
        // Avatar
        ImageView avatar = new ImageView();
        if (role.equals("admin")) {
            avatar.setImage(new Image("file:/C://OOP%20PROJECT//CrimeReportingSystem//src//gui//admin_icon.png"));
        } else if (role.equals("police")) {
            avatar.setImage(new Image("file:/C://OOP%20PROJECT//CrimeReportingSystem//src//gui//police_icon.png"));
        } else {
            avatar.setImage(new Image("file:/C://OOP%20PROJECT//CrimeReportingSystem//src//gui//citizen_icon.png"));
        }
        avatar.setFitHeight(120);
        avatar.setPreserveRatio(true);

        Label title = new Label(role.toUpperCase() + (mode.equals("register") ? " REGISTER" : " LOGIN"));
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setTextFill(Color.WHITE);

        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name");
        styleField(nameField);

        TextField emailField = new TextField();
        emailField.setPromptText("Enter your email");
        styleField(emailField);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        styleField(passwordField);

        Button loginBtn = new Button("Login");
        loginBtn.setMinWidth(200);
        loginBtn.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-font-size: 14;");

        Button registerBtn = new Button("Register");
        registerBtn.setMinWidth(200);
        registerBtn.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-size: 14;");

        loginBtn.setOnAction(e -> {
            String email = emailField.getText().trim();
            String password = passwordField.getText().trim();

            if (email.isEmpty() || password.isEmpty()) {
                showAlert("Please enter email and password.");
                return;
            }

            AuthServices auth = new AuthServices();
            User user = auth.login(email, password);

            if (user != null) {
                if (!user.getRole().equalsIgnoreCase(role)) {
                    showAlert("Incorrect role selected.");
                    return;
                }

                Session.setCurrentUser(user);
                switch (role) {
                    case "citizen":
                        new CitizenDashboard().start(SceneNavigator.getStage());
                        break;
                    case "police":
                        new PoliceDashboard().start(SceneNavigator.getStage());
                        break;
                    case "admin":
                        new AdminDashboard().start(SceneNavigator.getStage());
                        break;
                }
            } else {
                showAlert("Invalid credentials.");
            }
        });

        registerBtn.setOnAction(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String password = passwordField.getText().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                showAlert("Please enter name, email, and password.");
                return;
            }

            AuthServices auth = new AuthServices();
            User registeredUser = auth.register(name, email, password, role);

            if (registeredUser != null) {
                showAlert("Registered successfully. Now login.");
                new LoginRegisterScreen(role, "login").start(SceneNavigator.getStage());
            } else {
                showAlert("User already exists.");
            }
        });

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(50));
        layout.setStyle("-fx-background-color: #001f3f;");

        if (role.equals("citizen") && mode.equals("register")) {
            layout.getChildren().addAll(avatar, title, nameField, emailField, passwordField, registerBtn);
        } else {
            layout.getChildren().addAll(avatar, title, emailField, passwordField, loginBtn);
        }

        Scene scene = new Scene(layout, 500, 500);
        SceneNavigator.setScene(scene, capitalize(role) + " " + capitalize(mode));
    }

    private void styleField(TextField field) {
        field.setMaxWidth(250);
        field.setStyle("-fx-background-color: white; -fx-border-color: navyblue; -fx-border-radius: 5;");
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
