package com.genie.application.controller;

import com.genie.application.core.App;
import com.genie.application.core.Authenticator;
import com.genie.application.core.Database;
import com.genie.application.form.LoginForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    private Label title;

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        title.setText(App.applicationName);
    }

    @FXML
    protected void onLoginButtonClick(ActionEvent event) {
        var loginForm = new LoginForm();
        var authenticator = new Authenticator(new Database());
        var _email = email.getText();
        var _password = password.getText();

        if (loginForm.validate(_email, _password)) {
            // implement register logic here
            loginForm.addCustomError("No matching account found for that email address and password.");
        }

        var alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(loginForm.format());
    }
}