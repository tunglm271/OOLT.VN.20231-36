package com.example.coganhapp.data;

import javafx.collections.FXCollections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class DataReader {
    private static DataReader instance = new DataReader();
    private static String filename = "historyData.txt";

    private List<Result> historyData;

    private DataReader() {}

    public static DataReader getInstance() {
        return instance;
    }

    public List<Result> getHistory() {
        return historyData;
    }

    public void setHistoryData(List<Result> historyData) {
        this.historyData = historyData;
    }

    public void loadHistory() throws IOException {

        historyData = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);

        String input;

        try {
            while ((input = br.readLine()) != null) {
                String[] DataPieces = input.split("\t");

                String firstPlayerName = DataPieces[0];
                String secondPlayerName = DataPieces[1];
                String firstPlayerScore = DataPieces[2];
                String secondPlayerScore = DataPieces[3];
                Result result = new Result(firstPlayerName,secondPlayerName,
                        Integer.parseInt(firstPlayerScore),Integer.parseInt(secondPlayerScore));
                historyData.add(result);
            }

        } finally {
            if(br != null) {
                br.close();
            }
        }
    }

    public void storeHistory() throws IOException {

        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try {
            Iterator<Result> iter = historyData.iterator();
            while (iter.hasNext()) {
                Result item = iter.next();
                bw.write(String.format("%s\t%s\t%s\t%s",
                        item.getPlayer1name(),
                        item.getPlayer2name(),
                        item.getPlayer1score(),
                        item.getPlayer2score())
                );
                bw.newLine();
            }

        } finally {
            if (bw != null) {
                bw.close();
            }
        }
    }

}
