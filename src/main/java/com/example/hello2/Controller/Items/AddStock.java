package com.example.hello2.Controller.Items;

import com.example.hello2.Model.ItemModel;
import com.example.hello2.Reader.ItemsFileReader;
import com.example.hello2.Writer.ItemsFileWriter;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;

public class AddStock {
    public ChoiceBox<String> Items;
    public Button saveChanges;

    public TextField item;
    public TextArea itemDetailsArea;
    public TextField Number;
    public Button back;
    public Button ShowItemInfo;

    public void initialize() throws IOException {
        Number.setStyle("-fx-text-fill: black;");
        ItemsFileReader temp = new ItemsFileReader();
        ArrayList<ItemModel> itemList = temp.readFileItems();
        for (ItemModel item : itemList) {
            Items.getItems().add(item.getID());
            Items.setValue("Select Item to Add Stock to");
            if (item.getID().equals(Items.getValue())) {
                itemDetailsArea.setText(item.toString());

            }


        }
    }

    public void ShowItemInfo() throws IOException {
        ItemsFileReader temp = new ItemsFileReader();
        ArrayList<ItemModel> itemList = temp.readFileItems();
        for (ItemModel item : itemList) {
            if (item.getID().equals(Items.getValue())) {
                itemDetailsArea.setText(item.toString());

            }
        }
    }

    public void saveChanges() throws IOException {
        ItemsFileReader reader = new ItemsFileReader();
        ItemsFileWriter writer = new ItemsFileWriter();
        for (ItemModel items : reader.readFileItems()) {
            if (items.getID().equals(Items.getValue())) {
                int x = items.getCopies();
                String y = Number.getText();
                int numY = Integer.parseInt(y); // convert y to an integer
                items.setCopies(x + numY);
                if (items.getCopies() > 0) items.setStatus("Available");

                itemDetailsArea.setText(items.toString());
            }

        }
        writer.ItemsWriteFile(reader.getItemList());


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Changes saved successfully.");
        alert.showAndWait();

    }

    @FXML
    public void Back() throws IOException {
        EditItemController.Log(back);
    }


}
