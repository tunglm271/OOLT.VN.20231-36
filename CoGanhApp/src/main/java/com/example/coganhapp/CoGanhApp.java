package com.example.coganhapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class CoGanhApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CoGanhApp.class.getResource("GameUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 796, 545);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.getIcons().add(new Image("D:\\study\\project\\OOP\\OOLT.VN.20231-36\\CoGanhApp\\src\\main\\resources\\com\\example\\coganhapp\\media\\appicon.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() throws Exception {
        super.init();
    }
}