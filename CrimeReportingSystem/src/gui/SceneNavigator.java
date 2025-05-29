package gui;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneNavigator {
    private static Stage primaryStage;

    public static void setStage(Stage stage) {
        primaryStage = stage;
    }

    public static void setScene(Scene scene, String title) {
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Stage getStage() {
        return primaryStage;
    }
}
