package com.example.hello2;
//new change

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //Load the FXML file for the login/signup screen (Scene 3)
        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("/com/example/hello2/FXML/LoginSignup.fxml"))));
        Scene scene = new Scene(root);
        scene.setFill(Color.rgb(255, 204, 204)); // sets the background color to baby pink
        stage.setTitle("Genie's Store - Team 9");
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/hello2/Images/R.png")));
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        launch();
    }
}
