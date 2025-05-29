package gui;

import controller.AppController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.CrimeReport;
import services.CrimeServices;

import java.util.List;

public class ViewAllReportsScreen {

    private final AppController controller;
    private final String caller; // "admin" or "police"
    private final List<CrimeReport> reports = new CrimeServices().getAllReports();

    public ViewAllReportsScreen(AppController controller, String caller) {
        this.controller = controller;
        this.caller = caller.toLowerCase();
    }

    public void start(Stage stage) {
        ListView<String> reportList = new ListView<>();

        for (CrimeReport r : reports) {
            String summary = "• " + r.getTitle() +
                    " | ID: " + r.getReportId() +
                    " | Status: " + r.getStatus();
            reportList.getItems().add(summary);
        }

        reportList.setOnMouseClicked(e -> {
            int i = reportList.getSelectionModel().getSelectedIndex();
            if (i >= 0) {
                ReportDetailPopup.show(reports.get(i));
            }
        });

        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> {
            if (caller.equals("police")) {
                new PoliceDashboard().start(SceneNavigator.getStage());
            } else {
                new AdminDashboard().start(SceneNavigator.getStage());
            }
        });

        VBox layout = new VBox(15, reportList, backBtn);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #001f3f;");
        Scene scene = new Scene(layout, 600, 450);
        SceneNavigator.setScene(scene, "All Reports");
    }
}
