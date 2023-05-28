package com.example.hello2.Controller.Items;

import com.example.hello2.Model.ItemModel;
import com.example.hello2.Model.UserModel;
import com.example.hello2.Reader.ItemsFileReader;
import com.example.hello2.Reader.UserFileReader;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Statistics {

    public PieChart GenreTypePieChart;
    public PieChart ItemTypePieChart;
    public PieChart accountPieChart;
    public Button back;


    public void initialize() throws IOException {
        initializeAccountPieChart();
        initializeItemTypePieChart();
        initializeItemGenrePieChart();
    }

    private void initializeAccountPieChart() throws IOException {
        int vipCount = 0;
        int regularCount = 0;
        int guestCount = 0;

        UserFileReader reader = new UserFileReader();

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
        // Add data labels
        addDataLabels(accountPieChart);
    }

    private void initializeItemTypePieChart() throws IOException {
        int DVD = 0;
        int Record = 0;
        int Game = 0;

        ItemsFileReader reader = new ItemsFileReader();

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
        // Add data labels
        addDataLabels(ItemTypePieChart);
    }

    private void initializeItemGenrePieChart() throws IOException {
        int Drama = 0;
        int Horror = 0;
        int Comedy = 0;
        int Action = 0;

        ItemsFileReader reader = new ItemsFileReader();

        ArrayList<ItemModel> itemList = reader.readFileItems();
        for (ItemModel item : itemList) {
            String genre = item.getGenre();
            if (genre != null && !genre.equalsIgnoreCase("None")) {
                switch (genre) {
                    case "Drama" -> Drama++;
                    case "Horror" -> Horror++;
                    case "Comedy" -> Comedy++;
                    case "Action" -> Action++;
                }
            }
        }

        GenreTypePieChart.getData().addAll(
                new PieChart.Data("Drama", Drama),
                new PieChart.Data("Horror", Horror),
                new PieChart.Data("Comedy", Comedy),
                new PieChart.Data("Action", Action)
        );

        GenreTypePieChart.getData().forEach(data -> data.getNode().setStyle("-fx-text-fill: white;"));
        // Add data labels
        addDataLabels(GenreTypePieChart);
    }


    private void addDataLabels(PieChart pieChart) {
        for (PieChart.Data data : pieChart.getData()) {
            Label label = new Label(Integer.toString((int) data.getPieValue()));
            label.setStyle("-fx-font-size: 9pt;");
            Tooltip tooltip = new Tooltip(data.getName() + ": " + (int) data.getPieValue());
            Tooltip.install(data.getNode(), tooltip);

            data.getNode().setOnMouseEntered(event -> tooltip.show(pieChart.getScene().getWindow(), event.getScreenX(),
                    event.getScreenY() + 10));
            data.getNode().setOnMouseExited(event -> tooltip.hide());
        }
    }

    @FXML
    public void Back() throws IOException {
        EditItemController.Log(back);
    }
}
