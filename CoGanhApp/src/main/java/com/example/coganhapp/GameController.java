package com.example.coganhapp;

import com.example.coganhapp.broad.Piece;
import com.example.coganhapp.game.Player;
import com.example.coganhapp.game.PlayerSide;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class GameController {

    private Player player1 = new Player("Player 1", PlayerSide.RED);
    private Player player2 = new Player("Player 2", PlayerSide.BLUE);

    private boolean turn = false;

    private int kernel = 0;

    private int tmpRow;
    private int tmpColumn;


    @FXML
    private AnchorPane gameBroad;

    @FXML
    protected Label player2name;


    @FXML
    protected Piece[][] intersectionPoint = new Piece[5][5];

    @FXML
    private HBox surrenderButton;
    @FXML
    private Label surrenderText;


    @FXML
    private VBox player1Card;

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
        animation.setNode(player1Card);
        animation.setFromValue(0);
        animation.setToValue(1);

        FadeTransition animation2 = new FadeTransition();
        animation2.setDuration(Duration.seconds(1));
        animation2.setNode(player2Card);
        animation2.setFromValue(0);
        animation2.setToValue(1);

        animation.play();
        animation2.play();
        glowEffect();
        for(int i = 0;i<5;i++) {
            for(int j = 0;j<5;j++) {
                if(i == 0 || ((i == 1 || i == 2) && j == 4) || (i == 1 && j == 0)) {
                    intersectionPoint[i][j] = new Piece(i,j,player1);
                } else if(i == 4 || ((i == 2 || i == 3) && j == 0) || (i == 3 && j == 4)) {
                    intersectionPoint[i][j] = new Piece(i,j,player2);
                } else {
                    intersectionPoint[i][j] = new Piece(i,j,null);
                }
                gameBroad.getChildren().add(intersectionPoint[i][j]);
                intersectionPoint[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Piece source = (Piece) mouseEvent.getSource();
                        int row = source.getRow();
                        int columm = source.getColumn();
                        fadeEffect(row,columm);
                    }
                });
            }
        }
    }

    private void fadeEffect(int row, int column) {
        if(kernel == 0) {
            if(intersectionPoint[row][column].getPlayer() != null) {
                if ((turn && intersectionPoint[row][column].getPlayer() == player2) || (!turn && intersectionPoint[row][column].getPlayer() == player1)) {
                    Paint color = intersectionPoint[row][column].getFill();
                    switch (row) {
                        case 0 -> {
                            switch (column) {
                                case 0 -> {
                                    if (intersectionPoint[row][column + 1].getPlayer() == null) {
                                        intersectionPoint[row][column + 1].setFill(color);
                                        intersectionPoint[row][column + 1].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row + 1][column].getPlayer() == null) {
                                        intersectionPoint[row + 1][column].setFill(color);
                                        intersectionPoint[row + 1][column].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row + 1][column + 1].getPlayer() == null) {
                                        intersectionPoint[row + 1][column + 1].setFill(color);
                                        intersectionPoint[row + 1][column + 1].setOpacity(0.5);
                                    }
                                }
                                case 1, 3 -> {
                                    if (intersectionPoint[row + 1][column].getPlayer() == null) {
                                        intersectionPoint[row + 1][column].setFill(color);
                                        intersectionPoint[row + 1][column].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row][column - 1].getPlayer() == null) {
                                        intersectionPoint[row][column - 1].setFill(color);
                                        intersectionPoint[row][column - 1].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row][column + 1].getPlayer() == null) {
                                        intersectionPoint[row][column + 1].setFill(color);
                                        intersectionPoint[row][column + 1].setOpacity(0.5);
                                    }
                                }
                                case 2 -> {
                                    if (intersectionPoint[row + 1][column].getPlayer() == null) {
                                        intersectionPoint[row + 1][column].setFill(color);
                                        intersectionPoint[row + 1][column].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row][column - 1].getPlayer() == null) {
                                        intersectionPoint[row][column - 1].setFill(color);
                                        intersectionPoint[row][column - 1].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row][column + 1].getPlayer() == null) {
                                        intersectionPoint[row][column + 1].setFill(color);
                                        intersectionPoint[row][column + 1].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row + 1][column + 1].getPlayer() == null) {
                                        intersectionPoint[row + 1][column + 1].setFill(color);
                                        intersectionPoint[row + 1][column + 1].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row + 1][column - 1].getPlayer() == null) {
                                        intersectionPoint[row + 1][column - 1].setFill(color);
                                        intersectionPoint[row + 1][column - 1].setOpacity(0.5);
                                    }
                                }
                                case 4 -> {
                                    if (intersectionPoint[row + 1][column].getPlayer() == null) {
                                        intersectionPoint[row + 1][column].setFill(color);
                                        intersectionPoint[row + 1][column].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row + 1][column - 1].getPlayer() == null) {
                                        intersectionPoint[row + 1][column - 1].setFill(color);
                                        intersectionPoint[row + 1][column - 1].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row][column - 1].getPlayer() == null) {
                                        intersectionPoint[row][column - 1].setFill(color);
                                        intersectionPoint[row][column - 1].setOpacity(0.5);
                                    }
                                }
                            }
                        }

                        case 1, 3 -> {
                            switch (column) {
                                case 0 -> {
                                    if (intersectionPoint[row - 1][column].getPlayer() == null) {
                                        intersectionPoint[row - 1][column].setFill(color);
                                        intersectionPoint[row - 1][column].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row + 1][column].getPlayer() == null) {
                                        intersectionPoint[row + 1][column].setFill(color);
                                        intersectionPoint[row + 1][column].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row][column + 1].getPlayer() == null) {
                                        intersectionPoint[row][column + 1].setFill(color);
                                        intersectionPoint[row][column + 1].setOpacity(0.5);
                                    }
                                }
                                case 1, 3 -> {
                                    if (intersectionPoint[row - 1][column].getPlayer() == null) {
                                        intersectionPoint[row - 1][column].setFill(color);
                                        intersectionPoint[row - 1][column].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row + 1][column].getPlayer() == null) {
                                        intersectionPoint[row + 1][column].setFill(color);
                                        intersectionPoint[row + 1][column].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row][column + 1].getPlayer() == null) {
                                        intersectionPoint[row][column + 1].setFill(color);
                                        intersectionPoint[row][column + 1].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row][column - 1].getPlayer() == null) {
                                        intersectionPoint[row][column - 1].setFill(color);
                                        intersectionPoint[row][column - 1].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row - 1][column - 1].getPlayer() == null) {
                                        intersectionPoint[row - 1][column - 1].setFill(color);
                                        intersectionPoint[row - 1][column - 1].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row + 1][column + 1].getPlayer() == null) {
                                        intersectionPoint[row + 1][column + 1].setFill(color);
                                        intersectionPoint[row + 1][column + 1].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row + 1][column - 1].getPlayer() == null) {
                                        intersectionPoint[row + 1][column - 1].setFill(color);
                                        intersectionPoint[row + 1][column - 1].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row - 1][column + 1].getPlayer() == null) {
                                        intersectionPoint[row - 1][column + 1].setFill(color);
                                        intersectionPoint[row - 1][column + 1].setOpacity(0.5);
                                    }
                                }
                                case 2 -> {
                                    if (intersectionPoint[row - 1][column].getPlayer() == null) {
                                        intersectionPoint[row - 1][column].setFill(color);
                                        intersectionPoint[row - 1][column].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row + 1][column].getPlayer() == null) {
                                        intersectionPoint[row + 1][column].setFill(color);
                                        intersectionPoint[row + 1][column].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row][column + 1].getPlayer() == null) {
                                        intersectionPoint[row][column + 1].setFill(color);
                                        intersectionPoint[row][column + 1].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row][column - 1].getPlayer() == null) {
                                        intersectionPoint[row][column - 1].setFill(color);
                                        intersectionPoint[row][column - 1].setOpacity(0.5);
                                    }
                                }

                                case 4 -> {
                                    if (intersectionPoint[row - 1][column].getPlayer() == null) {
                                        intersectionPoint[row - 1][column].setFill(color);
                                        intersectionPoint[row - 1][column].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row + 1][column].getPlayer() == null) {
                                        intersectionPoint[row + 1][column].setFill(color);
                                        intersectionPoint[row + 1][column].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row][column - 1].getPlayer() == null) {
                                        intersectionPoint[row][column - 1].setFill(color);
                                        intersectionPoint[row][column - 1].setOpacity(0.5);
                                    }
                                }
                            }
                        }
                        case 2 -> {
                            switch (column) {
                                case 0 -> {
                                    if (intersectionPoint[row - 1][column].getPlayer() == null) {
                                        intersectionPoint[row - 1][column].setFill(color);
                                        intersectionPoint[row - 1][column].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row + 1][column].getPlayer() == null) {
                                        intersectionPoint[row + 1][column].setFill(color);
                                        intersectionPoint[row + 1][column].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row][column + 1].getPlayer() == null) {
                                        intersectionPoint[row][column + 1].setFill(color);
                                        intersectionPoint[row][column + 1].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row - 1][column + 1].getPlayer() == null) {
                                        intersectionPoint[row - 1][column + 1].setFill(color);
                                        intersectionPoint[row - 1][column + 1].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row + 1][column + 1].getPlayer() == null) {
                                        intersectionPoint[row + 1][column + 1].setFill(color);
                                        intersectionPoint[row + 1][column + 1].setOpacity(0.5);
                                    }
                                }
                                case 1, 3 -> {
                                    if (intersectionPoint[row - 1][column].getPlayer() == null) {
                                        intersectionPoint[row - 1][column].setFill(color);
                                        intersectionPoint[row - 1][column].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row + 1][column].getPlayer() == null) {
                                        intersectionPoint[row + 1][column].setFill(color);
                                        intersectionPoint[row + 1][column].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row][column + 1].getPlayer() == null) {
                                        intersectionPoint[row][column + 1].setFill(color);
                                        intersectionPoint[row][column + 1].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row][column - 1].getPlayer() == null) {
                                        intersectionPoint[row][column - 1].setFill(color);
                                        intersectionPoint[row][column - 1].setOpacity(0.5);
                                    }
                                }
                                case 2 -> {
                                    if (intersectionPoint[row - 1][column].getPlayer() == null) {
                                        intersectionPoint[row - 1][column].setFill(color);
                                        intersectionPoint[row - 1][column].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row + 1][column].getPlayer() == null) {
                                        intersectionPoint[row + 1][column].setFill(color);
                                        intersectionPoint[row + 1][column].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row][column + 1].getPlayer() == null) {
                                        intersectionPoint[row][column + 1].setFill(color);
                                        intersectionPoint[row][column + 1].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row][column - 1].getPlayer() == null) {
                                        intersectionPoint[row][column - 1].setFill(color);
                                        intersectionPoint[row][column - 1].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row - 1][column - 1].getPlayer() == null) {
                                        intersectionPoint[row - 1][column - 1].setFill(color);
                                        intersectionPoint[row - 1][column - 1].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row + 1][column + 1].getPlayer() == null) {
                                        intersectionPoint[row + 1][column + 1].setFill(color);
                                        intersectionPoint[row + 1][column + 1].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row + 1][column - 1].getPlayer() == null) {
                                        intersectionPoint[row + 1][column - 1].setFill(color);
                                        intersectionPoint[row + 1][column - 1].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row - 1][column + 1].getPlayer() == null) {
                                        intersectionPoint[row - 1][column + 1].setFill(color);
                                        intersectionPoint[row - 1][column + 1].setOpacity(0.5);
                                    }
                                }
                                case 4 -> {
                                    if (intersectionPoint[row - 1][column].getPlayer() == null) {
                                        intersectionPoint[row - 1][column].setFill(color);
                                        intersectionPoint[row - 1][column].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row + 1][column].getPlayer() == null) {
                                        intersectionPoint[row + 1][column].setFill(color);
                                        intersectionPoint[row + 1][column].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row][column - 1].getPlayer() == null) {
                                        intersectionPoint[row][column - 1].setFill(color);
                                        intersectionPoint[row][column - 1].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row - 1][column - 1].getPlayer() == null) {
                                        intersectionPoint[row - 1][column - 1].setFill(color);
                                        intersectionPoint[row - 1][column - 1].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row + 1][column - 1].getPlayer() == null) {
                                        intersectionPoint[row + 1][column - 1].setFill(color);
                                        intersectionPoint[row + 1][column - 1].setOpacity(0.5);
                                    }
                                }
                            }
                        }
                        case 4 -> {
                            switch (column) {
                                case 0 -> {
                                    if (intersectionPoint[row][column + 1].getPlayer() == null) {
                                        intersectionPoint[row][column + 1].setFill(color);
                                        intersectionPoint[row][column + 1].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row - 1][column].getPlayer() == null) {
                                        intersectionPoint[row - 1][column].setFill(color);
                                        intersectionPoint[row - 1][column].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row - 1][column + 1].getPlayer() == null) {
                                        intersectionPoint[row - 1][column + 1].setFill(color);
                                        intersectionPoint[row - 1][column + 1].setOpacity(0.5);
                                    }
                                }
                                case 1, 3 -> {
                                    if (intersectionPoint[row - 1][column].getPlayer() == null) {
                                        intersectionPoint[row - 1][column].setFill(color);
                                        intersectionPoint[row - 1][column].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row][column - 1].getPlayer() == null) {
                                        intersectionPoint[row][column - 1].setFill(color);
                                        intersectionPoint[row][column - 1].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row][column + 1].getPlayer() == null) {
                                        intersectionPoint[row][column + 1].setFill(color);
                                        intersectionPoint[row][column + 1].setOpacity(0.5);
                                    }
                                }
                                case 2 -> {
                                    if (intersectionPoint[row - 1][column].getPlayer() == null) {
                                        intersectionPoint[row - 1][column].setFill(color);
                                        intersectionPoint[row - 1][column].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row][column - 1].getPlayer() == null) {
                                        intersectionPoint[row][column - 1].setFill(color);
                                        intersectionPoint[row][column - 1].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row][column + 1].getPlayer() == null) {
                                        intersectionPoint[row][column + 1].setFill(color);
                                        intersectionPoint[row][column + 1].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row - 1][column + 1].getPlayer() == null) {
                                        intersectionPoint[row - 1][column + 1].setFill(color);
                                        intersectionPoint[row - 1][column + 1].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row - 1][column - 1].getPlayer() == null) {
                                        intersectionPoint[row - 1][column - 1].setFill(color);
                                        intersectionPoint[row - 1][column - 1].setOpacity(0.5);
                                    }
                                }
                                case 4 -> {
                                    if (intersectionPoint[row - 1][column].getPlayer() == null) {
                                        intersectionPoint[row - 1][column].setFill(color);
                                        intersectionPoint[row - 1][column].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row - 1][column - 1].getPlayer() == null) {
                                        intersectionPoint[row - 1][column - 1].setFill(color);
                                        intersectionPoint[row - 1][column - 1].setOpacity(0.5);
                                    }
                                    if (intersectionPoint[row][column - 1].getPlayer() == null) {
                                        intersectionPoint[row][column - 1].setFill(color);
                                        intersectionPoint[row][column - 1].setOpacity(0.5);
                                    }
                                }
                            }
                        }
                    }
                    tmpRow = row;
                    tmpColumn = column;
                    kernel = 1;
                }
            }
        } else {
            if(intersectionPoint[row][column].getOpacity() == 0.5) {
                intersectionPoint[row][column].setPlayer(intersectionPoint[tmpRow][tmpColumn].getPlayer());
                intersectionPoint[tmpRow][tmpColumn].setPlayer(null);
                if (intersectionPoint[row][column].getPlayer().getSide() == PlayerSide.RED) {
                    intersectionPoint[row][column].setFill(Color.RED);
                } else if (intersectionPoint[row][column].getPlayer().getSide() == PlayerSide.BLUE){
                    intersectionPoint[row][column].setFill(Color.BLUE);
                }
                intersectionPoint[row][column].setOpacity(1);
                turn = !turn;
                glowEffect();
            }
            for(int i = 0;i<5;i++) {
                for(int j = 0;j<5;j++) {
                    if(intersectionPoint[i][j].getPlayer() == null) {
                        intersectionPoint[i][j].setOpacity(0);
                    }
                }
            }
            kernel = 0;
        }

    }

    @FXML
    protected void glowEffect() {
        if(!turn) {
            player1Card.setStyle("-fx-effect:  dropshadow(three-pass-box, rgba(255,0,0,1), 10, 0, 0, 0);-fx-background-color:  #b1aebb;-fx-background-radius: 1em");
            player2Card.setStyle("-fx-effect:  dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);-fx-background-color:  #b1aebb;-fx-background-radius: 1em");
        } else {
            player2Card.setStyle("-fx-effect:  dropshadow(three-pass-box, rgba(0,0,255,1), 10, 0, 0, 0);-fx-background-color:  #b1aebb;-fx-background-radius: 1em");
            player1Card.setStyle("-fx-effect:  dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);-fx-background-color:  #b1aebb;-fx-background-radius: 1em");
        }
    }


}