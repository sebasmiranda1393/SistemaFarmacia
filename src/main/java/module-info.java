module com.example.lab2farmacia {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.lab2farmacia to javafx.fxml;
    exports com.example.lab2farmacia;
}