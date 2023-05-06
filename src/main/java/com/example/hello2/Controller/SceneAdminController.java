package com.example.hello2.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SceneAdminController {

    public Button DisplayCustomers;
    @FXML
    private Button AddItem;

    @FXML
    private Button EditItem;

    @FXML
    private Button DeleteItem;

    @FXML
    private Button DisplayAllItems;

    @FXML
    private Button DisplayAvailItems;

    @FXML
    void addItem(ActionEvent event) throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/AddItems.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) AddItem.getScene().getWindow();
        stage.setScene(scene);
        AddItemController controller = loader.getController();
        stage.show();
    }
    @FXML //pl
    void editItem(ActionEvent event) throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/EditItem.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) EditItem.getScene().getWindow();
        stage.setScene(scene);
        EditItemController controller = loader.getController();
        stage.show();
    }

    @FXML
    void deleteItem(ActionEvent event) throws IOException{
        Path path = Paths.get("src/main/resources/com/example/hello2/DeleteItem.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) DeleteItem.getScene().getWindow();
        stage.setScene(scene);
        DeleteItemController controller = loader.getController();
        stage.show();
    }
    @FXML
    void displayCustomers(ActionEvent event) throws IOException{
        Path path = Paths.get("src/main/resources/com/example/hello2/displayCustomers.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) EditItem.getScene().getWindow();
        stage.setScene(scene);
        DisplayCustomersController controller = loader.getController();
        stage.show();
    }

    @FXML
    void showAllItems(ActionEvent event) throws IOException{
        Path path = Paths.get("src/main/resources/com/example/hello2/DisplayItems.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) DisplayAllItems.getScene().getWindow();
        stage.setScene(scene);
        DisplayItemsController controller = loader.getController();
        stage.show();
    }

    @FXML
    void showAvailItems(ActionEvent event) throws IOException{
        Path path = Paths.get("src/main/resources/com/example/hello2/DisplayItemsAvail.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) DisplayAvailItems.getScene().getWindow();
        stage.setScene(scene);
        DisplayItemsAvailController controller = loader.getController();
        stage.show();
    }

}
