package com.example.hello2.Controller;

//Fixed and is working correctly

import com.example.hello2.Model.UserModel;
import com.example.hello2.Reader.UserFileReader;
import com.example.hello2.Writer.UsersFileWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class PromoteController {

    public UserFileReader temp = new UserFileReader();
    private ArrayList<UserModel> userList = temp.readUser();
    @FXML
    private TextField searchIdField;
    public Button searchCustomerButton;
    public TextArea CustomerDetail;
    public ChoiceBox<String> PromoteChoice;

    public Text text;
    public Button back;

    public PromoteController() throws IOException {
    }

    public void initialize(){
        PromoteChoice.getItems().addAll("Guest", "Regular", "VIP");
        PromoteChoice.setValue("Guest");

        text.setVisible(false);
    }
    public void searchItem(ActionEvent event) throws IOException {
        String idToSearch = searchIdField.getText(); // Change this to the ID you want to search for
        boolean found = false;
        UserModel user = null;

        for(UserModel users: userList){
            if(Objects.equals(users.getId(), idToSearch)){
                user =  users;
                found = true;
                break;
            }
        }
        
        if (found) {
            CustomerDetail.setText(user.getId());
        } else {
            System.out.println("User not found");
        }
    }

    public void saveChange(ActionEvent e) throws IOException {
        String idToModify = searchIdField.getText();
        String newAccountType = PromoteChoice.getValue();
        boolean found = false;



        UserModel user = null;
        for (UserModel users : userList) {
            if (Objects.equals(users.getId(), idToModify)){
                user = users;
                found = true;
                if (Objects.equals(users.getAccountType(), newAccountType)) {
                    text.setVisible(true);
                    text.setFill(Color.RED);
                    text.setText("New account type is the same as the old one.");
                    break;
                }
            }


        }
        if (found) {
            UsersFileWriter writer = new UsersFileWriter();
            for(UserModel temo : userList){
                if(Objects.equals(temo.getId(), idToModify)){
                    temo.setAccountType(newAccountType);
                }
            }
            writer.FileWriter(userList);

        } else {
            text.setVisible(true);
            text.setFill(Color.RED);
            text.setText("User with ID " + idToModify + " not found.");
        }
    }

    public void Back(ActionEvent event) throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/SceneAdmin.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}

