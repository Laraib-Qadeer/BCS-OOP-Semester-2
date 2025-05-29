package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import session.Session;

public class CitizenDashboard {

    public void start(Stage stage) {
        ImageView avatar = new ImageView(new Image("file:/C://OOP%20PROJECT//CrimeReportingSystem//src//gui//citizen_icon.png"));
        avatar.setFitHeight(100);
        avatar.setPreserveRatio(true);

        Text title = new Text("Citizen Dashboard");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        title.setFill(Color.WHITE);

        Button submitBtn = new Button("Submit New Report");
        submitBtn.setMinWidth(250);
        submitBtn.setStyle("-fx-background-color: #87CEFA; -fx-text-fill: white; -fx-font-size: 14;");

        Button viewBtn = new Button("View My Reports");
        viewBtn.setMinWidth(250);
        viewBtn.setStyle("-fx-background-color: #87CEFA; -fx-text-fill: white; -fx-font-size: 14;");

        Button logoutBtn = new Button("Logout");
        logoutBtn.setMinWidth(250);
        logoutBtn.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-font-size: 14;");

        submitBtn.setOnAction(e -> new SubmitReportScreen().start(SceneNavigator.getStage()));
        viewBtn.setOnAction(e -> new ViewReportsScreen().start(SceneNavigator.getStage()));
        logoutBtn.setOnAction(e -> {
            Session.setCurrentUser(null);
            new RoleSelectionScreen().start(SceneNavigator.getStage());
        });

        VBox layout = new VBox(20, avatar, title, submitBtn, viewBtn, logoutBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));
        layout.setStyle("-fx-background-color: #001f3f;");

        Scene scene = new Scene(layout, 600, 500);
        SceneNavigator.setScene(scene, "Citizen Dashboard");
    }
}
