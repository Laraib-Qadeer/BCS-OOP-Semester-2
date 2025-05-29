package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.CrimeReport;

public class ReportDetailPopup {

    public static void show(CrimeReport report) {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Report Details");

        Label title = new Label("Title: " + report.getTitle());
        Label desc = new Label("Description: " + report.getDescription());
        Label location = new Label("Location: " + report.getLocation());
        Label status = new Label("Status: " + report.getStatus());
        Label reporter = new Label("Reporter ID: " + report.getReporterId());
        Label id = new Label("Report ID: " + report.getReportId());

        for (Label label : new Label[]{title, desc, location, status, reporter, id}) {
            label.setTextFill(Color.WHITE);
            label.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
            label.setWrapText(true);
        }

        VBox layout = new VBox(10, title, desc, location, status, reporter, id);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #2c2f33;");

        Scene scene = new Scene(layout, 500, 300);
        popup.setScene(scene);
        popup.showAndWait();
    }
}
