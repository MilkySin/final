package com.example.hello2.Controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
    public Button back;

    @FXML
    public void viewTextFile(ActionEvent event) {
        Task<List<String>> task = new Task<List<String>>() {
            @Override
            protected List<String> call() throws Exception {
                List<String> lines = Files.readAllLines(Paths.get("new_items.txt"));
                return new ArrayList<String>(lines);
            }
        };

        progressBar.progressProperty().bind(task.progressProperty());
        progressBar.setVisible(true);

        task.setOnSucceeded(e -> {
            List<String> contentList = task.getValue();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Select an item from the list:");

            VBox vbox = new VBox();
                // create a container for the CheckBoxes

            List<CheckBox> checkBoxList = new ArrayList<>(); // keep track of selected CheckBoxes
            ToggleGroup group = new ToggleGroup();
            String item = null;

            final int[] selectedCount = {0}; // keep track of selected CheckBox count
            for (String line : contentList) {
                String finalItem = null;
                if (line.startsWith("ID")) {
                    item = line.trim();
                    // create a container for the button and the text
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


                    setLabelText(item);
                    // add the container to the main container
                } else {
                    vbox.getChildren().add(new Label(line)); // add the text to the main container
                }
            }

            ScrollPane scrollPane = new ScrollPane();
            selectedItemLabel.setVisible(false);
            scrollPane.setFitToWidth(true);
            scrollPane.setPrefHeight(200);
            scrollPane.setContent(vbox);
            scrollPane.setMaxHeight(200);
            alert.getDialogPane().setContent(scrollPane); // set the container as the content of the dialog pane

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
            selectedItemLabel.setVisible(true);
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
    public void Back(ActionEvent event) throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/LoginSignup.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}