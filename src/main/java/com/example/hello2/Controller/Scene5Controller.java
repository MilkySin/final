package com.example.hello2.Controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Scene5Controller {

    @FXML
    private Button viewTextFileButton;

    @FXML
    private ProgressBar progressBar;

    @FXML
    public void viewTextFile(ActionEvent event) {
        Task<String> task = new Task<String>() {
            @Override
            protected String call() throws Exception {
                String content = Files.readString(Paths.get("C:/Users/ShirinLP/IdeaProjects/Hello2/new_items.txt"));
                return content;
            }
        };

        progressBar.progressProperty().bind(task.progressProperty());
        progressBar.setVisible(true);

        task.setOnSucceeded(e -> {
            String content = task.getValue();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, content);
            alert.setHeaderText("Text File Contents");
            alert.showAndWait();
            progressBar.setVisible(false);
        });

        task.setOnFailed(e -> {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error reading file: " + task.getException().getMessage());
            alert.showAndWait();
            progressBar.setVisible(false);
        });

        new Thread(task).start();
    }
}
