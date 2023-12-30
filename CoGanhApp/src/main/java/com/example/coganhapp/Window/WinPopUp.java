package com.example.coganhapp.Window;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.*;

public class WinPopUp {
    public static void display(String playName)
    {
        Stage popupwindow =new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Win!");

        Label label1= new Label(playName + " win !");
        label1.setFont(Font.font("Arial",FontWeight.BOLD,14));

        Button button1= new Button("OK !");

        button1.setOnAction(e -> popupwindow.close());



        VBox layout= new VBox(10);


        layout.getChildren().addAll(label1, button1);

        layout.setAlignment(Pos.CENTER);

        Scene scene1= new Scene(layout, 200, 150);

        popupwindow.setScene(scene1);

        popupwindow.showAndWait();

    }
}
