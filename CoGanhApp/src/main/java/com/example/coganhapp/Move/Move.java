package com.example.coganhapp.Move;

import com.example.coganhapp.broad.Piece;
import javafx.scene.paint.Color;

import java.util.Vector;

public class Move {
    public static void Undo(Vector<Piece[][]> undoList, Piece[][] intersectionPoint) {
        if (undoList.size() != 0) {
            for(int i = 0;i<5;i++) {
                for(int j = 0;j<5;j++) {
                    if (undoList.get(undoList.size()-1)[i][j].getPlayer() != null) {
                        intersectionPoint[i][j].setPlayer(undoList.get(undoList.size()-1)[i][j].getPlayer());
                        intersectionPoint[i][j].setFill(undoList.get(undoList.size()-1)[i][j].getFill());
                        intersectionPoint[i][j].setOpacity(1);
                    } else {
                        intersectionPoint[i][j].setPlayer(null);
                        intersectionPoint[i][j].setFill(Color.BLUE);
                        intersectionPoint[i][j].setOpacity(0);
                    }
                }
            }
            undoList.remove(undoList.size()-1);
        }
    }
}
