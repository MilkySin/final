package com.example.hello2.Controller.Items;
//fixed

import com.example.hello2.Model.ItemModel;
import com.example.hello2.Reader.ItemsFileReader;
import com.example.hello2.Writer.ItemsFileWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteItemController {
    public ChoiceBox<String> IDchoicebox;
    public Button delete;
    public Button back;
    public javafx.scene.control.TextArea TextArea;

    public void initialize() throws IOException {
        ItemsFileReader temp = new ItemsFileReader();
        ArrayList<ItemModel> itemList = temp.readFileItems();

        for (ItemModel item : itemList) {
            IDchoicebox.getItems().add(item.getID());
            IDchoicebox.setValue("Select Item to Delete");
        }

        IDchoicebox.setOnAction(event -> {
            String searchId = IDchoicebox.getValue();
            for (ItemModel item : itemList) {
                if (item.getID().equals(searchId)) {
                    TextArea.setText(item.toString());
                    break; // Exit the loop once a match is found
                }
            }
        });
    }


    @FXML
    void Delete() throws IOException {
        ItemsFileReader reader = new ItemsFileReader();
        ItemsFileWriter writer = new ItemsFileWriter();
        String searchId = IDchoicebox.getValue();
        ArrayList<ItemModel> itemList = reader.readFileItems();
        String selectedID = IDchoicebox.getValue();
        if (selectedID == null || selectedID.equals("Select Item to Delete")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an item to delete.");
            alert.showAndWait();
            return;
        }
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getID().equals(searchId)) {
                itemList.remove(i);
                writer.ItemsWriteFile(itemList);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Item Successfully deleted.");
                alert.showAndWait();
                break;
            }
        }
    }

    @FXML
    public void Back() throws IOException {
        EditItemController.Log(back);
    }


}
