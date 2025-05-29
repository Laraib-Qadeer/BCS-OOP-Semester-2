package gui;

import controller.AppController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import models.Police;
import services.AuthServices;
import utils.FileStorage;

import java.io.File;
import java.util.UUID;

public class CreatePoliceScreen {

    private final AuthServices authService = new AuthServices();

    public CreatePoliceScreen(AppController controller) {}

    public void start(Stage stage) {
        Label title = new Label("Create Police Account");
        title.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-text-fill: white;");

        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button createBtn = new Button("Create Account");
        createBtn.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");
        createBtn.setOnAction(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String password = passwordField.getText().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                showAlert("All fields are required.");
                return;
            }

            Police newPolice = new Police(UUID.randomUUID().toString(), name, email, password);
            authService.getAllUsers().add(newPolice);
            authService.saveUsers();
            FileStorage.writeToFile(new File("db/user_log.txt"),
                    "Police registered by Admin: " + name + " (" + email + ")\n");

            showAlert("Police account created successfully!");
            new AdminDashboard().start(SceneNavigator.getStage());
        });

        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> new AdminDashboard().start(SceneNavigator.getStage()));

        VBox layout = new VBox(15, title, nameField, emailField, passwordField, createBtn, backBtn);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #001f3f;");
        Scene scene = new Scene(layout, 500, 400);
        SceneNavigator.setScene(scene, "Create Police Account");
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        alert.showAndWait();
    }
}
