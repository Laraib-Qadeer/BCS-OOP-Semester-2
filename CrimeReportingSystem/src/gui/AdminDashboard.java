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

public class AdminDashboard {

    private final AppController controller = new AppController();

    public void start(Stage stage) {
        ImageView icon = new ImageView(new Image("file:/C://OOP%20PROJECT//CrimeReportingSystem//src//gui//admin_icon.png"));
        icon.setFitHeight(100);
        icon.setPreserveRatio(true);

        Text title = new Text("Admin Dashboard");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        title.setStyle("-fx-fill: WHITE;");

        Button btn1 = createButton("Create Police Account", () -> new CreatePoliceScreen(controller).start(SceneNavigator.getStage()));
        Button btn2 = createButton("Manage Users", () -> new ManageUsersScreen().start(SceneNavigator.getStage()));
        Button btn3 = createButton("View All Reports", () -> new ViewAllReportsScreen(controller, "admin").start(SceneNavigator.getStage()));
        Button btn4 = createButton("Update Report Status", () -> new StatusUpdateScreen(controller,"admin").start(SceneNavigator.getStage()));
        Button btn5 =createButton("Exit", () -> SceneNavigator.getStage().close());

        VBox layout = new VBox(15, icon, title, btn1, btn2, btn3, btn4,btn5);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));
        layout.setStyle("-fx-background-color: #001f3f;");

        Scene scene = new Scene(layout, 600, 600);
        SceneNavigator.setScene(scene, "Admin Dashboard");

        btn1.setStyle("-fx-background-color: #87CEFA; -fx-text-fill: white;");
        btn2.setStyle("-fx-background-color: #87CEFA; -fx-text-fill: white;");
        btn3.setStyle("-fx-background-color: #87CEFA; -fx-text-fill: white;");
        btn4.setStyle("-fx-background-color: #87CEFA; -fx-text-fill: white;");
        btn5.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white;");
    }

    private Button createButton(String text, Runnable action) {
        Button btn = new Button(text);
        btn.setMinWidth(250);
        btn.setOnAction(e -> action.run());
        return btn;
    }
}
