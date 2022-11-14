package com.java_bezier;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class JavaBezierApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MainComponent mainComponent = new MainComponent();

        Scene scene = new Scene(mainComponent);
        stage.setTitle("Java Bezier drawer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}