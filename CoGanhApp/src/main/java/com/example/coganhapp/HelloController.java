package com.example.coganhapp;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.util.EventListener;
import java.util.Objects;

public class HelloController {

    @FXML
    protected Label player2name;

    @FXML
    private HBox surrenderButton;
    @FXML
    private Label surrenderText;


    @FXML
    private VBox Player1Card;

    @FXML
    private VBox player2Card;
    @FXML
    protected void mouseEnterEffect() {
        surrenderButton.setCursor(Cursor.HAND);
        surrenderText.setTextFill(Color.rgb(255,31,69));
        surrenderButton.setStyle("-fx-background-color: white;-fx-background-radius: 2em;" );
    }

    @FXML
    protected void mouseLeaveEffect() {
        surrenderText.setTextFill(Color.WHITE);
        surrenderButton.setStyle("-fx-background-color:  #ff1f45;-fx-background-radius: 2em;");
    }

    @FXML
    protected void menuButtonEnterEffect(MouseEvent e) {
        if(e.getTarget() instanceof HBox box) {
            box.setStyle("-fx-background-color: #3a3153;-fx-background-radius: 2em");
        }
    }

    @FXML
    protected void menuButtonLeaveEffect(MouseEvent e) {
        if(e.getTarget() instanceof HBox box) {
            box.setStyle("-fx-background-color:  #5f43b2");
        }
    }

    @FXML
    protected void clickSurrender() {
        surrenderPopUp.display();
    }

    @FXML
    protected void clickHelpButton(MouseEvent event) throws IOException {
        Parent root =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HelpUI.fxml")));
        Node source = (Node) event.getSource();
        source.getScene().setRoot(root);
    }

    @FXML
    protected void clickExitButton() {
        ExitDialog.display();
    }

    @FXML
    protected void enterPlayerName(ActionEvent event) {
        if(event.getTarget() instanceof TextField input) {
            player2name.setText(input.getText());
        }
    }
    @FXML
    public  void initialize() {
        FadeTransition animation = new FadeTransition();
        animation.setDuration(Duration.seconds(1));
        animation.setNode(Player1Card);
        animation.setFromValue(0);
        animation.setToValue(1);

        FadeTransition animation2 = new FadeTransition();
        animation2.setDuration(Duration.seconds(1));
        animation2.setNode(player2Card);
        animation2.setFromValue(0);
        animation2.setToValue(1);

        animation.play();
        animation2.play();
    }
}