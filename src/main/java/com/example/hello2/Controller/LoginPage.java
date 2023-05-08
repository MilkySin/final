package com.example.hello2.Controller;
//login screen
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import com.example.hello2.Modal.ItemModel;
import com.example.hello2.Modal.UserModel;
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
    private Button back;

    @FXML
    void handleLogIn(ActionEvent event) throws IOException {
        // Validate the format of the ID field
//        String ID = IDField.getText().trim();
//        if (!ID.matches("C\\d{3}")) {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Invalid ID");
//            alert.setHeaderText(null);
//            alert.setContentText("The ID should start with a capital 'C' followed by three digits.");
//            alert.showAndWait();
//            return;
//        }
//        int userID = Integer.parseInt(ID.substring(1));
//
//        // Read the user info from file and check if the credentials match
//        String password = passwordField.getText();
//        File file = new File("userinfo.txt");


//        try (Scanner scanner = new Scanner(file)) {
//            String username = null;
//            String storedPassword = null;
//            String storedID = null;
////            String accountType = null;
//            while (scanner.hasNextLine()) {
//               accountType = null;
//                String line = scanner.nextLine();
//                if (line.startsWith("Username:")) {
//                    username = line.substring("Username:".length()).trim();
//                } else if (line.startsWith("Password:")) {
//                    storedPassword = line.substring("Password:".length()).trim();
//                } else if (line.startsWith("ID:")) {
//                    storedID = line.substring("ID:".length()).trim();
//                } else if (line.startsWith("Account Type:")) {
//                    accountType = line.substring("Account Type:".length()).trim();}
//                if (username != null && storedPassword != null && storedID != null && accountType != null) {
//                    if (storedID.equals(ID) && storedPassword.equals(password)) {
//                        if(Objects.equals(username, "Admin")){
//                            Path path = Paths.get("src/main/resources/com/example/hello2/SceneAdmin.fxml");
//                            FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
//
//                            Parent root = loader.load();
//                            Scene scene = new Scene(root);
//                            Stage stage = (Stage) LogIn.getScene().getWindow();
//                            stage.setScene(scene);
//                            SceneAdminController controller = loader.getController();
//                            stage.show();
//
//                        }else {
//                            // If the credentials match, go to Scene4
//                            Path path = Paths.get("src/main/resources/com/example/hello2/CheckAccount.fxml");
//                            FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
//
//                            Parent root = loader.load();
//                            Scene scene = new Scene(root);
//                            Stage stage = (Stage) LogIn.getScene().getWindow();
//                            stage.setScene(scene);
//                            CheckAccountController controller = loader.getController();
//                            controller.setUserName(username); // Set the username in Scene4
//                            controller.setAccount(accountType); // Set the Account type in Scene4
//                            controller.setUserID(userID);
//                            stage.show();
//                        }
//
//                        return;
//
//                    }
//                }
//            }
        // If we reach here, the file was read completely but the credentials were not found
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Invalid Credentials");
//            alert.setHeaderText(null);
//            alert.setContentText("The ID and/or password is incorrect.");
//            alert.showAndWait();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

        UserFileReader temp = new UserFileReader();
        ArrayList<UserModel> itemList  = temp.readUser();

        String ID = IDField.getText().trim();
        String password = passwordField.getText().trim();
        if (!ID.matches("C\\d{3}")) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid ID");
        alert.setHeaderText(null);
        alert.setContentText("The ID should start with a capital 'C' followed by three digits.");
        alert.showAndWait();
    }

        for(UserModel users : itemList){
            if (ID.equals(users.getID()) && password.equals(users.getPassword())){
                if(Objects.equals(users.getID(), "C000")){
                    Path path = Paths.get("src/main/resources/com/example/hello2/SceneAdmin.fxml");
                    FXMLLoader loader = new FXMLLoader(path.toUri().toURL());

                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) LogIn.getScene().getWindow();
                    stage.setScene(scene);
                    SceneAdminController controller = loader.getController();
                    stage.show();
                } else{
                    Path path = Paths.get("src/main/resources/com/example/hello2/CheckAccount.fxml");
                    FXMLLoader loader = new FXMLLoader(path.toUri().toURL());

                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) LogIn.getScene().getWindow();
                    stage.setScene(scene);
                    CheckAccountController controller = loader.getController();
                    controller.setUserName(users.getUsername()); // Set the username in Scene4
                    controller.setAccount(accountType); // Set the Account type in Scene4
                    controller.setUserID(users.getID());
                    stage.show();
                }
            }
        }
//        if (line.startsWith("Username:")) {
//                    username = line.substring("Username:".length()).trim();
//                } else if (line.startsWith("Password:")) {
//                    storedPassword = line.substring("Password:".length()).trim();
//                } else if (line.startsWith("ID:")) {
//                    storedID = line.substring("ID:".length()).trim();
//                } else if (line.startsWith("Account Type:")) {
//                    accountType = line.substring("Account Type:".length()).trim();}
//                if (username != null && storedPassword != null && storedID != null && accountType != null) {
//                    if (storedID.equals(ID) && storedPassword.equals(password)) {


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
