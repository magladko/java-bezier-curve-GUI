package com.java_bezier.models;

import javafx.beans.property.SimpleDoubleProperty;

/**
 * Point is a model class used for managing the data exchange inside controllers and between them.
 * The property type is used for facilitating communication with javafx TableView component.
 * Point stores information about position in 2D and weight which is used for calculating Bézier curve.
 *
 * @see com.java_bezier.DrawingCanvas
 * @see com.java_bezier.PointsTable
 * @see javafx.scene.control.TableView
 * @see SimpleDoubleProperty
 */
public class Point {
    private final SimpleDoubleProperty x = new SimpleDoubleProperty();
    private final SimpleDoubleProperty y = new SimpleDoubleProperty();
    private final SimpleDoubleProperty weight = new SimpleDoubleProperty();

    public Point() {
        this(0.,0.,0.);
    }

    public Point(Double x, Double y, Double weight) {
        setX(x);
        setY(y);
        setWeight(weight);
    }

    public Double getX() {
        return x.get();
    }

    public void setX(Double fName) {
        x.set(fName);
    }

    public Double getY() {
        return y.get();
    }

    public void setY(Double y) {
        this.y.set(y);
    }

    public Double getWeight() {
        return weight.get();
    }

    public void setWeight(Double weight) {
        this.weight.set(weight);
    }
}
