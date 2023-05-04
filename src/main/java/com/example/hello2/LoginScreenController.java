package com.example.hello2;
//choose sign up or login
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginScreenController {

    @FXML
    private Button loginButton;

    @FXML
    private Button signupButton;

    public void login(ActionEvent event) {
        try {
            // Load the FXML file for Scene 2
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene2.fxml"));
            Parent root = loader.load();

            // Set up the stage and show Scene 2
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

    public void signup(ActionEvent event) {
        try {
            // Load the FXML file for Scene 1
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene1.fxml"));
            Parent root = loader.load();

            // Set up the stage and show Scene 1
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
