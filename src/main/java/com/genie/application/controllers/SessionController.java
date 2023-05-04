package com.genie.application.controllers;

import com.genie.application.core.App;
import com.genie.application.core.Authenticator;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SessionController implements Initializable {
    @FXML
    private Label loginError;

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        loginError.setText("Sign in to " + App.APP_NAME);
    }

    @FXML
    protected void onLoginButtonClick(ActionEvent event) throws Exception {
        var authentication = App.resolve("Core/Authenticator", Authenticator.class);
        if (! authentication.attempt(email.getText(), password.getText())) {
            loginError.setText("No matching account found.");
            return;
        }
        App.redirect("dataview.fxml", 320, 240);
    }
}