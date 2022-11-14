module com.java_bezier {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.java_bezier to javafx.fxml;
    opens com.java_bezier.models to javafx.fxml;
    exports com.java_bezier;
    exports com.java_bezier.models;
}