package com.example.hello2.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Scene5Controller {

    @FXML
    private Button viewTextFileButton;

    @FXML
    public void viewTextFile() {
        try {
            String content = Files.readString(Paths.get("C:/Users/ShirinLP/IdeaProjects/Hello2/new_items.txt"));
            Alert alert = new Alert(Alert.AlertType.INFORMATION, content);
            alert.setHeaderText("Text File Contents");
            alert.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error reading file: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void viewFile(ActionEvent event) {
        viewTextFile();
    }
}
