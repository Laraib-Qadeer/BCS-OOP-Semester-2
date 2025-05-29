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

import java.util.List;

import models.Citizen;
import models.CrimeReport;
import services.CrimeServices;
import session.Session;

public class ViewReportsScreen {

    public void start(Stage stage) {
        Label title = new Label("My Submitted Reports");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setTextFill(Color.WHITE);

        ListView<String> reportList = new ListView<>();
        reportList.setPrefSize(500, 300);

        List<CrimeReport> reports;
        if (!(Session.getCurrentUser() instanceof Citizen)) {
            reportList.getItems().add("Error: Only citizens can view their reports.");
            reports = null;
        } else {
            Citizen citizen = (Citizen) Session.getCurrentUser();
            reports = new CrimeServices().getReportsByUser(citizen.getId());

            if (reports.isEmpty()) {
                reportList.getItems().add("No reports submitted yet.");
            } else {
                for (CrimeReport report : reports) {
                    String display = "• " + report.getTitle() +
                            " | Location: " + report.getLocation() +
                            " | Status: " + report.getStatus();
                    reportList.getItems().add(display);
                }
            }
        }

        reportList.setOnMouseClicked(e -> {
            int index = reportList.getSelectionModel().getSelectedIndex();
            if (index >= 0 && reports != null && index < reports.size()) {
                ReportDetailPopup.show(reports.get(index));
            }
        });

        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: #6c757d; -fx-text-fill: white;");
        backBtn.setOnAction(e -> new CitizenDashboard().start(SceneNavigator.getStage()));

        VBox layout = new VBox(15, title, reportList, backBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.setStyle("-fx-background-color: #001f3f;");

        Scene scene = new Scene(layout, 600, 450);
        SceneNavigator.setScene(scene, "View My Reports");
    }
}
