package com.dreamcatcher.genie.app.controllers;

import com.dreamcatcher.genie.app.Main;
import com.dreamcatcher.genie.app.core.Authenticator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class DeveloperController {
    @FXML
    private Label loginError;

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    protected void onReloadButtonClick(ActionEvent event) throws IOException {
        var app = new Main();
        app.start(new Stage());
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    protected void onLoginButtonClick(ActionEvent event) throws Exception {
        var authentication = new Authenticator();
        if (authentication.attempt(email.getText(), password.getText())) {
            loginError.setText("Welcome to JavaSex Application!");
            return;
        }
        loginError.setText("Invalid Credentials");
    }
}