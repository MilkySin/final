package com.example.hello2;


//new change
//new new change
import com.example.hello2.Controller.ItemsFileReader;
import com.example.hello2.Modal.ItemModel;
import com.example.hello2.Modal.UserModel;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

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
        new ItemsFileReader();
        ItemsFileReader temp = new ItemsFileReader();
//        temp.readItems();

        ArrayList<ItemModel> itemList  = temp.readItems();
//        ItemsFileReader temp2;
//        ArrayList<UserModel> userList  = temp2.readItems();

//        System.out.println(itemList.get(0).getRentalType() == );

        for(ItemModel temp2: itemList) {
            System.out.println(temp2.getID());
        }
        launch();
    }
}
