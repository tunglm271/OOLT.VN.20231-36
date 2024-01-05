module com.example.coganhapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.coganhapp to javafx.fxml;
    exports com.example.coganhapp;
    exports com.example.coganhapp.window;
    opens com.example.coganhapp.window to javafx.fxml;
    exports com.example.coganhapp.data;
    opens com.example.coganhapp.data to javafx.fxml;
    exports com.example.coganhapp.game;
    opens com.example.coganhapp.game to javafx.fxml;
}