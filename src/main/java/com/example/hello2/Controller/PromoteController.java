package com.example.hello2.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PromoteController {
    @FXML
    private TextField searchIdField;
    public Button searchCustomerButton;
    public TextArea CustomerDetail;
    public ChoiceBox<String> PromoteChoice;
    private Path filePath = Paths.get("userinfo.txt");
    private String accountType = null;

    public Text text;
    public Button back;

    public void initialize(){
        PromoteChoice.getItems().addAll("Guest", "Regular", "VIP");
        PromoteChoice.setValue("Guest");

        text.setVisible(false);
    }
    public void searchItem(ActionEvent event) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            String result;
            String idToSearch = searchIdField.getText(); // Change this to the ID you want to search for
            boolean found = false;
            String username = null, password = null, id = null, address = null, phoneNumber = null;

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(":\\s");
                if (fields[0].equals("Username")) {
                    username = fields[1];
                } else if (fields[0].equals("Password")) {
                    password = fields[1];
                } else if (fields[0].equals("ID") && fields[1].equals(idToSearch)) {
                    found = true;
                    id = fields[1];
                } else if (found) {
                    switch (fields[0]) {
                        case "Address":
                            address = fields[1];
                            break;
                        case "Phone Number":
                            phoneNumber = fields[1];
                            break;
                        case "Account Type":
                            accountType = fields[1];
                            break;
                    }
                }
                if (found && fields[0].equals("")) {
                    break;
                }
            }

            if (found) {
                result = "Username: " + username + "\n" +
                        "Password: " + password + "\n" +
                        "ID: " + id + "\n" +
                        "Address: " + address + "\n" +
                        "Phone Number: " + phoneNumber + "\n" +
                        "Account Type: " + accountType + "\n";
                CustomerDetail.setText(result);
            } else {
                System.out.println("User with ID " + idToSearch + " not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveChange(ActionEvent e) throws IOException {
        String idToModify = searchIdField.getText();
        String newAccountType = PromoteChoice.getValue();
        List<String> fileContent = new ArrayList<>(Files.readAllLines(filePath));

        boolean found = false;

        for (int i = 0; i < fileContent.size(); i++) {
            String line = fileContent.get(i);
            String[] fields = line.split(":\\s");
            if (fields[0].equals("ID") && fields[1].equals(idToModify)) {
                found = true;
                fileContent.set(i + 3, "Account Type: " + newAccountType);
                break;
            }
        }

        if (Objects.equals(accountType, newAccountType)) {
            text.setVisible(true);
            text.setFill(Color.RED);
            text.setText("New account type is the same as the old one.");
        } else if (!found) {
            text.setVisible(true);
            text.setFill(Color.RED);
            text.setText("User with ID " + idToModify + " not found.");
        } else {
            Files.write(filePath, fileContent, StandardCharsets.UTF_8);
            text.setVisible(true);
            text.setFill(Color.GREEN);
            text.setText("Account Type for user with ID " + idToModify + " modified to " + newAccountType);
        }
    }
    public void Back(ActionEvent event) throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/SceneAdmin.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}

