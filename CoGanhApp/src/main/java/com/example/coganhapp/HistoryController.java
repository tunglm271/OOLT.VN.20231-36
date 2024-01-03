package com.example.coganhapp;

import com.example.coganhapp.data.DataReader;
import com.example.coganhapp.data.Result;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.LinkedList;

public class HistoryController {

    @FXML
    private TableView<Result> historyTable;
    @FXML
    private TableColumn<Result, String> firstPlayerColumn;
    @FXML
    private TableColumn<Result, String> secondPlayerColumn;
    @FXML
    private TableColumn<Result, Integer> firstPlayerScore;
    @FXML
    private TableColumn<Result, Integer> secondPlayerScore;
    private LinkedList<Result> historyList = new LinkedList<Result>();

    @FXML
    public  void initialize() {
        firstPlayerColumn.setCellValueFactory(new PropertyValueFactory<Result,String>("player1name"));
        secondPlayerColumn.setCellValueFactory(new PropertyValueFactory<Result, String>("player2name"));
        firstPlayerScore.setCellValueFactory(new PropertyValueFactory<Result, Integer>("player1score"));
        secondPlayerScore.setCellValueFactory(new PropertyValueFactory<Result, Integer>("player2score"));
        historyTable.setItems(FXCollections.observableList(DataReader.getInstance().getHistory()));
        historyTable.setPlaceholder(new Label("No match in history"));
    }


}
