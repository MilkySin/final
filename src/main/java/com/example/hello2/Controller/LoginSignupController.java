package com.example.hello2.Controller;
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
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoginSignupController {

    @FXML
    private Button loginButton;

    @FXML
    private Button signupButton;

    public void login(ActionEvent event) {
        try {
            // Load the FXML file for Scene 2
            Path path = Paths.get("src/main/resources/com/example/hello2/Login.fxml");
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
