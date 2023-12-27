package com.example.coganhapp.broad;

import com.example.coganhapp.game.Player;
import com.example.coganhapp.game.PlayerSide;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Piece extends Circle {
    private int row;
    private int column;

    private Player player;

    public Piece(int row, int column, Player player) {
        super(80+80*column,113+80*row,10);
        if(player == null) {
            setOpacity(0);
        } else {
            if (player.getSide() == PlayerSide.RED) {
                setFill(Color.RED);
            } else {
                setFill(Color.BLUE);
            }
        }
        this.row = row;
        this.column = column;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
