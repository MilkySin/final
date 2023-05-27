package com.example.hello2.Controller.Users;
//sign up screen goes to scene3

import com.example.hello2.Model.UserModel;
import com.example.hello2.Reader.UserFileReader;
import com.example.hello2.Writer.UsersFileWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Adduser {
    public Button back;
    @FXML
    private TextField UsernameField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private TextField AddressField;

    @FXML
    private TextField NumberField;
    @FXML
    private TextField Balance;
    @FXML
    private ChoiceBox<String> AccountType;
    @FXML
    private Text Success;

    public void Back() throws IOException {
        Log(back);
    }

    public void initialize() {
        AccountType.getItems().addAll("Guest", "Regular", "VIP");
        AccountType.setValue("Guest");
    }

    static void Log(Button back) throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/FXML/SceneAdmin.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void create(ActionEvent event) throws IOException {
        String username = UsernameField.getText().trim();
        String password = PasswordField.getText().trim();
        String address = AddressField.getText().trim().trim();
        String number = NumberField.getText().trim();
        String accountType = AccountType.getValue();
        String balance = Balance.getText().trim();
        int numReturned = 0;
        UsersFileWriter writer = new UsersFileWriter();
        UserFileReader read = new UserFileReader();

        String passwordRegex = "^(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|.<>?])(?=.*\\d)(?=.*[A-Z])[^,]{8,}$";
        String phoneRegex = "\\d{3,20}";
        String noEmptyRegex = "\\S+";


        ArrayList<UserModel> userModelArrayList = read.readFileUser();
        String ID = "C" + (userModelArrayList.size() + 1);

        Alert error = new Alert(Alert.AlertType.ERROR);

        if (!(username.length() >= 10 && username.length() <= 100)) {
            error.setContentText("Invalid Username, must be between 10 and 100");
            error.showAndWait();
            return;
        }

        if (!username.matches(noEmptyRegex) || !address.matches(noEmptyRegex) || !password.matches(noEmptyRegex) || !number.matches(noEmptyRegex) || !balance.matches(noEmptyRegex)) {
            error.setContentText("Null field");
            error.showAndWait();
        } else if (!password.matches(passwordRegex)) {
            error.setContentText("Invalid Password");
            error.showAndWait();
        } else if (!number.matches(phoneRegex)) {
            error.setContentText("Invalid Phone number");
            error.showAndWait();
        } else if (!(password.matches(passwordRegex)) && number.matches(phoneRegex)) {
            error.setContentText("Invalid Password and Phone number");
            error.showAndWait();
        } else {
            UserModel registeredUser = new UserModel(username, password, ID, address, accountType,
                    number, Double.parseDouble(balance));
            Success.setText("Account created successfully, ID: " + registeredUser.getId());
            registeredUser.setNumReturned(numReturned);
            read.getUserList().add(registeredUser);
            writer.UserWriteFile(read.getUserList());
        }
        // Load Scene 3 and pass the username as a parameter
//        Path path = Paths.get("src/main/resources/com/example/hello2/FXML/LoginSignup.fxml");
//        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
//
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
//
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        if (stage != null) {
//            stage.setScene(scene);
//            stage.setResizable(false);
//            stage.show();
//        } else {
//            System.out.println("Error: Stage is null");
    }
}
