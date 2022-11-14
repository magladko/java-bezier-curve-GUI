package com.java_bezier;

import com.java_bezier.models.Point;
import com.java_bezier.models.PointsSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

public class PointsTable extends VBox {
    @FXML private TableView<Point> tableView;
    @FXML private TextField x;
    @FXML private TextField y;
    @FXML private TextField weight;
    @FXML private Label labelDelete;

    public PointsTable() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("table.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        labelDelete.setText("Delete selected point: ");

        PointsSingleton.getInstance(tableView.getItems());
    }

    @FXML
    public void addPoint(ActionEvent event) {
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

    @FXML
    public void deletePoint(ActionEvent event) {
        Point point = tableView.getSelectionModel().getSelectedItem();
        tableView.getItems().remove(point);
    }

    @FXML
    public void clearAllPoints(ActionEvent event) {
        tableView.getItems().clear();
    }
}
