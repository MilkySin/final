package com.example.hello2.Controller.Display;
//sign up screen goes to scene3

import com.example.hello2.Model.UserModel;
import com.example.hello2.Reader.UserFileReader;
import com.example.hello2.Writer.UsersFileWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class SignupPage {
    public Button back;
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField IDField;

    @FXML
    private TextField AddressField;

    @FXML
    private TextField NumberField;

    @FXML
    private TextField Balance;

    public void Back() throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/LoginSignup.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void signup(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String ID = IDField.getText();
        String address = AddressField.getText();
        String number = NumberField.getText();
        String accountType = "Guest";
        String balance = Balance.getText();
        int numReturned = 0;
        UsersFileWriter writer = new UsersFileWriter();
        UserFileReader read = new UserFileReader();

        String passwordRegex = "^(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|.<>?])(?=.*\\d)(?=.*[A-Z])[^,]{8,}$";
        String IDRegex = "^C\\d{3}$";

        for (UserModel user : read.readFileUser()) {
            if (Objects.equals(user.getId(), ID)) {
                System.out.println("Same ID");
                return;
            }
        }

        if (password.matches(passwordRegex) && ID.matches(IDRegex)) {
            UserModel registeredUser = new UserModel(username, password, ID, address, accountType,
                                                     Integer.parseInt(number), Float.parseFloat(balance));
            registeredUser.setNumReturned(numReturned);
            read.getUserList().add(registeredUser);
            writer.UserWriteFile(read.getUserList());

        } else {
            System.out.println("Invalid Pass or ID");
        }

        // Load Scene 3 and pass the username as a parameter
        Path path = Paths.get("src/main/resources/com/example/hello2/LoginSignup.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());

        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (stage != null) {
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } else {
            System.out.println("Error: Stage is null");
        }
    }
}