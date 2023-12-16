package com.example.coganhapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.getIcons().add(new Image("D:\\download\\vs-versus-vector-background-red-blue-colors-separated-color-sides-glitch-effect-sport-game-fight-battle-210679642.jpg"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}