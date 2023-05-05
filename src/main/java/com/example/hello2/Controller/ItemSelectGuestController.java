package com.example.hello2.Controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class ItemSelectGuestController {
    @FXML
    public Label selectedItemLabel;
    @FXML
    private Label label;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button viewTextFileButton;

    @FXML
    public void viewTextFile(ActionEvent event) {
        Task<List<String>> task = new Task<List<String>>() {
            @Override
            protected List<String> call() throws Exception {
                List<String> lines = Files.readAllLines(Paths.get("C:/Users/ShirinLP/IdeaProjects/Hello2/new_items.txt"));
                return new ArrayList<String>(lines);
            }
        };

        progressBar.progressProperty().bind(task.progressProperty());
        progressBar.setVisible(true);

        task.setOnSucceeded(e -> {
            List<String> contentList = task.getValue();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Select an item from the list:");

            VBox vbox = new VBox(); // create a container for the CheckBoxes

            List<CheckBox> checkBoxList = new ArrayList<>(); // keep track of selected CheckBoxes
            ToggleGroup group = new ToggleGroup();
            final int[] selectedCount = {0}; // keep track of selected CheckBox count
            String item = null;
            for (String line : contentList) {
                if (line.startsWith("ID")) {
                    item = line.trim();
                    HBox hbox = new HBox(); // create a container for the button and the text
                    Button button = new Button("Select");
                    String finalItem = item;
                    button.setOnAction((ActionEvent e2) -> {
                        setLabelText(finalItem);
                    });
                    RadioButton radioButton = new RadioButton(item);
                    radioButton.setToggleGroup(group);
                    hbox.getChildren().addAll(button, radioButton); // add the button and the text to the container
                    vbox.getChildren().add(hbox); // add the container to the main container
                } else {
                    vbox.getChildren().add(new Label(line)); // add the text to the main container
                }
            }
            CheckBox checkBox = new CheckBox(item);
            checkBox.setOnAction((ActionEvent event1) -> {
                if (checkBox.isSelected()) {
                    if (selectedCount[0] < 2) {
                        selectedCount[0]++;
                    } else {
                        checkBox.setSelected(false);
                    }
                } else {
                    selectedCount[0]--;
                }
            });

            vbox.getChildren().add(checkBox); // add the CheckBox to the container
            checkBoxList.add(checkBox);

            alert.getDialogPane().setContent(vbox); // set the container as the content of the dialog pane

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                StringBuilder selectedItemBuilder = new StringBuilder();
                for (CheckBox checkBox1 : checkBoxList) {
                    if (checkBox1.isSelected()) {
                        selectedItemBuilder.append(checkBox1.getText()).append(", ");
                    }
                }
                String selectedItem = selectedItemBuilder.toString();
                if (!selectedItem.isEmpty()) {
                    selectedItem = selectedItem.substring(0, selectedItem.length() - 2); // remove the trailing ", "
                }
                setLabelText(selectedItem);
            }
            progressBar.setVisible(false);
        });

        task.setOnFailed(e -> {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error reading file: " + task.getException().getMessage());
            alert.showAndWait();
            progressBar.setVisible(false);
        });

        new Thread(task).start();
    }



    public void setLabelText(String text) {
        selectedItemLabel.setText(text);
    }
}