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

public class SessionController implements Initializable {
    @FXML
    private Label title;

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        App.setTitle(String.format("%s: %s", App.applicationName, "login"));
    }

    @FXML
    protected void onLoginButtonClick(ActionEvent event) {
        var loginForm = new LoginForm();
        var authenticator = new Authenticator(new Database());
        var _email = email.getText();
        var _password = password.getText();

        if (loginForm.validate(_email, _password)) {
            if (authenticator.attempt(_email, _password)) {
                //        App.redirect(); // Moves to rental page
                return;
            }
            loginForm.addCustomError("No matching account found for that email address and password.");
        }

        new Alert(Alert.AlertType.ERROR, loginForm.format()).show();
    }
}