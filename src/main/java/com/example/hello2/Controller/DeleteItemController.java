package com.example.hello2.Controller;

import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DeleteItemController {

    public void initialize() {
        Task<List<String>> task = new Task<>() {
            @Override
            protected List<String> call() throws Exception {
                List<String> lines = Files.readAllLines(Paths.get("new_items.txt"));
                return new ArrayList<>(lines);
            }
        };
        task.setOnSucceeded(e -> {
            List<String> contentList = task.getValue(); // keep track of selected CheckBoxes
            VBox vbox = new VBox();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            List<CheckBox> checkBoxList = new ArrayList<>();
            for (String line : contentList) {
                if (line.startsWith("Title")) {
                    String item = line.trim();
                    CheckBox checkBox = new CheckBox(item);
                    vbox.getChildren().add(checkBox); // add the CheckBox to the container
                    checkBoxList.add(checkBox);
                }
            }
            alert.getDialogPane().setContent(vbox); // set the container as the content of the dialog pane

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
