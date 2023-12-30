package com.example.coganhapp.game;

import com.example.coganhapp.broad.Piece;

import java.util.LinkedList;

public class Player {
    private String playerName;
    private PlayerSide side;
    private int win;
    private int lose;

    public Player(String name, PlayerSide side, int win ,int lose) {
        this.playerName = name;
        this.side = side;
        this.win = win;
        this.lose = lose;
    }

    public PlayerSide getSide() {
        return side;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
