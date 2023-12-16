module com.example.coganhapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.coganhapp to javafx.fxml;
    exports com.example.coganhapp;
}