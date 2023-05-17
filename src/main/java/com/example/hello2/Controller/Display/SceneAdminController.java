package com.example.hello2.Controller.Display;
//fixed

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
    public Button DeleteUser;
    public Button AddUser;
    public Button EditUser;
    public Button AddStock;
    @FXML
    private Button AddItem;
    @FXML
    private Button EditItem;
    @FXML
    private Button DeleteItem;
    @FXML
    private Button DisplayAllItems;
    @FXML
    private Button back;
    @FXML
    private Button promote;

    @FXML
    void addItem() throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/AddItems.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) AddItem.getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
        //pl
    void editItem() throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/EditItem.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) EditItem.getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void deleteItem() throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/DeleteItem.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) DeleteItem.getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void displayCustomers() throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/displayCustomers.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) EditItem.getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void showAllItems() throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/DisplayItems.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) DisplayAllItems.getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    @FXML
    void deleteUser() throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/deleteUser.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) EditItem.getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void Promote() throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/Promote.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) promote.getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void Back() throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/LoginSignup.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void AddUser() throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/Signup.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) EditItem.getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void EditUser() throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/EditUser.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) EditItem.getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void AddStock() throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/AddStock.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) EditItem.getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
