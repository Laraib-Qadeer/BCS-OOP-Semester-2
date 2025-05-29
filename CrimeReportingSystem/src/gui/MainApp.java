package gui;

import javafx.application.Application;
import javafx.stage.Stage;
import gui.RoleSelectionScreen;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        SceneNavigator.setStage(primaryStage);
        new RoleSelectionScreen().start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

