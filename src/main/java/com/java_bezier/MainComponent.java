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

    /**
     * FXML context for table of points component.
     */
    @FXML private PointsTable pointsTable;
    /**
     * FXML context for canvas and drawing Bézier curve checkbox.
     */
    @FXML private DrawingCanvas drawingCanvas;  // drawing canvas context

    /**
     * Initialize MainComponent class to load resources.
     */
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
