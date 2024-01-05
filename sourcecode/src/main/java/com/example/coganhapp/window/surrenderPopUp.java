package com.example.coganhapp.window;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.*;


public class surrenderPopUp {


    public static void display(boolean winner, String player1 , String player2)
    {
        Stage popupwindow=new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Surrender");
        popupwindow.initStyle(StageStyle.UNDECORATED);
        Label label1= new Label("You've surrendered !");
        label1.setFont(Font.font("Arial",FontWeight.BOLD,14));
        Label winnerText = new Label();
        Button button1= new Button("OK !");
        if(winner == false) {
            winnerText.setText(player2 + " win!");
            winnerText.setFont(Font.font("Arial",FontWeight.BOLD,20));
            winnerText.setTextFill(Color.BLUE);
            button1.setStyle("-fx-background-color: blue");
        } else {
            winnerText.setText(player1 + " win!");
            winnerText.setFont(Font.font("Arial",FontWeight.BOLD,20));
            winnerText.setTextFill(Color.RED);
            button1.setStyle("-fx-background-color: red");
        }
        button1.setTextFill(Color.WHITE);
        button1.setFont(Font.font("Arial",FontWeight.BOLD,12));
        button1.setOnAction(e -> popupwindow.close());



        VBox layout= new VBox(10);


        layout.getChildren().addAll(label1,winnerText, button1);

        layout.setAlignment(Pos.CENTER);

        Scene scene1= new Scene(layout, 200, 150);

        popupwindow.setScene(scene1);

        popupwindow.showAndWait();

    }

}

