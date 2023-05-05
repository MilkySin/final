package com.example.hello2.Controller;
//login screen
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

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
    private String accountType;

    @FXML
    void handleLogIn(ActionEvent event) {
        // Validate the format of the ID field
        String ID = IDField.getText().trim();
        if (!ID.matches("C\\d{3}")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid ID");
            alert.setHeaderText(null);
            alert.setContentText("The ID should start with a capital 'C' followed by three digits.");
            alert.showAndWait();
            return;
        }

        // Read the user info from file and check if the credentials match
        String password = passwordField.getText();
        File file = new File("userinfo.txt");
        try (Scanner scanner = new Scanner(file)) {
            String username = null;
            String storedPassword = null;
            String storedID = null;
//            String accountType = null;
            while (scanner.hasNextLine()) {
               accountType = null;
                String line = scanner.nextLine();
                if (line.startsWith("Username:")) {
                    username = line.substring("Username:".length()).trim();
                } else if (line.startsWith("Password:")) {
                    storedPassword = line.substring("Password:".length()).trim();
                } else if (line.startsWith("ID:")) {
                    storedID = line.substring("ID:".length()).trim();
                } else if (line.startsWith("Account Type:")) {
                    accountType = line.substring("Account Type:".length()).trim();}
                if (username != null && storedPassword != null && storedID != null && accountType != null) {
                    if (storedID.equals(ID) && storedPassword.equals(password)) {
                        if(Objects.equals(username, "Admin")){
                            Path path = Paths.get("src/main/resources/com/example/hello2/AddItems.fxml");
                            FXMLLoader loader = new FXMLLoader(path.toUri().toURL());

                            Parent root = loader.load();
                            Scene scene = new Scene(root);
                            Stage stage = (Stage) LogIn.getScene().getWindow();
                            stage.setScene(scene);
//                            AddItemController controller = loader.getController();
                            stage.show();

                        }else {
                            // If the credentials match, go to Scene4
                            Path path = Paths.get("src/main/resources/com/example/hello2/CheckAccount.fxml");
                            FXMLLoader loader = new FXMLLoader(path.toUri().toURL());

                            Parent root = loader.load();
                            Scene scene = new Scene(root);
                            Stage stage = (Stage) LogIn.getScene().getWindow();
                            stage.setScene(scene);
                            CheckAccountController controller = loader.getController();
                            controller.setUserName(username); // Set the username in Scene4
                            controller.setAccount(accountType); // Set the Account type in Scene4
                            stage.show();
                        }

                        return;

                    }
                }
            }
            // If we reach here, the file was read completely but the credentials were not found
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Credentials");
            alert.setHeaderText(null);
            alert.setContentText("The ID and/or password is incorrect.");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
