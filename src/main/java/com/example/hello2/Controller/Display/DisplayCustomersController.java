package com.example.hello2.Controller.Display;

//Fixed
import com.example.hello2.Model.ItemModel;
import com.example.hello2.Model.UserModel;
import com.example.hello2.Reader.ItemsFileReader;
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
import java.util.Comparator;
import java.util.Objects;

public class DisplayCustomersController {
    public  Button VIP ;
    @FXML
    public TextArea customerTextArea;

    public Button back;
    public Button SortByID;
    public Button SortByName;
    public Button Regular;
    public Button Guest;


    @FXML
    public void initialize() throws Exception {
        UserFileReader reader = new UserFileReader();
        StringBuilder fileContent = new StringBuilder();

        for(UserModel Users : reader.readFileUser()){
            fileContent.append(Users.toString());
        }
        customerTextArea.setText(fileContent.toString());

    }

    @FXML


    void SortByID() throws IOException {
        System.out.println("Sorting by ID...");
        UserFileReader read = new UserFileReader();
        StringBuilder content = new StringBuilder();

        // Sort by ID
        read.readFileUser().sort(Comparator.comparing(UserModel::getId));
        customerTextArea.clear();

        for(UserModel users: read.getUserList()){
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

    @FXML
    void SortByName() throws IOException {
        System.out.println("Sorting by name...");
        UserFileReader reader = new UserFileReader();
        StringBuilder content = new StringBuilder();

        // Sort by name
        reader.readFileUser().sort(Comparator.comparing(UserModel::getUsername));

        customerTextArea.clear();
        for(UserModel users: reader.getUserList()){
            content.append(users.toString());
        }
        customerTextArea.setText(content.toString());
    }


    public void Regular(ActionEvent event) throws IOException {
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

    public void Guest(ActionEvent event) throws IOException {
        UserFileReader temp = new UserFileReader();
        ArrayList<UserModel> Userlist = temp.readFileUser();
        ArrayList<UserModel> Guestlist=new ArrayList<>();
        for (UserModel User : Userlist){
            if (Objects.equals(User.getAccountType(), "Guest")){
                Guestlist.add(User);
            }
        }
        customerTextArea.setText(Guestlist.toString());
    }

    public void VIP(ActionEvent event) throws IOException {
        UserFileReader temp = new UserFileReader();
        ArrayList<UserModel> Userlist = temp.readFileUser();
        ArrayList<UserModel> Viplist=new ArrayList<>();
        for (UserModel User : Userlist){
            if (Objects.equals(User.getAccountType(), "VIP")){
                Viplist.add(User);
            }
        }
        customerTextArea.setText(Viplist.toString());
    }
}
