module com.example.coganhapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.coganhapp to javafx.fxml;
    exports com.example.coganhapp;
    exports com.example.coganhapp.Move;
    opens com.example.coganhapp.Move to javafx.fxml;
    exports com.example.coganhapp.Window;
    opens com.example.coganhapp.Window to javafx.fxml;
    exports com.example.coganhapp.data;
    opens com.example.coganhapp.data to javafx.fxml;
    exports com.example.coganhapp.Controller;
    opens com.example.coganhapp.Controller to javafx.fxml;
}