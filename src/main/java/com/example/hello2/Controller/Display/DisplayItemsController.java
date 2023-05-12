package com.example.hello2.Controller.Display;

//Fixed
import com.example.hello2.Model.ItemModel;
import com.example.hello2.Model.UserModel;
import com.example.hello2.Reader.ItemsFileReader;
import com.example.hello2.Reader.UserFileReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class DisplayItemsController {
    @FXML
    public TextArea selectedItemLabel;
    public Button back;
    public TextArea itemstextArea;
    public Button Available;
    public Button Unavilable;

    @FXML
    public void initialize() throws Exception {
        ItemsFileReader reader = new ItemsFileReader();
        StringBuilder fileContent = new StringBuilder();
        for(ItemModel items : reader.readFileItems()){
            fileContent.append(items.toString());
        }
        itemstextArea.setText(fileContent.toString());
    }
    @FXML


    void SortByID() throws IOException {
        System.out.println("Sorting by ID...");
        ItemsFileReader read = new ItemsFileReader();
        StringBuilder content = new StringBuilder();

        // Sort by ID
        read.readFileItems().sort(Comparator.comparing(ItemModel::getID));
        itemstextArea.clear();

        for(ItemModel item: read.getItemList()){
            content.append(item.toString());
            System.out.println(item.toString());
        }

        itemstextArea.setText(content.toString());
    }
    @FXML
    void SortByName() throws IOException {
        System.out.println("Sorting by name...");
        ItemsFileReader reader = new ItemsFileReader();
        StringBuilder content = new StringBuilder();

        // Sort by name
        reader.readFileItems().sort(Comparator.comparing(ItemModel::getTitle));

        itemstextArea.clear();
        for(ItemModel item: reader.getItemList()){
            content.append(item.toString());
        }
        itemstextArea.setText(content.toString());
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

    public void Available(ActionEvent event) throws IOException {
        ItemsFileReader reader = new ItemsFileReader();
        ArrayList<ItemModel> Itemlist=reader.readFileItems();
        StringBuilder fileContent = new StringBuilder();
        for (ItemModel item : Itemlist){
            if (Objects.equals(item.getStatus(), "Available")){
                fileContent.append(item);
            }
        }
        itemstextArea.setText(fileContent.toString());
    }

    public void Unavilable(ActionEvent event) throws IOException {
        ItemsFileReader reader = new ItemsFileReader();
        ArrayList<ItemModel> Itemlist=reader.readFileItems();
        ArrayList<ItemModel> UNAvaillist =new ArrayList<>();
        for (ItemModel item : Itemlist){
            if (Objects.equals(item.getStatus(), "Borrowed")){

                UNAvaillist.add(item);
            }
        }
        itemstextArea.setText(UNAvaillist.toString());
    }
}
