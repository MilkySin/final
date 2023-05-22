package com.example.hello2.Controller.Display;
//choose sign up or login
//fixed

import com.example.hello2.Model.UserModel;
import com.example.hello2.Reader.UserFileReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class LoginSignupController {

    public TextField IDField;
    public PasswordField passwordField;
    @FXML
    public Button LogIn;

    @FXML
    public String handleLogIn() throws IOException {
        UserFileReader temp = new UserFileReader();
        ArrayList<UserModel> itemList = temp.readFileUser();
        String ID = IDField.getText().trim();
        String password = passwordField.getText().trim();
        if (!ID.matches("C\\d{3}")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid ID");
            alert.setHeaderText(null);
            alert.setContentText("The ID should start with a capital 'C' followed by three digits.");
            alert.showAndWait();
        }

        boolean userFound = false;
        for (UserModel users : itemList) {
            if (ID.equals(users.getId()) && password.equals(users.getPassword())) {
                if (Objects.equals(users.getId(), "C000") && Objects.equals(users.getUsername(), "Admin")) {
                    Path path = Paths.get("src/main/resources/com/example/hello2/SceneAdmin.fxml");
                    FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) LogIn.getScene().getWindow();
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    if (Objects.equals(users.getAccountType(), "Guest")) {
                        Path path = Paths.get("src/main/resources/com/example/hello2/GuestUser.fxml");
                        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) LogIn.getScene().getWindow();
                        ItemSelectGuestController guestUserController = loader.getController(); // Create an instance
                        // of ItemSelectGuestController
                        guestUserController.setID(ID); // Set the ID value
                        guestUserController.setInitialize();
                        stage.setResizable(false);
                        stage.setScene(scene);
                        stage.show();
                    } else if (Objects.equals(users.getAccountType(), "Regular")) {
                        Path path = Paths.get("src/main/resources/com/example/hello2/RegularUser.fxml");
                        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) LogIn.getScene().getWindow();
                        okdokaofd regularUserController = loader.getController(); // Create an
                        // instance
                        // of
                        // ItemSelectGuestController
                        regularUserController.setID(ID);// Set the ID value
                        regularUserController.setInitialize();
                        stage.setResizable(false);
                        stage.setScene(scene);
                        stage.show();
                    } else if (Objects.equals(users.getAccountType(), "VIP")) {
                        Path path = Paths.get("src/main/resources/com/example/hello2/VIPUser.fxml");
                        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) LogIn.getScene().getWindow();
                        ItemSelectVIPController VIPUserController = loader.getController(); // Create an instance of
                        // ItemSelectGuestController
                        VIPUserController.setID(ID); // Set the ID value
                        VIPUserController.setInitialize();
                        stage.setResizable(false);
                        stage.setScene(scene);
                        stage.show();
                    }
                }
                userFound = true;
                break;
            }
        }

        if (!userFound) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Credentials");
            alert.setHeaderText(null);
            alert.setContentText("The ID and/or password is incorrect.");
            alert.showAndWait();
        }
        return ID;
    }


    public void signup(ActionEvent event) {
        try {
            // Load the FXML file for Scene 1
            Path path = Paths.get("src/main/resources/com/example/hello2/Signup.fxml");
            FXMLLoader loader = new FXMLLoader(path.toUri().toURL());

            // Set up the stage and show Scene 1
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            // Close the login screen
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
