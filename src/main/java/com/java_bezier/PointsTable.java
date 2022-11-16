package com.java_bezier;

import com.java_bezier.models.Point;
import com.java_bezier.models.PointsSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

/**
 * PointsTable manages whole context related to the TableView component,
 * such as adding new points, deleting selected or clearing whole table.
 * PointsTable is used in the table.fxml file as a root controller.
 */
public class PointsTable extends VBox {
    /**
     * FXML context for the TableView used for dataflow.
     */
    @FXML private TableView<Point> tableView;
    /**
     * FXML context for the new point's <i>x</i> coordinate input.
     */
    @FXML private TextField x;
    /**
     * FXML context for the new point's <i>y</i> coordinate input.
     */
    @FXML private TextField y;
    /**
     * FXML context for the new point's <i>weight</i> input.
     */
    @FXML private TextField weight;
    /**
     * FXML context for the delete button label.
     */
    @FXML private Label deleteButtonLabel;

    /**
     * Default constructor implements FXML resource loading and
     * initializes PointsSingleton class with a reference to a list in the TableView object.
     *
     * @see PointsSingleton
     * @see TableView
     */
    public PointsTable() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("table.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        deleteButtonLabel.setText("Delete selected point: ");
        PointsSingleton.initializeWithReference(tableView.getItems());
    }

    /**
     * Manages adding new point to the table based on the values in the TextFields.
     * Ignores incorrect data.
     *
     * @see TextField
     */
    @FXML
    public void addPoint() {
        if (x.getText() == null || Objects.equals(x.getText(), "") ||
                y.getText() == null || Objects.equals(x.getText(), "") ||
                weight.getText() == null || Objects.equals(x.getText(), "")) return;
        try {
            tableView.getItems().add(new Point(Double.parseDouble(x.getText()),
                    Double.parseDouble(y.getText()),
                    Double.parseDouble(weight.getText())
            ));

        } catch (NumberFormatException ignored) { return; }

        x.setText("");
        y.setText("");
        weight.setText("");
    }

    /**
     * Removes point from the model.
     */
    @FXML
    public void deletePoint() {
        Point point = tableView.getSelectionModel().getSelectedItem();
        tableView.getItems().remove(point);
    }

    /**
     * Clears all the points from the model.
     */
    @FXML
    public void clearAllPoints() {
        tableView.getItems().clear();
    }
}
