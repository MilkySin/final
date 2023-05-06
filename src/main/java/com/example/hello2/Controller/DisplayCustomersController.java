package com.example.hello2.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DisplayCustomersController {
    @FXML
    public TextArea customerTextArea;



    @FXML
    private Button displayButton;

    @FXML
    void display() {
        Path path = Paths.get("userinfo.txt");
        File file = new File(path.toUri());
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
            customerTextArea.setText(content.toString());
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error reading file: " + e.getMessage());
            alert.showAndWait();
        }
    }
}
