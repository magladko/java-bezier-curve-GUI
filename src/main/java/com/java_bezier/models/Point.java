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
    /**
     * The SimpleDoubleProperty for <i>x</i> coordinate.
     */
    private final SimpleDoubleProperty x = new SimpleDoubleProperty();
    /**
     * The SimpleDoubleProperty for <i>y</i> coordinate.
     */
    private final SimpleDoubleProperty y = new SimpleDoubleProperty();
    /**
     * The SimpleDoubleProperty for <i>weight</i> field used for the Bézier curve calculations.
     */
    private final SimpleDoubleProperty weight = new SimpleDoubleProperty();

    /**
     * Default constructor sets coordinates to (0., 0., 0.) values
     */
    public Point() {
        this(0.,0.,0.);
    }

    /**
     * Initialize Point class object using bare coordinate values and weight.
     *
     * @param x Double <i>x</i> coordinate for the point.
     * @param y Double <i>y</i> coordinate for the point.
     * @param weight Double <i>weight</i> used for the Bezier calculations.
     */
    public Point(Double x, Double y, Double weight) {
        setX(x);
        setY(y);
        setWeight(weight);
    }

    /**
     * @return Double <i>x</i> coordinate value
     */
    public Double getX() {
        return x.get();
    }

    /**
     * @param x Double <i>x</i> coordinate value.
     */
    public void setX(Double x) {
        this.x.set(x);
    }

    /**
     * @return Double <i>y</i> coordinate value.
     */
    public Double getY() {
        return y.get();
    }

    /**
     * @param y Double value for <i>y</i> coordinate.
     */
    public void setY(Double y) {
        this.y.set(y);
    }

    /**
     * @return Double weight of the point used fot Bézier curve calculations.
     */
    public Double getWeight() {
        return weight.get();
    }

    /**
     * @param weight Double weight used for the Bézier curve calculations.
     */
    public void setWeight(Double weight) {
        this.weight.set(weight);
    }
}
