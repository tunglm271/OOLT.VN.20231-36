package com.example.coganhapp;

import com.example.coganhapp.data.DataReader;
import com.example.coganhapp.data.Result;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class CoGanhApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CoGanhApp.class.getResource("GameUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 796, 545);
        stage.setTitle("Game cờ gánh");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        //stage.getIcons().add(new Image("D:\\study\\project\\OOP\\OOLT.VN.20231-36\\CoGanhApp\\src\\main\\resources\\com\\example\\coganhapp\\media\\appicon.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }



    @Override
    public void init() throws Exception {
        try {
            DataReader.getInstance().loadHistory();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void stop() throws Exception {
        try {
            DataReader.getInstance().storeHistory();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}