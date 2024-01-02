module com.example.coganhapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.coganhapp to javafx.fxml;
    exports com.example.coganhapp;
    exports com.example.coganhapp.Window;
    opens com.example.coganhapp.Window to javafx.fxml;
    exports com.example.coganhapp.data;
    opens com.example.coganhapp.data to javafx.fxml;
    exports com.example.coganhapp.broad;
    opens com.example.coganhapp.broad to javafx.fxml;
}