package com.java_bezier.models;

import javafx.collections.ObservableList;

/**
 * PointsSingleton is used for communication between controllers in the project.
 * It assures that the TableView and Canvas shows the same collection of points.
 * In the project it is used to store a reference to Point list stored in the TableView component.
 * A listener of the contained ObservableList is used inside DrawingCanvas class to react to any changes.
 *
 * @see com.java_bezier.PointsTable
 * @link <a href="https://www.oracle.com/technical-resources/articles/java/singleton.html">Singleton design pattern</a>
 */
public class PointsSingleton {
    private static PointsSingleton _instance = null;
    private final ObservableList<Point> points;

    private PointsSingleton(ObservableList<Point> list) {
        this.points = list;
    }

    /**
     * Initializes a new Singleton object with specified reference to ObservableList of points.
     *
     * @param pointsListReference A reference for the ObservableList of points used in PointsTable class
     */
    public static synchronized void initializeWithReference(ObservableList<Point> pointsListReference){
        if (_instance == null) _instance = new PointsSingleton(pointsListReference);
    }
    public static synchronized PointsSingleton getInstance(){
        return _instance;
    }
    public synchronized ObservableList<Point> getItems() {
        return points;
    }
}
