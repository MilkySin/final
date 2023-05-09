package com.example.hello2.Controller;

import com.example.hello2.Model.UserModel;
import com.example.hello2.Reader.UserFileReader;
import com.example.hello2.Writer.UsersFileWriter;
import javafx.event.ActionEvent;
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

    public void initialize() throws IOException {
        UserFileReader temp = new UserFileReader();
        ArrayList<UserModel> Userlist = temp.readFileUser();
        for (UserModel User : Userlist) {
            IDchoicebox.getItems().add(User.getId());
            IDchoicebox.setValue("Select User to Delete");

        }
    }

    @FXML
    void Delete() throws IOException {
        UserFileReader reader = new UserFileReader();
        UsersFileWriter writer = new UsersFileWriter();
        String searchId = IDchoicebox.getValue();
        ArrayList<UserModel> Userlist = reader.readFileUser();
        String selectedID = IDchoicebox.getValue();
        if (selectedID == null || selectedID.equals("Select user to Delete")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a User to delete.");
            alert.showAndWait();
            return;
        }
        for (int i = 0; i < Userlist.size(); i++) {
            if (Userlist.get(i).getId().equals(searchId)) {
                Userlist.remove(i);
                writer.UserWriteFile(Userlist);
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
