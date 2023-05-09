package com.example.hello2.Controller;
//fixed
import com.example.hello2.Controller.Items.AddItemController;
import com.example.hello2.Controller.Items.DeleteItemController;
import com.example.hello2.Controller.Items.EditItemController;
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

    public  Button VIPCUSTOMER ;
    public Button DisplayCustomers;
    public Button DeleteUser;
    public Button RegularCustomer;
    public Button GuestAccount;
    public Button DisplayUnavailableItems;
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
    private Button back;
    @FXML
    private Button promote;

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
    @FXML
    void deleteUser(ActionEvent event) throws IOException{
        Path path = Paths.get("src/main/resources/com/example/hello2/deleteUser.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) EditItem.getScene().getWindow();
        stage.setScene(scene);
        com.example.hello2.Controller.Users.DeleteUser controller = loader.getController();
        stage.show();
    }
    public void Promote(ActionEvent event) throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/Promote.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) promote.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void Back(ActionEvent event) throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/LoginSignup.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void DisplayVIP (ActionEvent event) throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/DisplayVIP.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void DisplayRegular (ActionEvent event) throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/DisplayRegular.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void DisplayGuest (ActionEvent event) throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/DisplayGuest.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void DisplayUnavailableItems(ActionEvent event) throws IOException{
        Path path = Paths.get("src/main/resources/com/example/hello2/DisplayUnavailableItems.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) EditItem.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
