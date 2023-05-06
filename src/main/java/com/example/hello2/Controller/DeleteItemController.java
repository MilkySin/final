package com.example.hello2.Controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeleteItemController {
    public ChoiceBox<String> IDchoicebox;
    public Button delete;
    public Button back;

    public void initialize() {
        Task<List<String>> task = new Task<>() {
            @Override
            protected List<String> call() throws Exception {
                List<String> lines = Files.readAllLines(Paths.get("new_items.txt"));
                return new ArrayList<>(lines);
            }
        };
        task.setOnSucceeded(e -> {
            List<String> contentList = task.getValue();
            for (String line : contentList) {
                if (line.startsWith("ID")) {
                    String item = line.trim();
                    IDchoicebox.getItems().add(item);
                    IDchoicebox.setValue("Select Item to Delete");
                }
            }
        });
        task.setOnFailed(e -> {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error reading file: " + task.getException().getMessage());
            alert.showAndWait();
        });

        new Thread(task).start();
    }

    @FXML
    void Delete() throws IOException {
        String selectedID = IDchoicebox.getValue();
        if (selectedID == null || selectedID.equals("Select Item to Delete")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an item to delete.");
            alert.showAndWait();
            return;
        }
        Path path = Paths.get("new_items.txt");
        List<String> fileContent = new ArrayList<>(Files.readAllLines(path));
        try (PrintWriter writer = new PrintWriter(new FileWriter(path.toFile()))) {
            boolean found = false;
            for (int i = 0; i < fileContent.size(); i++) {
                String line = fileContent.get(i);
                if (line.startsWith("ID") && line.trim().equals(selectedID)) {
                    found = true;
                    for (int j = i; j < i + 7; j++) {
                        fileContent.remove(i);
                    }
                    break;
                }
            }
            if (found) {
                for (String line : fileContent) {
                    writer.println(line);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Item deleted successfully.");
                alert.showAndWait();
                IDchoicebox.getItems().remove(selectedID);
                IDchoicebox.setValue("Select Item to Delete");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Item not found.");
                alert.showAndWait();
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error deleting item: " + e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
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
