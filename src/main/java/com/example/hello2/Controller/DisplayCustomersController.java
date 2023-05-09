package com.example.hello2.Controller;

//Fixed
import com.example.hello2.Model.UserModel;
import com.example.hello2.Reader.UserFileReader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DisplayCustomersController {
    @FXML
    public TextArea customerTextArea;

    public Button back;

    @FXML
    void display() throws IOException {
        UserFileReader read = new UserFileReader();
        StringBuilder content = new StringBuilder();

        for(UserModel users: read.readFileUser()){
            content.append(users.toString());
        }
        customerTextArea.setText(content.toString());
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
