package com.java_bezier.models;

import javafx.collections.ObservableList;

public class PointsSingleton {

    private static PointsSingleton single_instance = null;
    private final ObservableList<Point> points;

    private PointsSingleton(){
        points = null;
    }

    private PointsSingleton(ObservableList<Point> list) {
        this.points = list;
    }

    public static PointsSingleton getInstance(ObservableList<Point> points){
        if (single_instance == null) single_instance = new PointsSingleton(points);
        return single_instance;
    }
    public static PointsSingleton getInstance(){
        if (single_instance == null) single_instance = new PointsSingleton();
        return single_instance;
    }
    public synchronized ObservableList<Point> getItems() {
        return points;
    }
}
