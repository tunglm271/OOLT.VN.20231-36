package com.example.coganhapp;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WinPopUp {
    public static void display(boolean winner, String player1 , String player2)
    {
        Stage popupwindow=new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Winner found!");
        Label winnerText = new Label();
        if(winner == false) {
            winnerText.setText(player2 + " win!");
            winnerText.setFont(Font.font("Arial",FontWeight.BOLD,20));
            winnerText.setTextFill(Color.BLUE);
        } else {
            winnerText.setText(player1 + " win!");
            winnerText.setFont(Font.font("Arial",FontWeight.BOLD,20));
            winnerText.setTextFill(Color.RED);
        }

        Button button1= new Button("OK !");

        button1.setOnAction(e -> popupwindow.close());



        VBox layout= new VBox(10);


        layout.getChildren().addAll(winnerText, button1);

        layout.setAlignment(Pos.CENTER);

        Scene scene1= new Scene(layout, 200, 150);

        popupwindow.setScene(scene1);

        popupwindow.showAndWait();

    }
}
