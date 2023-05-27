package com.example.hello2.Controller.Display;

import com.example.hello2.Model.ItemModel;
import com.example.hello2.Model.UserModel;
import com.example.hello2.Reader.ItemsFileReader;
import com.example.hello2.Reader.UserFileReader;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Statistics {
    @FXML
    private PieChart ItemTypePieChart;
    @FXML
    private PieChart accountPieChart;

    public void initialize() throws IOException {
        initializeAccountPieChart();
        initializeItemTypePieChart();
    }

    private void initializeAccountPieChart() throws IOException {
        int vipCount = 0;
        int regularCount = 0;
        int guestCount = 0;

        UserFileReader reader = new UserFileReader();
        StringBuilder fileContent = new StringBuilder();

        for (UserModel user : reader.readFileUser()) {
            fileContent.append(user.toString());
        }

        ArrayList<UserModel> userList = reader.readFileUser();
        for (UserModel user : userList) {
            if (Objects.equals(user.getAccountType(), "Regular")) {
                regularCount++;
            }
            if (Objects.equals(user.getAccountType(), "Guest")) {
                guestCount++;
            }
            if (Objects.equals(user.getAccountType(), "VIP")) {
                vipCount++;
            }
        }

        accountPieChart.getData().addAll(
                new PieChart.Data("VIP", vipCount),
                new PieChart.Data("Regular", regularCount),
                new PieChart.Data("Guest", guestCount)
        );
        accountPieChart.getData().forEach(data -> data.getNode().setStyle("-fx-text-fill: white;"));
    }

    private void initializeItemTypePieChart() throws IOException {
        int DVD = 0;
        int Record = 0;
        int Game = 0;

        ItemsFileReader reader = new ItemsFileReader();
        StringBuilder fileContent = new StringBuilder();
        for (ItemModel item : reader.readFileItems()) {
            fileContent.append(item.toString());
        }

        ArrayList<ItemModel> itemList = reader.readFileItems();
        for (ItemModel item : itemList) {
            if (Objects.equals(item.getRentalType(), "DVD")) {
                DVD++;
            }
            if (Objects.equals(item.getRentalType(), "Record")) {
                Record++;
            }
            if (Objects.equals(item.getRentalType(), "Game")) {
                Game++;
            }
        }

        ItemTypePieChart.getData().addAll(
                new PieChart.Data("DVD", DVD),
                new PieChart.Data("Record", Record),
                new PieChart.Data("Game", Game)
        );
        ItemTypePieChart.getData().forEach(data -> data.getNode().setStyle("-fx-text-fill: white;"));
    }
}
