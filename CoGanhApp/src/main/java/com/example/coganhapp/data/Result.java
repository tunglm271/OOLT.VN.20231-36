package com.example.coganhapp.data;

public class Result {
    private String player1name;
    private String player2name;
    private int player1score;
    private int player2score;

    public Result(String player1name, String player2name, int player1score, int player2score) {
        this.player1name = player1name;
        this.player2name = player2name;
        this.player1score = player1score;
        this.player2score = player2score;
    }

    public String getPlayer1name() {
        return player1name;
    }

    public String getPlayer2name() {
        return player2name;
    }

    public int getPlayer1score() {
        return player1score;
    }

    public int getPlayer2score() {
        return player2score;
    }
}
