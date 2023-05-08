package com.example.hello2;


//new change
//new new change
import com.example.hello2.Controller.AFileReader;
import com.example.hello2.Modal.ItemModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Load the FXML file for the login/signup screen (Scene 3)
//        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginSignup.fxml")));
//        Scene scene = new Scene(root);
//        scene.setFill(Color.rgb(255, 204, 204)); // sets the background color to baby pink
//        stage.setTitle("Login or Signup");
//        Image icon = new Image("file:/C:/Users/ShirinLP/Pictures/OIP.png");
//        stage.getIcons().add(icon);
//        stage.setScene(scene);
//        stage.show();
    }

    public static void main(String[] args) {
        new AFileReader();
        AFileReader temp = new AFileReader();
//        temp.readItems();

        ArrayList<ItemModel> itemList  = temp.readItems();
//        System.out.println(itemList.get(0).getRentalType() == );
        launch();
    }
}
