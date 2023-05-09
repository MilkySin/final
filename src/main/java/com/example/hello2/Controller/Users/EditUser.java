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
    public Button back;
    public TextField itemIdField;
    public Label title;
    public TextField UsernameField;
    public TextField PasswordField;

    public ChoiceBox AccountTypeChoicebox;
    public TextField Addressfield;
    public Button saveChangesButton;
    public TextArea itemDetailsArea;
    public Button searchItemButton;
    public TextField searchIdField;
    public TextField NumberField;

    public void initialize (){
        AccountTypeChoicebox.getItems().addAll("Guest","Regular","VIP");
        AccountTypeChoicebox.setValue("Guest");
    }
    public void searchUser()throws IOException{
        UserFileReader temp= new UserFileReader();
        ArrayList<UserModel> Userlist= temp.readFileUser();
        String searchID = searchIdField.getText();
        for (UserModel user : Userlist){
            if (user.getId().equals(searchID)){
                itemDetailsArea.setText(user.toString());
            }
        }
    }
    public void saveChanges() throws IOException{
        String searchId = searchIdField.getText();
        UserFileReader reader= new UserFileReader();
        UsersFileWriter writer = new UsersFileWriter();
        for(UserModel User: reader.readFileUser()){
            if(Objects.equals(User.getId(), searchId)){
                User.setId(itemIdField.getText());
                User.setAddress(itemIdField.getText());
                User.setAccountType((String) AccountTypeChoicebox.getValue());
                User.setPassword(PasswordField.getText());
                User.setUsername(UsernameField.getText());
                User.setPhoneNumber(Integer.parseInt(NumberField.getText()));


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
