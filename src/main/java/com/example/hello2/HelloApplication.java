package com.example.hello2;


//new change
//new new change

import com.example.hello2.Reader.SelectedItemsReader;
import com.example.hello2.Writer.*;
import com.example.hello2.Model.*;

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
        //Load the FXML file for the login/signup screen (Scene 3)
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginSignup.fxml")));
        Scene scene = new Scene(root);
        scene.setFill(Color.rgb(255, 204, 204)); // sets the background color to baby pink
        stage.setTitle("Genie's Store - Team 9");
        Image icon = new Image("file:/C:/Users/ShirinLP/Pictures/OIP.png");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
//        UserModel user = new UserModel("Minh","Minh123!","C002","C002","Regular",1234567);
//        UserModel user2 = new UserModel("Lol","Minh123!","C002","C002","Regular",1234567);
//        ItemModel item = new ItemModel("I444-444","Harry Potter","DVD", "Week Loan",499988,70.0,"Borrowed");
//        ItemModel item2 = new ItemModel("I444-442","Harry Potter","DVD", "Week Loan",499988,70.0,"Borrowed");
//
//        ArrayList<String> items = new ArrayList<>();
//        ArrayList<String> items2 = new ArrayList<>();
//
//        items.add(item.getUserID());
//        items.add(item2.getUserID());
//
//        items2.add(item.getUserID());
//        SelectedItems selectedItems = new SelectedItems(user.getId(), items);
//        SelectedItems selectedItems2 = new SelectedItems(user2.getId(), items2);
//
//        SelectedItemsWriter writer = new SelectedItemsWriter();
//        writer.SelectedItemsWriteFIle(selectedItems);
//        writer.SelectedItemsWriteFIle(selectedItems2);

//        ArrayList<SelectedItems> reader = new SelectedItemsReader().readFileSelectedItems();
//        for(SelectedItems i : reader){
//            System.out.println(i.getSelectedItemsList());
//        }
        launch();
    }
}
