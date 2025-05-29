package gui;

import controller.AppController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.CrimeServices;

public class StatusUpdateScreen {

    private final CrimeServices crimeService = new CrimeServices();
    private final String caller;

    public StatusUpdateScreen(AppController controller, String caller) {
        this.caller = caller.toLowerCase();
    }

    public void start(Stage stage) {
        Label title = new Label("Update Report Status");
        title.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-text-fill: white;");

        TextField reportIdField = new TextField();
        reportIdField.setPromptText("Report ID");

        TextField statusField = new TextField();
        statusField.setPromptText("New Status (Verified / Rejected / Case Solved)");

        Button updateBtn = new Button("Update");
        updateBtn.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;");
        updateBtn.setOnAction(e -> {
            String id = reportIdField.getText().trim();
            String status = statusField.getText().trim();

            if (id.isEmpty() || status.isEmpty()) {
                showAlert("Please fill in both fields.");
                return;
            }

            boolean updated = crimeService.updateStatus(id, status);
            showAlert(updated ? "Report status updated!" : "Report not found.");
            if (updated) navigateBack();
        });

        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> navigateBack());

        VBox layout = new VBox(15, title, reportIdField, statusField, updateBtn, backBtn);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #001f3f;");
        Scene scene = new Scene(layout, 500, 400);
        SceneNavigator.setScene(scene, "Update Report Status");
    }

    private void navigateBack() {
        if (caller.equals("police")) {
            new PoliceDashboard().start(SceneNavigator.getStage());
        } else {
            new AdminDashboard().start(SceneNavigator.getStage());
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        alert.showAndWait();
    }
}
