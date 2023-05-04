package com.example.hello2.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Scene3Controller {
    @FXML
    private AnchorPane root;

    @FXML
    public Label accountTypeLabel;
    public Button next;
    @FXML
    private Label welcomeLabel;
    private String accountType;


    public void setUsername(String username) {
        welcomeLabel.setText("Welcome, " + username);
    }


    public void setAccount(String accountType) {
        accountTypeLabel.setText("Account Type: "+ accountType);

    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
        accountTypeLabel.setText("Account Type: " + accountType);
    }
}
