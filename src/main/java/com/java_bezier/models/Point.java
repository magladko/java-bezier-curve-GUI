package com.java_bezier.models;

import javafx.beans.property.SimpleDoubleProperty;

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

    public void setY(Double fName) {
        y.set(fName);
    }

    public Double getWeight() {
        return weight.get();
    }

    public void setWeight(Double fName) {
        weight.set(fName);
    }
}
