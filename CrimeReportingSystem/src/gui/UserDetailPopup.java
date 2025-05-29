package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.User;
import services.AuthServices;

public class UserDetailPopup {

    public static void show(User user, Runnable onUpdated, Runnable onDeleted) {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("User Details");

        Label titleLabel = new Label("User Details");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setTextFill(Color.WHITE);

        Label nameLabel = new Label("Name: " + user.getName());
        Label emailLabel = new Label("Email: " + user.getEmail());
        Label roleLabel = new Label("Role: " + user.getRole());
        Label passwordLabel = new Label("Current Password: " + user.getPassword());

        for (Label label : new Label[]{nameLabel, emailLabel, roleLabel, passwordLabel}) {
            label.setTextFill(Color.WHITE);
            label.setFont(Font.font("Arial", 14));
        }

        PasswordField newPasswordField = new PasswordField();
        newPasswordField.setPromptText("Enter new password");
        newPasswordField.setMaxWidth(250);

        Button changePasswordBtn = new Button("Change Password");
        changePasswordBtn.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;");
        changePasswordBtn.setPrefWidth(200);

        Button deleteUserBtn = new Button("Delete User");
        deleteUserBtn.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white;");
        deleteUserBtn.setPrefWidth(200);

        AuthServices auth = new AuthServices();

        changePasswordBtn.setOnAction(e -> {
            String newPassword = newPasswordField.getText().trim();
            if (!newPassword.isEmpty()) {
                user.setPassword(newPassword);
                showAlert("Password changed for: " + user.getName() + " (" + user.getEmail() + ")");
                onUpdated.run();
                popup.close();
            } else {
                showAlert("Password cannot be empty.");
            }
        });

        deleteUserBtn.setOnAction(e -> {
            if (user.getRole().equalsIgnoreCase("admin")) {
                showAlert("You cannot delete an admin user.");
                return;
            }

            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirm Deletion");
            confirm.setHeaderText("Are you sure you want to delete this user?");
            confirm.setContentText(user.getName() + " (" + user.getEmail() + ")");

            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            confirm.getButtonTypes().setAll(yes, no);

            confirm.showAndWait().ifPresent(response -> {
                if (response == yes) {
                    auth.removeUser(user.getEmail());
                    showAlert("User deleted successfully: " + user.getName() + " (" + user.getEmail() + ")");
                    onDeleted.run();
                    popup.close();
                }
            });
        });

        VBox layout = new VBox(12);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setStyle("-fx-background-color: #2c2f33;");
        layout.getChildren().addAll(
                titleLabel,
                nameLabel,
                emailLabel,
                roleLabel,
                passwordLabel,
                new Label("New Password:"),
                newPasswordField,
                changePasswordBtn,
                deleteUserBtn
        );

        Scene scene = new Scene(layout, 450, 420);
        popup.setScene(scene);
        popup.showAndWait();
    }

    private static void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
