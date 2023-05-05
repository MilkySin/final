package com.example.hello2.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EditItemController {

    public TextField itemIdField;
    public Button searchItemButton;
    @FXML
    private TextField searchIdField;

    @FXML
    private TextArea itemDetailsArea;

    @FXML
    private TextField rentalFeeField;

    @FXML
    private TextField copiesField;

    @FXML
    private Button saveChangesButton;

    private String filePath = "src/main/resources/items.txt";

    public void searchItem(ActionEvent event) {
        String searchId = searchIdField.getText();
        List<String> itemList = readItems();
        String itemDetails = "";
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).startsWith("ID: " + searchId)) {
                for (int j = i; j < i + 6; j++) {
                    itemDetails += itemList.get(j) + "\n";
                }
                itemDetailsArea.setText(itemDetails);
                break;
            }
        }
    }

    public void saveChanges(ActionEvent event) {
        String searchId = searchIdField.getText();
        List<String> itemList = readItems();
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).startsWith("ID: " + searchId)) {
                itemList.set(i + 4, "Copies: " + copiesField.getText());
                itemList.set(i + 5, "Rental Fee (USD): " + rentalFeeField.getText());
                writeItems(itemList);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Changes saved successfully.");
                alert.showAndWait();
                break;
            }
        }
    }

    private List<String> readItems() {
        List<String> itemList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
            String line = reader.readLine();
            while (line != null) {
                itemList.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    private void writeItems(List<String> itemList) {
        try {
            FileWriter writer = new FileWriter(new File(filePath));
            for (String line : itemList) {
                writer.write(line + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
