package com.example.coganhapp.Window;

import com.example.coganhapp.data.DataReader;
import com.example.coganhapp.data.Result;
import com.example.coganhapp.game.Player;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ExitDialog {
    public static void display(Player player1, Player player2)
    {
        Stage popupwindow=new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Exit");
        Label label1= new Label("Do you want to quit ?");
        label1.setFont(Font.font("Arial", FontWeight.BOLD,14));

        Button button1= new Button("YES !");

        button1.setOnAction(e -> {
            Result result = new Result(player1.getPlayerName(), player2.getPlayerName(), player1.win, player1.lose);
            DataReader.getInstance().getHistory().add(result);
            Platform.exit();
        });



        VBox layout= new VBox(10);


        layout.getChildren().addAll(label1, button1);

        layout.setAlignment(Pos.CENTER);

        Scene scene1= new Scene(layout, 200, 150);

        popupwindow.setScene(scene1);

        popupwindow.showAndWait();

    }
}
