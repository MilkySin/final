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
    @FXML
    private void handleNextButton() {
        Stage stage = (Stage) root.getScene().getWindow();
        if (accountType.equals("VIP")) {
            // Navigate to Scene5
            // You can load the Scene5 FXML file and set it as the root node of a new Scene
            // Then show the new Scene on the stage
        } else if (accountType.equals("Regular")) {
            // Navigate to Scene6
            // You can load the Scene6 FXML file and set it as the root node of a new Scene
            // Then show the new Scene on the stage
        } else if (accountType.equals("Guest")) {
            // Navigate to Scene7
            // You can load the Scene7 FXML file and set it as the root node of a new Scene
            // Then show the new Scene on the stage
        }
    }
}
