package com.java_bezier.utils;

import com.java_bezier.models.Point;

/**
 * SimplePoint encapsulates 2D coordinates in x and y Double fields.
 * In contradiction to Point it uses basic types for holding data and does not contain additional data.
 * Used for passing 2D coordinates data in functions.
 */
public class SimplePoint {
    private Double x;
    private Double y;

    public SimplePoint(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public SimplePoint(Point point) {
        this.x = point.getX();
        this.y = point.getY();
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }
}
