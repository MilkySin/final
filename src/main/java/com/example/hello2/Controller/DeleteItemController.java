package com.example.hello2.Controller;

import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DeleteItemController {
    public ChoiceBox<String> IDchoicebox;

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
                if (line.startsWith("Title")) {
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

    void Delete(){

    }
}
