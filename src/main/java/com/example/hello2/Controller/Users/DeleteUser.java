package com.example.hello2.Controller.Users;
//fixed

import com.example.hello2.Model.UserModel;
import com.example.hello2.Reader.UserFileReader;
import com.example.hello2.Writer.UsersFileWriter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DeleteUser {
    public ChoiceBox<String> IDchoicebox;
    public Button delete;
    public Button back;
    public javafx.scene.control.TextArea TextArea;

    public void initialize() throws IOException {
        UserFileReader temp = new UserFileReader();
        ArrayList<UserModel> userList = temp.readFileUser();
        for (UserModel User : userList) {
            IDchoicebox.getItems().add(User.getId());
            IDchoicebox.setValue("Select User to Delete");
        }
        IDchoicebox.setOnAction(event -> {
            String searchId = IDchoicebox.getValue();
            for (UserModel user : userList) {
                if (user.getId().equals(searchId)) {
                    TextArea.setText(user.toString());
                    break; // Exit the loop once a match is found
                }
            }
        });
    }

    @FXML
    void Delete() throws IOException {
        UserFileReader reader = new UserFileReader();
        UsersFileWriter writer = new UsersFileWriter();
        String searchId = IDchoicebox.getValue();
        ArrayList<UserModel> userList = reader.readFileUser();
        String selectedID = IDchoicebox.getValue();
        if (selectedID == null || selectedID.equals("Select user to Delete")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a User to delete.");
            alert.showAndWait();
            return;
        }
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId().equals(searchId)) {
                userList.remove(i);
                writer.UserWriteFile(userList);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Item Successfully deleted.");
                alert.showAndWait();
                break;
            }
        }
    }

    @FXML
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
