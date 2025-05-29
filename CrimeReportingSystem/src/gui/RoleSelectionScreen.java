package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RoleSelectionScreen {

    public void start(Stage stage) {
        //stage.initStyle(StageStyle.DECORATED);
        ImageView background = new ImageView(
                new Image(getClass().getResource("/gui/role_selection_bg.png").toExternalForm())
        );
        background.setPreserveRatio(false);
        background.setFitWidth(768);
        background.setFitHeight(720);

        Button adminBtn = buildButton("ADMIN", 100, 600);
        adminBtn.setOnAction(e -> new LoginRegisterScreen("admin", "login").start(stage));

        Button policeBtn = buildButton("POLICE", 310, 600);
        policeBtn.setOnAction(e -> new LoginRegisterScreen("police", "login").start(stage));

        Button citizenBtn = buildButton("CITIZEN", 510, 600);
        citizenBtn.setOnAction(e -> new CitizenLoginOrRegisterScreen().start(stage));

        Pane root = new Pane();
        root.getChildren().addAll(background, adminBtn, policeBtn, citizenBtn);

        Scene scene = new Scene(root, 768, 720);

        stage.setTitle("Police Command Center");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private Button buildButton(String label, double x, double y) {
        Button btn = new Button(label);
        btn.setLayoutX(x);
        btn.setLayoutY(y);
        btn.setPrefWidth(150);
        btn.setPrefHeight(60);

        btn.setStyle("""
            -fx-background-color: transparent;
            -fx-text-fill: white;
            -fx-font-weight: bold;
            -fx-font-size: 18px;
            -fx-border-color: transparent;
            -fx-text-alignment: center;
            -fx-font-family: 'Arial', 'Segoe UI', sans-serif;
        """);

        return btn;
    }
}
