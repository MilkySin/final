package com.example.hello2.Controller.Users;

//Fixed and is working correctly

import com.example.hello2.Model.ItemModel;
import com.example.hello2.Model.UserModel;
import com.example.hello2.Reader.ItemsFileReader;
import com.example.hello2.Reader.UserFileReader;
import com.example.hello2.Writer.ItemsFileWriter;
import com.example.hello2.Writer.UsersFileWriter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class PromoteController {

    public  ChoiceBox ID  ;
    public UserFileReader temp = new UserFileReader();
    private final ArrayList<UserModel> userList = temp.readFileUser();

    public TextArea CustomerDetail;
    public ChoiceBox<String> PromoteChoice;

    public Text text;
    public Button back;

    public PromoteController() throws IOException {
    }

    public void initialize() throws IOException {
        PromoteChoice.getItems().addAll("Guest", "Regular", "VIP");
        PromoteChoice.setValue("choose type");
        UserFileReader temp = new UserFileReader();
        ArrayList<UserModel> Userlist = temp.readFileUser();
        for (UserModel User : Userlist) {
            ID.getItems().add(User.getId());
            ID.setValue("Select User to Update");
        }
        ID.setOnAction(event -> {
            String searchId = (String) ID.getValue();
            for (UserModel user : Userlist) {
                if (user.getId().equals(searchId)) {
                    CustomerDetail.setText(user.toString());
                    break; // Exit the loop once a match is found

                }
            }
        });
    }



    public void saveChange() throws IOException {
        UserFileReader reader = new UserFileReader();
        UsersFileWriter writer = new UsersFileWriter();
        for(UserModel user: reader.readFileUser()){
            if(user.getId().equals(ID.getValue())) {
                String x = user.getAccountType();
                String y = PromoteChoice.getValue();

                user.setAccountType(y);

                CustomerDetail.setText(user.toString());

                if(x.equals(y)){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText(null);
                    alert.setContentText("New account type is the same as the old account type.");
                    alert.showAndWait();
                }
            }
        }
        writer.UserWriteFile(reader.getUserList());

        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Success");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Changes saved successfully.");
        successAlert.showAndWait();
    }

    public void Back() throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/SceneAdmin.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}

