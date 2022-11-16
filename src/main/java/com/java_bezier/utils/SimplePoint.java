package com.java_bezier.utils;

import com.java_bezier.models.Point;

/**
 * SimplePoint encapsulates 2D coordinates in x and y Double fields.
 * In contradiction to Point it uses basic types for holding data and does not contain additional data.
 * Used for passing 2D coordinates data in functions.
 */
public class SimplePoint {
    /**
     * The <i>x</i> coordinate value.
     */
    private Double x;
    /**
     * The <i>y</i> coordinate value.
     */
    private Double y;

    /**
     * Initialize SimplePoint class object using Double coordinates <i>x</i> and <i>y</i>
     * @param x Double <i>x</i> coordinate
     * @param y Double <i>x</i> coordinate
     */
    public SimplePoint(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Initialize SimplePoint with Point object. Weight value is lost.
     * @param point Point class object
     */
    public SimplePoint(Point point) {
        this.x = point.getX();
        this.y = point.getY();
    }

    /**
     * @return Double x coordinate
     */
    public Double getX() {
        return x;
    }

    /**
     * @return Double y coordinate
     */
    public Double getY() {
        return y;
    }
}
