package com.example.hello2.Controller.Display;
//fixed
import com.example.hello2.Model.UserModel;
import com.example.hello2.Reader.UserFileReader;
import javafx.event.ActionEvent;
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
import java.util.ArrayList;
import java.util.Objects;

public class DisplayRegular {
    @FXML
    public TextArea customerTextArea;

    public Button back;

public String toString(UserModel u) {
    return "ID: " + u.getId() + "\n Username: " + u.getUsername() + "\n Password: " + u.getPassword()
            + "\n Address: " + u.getAddress() + "\n Phone Number: " + u.getPhoneNumber()
            + ", Account Type: " + u.getAccountType();
}

    @FXML
    public void initialize() throws IOException {
        UserFileReader temp = new UserFileReader();
        ArrayList<UserModel> Userlist = temp.readFileUser();
        ArrayList<UserModel> Regularlist=new ArrayList<>();
        for (UserModel User : Userlist){
            if (Objects.equals(User.getAccountType(), "Regular")){
                Regularlist.add(User);
            }
        }
        customerTextArea.setText(Regularlist.toString());
    }
    @FXML
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