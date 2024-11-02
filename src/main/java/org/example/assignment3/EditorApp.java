package org.example.assignment3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditorApp extends Application {

    public void start(Stage stage) {
        MainUI root = new MainUI();
        Scene scene = new Scene(root);
        stage.setTitle("CMPT381 A3");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
