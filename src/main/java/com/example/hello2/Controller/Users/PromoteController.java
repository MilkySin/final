package com.example.hello2.Controller.Users;

//Fixed and is working correctly

import com.example.hello2.Model.UserModel;
import com.example.hello2.Reader.UserFileReader;
import com.example.hello2.Writer.UsersFileWriter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PromoteController {

    public ChoiceBox<String> ID;
    public UserFileReader temp = new UserFileReader();

    public TextArea CustomerDetail;
    public ChoiceBox<String> PromoteChoice;

    public Text text;
    public Button back;

    public PromoteController() {
    }

    public void initialize() throws IOException {
        PromoteChoice.getItems().addAll("Guest", "Regular", "VIP");
        PromoteChoice.setValue("choose type");
        UserFileReader temp = new UserFileReader();
        ArrayList<UserModel> userList = temp.readFileUser();
        for (UserModel User : userList) {
            ID.getItems().add(User.getId());
            ID.setValue("Select User to Update");
        }
        ID.setOnAction(event -> {
            String searchId = ID.getValue();
            for (UserModel user : userList) {
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
        for (UserModel user : reader.readFileUser()) {
            if (user.getId().equals(ID.getValue())) {
                String x = user.getAccountType();
                String y = PromoteChoice.getValue();

                user.setAccountType(y);

                CustomerDetail.setText(user.toString());

                if (x.equals(y)) {
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
        Path path = Paths.get("src/main/resources/com/example/hello2/FXML/SceneAdmin.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}

