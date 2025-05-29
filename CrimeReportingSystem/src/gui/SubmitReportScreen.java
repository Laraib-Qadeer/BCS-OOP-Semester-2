package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import models.Citizen;
import services.CrimeServices;
import session.Session;

import java.util.Collections;

public class SubmitReportScreen {

    public void start(Stage stage) {
        Label title = new Label("Submit New Report");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        title.setTextFill(Color.WHITE);

        TextField reportTitle = new TextField();
        reportTitle.setPromptText("Report Title");

        TextArea description = new TextArea();
        description.setPromptText("Describe what happened...");
        description.setWrapText(true);

        TextField location = new TextField();
        location.setPromptText("Location");

        Button submitBtn = new Button("Submit");
        submitBtn.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");
        submitBtn.setMinWidth(300);

        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: #6c757d; -fx-text-fill: white;");

        submitBtn.setOnAction(e -> {
            String titleText = reportTitle.getText();
            String desc = description.getText();
            String loc = location.getText();

            if (titleText.isEmpty() || desc.isEmpty() || loc.isEmpty()) {
                showAlert("Please fill all fields.");
                return;
            }

            if (!(Session.getCurrentUser() instanceof Citizen)) {
                showAlert("Only citizens can submit reports.");
                return;
            }

            Citizen citizen = (Citizen) Session.getCurrentUser();
            new CrimeServices().submitReport(
                    citizen.getId(), titleText, desc, loc, Collections.emptyList()
            );

            showAlert("Report submitted successfully.");
            reportTitle.clear();
            description.clear();
            location.clear();
        });

        backBtn.setOnAction(e -> {
            if (Session.getCurrentUser() != null && "anonymous".equals(Session.getCurrentUser().getId())) {
                new CitizenLoginOrRegisterScreen().start(SceneNavigator.getStage());
            } else {
                new CitizenDashboard().start(SceneNavigator.getStage());
            }
        });


        VBox layout = new VBox(15, title, reportTitle, description, location, submitBtn, backBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.setStyle("-fx-background-color: #001f3f;");

        Scene scene = new Scene(layout, 600, 500);
        SceneNavigator.setScene(scene, "Submit Report");
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
