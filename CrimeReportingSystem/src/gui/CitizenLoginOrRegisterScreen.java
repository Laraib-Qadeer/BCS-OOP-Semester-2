package gui;

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
import javafx.scene.paint.Color;
import models.Citizen;
import session.Session;

public class CitizenLoginOrRegisterScreen {

    public void start(Stage stage) {
        ImageView avatar = new ImageView(new Image("file:/C://OOP%20PROJECT//CrimeReportingSystem//src//gui//citizen_icon.png"));
        avatar.setFitHeight(100);
        avatar.setPreserveRatio(true);

        Text title = new Text("CITIZEN ACCESS");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setFill(Color.WHITE);


        Button loginBtn = new Button("Login");
        loginBtn.setMinWidth(200);
        loginBtn.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");

        Button registerBtn = new Button("Register");
        registerBtn.setMinWidth(200);
        registerBtn.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;");

        Button anonymousBtn = new Button("Report Anonymously");
        anonymousBtn.setMinWidth(200);
        anonymousBtn.setStyle("-fx-background-color: #555555; -fx-text-fill: white;");

        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: #6c757d; -fx-text-fill: white;");
        backBtn.setOnAction(e -> new RoleSelectionScreen().start(SceneNavigator.getStage()));


        loginBtn.setOnAction(e -> new LoginRegisterScreen("citizen", "login").start(SceneNavigator.getStage()));
        registerBtn.setOnAction(e -> new LoginRegisterScreen("citizen", "register").start(SceneNavigator.getStage()));

        anonymousBtn.setOnAction(e -> {
            Citizen anon = new Citizen("anonymous", "Anonymous", "none", "none");
            Session.setCurrentUser(anon);
            new SubmitReportScreen().start(SceneNavigator.getStage());
        });

        VBox layout = new VBox(15, avatar, title, loginBtn, registerBtn, anonymousBtn, backBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.setStyle("-fx-background-color:#001f3f;");

        Scene scene = new Scene(layout, 500, 550);
        SceneNavigator.setScene(scene, "Citizen Options");
    }
}
