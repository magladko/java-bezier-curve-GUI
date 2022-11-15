package com.java_bezier;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;

/**
 * MainComponent contains PointsTable and DrawingCanvas.
 * It connects both mentioned components in a single scene.
 */
public class MainComponent extends HBox {

    @FXML private PointsTable pointsTable;
    @FXML private DrawingCanvas drawingCanvas;

    public MainComponent() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
