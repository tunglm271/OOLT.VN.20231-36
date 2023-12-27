package com.example.coganhapp.game;

import com.example.coganhapp.broad.Piece;

import java.util.LinkedList;

public class Player {
    private String playerName;
    private PlayerSide side;

    public Player(String name, PlayerSide side) {
        this.playerName = name;
        this.side = side;
    }

    public PlayerSide getSide() {
        return side;
    }
}
