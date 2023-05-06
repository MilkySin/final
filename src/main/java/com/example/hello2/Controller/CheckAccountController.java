package com.example.hello2.Controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CheckAccountController {

    @FXML
    private AnchorPane root;

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label accountTypeLabel;

    @FXML
    private Button nextButton;

    private String accountType;
    @FXML
    private Button back;


    public void setAccount(String accountType) {
        this.accountType = accountType;
        accountTypeLabel.setText("Account Type: "+ accountType);
    }

    public void setUserName(String username) {
        welcomeLabel.setText("Welcome, " + username);
    }

    @FXML
    public void handleNextButton(ActionEvent event) throws IOException {
        setAccount(this.accountType);
        if (this.accountType == null) {
            System.out.println("Account type not set");
            return;
        }
        Stage stage = (Stage) root.getScene().getWindow();
        switch (this.accountType) {
            case "VIP" -> {
                Parent scene5Parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hello2/VIPUser.fxml")));
                Scene scene5 = new Scene(scene5Parent);
                stage.setScene(scene5);
            }
            case "Regular" -> {
                Parent scene6Parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hello2/RegularUser.fxml")));
                Scene scene6 = new Scene(scene6Parent);
                stage.setScene(scene6);
            }
            case "Guest" -> {
                Parent scene8Parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hello2/GuestUser.fxml")));
                Scene scene8 = new Scene(scene8Parent);
                stage.setScene(scene8);
            }
            default -> System.out.println("Invalid account type: " + this.accountType);
        }
        stage.show();
    }
//    }
//    switch (this.accountType) {
//        case "VIP":
//            Parent scene5Parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hello2/VIPUser.fxml")));
//            Scene scene5 = new Scene(scene5Parent);
//            stage.setScene(scene5);
//            break;
//        case "Regular":
//            Parent scene6Parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hello2/RegularUser.fxml")));
//            Scene scene6 = new Scene(scene6Parent);
//            stage.setScene(scene6);
//            break;
//        case "Guest":
//            Parent scene8Parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hello2/GuestUser.fxml")));
//            Scene scene8 = new Scene(scene8Parent);
//            stage.setScene(scene8);
//            break;
//        default:
//            System.out.println("Invalid account type: " + this.accountType);
//            break;
//    }
//        stage.show();
//}
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
