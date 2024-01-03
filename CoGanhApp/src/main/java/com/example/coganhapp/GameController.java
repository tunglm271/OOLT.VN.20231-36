package com.example.coganhapp;

import com.example.coganhapp.game.Move;
import com.example.coganhapp.window.ExitDialog;
import com.example.coganhapp.window.WinPopUp;
import com.example.coganhapp.window.surrenderPopUp;
import com.example.coganhapp.game.Piece;
import com.example.coganhapp.game.Player;
import com.example.coganhapp.game.PlayerSide;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;
import java.util.Vector;

public class GameController {

    private Player player1 = new Player("Player 1", PlayerSide.RED,0,0);
    private Player player2 = new Player("Player 2", PlayerSide.BLUE,0,0);

    private int n1 = 8, n2 = 8;

    private boolean turn = false;

    private int kernel = 0;
    //dung 4 bien nay de dieu khien

    private int tmpRow;
    private int tmpColumn;
    private int helpKernel = 0;

    private int historyKernel = 0;

    private boolean checkNumber = true;

    private Vector<Piece[][]> undoList= new Vector<>();

    @FXML
    private AnchorPane ancherRoot;

    @FXML
    private AnchorPane gameBroad;

    @FXML
    private TextField inputName1;

    @FXML
    private TextField inputName2;

    @FXML
    protected Label player1name;

    @FXML
    protected Label player2name;

    @FXML
    protected Label player1Score;

    @FXML
    protected Label player1Win;

    @FXML
    protected Label player2Lose;

    @FXML
    protected Label player2Win;

    @FXML
    protected Label player1Lose;

    @FXML
    protected Label player2Score;

    @FXML
    protected Piece[][] intersectionPoint = new Piece[5][5];

    @FXML
    private HBox surrenderButton;
    @FXML
    private Label surrenderText;


    @FXML
    private HBox playBtn;

    @FXML
    private HBox helpButton;
    @FXML
    private HBox historyBtn;


    @FXML
    private VBox player1Card;

    @FXML
    private VBox player2Card;
    @FXML
    private HBox undoBtn;
    @FXML
    private Label undoText;

    @FXML
    protected void undoEnterEffect() {
        undoBtn.setCursor(Cursor.HAND);
        undoText.setTextFill(Color.rgb(0,204,255));
        undoBtn.setStyle("-fx-background-color: white;-fx-background-radius: 2em;" );
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.15),undoBtn);
        scaleTransition.setToY(1.2);
        scaleTransition.setToX(1.2);
        scaleTransition.play();
    }

    @FXML
    protected void undoLeaveEffet() {
        undoBtn.setCursor(Cursor.HAND);
        undoText.setTextFill(Color.WHITE);
        undoBtn.setStyle("-fx-background-color: #00CCFF;-fx-background-radius: 2em;" );
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.15),undoBtn);
        scaleTransition.setToY(1);
        scaleTransition.setToX(1);
        scaleTransition.play();
    }
    @FXML
    protected void mouseEnterEffect() {
        surrenderButton.setCursor(Cursor.HAND);
        surrenderText.setTextFill(Color.rgb(255,31,69));
        surrenderButton.setStyle("-fx-background-color: white;-fx-background-radius: 2em;" );
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.15),surrenderButton);
        scaleTransition.setToY(1.2);
        scaleTransition.setToX(1.2);
        scaleTransition.play();
    }

    @FXML
    protected void mouseLeaveEffect() {
        surrenderText.setTextFill(Color.WHITE);
        surrenderButton.setStyle("-fx-background-color:  #ff1f45;-fx-background-radius: 2em;");
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.15),surrenderButton);
        scaleTransition.setToY(1);
        scaleTransition.setToX(1);
        scaleTransition.play();
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
        surrenderPopUp.display(turn,player1name.getText(), player2name.getText());
        if (turn) {
            player1.win += 1;
            player2.lose += 1;
            player1Win.setText("Win: " + player1.win);
            player2Lose.setText("Lose: " + player2.lose);
        } else {
            player2.win += 1;
            player1.lose += 1;
            player2Win.setText("Win: " + player2.win);
            player1Lose.setText("Lose: " + player1.lose);
        }
        turn = false;
        player1Score.setText("8");
        player2Score.setText("8");
        reset();
    }

    @FXML
    protected void clickUndo() {
        Move.Undo(undoList, intersectionPoint);
        turn = !turn;
        glowEffect();
        getBoard();
    }

    @FXML
    protected void clickOptionButton(MouseEvent event) throws IOException {
        if(event.getSource() == playBtn) {
            if(helpKernel == 1) {
                ancherRoot.getChildren().remove(ancherRoot.getChildren().size()-1);
                helpKernel = 0;
            } else if(historyKernel == 1) {
                ancherRoot.getChildren().remove(ancherRoot.getChildren().size()-1);
                historyKernel = 0;
            }
        } else if(event.getSource() == helpButton){
            if(helpKernel == 0) {
                if (historyKernel == 1) {
                    ancherRoot.getChildren().remove(ancherRoot.getChildren().size()-1);
                    historyKernel = 0;
                }
                Parent root =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HelpUI.fxml")));
                ancherRoot.getChildren().add(root);
                helpKernel = 1;
            }
        } else {
            if (historyKernel == 0) {
                if (helpKernel == 1) {
                    ancherRoot.getChildren().remove(ancherRoot.getChildren().size() - 1);
                    helpKernel = 0;
                }
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("history.fxml")));
                ancherRoot.getChildren().add(root);
                historyKernel = 1;
            }
        }
    }

    @FXML
    protected void clickExitButton() {
        ExitDialog.display(player1,player2);
    }

    @FXML
    protected void enterPlayerName(ActionEvent event) {
        if(event.getSource() == inputName1) {
            player1name.setText(inputName1.getText());
            player1.setPlayerName(inputName1.getText());
        } else {
            player2name.setText(inputName2.getText());
            player2.setPlayerName(inputName2.getText());
        }
    }
    @FXML
    public  void initialize() {
        FadeTransition animation = new FadeTransition();
        animation.setDuration(Duration.seconds(1));
        animation.setNode(player1Card);
        animation.setFromValue(0);
        animation.setToValue(1);
        player1Score.setText(String.valueOf(n1));

        FadeTransition animation2 = new FadeTransition();
        animation2.setDuration(Duration.seconds(1));
        animation2.setNode(player2Card);
        animation2.setFromValue(0);
        animation2.setToValue(1);
        player2Score.setText(String.valueOf(n2));

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
                        makeMove(row,columm);
                    }
                });
            }
        }
    }

    private void copyintersectionPoint() {
        Piece[][] tmp = new Piece[5][5];
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                tmp[i][j] = new Piece(i,j,null);
        for(int i = 0;i<5;i++) {
            for(int j = 0;j<5;j++) {
                if (intersectionPoint[i][j].getPlayer() != null) {
                    tmp[i][j].setPlayer(intersectionPoint[i][j].getPlayer());
                    tmp[i][j].setFill(intersectionPoint[i][j].getFill());
                    tmp[i][j].setOpacity(1);
                } else {
                    tmp[i][j].setPlayer(null);
                    tmp[i][j].setFill(Color.BLUE);
                    tmp[i][j].setOpacity(0);
                }
            }
        }
        if (checkNumber) {
            undoList.add(tmp);
            checkNumber = false;
        } else {
            checkNumber = true;
        }
    }

    private void makeMove(int row, int column) {
        copyintersectionPoint();
        if(kernel == 0) {
            if(intersectionPoint[row][column].getPlayer() != null) {
                if ((turn && intersectionPoint[row][column].getPlayer() == player2) || (!turn && intersectionPoint[row][column].getPlayer() == player1)) {
                    Paint color = intersectionPoint[row][column].getFill();
                    switch (row) {
                        case 0 -> {
                            switch (column) {
                                case 0 -> {
                                    fade(intersectionPoint[row][column+1],color);
                                    fade(intersectionPoint[row+1][column],color);
                                    fade(intersectionPoint[row+1][column+1],color);
                                }
                                case 1, 3 -> {
                                    fade(intersectionPoint[row+1][column],color);
                                    fade(intersectionPoint[row][column-1],color);
                                    fade(intersectionPoint[row][column+1],color);
                                }
                                case 2 -> {
                                    fade(intersectionPoint[row+1][column],color);
                                    fade(intersectionPoint[row][column-1],color);
                                    fade(intersectionPoint[row][column+1],color);
                                    fade(intersectionPoint[row+1][column+1],color);
                                    fade(intersectionPoint[row+1][column-1],color);
                                }
                                case 4 -> {
                                    fade(intersectionPoint[row+1][column],color);
                                    fade(intersectionPoint[row+1][column-1],color);
                                    fade(intersectionPoint[row][column-1],color);
                                }
                            }
                        }

                        case 1, 3 -> {
                            switch (column) {
                                case 0 -> {
                                    fade(intersectionPoint[row-1][column],color);
                                    fade(intersectionPoint[row+1][column],color);
                                    fade(intersectionPoint[row][column+1],color);
                                }
                                case 1, 3 -> {
                                    fade(intersectionPoint[row-1][column],color);
                                    fade(intersectionPoint[row+1][column],color);
                                    fade(intersectionPoint[row][column+1],color);
                                    fade(intersectionPoint[row][column-1],color);
                                    fade(intersectionPoint[row-1][column-1],color);
                                    fade(intersectionPoint[row+1][column+1],color);
                                    fade(intersectionPoint[row+1][column-1],color);
                                    fade(intersectionPoint[row-1][column+1],color);
                                }
                                case 2 -> {
                                    fade(intersectionPoint[row-1][column],color);
                                    fade(intersectionPoint[row+1][column],color);
                                    fade(intersectionPoint[row][column+1],color);
                                    fade(intersectionPoint[row][column-1],color);
                                }

                                case 4 -> {
                                    fade(intersectionPoint[row-1][column],color);
                                    fade(intersectionPoint[row+1][column],color);
                                    fade(intersectionPoint[row][column-1],color);
                                }
                            }
                        }
                        case 2 -> {
                            switch (column) {
                                case 0 -> {
                                    fade(intersectionPoint[row-1][column],color);
                                    fade(intersectionPoint[row+1][column],color);
                                    fade(intersectionPoint[row][column+1],color);
                                    fade(intersectionPoint[row-1][column+1],color);
                                    fade(intersectionPoint[row+1][column+1],color);
                                }
                                case 1, 3 -> {
                                    fade(intersectionPoint[row-1][column],color);
                                    fade(intersectionPoint[row+1][column],color);
                                    fade(intersectionPoint[row][column+1],color);
                                    fade(intersectionPoint[row][column-1],color);
                                }
                                case 2 -> {
                                    fade(intersectionPoint[row-1][column],color);
                                    fade(intersectionPoint[row+1][column],color);
                                    fade(intersectionPoint[row][column+1],color);
                                    fade(intersectionPoint[row][column-1],color);
                                    fade(intersectionPoint[row-1][column-1],color);
                                    fade(intersectionPoint[row+1][column+1],color);
                                    fade(intersectionPoint[row+1][column-1],color);
                                    fade(intersectionPoint[row-1][column+1],color);
                                }
                                case 4 -> {
                                    fade(intersectionPoint[row-1][column],color);
                                    fade(intersectionPoint[row+1][column],color);
                                    fade(intersectionPoint[row][column-1],color);
                                    fade(intersectionPoint[row-1][column-1],color);
                                    fade(intersectionPoint[row+1][column-1],color);
                                }
                            }
                        }
                        case 4 -> {
                            switch (column) {
                                case 0 -> {
                                    fade(intersectionPoint[row][column+1],color);
                                    fade(intersectionPoint[row-1][column],color);
                                    fade(intersectionPoint[row-1][column+1],color);
                                }
                                case 1, 3 -> {
                                    fade(intersectionPoint[row-1][column],color);
                                    fade(intersectionPoint[row][column -1],color);
                                    fade(intersectionPoint[row][column+1],color);
                                }
                                case 2 -> {
                                    fade(intersectionPoint[row-1][column],color);
                                    fade(intersectionPoint[row][column-1],color);
                                    fade(intersectionPoint[row][column+1],color);
                                    fade(intersectionPoint[row-1][column+1],color);
                                    fade(intersectionPoint[row-1][column-1],color);
                                }
                                case 4 -> {
                                    fade(intersectionPoint[row-1][column],color);
                                    fade(intersectionPoint[row-1][column-1],color);
                                    fade(intersectionPoint[row][column-1],color);
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
                ///viet ham xem an  nhau nhu nao
                switch (row) {
                    case 0, 4 -> {
                        switch (column) {
                            case 1, 2, 3 -> {
                                makeGanh(intersectionPoint[row][column], intersectionPoint[row][column-1], intersectionPoint[row][column+1]);
                            }
                        }
                    }
                    case 1, 3 -> {
                        switch (column) {
                            case 0, 4 -> {
                                makeGanh(intersectionPoint[row][column], intersectionPoint[row+1][column], intersectionPoint[row-1][column]);
                            }
                            case 2 -> {
                                makeGanh(intersectionPoint[row][column], intersectionPoint[row+1][column], intersectionPoint[row-1][column]);
                                makeGanh(intersectionPoint[row][column], intersectionPoint[row][column-1], intersectionPoint[row][column+1]);
                            }
                            case 1, 3 -> {
                                makeGanh(intersectionPoint[row][column], intersectionPoint[row+1][column], intersectionPoint[row-1][column]);
                                makeGanh(intersectionPoint[row][column], intersectionPoint[row][column-1], intersectionPoint[row][column+1]);
                                makeGanh(intersectionPoint[row][column], intersectionPoint[row-1][column-1], intersectionPoint[row+1][column+1]);
                                makeGanh(intersectionPoint[row][column], intersectionPoint[row+1][column-1], intersectionPoint[row-1][column+1]);
                            }
                        }
                    }
                    case 2 -> {
                        switch (column) {
                            case 0, 4 -> {
                                makeGanh(intersectionPoint[row][column], intersectionPoint[row+1][column], intersectionPoint[row-1][column]);
                            }
                            case 1, 3 -> {
                                makeGanh(intersectionPoint[row][column], intersectionPoint[row+1][column], intersectionPoint[row-1][column]);
                                makeGanh(intersectionPoint[row][column], intersectionPoint[row][column-1], intersectionPoint[row][column+1]);
                            }
                            case 2 -> {
                                makeGanh(intersectionPoint[row][column], intersectionPoint[row+1][column], intersectionPoint[row-1][column]);
                                makeGanh(intersectionPoint[row][column], intersectionPoint[row][column-1], intersectionPoint[row][column+1]);
                                makeGanh(intersectionPoint[row][column], intersectionPoint[row-1][column-1], intersectionPoint[row+1][column+1]);
                                makeGanh(intersectionPoint[row][column], intersectionPoint[row+1][column-1], intersectionPoint[row-1][column+1]);
                            }
                        }
                    }
                }

                makeVay();

                getBoard();

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

    protected void getBoard() {
        n1 = 0; n2 = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (intersectionPoint[i][j].getPlayer() == player1)
                    n1++;
                if (intersectionPoint[i][j].getPlayer() == player2)
                    n2++;
            }
        }

        player1Score.setText(String.valueOf(n1));
        player2Score.setText(String.valueOf(n2));
        if (n1 == 16) {
            player1.win += 1;
            player2.lose += 1;
            player1Win.setText("Win: " + player1.win);
            player2Lose.setText("Lose: " + player2.lose);
            WinPopUp.display(player1name.getText());
            player1Score.setText("8");
            player2Score.setText("8");
            reset();
        }
        if (n2 == 16) {
            player2.win += 1;
            player1.lose += 1;
            player2Win.setText("Win: " + player2.win);
            player1Lose.setText("Lose: " + player1.lose);
            WinPopUp.display(player1name.getText());
            player1Score.setText("8");
            player2Score.setText("8");
            reset();
        }
    }

    protected void makeVay() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (intersectionPoint[i][j].getPlayer() != null) {
                    switch (i) {
                        case 0 -> {
                            switch (j) {
                                case 0 -> {
                                    if (checkVay(i, j, i, j+1) && checkVay(i, j, i+1, j) && checkVay(i, j, i+1, j+1))
                                        changeColor(intersectionPoint[i][j]);
                                }
                                case 1, 3 -> {
                                    if (checkVay(i, j, i, j+1) && checkVay(i, j, i+1, j) && checkVay(i, j, i, j-1))
                                        changeColor(intersectionPoint[i][j]);
                                }
                                case 2 -> {
                                    if (checkVay(i, j, i+1, j) && checkVay(i, j, i, j-1) && checkVay(i, j, i, j+1) && checkVay(i, j, i+1, j+1) && checkVay(i, j, i+1, j-1))
                                        changeColor(intersectionPoint[i][j]);
                                }
                                case 4 -> {
                                    if (checkVay(i, j, i+1, j) && checkVay(i, j, i+1, j-1) && checkVay(i, j, i, j-1))
                                        changeColor(intersectionPoint[i][j]);
                                }
                            }
                        }
                        case 1, 3 -> {
                            switch (j) {
                                case 0 -> {
                                    if (checkVay(i, j, i-1, j) && checkVay(i, j, i+1, j) && checkVay(i, j, i, j+1))
                                        changeColor(intersectionPoint[i][j]);
                                }
                                case 1, 3 -> {
                                    if (checkVay(i, j, i-1, j) && checkVay(i, j, i+1, j) && checkVay(i, j,i,j+1)&&checkVay(i,j,i,j-1)&&checkVay(i,j,i-1,j-1)&&checkVay(i,j,i+1,j+1)&&checkVay(i,j,i+1,j-1)&&checkVay(i,j,i-1,j+1))
                                        changeColor(intersectionPoint[i][j]);
                                }
                                case 2 -> {
                                    if (checkVay(i,j,i-1,j)&&checkVay(i,j,i+1,j)&&checkVay(i,j,i,j+1)&&checkVay(i,j,i,j-1))
                                        changeColor(intersectionPoint[i][j]);
                                }
                                case 4 -> {
                                    if (checkVay(i,j,i-1,j)&&checkVay(i,j,i+1,j)&&checkVay(i,j,i,j-1))
                                        changeColor(intersectionPoint[i][j]);
                                }
                            }
                        }
                        case 2 -> {
                            switch (j) {
                                case 0 -> {
                                    if (checkVay(i,j,i-1,j)&&checkVay(i,j,i+1,j)&&checkVay(i,j,i,j+1)&&checkVay(i,j,i-1,j+1)&&checkVay(i,j,i+1,j+1))
                                        changeColor(intersectionPoint[i][j]);
                                }
                                case 1, 3 -> {
                                    if (checkVay(i,j,i-1,j)&&checkVay(i,j,i+1,j)&&checkVay(i,j,i,j+1)&&checkVay(i,j,i,j-1))
                                        changeColor(intersectionPoint[i][j]);
                                }
                                case 2 -> {
                                    if (checkVay(i, j, i-1, j) && checkVay(i, j, i+1, j) && checkVay(i, j,i,j+1)&&checkVay(i,j,i,j-1)&&checkVay(i,j,i-1,j-1)&&checkVay(i,j,i+1,j+1)&&checkVay(i,j,i+1,j-1)&&checkVay(i,j,i-1,j+1))
                                        changeColor(intersectionPoint[i][j]);
                                }
                                case 4 -> {
                                    if (checkVay(i,j,i-1,j)&&checkVay(i,j,i+1,j)&&checkVay(i,j,i,j-1)&&checkVay(i,j,i-1,j-1)&&checkVay(i,j,i+1,j-1))
                                        changeColor(intersectionPoint[i][j]);
                                }
                            }
                        }
                        case 4 -> {
                            switch (j) {
                                case 0 -> {
                                    if (checkVay(i,j,i,j+1)&&checkVay(i,j,i-1,j)&&checkVay(i,j,i-1,j+1))
                                        changeColor(intersectionPoint[i][j]);
                                }
                                case 1, 3 -> {
                                    if (checkVay(i,j,i-1,j)&&checkVay(i,j,i,j-1)&&checkVay(i,j,i,j+1))
                                        changeColor(intersectionPoint[i][j]);
                                }
                                case 2 -> {
                                    if(checkVay(i,j,i-1,j)&&checkVay(i,j,i,j-1)&&checkVay(i,j,i,j+1)&&checkVay(i,j,i-1,j-1)&&checkVay(i,j,i-1,j-1))
                                        changeColor(intersectionPoint[i][j]);
                                }
                                case 4 -> {
                                    if (checkVay(i,j,i-1,j)&&checkVay(i,j,i-1,j-1)&&checkVay(i,j,i,j-1))
                                        changeColor(intersectionPoint[i][j]);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    protected boolean checkVay(int i, int j, int m, int n) {
        if (intersectionPoint[m][n].getPlayer() == null)
            return false;
        if (intersectionPoint[i][j].getPlayer() != intersectionPoint[m][n].getPlayer())
            return true;
        return false;
    }

    protected void changeColor(Piece piece) {
        if (piece.getPlayer().getSide() == PlayerSide.RED) {
            piece.setFill(Color.BLUE);
            piece.setPlayer(player2);
            piece.setOpacity(1);
        } else if (piece.getPlayer().getSide() == PlayerSide.BLUE) {
            piece.setFill(Color.RED);
            piece.setPlayer(player1);
            piece.setOpacity(1);
        }
    }

    protected void makeGanh(Piece piece1, Piece piece2, Piece piece3) {
        if (piece1.getPlayer() != piece2.getPlayer() && piece1.getPlayer() != piece3.getPlayer() && piece2.getPlayer() != null && piece3.getPlayer() != null) {
            piece2.setPlayer(piece1.getPlayer());
            piece3.setPlayer(piece1.getPlayer());
            piece2.setFill(piece1.getFill());
            piece3.setFill(piece1.getFill());
            piece2.setOpacity(1);
            piece3.setOpacity(1);
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

    protected void fade(Piece piece, Paint paint) {
        if(piece.getPlayer() == null) {
            piece.setFill(paint);
            piece.setOpacity(0.5);
        }
    }
    protected void reset() {
        for(int i = 0;i<5;i++) {
            for(int j = 0;j<5;j++) {
                if(i == 0 || ((i == 1 || i == 2) && j == 4) || (i == 1 && j == 0)) {
                    intersectionPoint[i][j].setPlayer(player1);
                    intersectionPoint[i][j].setFill(Color.RED);
                    intersectionPoint[i][j].setOpacity(1);
                } else if(i == 4 || ((i == 2 || i == 3) && j == 0) || (i == 3 && j == 4)) {
                    intersectionPoint[i][j].setPlayer(player2);
                    intersectionPoint[i][j].setFill(Color.BLUE);
                    intersectionPoint[i][j].setOpacity(1);
                } else {
                    intersectionPoint[i][j].setPlayer(null);
                    intersectionPoint[i][j].setOpacity(0);
                }
            }
        }
        turn = false;
        glowEffect();
    }



}