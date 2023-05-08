package com.example.hello2.Controller;


import com.example.hello2.Model.ItemModel;
import com.example.hello2.Reader.ItemsFileReader;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class DeleteItemController {
    public ChoiceBox<String> IDchoicebox;
    public Button delete;
    public Button back;

    public void initialize() {
        ItemsFileReader temp = new ItemsFileReader();
        ArrayList<ItemModel> itemlist = temp.readItems();
        for (ItemModel item : itemlist) {
            IDchoicebox.getItems().add(item.getID());
            IDchoicebox.setValue("Select Item to Delete");
        }

    }

    @FXML
    void Delete() throws IOException {
        ItemsFileReader temp = new ItemsFileReader();

        ArrayList<ItemModel> itemlist = temp.readItems();
        String selectedID = IDchoicebox.getValue();
        if (selectedID == null || selectedID.equals("Select Item to Delete")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an item to delete.");
            alert.showAndWait();
            return;
        }
        Iterator<ItemModel> iterator = itemlist.iterator();
        while (iterator.hasNext()) {
            ItemModel item = iterator.next();
            if (selectedID.equals(String.valueOf(item))) {
                iterator.remove();
            }
        }
    }

    @FXML
    public void Back(ActionEvent event) throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/SceneAdmin.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
