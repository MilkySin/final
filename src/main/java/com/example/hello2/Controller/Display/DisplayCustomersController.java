package com.example.hello2.Controller.Display;

//Fixed

import com.example.hello2.Controller.Users.EditUser;
import com.example.hello2.Model.UserModel;
import com.example.hello2.Reader.UserFileReader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class DisplayCustomersController {
    public Button VIP;
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

        for (UserModel Users : reader.readFileUser()) {
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

        for (UserModel users : read.getUserList()) {
            content.append(users.toString());

        }

        customerTextArea.setText(content.toString());
    }

    @FXML
    public void Back() throws IOException {
        EditUser.Log(back);
    }

    @FXML
    void SortByName() throws IOException {
        System.out.println("Sorting by name...");
        UserFileReader reader = new UserFileReader();
        StringBuilder content = new StringBuilder();

        // Sort by name
        reader.readFileUser().sort(Comparator.comparing(UserModel::getUsername));

        customerTextArea.clear();
        for (UserModel users : reader.getUserList()) {
            content.append(users.toString());
        }
        customerTextArea.setText(content.toString());
    }


    public void Regular() throws IOException {
        UserFileReader temp = new UserFileReader();
        ArrayList<UserModel> userList = temp.readFileUser();
        ArrayList<UserModel> regularList = new ArrayList<>();
        for (UserModel User : userList) {
            if (Objects.equals(User.getAccountType(), "Regular")) {
                regularList.add(User);
            }
        }
        customerTextArea.setText(regularList.toString());
    }

    public void Guest() throws IOException {
        UserFileReader temp = new UserFileReader();
        ArrayList<UserModel> userList = temp.readFileUser();
        ArrayList<UserModel> guestList = new ArrayList<>();
        for (UserModel User : userList) {
            if (Objects.equals(User.getAccountType(), "Guest")) {
                guestList.add(User);
            }
        }
        customerTextArea.setText(guestList.toString());
    }

    public void VIP() throws IOException {
        UserFileReader temp = new UserFileReader();
        ArrayList<UserModel> userList = temp.readFileUser();
        ArrayList<UserModel> VipList = new ArrayList<>();
        for (UserModel User : userList) {
            if (Objects.equals(User.getAccountType(), "VIP")) {
                VipList.add(User);
            }
        }
        customerTextArea.setText(VipList.toString());
    }
}
