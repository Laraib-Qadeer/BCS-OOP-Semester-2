package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.CrimeReport;
import services.CrimeServices;

import java.util.List;

public class HandleEmergencyScreen {

    private final CrimeServices crimeService = new CrimeServices();

    public HandleEmergencyScreen(controller.AppController controller) {}

    public void start(Stage stage) {
        Label title = new Label("Declare Emergency Report");
        title.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-text-fill: white;");

        TextField reportIdField = new TextField();
        reportIdField.setPromptText("Enter Report ID");

        TextField descField = new TextField();
        descField.setPromptText("Emergency Description");
        descField.setDisable(true);

        Button declareBtn = new Button("Declare Emergency");
        declareBtn.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white;");
        declareBtn.setDisable(true);

        Button sendTeamBtn = new Button("Send Team Immediately");
        sendTeamBtn.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");
        sendTeamBtn.setDisable(true);

        Button validateBtn = new Button("Validate Report ID");
        validateBtn.setOnAction(e -> {
            String reportId = reportIdField.getText().trim();
            List<CrimeReport> allReports = crimeService.getAllReports();
            boolean exists = allReports.stream().anyMatch(r -> r.getReportId().equals(reportId));

            if (exists) {
                descField.setDisable(false);
                declareBtn.setDisable(false);
                sendTeamBtn.setDisable(false);
                showAlert("Report ID found. You may now proceed.");
            } else {
                showAlert("Report ID not found.");
            }
        });

        declareBtn.setOnAction(e -> {
            showAlert("Emergency declared for: " + reportIdField.getText());
        });

        sendTeamBtn.setOnAction(e -> {
            showAlert("Notification sent to team.\nThey will respond as soon as possible.");
        });

        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> new PoliceDashboard().start(SceneNavigator.getStage()));

        VBox layout = new VBox(15, title, reportIdField, validateBtn, descField, declareBtn, sendTeamBtn, backBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.setStyle("-fx-background-color: #001f3f;");
        Scene scene = new Scene(layout, 500, 500);
        SceneNavigator.setScene(scene, "Declare Emergency");
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        alert.showAndWait();
    }
}
