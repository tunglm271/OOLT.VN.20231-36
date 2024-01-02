package com.example.coganhapp.Window;

import com.example.coganhapp.data.DataReader;
import com.example.coganhapp.data.Result;
import com.example.coganhapp.broad.Player;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ExitDialog {
    public static void display(Player player1, Player player2)
    {
        Stage popupwindow=new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Exit");
        popupwindow.initStyle(StageStyle.UNDECORATED);
        Label label1= new Label("Do you want to quit ?");
        label1.setFont(Font.font("Arial", FontWeight.BOLD,14));
        Label label2 = new Label("Current match will not be saved");
        label2.setTextFill(Color.RED);
        HBox hBox = new HBox();
        Button button1 = new Button("YES");
        button1.setTextFill(Color.WHITE);
        button1.setFont(Font.font("Arial",FontWeight.BOLD,12));
        button1.setStyle("-fx-background-color: Red");
        Button button2 = new Button("NO");
        button1.setFont(Font.font("Arial",FontWeight.BOLD,12));
        button1.setOnAction(e -> {
            Result result = new Result(player1.getPlayerName(), player2.getPlayerName(), player1.win, player1.lose);
            DataReader.getInstance().getHistory().add(result);
            Platform.exit();
        });
        button2.setOnAction(e -> {
            popupwindow.close();
        });

        hBox.getChildren().addAll(button1,button2);
        VBox layout= new VBox(10);
        hBox.setSpacing(15);
        hBox.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label1, label2, hBox);

        layout.setAlignment(Pos.CENTER);

        Scene scene1= new Scene(layout, 200, 150);

        popupwindow.setScene(scene1);

        popupwindow.showAndWait();

    }

}
