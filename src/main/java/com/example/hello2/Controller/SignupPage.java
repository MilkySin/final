package com.example.hello2.Controller;
//sign up screen goes to scene3
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.ArrayList;

public class SignupPage {
    public Button back;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Scene previousScene;

    @FXML
    private ChoiceBox<String> accountTypeChoiceBox;

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

    public void setPreviousScene(Scene previousScene) {
        this.previousScene = previousScene;
    }
    public void initialize() {
        ObservableList<String> accountTypes = FXCollections.observableArrayList("Regular", "Guest", "VIP");
        accountTypeChoiceBox.setItems(accountTypes);
        accountTypeChoiceBox.setValue("Regular");
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



    public void signup(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String ID = IDField.getText();
        String address = AddressField.getText();
        String number = NumberField.getText();
        String accountType = accountTypeChoiceBox.getValue();

        if (username.isEmpty() || password.isEmpty() || ID.isEmpty() || address.isEmpty() || number.isEmpty() || accountType.isEmpty()) {
            System.out.println("Please enter all required fields!");
            return;
        }

        // Check ID format
        if (!ID.matches("C\\d{3}")) {
            System.out.println("ID must start with a capital C and be followed by 3 digits!");
            return;
        }

        // Check password constraints
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasNumber = false;
        boolean hasSpecialChar = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
        }

        if (password.length() < 8 || !hasUpperCase || !hasLowerCase || !hasNumber || !hasSpecialChar) {
            System.out.println("Password must be at least 8 characters long and contain a mix of uppercase and lowercase letters, numbers, and special characters!");
            return;
        }

        // Check phone number format
        if (!number.matches("\\d+")) {
            System.out.println("Phone number must only contain digits!");
            return;
        }

        // Check if ID already exists
//        BufferedReader reader = new BufferedReader(new FileReader(Paths.get("userinfo.txt").toFile()));
//        String line;
//
//        while ((line = reader.readLine()) != null) {
//            if (line.startsWith("ID: " + ID)) {
//                System.out.println("ID already exists. Please enter a different ID.");
//                reader.close();
//                return;
//            }
//        }
//        reader.close();

        ArrayList<>

        // Save username, password, ID, address, phone number, and account type to a text file
        File file = new File("userinfo.txt");
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write( username + "," + password + "," + ID + "," + address + "," + number + "," + accountType);
//        bw.write("Password: " + password + "\n");
//        bw.write("ID: " + ID + "\n");
//        bw.write("Address: " + address + "\n");
//        bw.write("Phone Number: " + number + "\n");
//        bw.write("Account Type: " + accountType + "\n");
        bw.write("\n");
        bw.close();
        fw.close();

        // Load Scene 3 and pass the username as a parameter
        Path path = Paths.get("src/main/resources/com/example/hello2/Scene3.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());

        Parent root = loader.load();
        Scene scene = new Scene(root);

        UNUSED controller = loader.getController();
        controller.setUsername(username);
        controller.setAccount(accountType);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (stage != null) {
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Error: Stage is null");
        }
    }





}
