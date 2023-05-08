package com.example.hello2.Controller;
//login screen
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

import com.example.hello2.Model.UserModel;
import com.example.hello2.Reader.UserFileReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginPage {
    @FXML
    private TextField IDField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button LogIn;
    @FXML
    private Button back;

    @FXML
    void handleLogIn(ActionEvent event) throws IOException {
        UserFileReader temp = new UserFileReader();
        ArrayList<UserModel> itemList = temp.readUser();

        String ID = IDField.getText().trim();
        String password = passwordField.getText().trim();
        if (!ID.matches("C\\d{3}")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid ID");
            alert.setHeaderText(null);
            alert.setContentText("The ID should start with a capital 'C' followed by three digits.");
            alert.showAndWait();
        }

        for (UserModel users : itemList) {
            if (ID.equals(users.getID()) && password.equals(users.getPassword())) {
                if (Objects.equals(users.getID(), "C000")) {
                    Path path = Paths.get("src/main/resources/com/example/hello2/SceneAdmin.fxml");
                    FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) LogIn.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } else {
                    if (Objects.equals(users.getAccountType(), "Guest")) {
                        Path path = Paths.get("src/main/resources/com/example/hello2/GuestUser.fxml");
                        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) LogIn.getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } else if (Objects.equals(users.getAccountType(), "Regular")) {
                        Path path = Paths.get("src/main/resources/com/example/hello2/RegularUser.fxml");
                        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) LogIn.getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } else if (Objects.equals(users.getAccountType(), "VIP")) {
                        Path path = Paths.get("src/main/resources/com/example/hello2/VIPUser.fxml");
                        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) LogIn.getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Invalid Credentials");
                        alert.setHeaderText(null);
                        alert.setContentText("The ID and/or password is incorrect.");
                        alert.showAndWait();
                    }
                }
            }
        }
    }

    public void Back(ActionEvent event) throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/LoginSignup.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
