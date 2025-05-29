package gui;

import controller.AppController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PoliceDashboard {

    private final AppController controller = new AppController();

    public void start(Stage stage) {
        ImageView icon = new ImageView(new Image("file:/C://OOP%20PROJECT//CrimeReportingSystem//src//gui//police_icon.png"));
        icon.setFitHeight(100);
        icon.setPreserveRatio(true);

        Text title = new Text("Police Dashboard");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        title.setStyle("-fx-fill: white;");

        Button viewBtn = createButton("View All Reports", () -> new ViewAllReportsScreen(controller, "police").start(SceneNavigator.getStage()));
        Button updateBtn = createButton("Update Report Status", () -> new StatusUpdateScreen(controller,"police").start(SceneNavigator.getStage()));
        Button emergencyBtn = createButton("Handle Emergency Reports", () -> new HandleEmergencyScreen(controller).start(SceneNavigator.getStage()));
        Button exitBtn = createButton("Exit", () -> SceneNavigator.getStage().close());

        VBox layout = new VBox(15, icon, title, viewBtn, updateBtn, emergencyBtn, exitBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));
        layout.setStyle("-fx-background-color: #001f3f;");

        Scene scene = new Scene(layout, 600, 600);
        SceneNavigator.setScene(scene, "Police Dashboard");

        viewBtn.setStyle("-fx-background-color: #87CEFA; -fx-text-fill: white;");
        updateBtn.setStyle("-fx-background-color: #87CEFA; -fx-text-fill: white;");
        emergencyBtn.setStyle("-fx-background-color: #87CEFA; -fx-text-fill: white;");
        exitBtn.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white;");
    }

    private Button createButton(String text, Runnable action) {
        Button btn = new Button(text);
        btn.setMinWidth(250);
        btn.setOnAction(e -> action.run());
        return btn;
    }

}
