package com.example.coganhapp;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.*;


public class surrenderPopUp {


    public static void display()
    {
        Stage popupwindow=new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Surrender");

        Label label1= new Label("You've surrendered !");
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
