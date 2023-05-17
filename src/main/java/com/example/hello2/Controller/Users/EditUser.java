package com.example.hello2.Controller.Users;
//fixed

import com.example.hello2.Model.UserModel;
import com.example.hello2.Reader.UserFileReader;
import com.example.hello2.Writer.UsersFileWriter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class EditUser {
    public ChoiceBox<String> ID;
    public Button back;
    public TextField itemIdField;
    public TextField UsernameField;
    public TextField PasswordField;

    public ChoiceBox<String> AccountTypeChoicebox;
    public TextField Addressfield;
    public Button saveChangesButton;
    public TextArea itemDetailsArea;
    public TextField NumberField;
    public TextField balanceField;

    public void initialize() throws IOException {
        AccountTypeChoicebox.getItems().addAll("Guest", "Regular", "VIP");
        AccountTypeChoicebox.setValue("Guest");
        UserFileReader temp = new UserFileReader();
        ArrayList<UserModel> Userlist = temp.readFileUser();
        for (UserModel user : Userlist) {
            ID.getItems().add(user.getId());
            ID.setValue("Select User to Edit");
        }
        ID.setOnAction(event -> {
            String searchId = ID.getValue();
            for (UserModel user : Userlist) {
                if (user.getId().equals(searchId)) {
                    itemDetailsArea.setText(user.toString());
                    break; // Exit the loop once a match is found
                }
            }
        });

    }

    public void saveChanges() throws IOException {
        String searchId = ID.getValue();
        UserFileReader reader = new UserFileReader();
        UsersFileWriter writer = new UsersFileWriter();

        for (UserModel user : reader.readFileUser()) {
            if (Objects.equals(user.getId(), searchId)) {
                if (itemIdField != null && !itemIdField.getText().isEmpty()) {
                    user.setId(itemIdField.getText());
                }

                if (!Addressfield.getText().isEmpty()) {
                    user.setAddress(Addressfield.getText());
                }

                if (!PasswordField.getText().isEmpty()) {
                    user.setPassword(PasswordField.getText());
                }

                if (!UsernameField.getText().isEmpty()) {
                    user.setUsername(UsernameField.getText());
                }

                if (!NumberField.getText().isEmpty()) {
                    user.setPhoneNumber(Integer.parseInt(NumberField.getText()));
                }

                if (AccountTypeChoicebox != null && !AccountTypeChoicebox.getValue().isEmpty()) {
                    user.setAccountType(AccountTypeChoicebox.getValue());
                }
                if (!balanceField.getText().isEmpty()) {
                    user.setBalance(Integer.parseInt(balanceField.getText()));
                }

                itemDetailsArea.setText(user.toString());
            }
        }

        writer.UserWriteFile(reader.getUserList());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Changes saved successfully.");
        alert.showAndWait();


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
