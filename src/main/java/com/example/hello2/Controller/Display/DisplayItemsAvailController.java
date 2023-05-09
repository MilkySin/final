package com.example.hello2.Controller.Display;
//fixed
import com.example.hello2.Model.ItemModel;
import com.example.hello2.Reader.ItemsFileReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class DisplayItemsAvailController {
    @FXML
    public TextArea textArea;

    public Button back;
    @FXML
    public void initialize() throws IOException {
        ItemsFileReader reader = new ItemsFileReader();
        ArrayList<ItemModel> Itemlist=reader.readFileItems();
        StringBuilder fileContent = new StringBuilder();
        for (ItemModel item : Itemlist){
            if (Objects.equals(item.getStatus(), "Available")){
                fileContent.append(item);
            }
        }
        textArea.setText(fileContent.toString());
    }
        @FXML
        public void Back() throws IOException {
            Path path = Paths.get("src/main/resources/com/example/hello2/SceneAdmin.fxml");
            FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

