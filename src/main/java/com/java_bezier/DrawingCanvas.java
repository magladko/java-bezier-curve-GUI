package com.java_bezier;

import com.java_bezier.models.Point;
import com.java_bezier.models.PointsSingleton;
import com.java_bezier.utils.CustomBezier;
import javafx.beans.property.SimpleIntegerProperty;
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
import java.util.ArrayList;
import java.util.List;

public class DrawingCanvas extends VBox {

    @FXML private Canvas canvas;
    @FXML private CheckBox drawBezier;
//    private ArrayList<Point> points;
    private final Color drawColor;
    private final GraphicsContext gc;
    private final Integer pointThickness = 4;

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

    public void drawNewPoint(Point point) {
        gc.setFill(drawColor);
        if (drawBezier.isSelected()) gc.setFill(Color.gray(0.7));
        gc.fillOval(point.getX()-pointThickness/2., point.getY()-pointThickness/2.,
                pointThickness, pointThickness);
    }

    public void redraw(List<Point> list) {
        gc.clearRect(0., 0., canvas.getWidth(), canvas.getHeight());
        list.forEach(this::drawNewPoint);
        if (drawBezier.isSelected()) drawBezier();
    }

    public void redraw() {
        gc.clearRect(0., 0., canvas.getWidth(), canvas.getHeight());
        PointsSingleton.getInstance().getItems().forEach(this::drawNewPoint);
        if (drawBezier.isSelected()) drawBezier();
    }

    @FXML
    private void checkBoxSwitch() {
        redraw();
    }

    private void drawBezier() {
        ObservableList<Point> points = PointsSingleton.getInstance().getItems();
        gc.setFill(drawColor);
        if (points.size() == 2) {
            gc.beginPath();
            gc.moveTo(points.get(0).getX(), points.get(0).getY());
            gc.lineTo(points.get(1).getX(), points.get(1).getY());
            gc.stroke();
            return;
        }
        if (points.size() < 3) return;

        double x = points.get(0).getX(), y = points.get(0).getY(), polynomial = 0.;
        gc.moveTo(x, y);
        gc.beginPath();

        for (double u = 0; u <= 1; u += 0.0002) {
            double nominatorX = 0, nominatorY = 0, denominator = 0;

            for (int i = 0; i < points.size(); i++) {
                polynomial = CustomBezier.BezierPoly(i, points.size()-1, u);
                nominatorX += points.get(i).getWeight() * points.get(i).getX() * polynomial;
                nominatorY += points.get(i).getWeight() * points.get(i).getY() * polynomial;
                denominator += points.get(i).getWeight() * polynomial;
            }
            if (polynomial != 0) {
                x = nominatorX / denominator;
                y = nominatorY / denominator;
            }
            gc.lineTo(x, y);
        }
        gc.stroke();
    }
}
