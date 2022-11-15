package com.java_bezier;

import com.java_bezier.models.Point;
import com.java_bezier.models.PointsSingleton;
import com.java_bezier.utils.CustomBezier;
import com.java_bezier.utils.SimplePoint;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.List;

/**
 * DrawingCanvas controls data and display connected with
 * plotting shapes on the javafx Canvas component.
 * It uses listener attached to the PointsSingleton ObservableList parameter to react to
 * any changes made inside TableView component as well as adds a new point to
 * the PointsSingleton ObservableList whenever user marks a new point.
 *
 * @see PointsSingleton
 */
public class DrawingCanvas extends VBox {
    @FXML private Canvas canvas;
    @FXML private CheckBox drawBezier;
    private final Color drawColor;
    private final GraphicsContext gc;
    private final Integer pointThickness = 4;

    /**
     * Loads resource data from canvas.fxml file, then adds listeners responsible
     * for communicating with javafx TableView using PointsSingleton ObservableList.
     */
    public DrawingCanvas() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("canvas.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        drawColor = Color.BLACK;
        gc = canvas.getGraphicsContext2D();

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            Point point = new Point(event.getX(), event.getY(), 1.);
            PointsSingleton.getInstance().getItems().add(point);
        });

        PointsSingleton.getInstance().getItems().addListener((ListChangeListener<Point>) change -> {
            while (change.next()){
                if (change.wasAdded() || change.wasRemoved())
                    redraw(PointsSingleton.getInstance().getItems());
            }
        });
    }
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * Draws new dot on the canvas.
     * @param point The Point argument, which coordinates are used for drawing new dot.
     */
    public void drawNewPoint(Point point) {
        gc.setFill(drawColor);
        if (drawBezier.isSelected()) gc.setFill(Color.gray(0.7));
        gc.fillOval(point.getX()-pointThickness/2., point.getY()-pointThickness/2.,
                pointThickness, pointThickness);
    }

    /**
     * Draws indicated points on the cleared canvas.
     * @param list List of Point values.
     */
    public void redraw(List<Point> list) {
        gc.clearRect(0., 0., canvas.getWidth(), canvas.getHeight());
        list.forEach(this::drawNewPoint);
        if (drawBezier.isSelected()) drawBezier();
    }

    /**
     * Refreshes canvas shapes based on the points contained in
     * the PointsSingleton and drawBezier checkbox value.
     */
    public void redraw() {
        gc.clearRect(0., 0., canvas.getWidth(), canvas.getHeight());
        PointsSingleton.getInstance().getItems().forEach(this::drawNewPoint);
        if (drawBezier.isSelected()) drawBezier();
    }

    /**
     * Forces to refresh canvas with new data effectively plotting or erasing the Bézier curve shape.
     */
    @FXML
    private void checkBoxSwitch() {
        redraw();
    }

    /**
     * Draws Bézier curve on the canvas plotting lines
     * based on the CustomBezier.calculateBezierCurve method output.
     * @see CustomBezier
     */
    private void drawBezier() {
        gc.setFill(drawColor);
        List<SimplePoint> points = CustomBezier.calculateBezierCurve(
                PointsSingleton.getInstance().getItems(), 0.0002);
        if (points.size() < 2) return;
        gc.beginPath();
        gc.moveTo(points.get(0).getX(), points.get(0).getY());
        for (int i = 1; i < points.size(); i++)
            gc.lineTo(points.get(i).getX(), points.get(i).getY());
        gc.stroke();
    }
}
