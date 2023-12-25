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
        stage.getIcons().add(new Image("D:\\download\\vs-versus-vector-background-red-blue-colors-separated-color-sides-glitch-effect-sport-game-fight-battle-210679642.jpg"));
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