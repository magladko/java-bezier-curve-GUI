package com.java_bezier;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Project initialization class.
 * The key role is to initialize new window with established content.
 *
 * @link <a href="https://openjfx.io/openjfx-docs/">javafx documentation</a>
 */
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