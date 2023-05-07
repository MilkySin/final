package com.example.hello2.Controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class ItemSelectRegularController {

    @FXML
    public Label selectedItemLabel;
    @FXML
    private Label label;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button viewTextFileButton;
    public Button back;
    private int userID;
    public void setUserID(int userID) {
        this.userID = userID;
    }

    private List<String> selectedItems = new ArrayList<>(); // keep track of selected items

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

            VBox vbox = new VBox(); // create a container for the CheckBoxes

            List<CheckBox> checkBoxList = new ArrayList<>(); // keep track of CheckBoxes
            for (String line : contentList) {
                if (line.startsWith("ID")) {
                    String item = line.trim();
                    CheckBox checkBox = new CheckBox(item);
                    vbox.getChildren().add(checkBox); // add the CheckBox to the container
                    checkBoxList.add(checkBox);
                    // if the item was previously selected, mark the CheckBox as selected
                    if (selectedItems.contains(item)) {
                        checkBox.setSelected(true);
                    }
                }
            }
            alert.getDialogPane().setContent(vbox); // set the container as the content of the dialog pane

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                selectedItems.clear(); // clear the list of selected items
                StringBuilder selectedItemBuilder = new StringBuilder();
                for (CheckBox checkBox : checkBoxList) {
                    if (checkBox.isSelected()) {
                        String selectedItem = checkBox.getText();
                        selectedItems.add(selectedItem); // add the selected item to the list
                        selectedItemBuilder.append(selectedItem).append(", ");
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
        selectedItemLabel.setText(text) ;
    }

    // save the selected items to a file
    public void saveSelectedItems() throws IOException {
        Path path = Paths.get("selected_items.txt");
        Files.write(path, "".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        String userIdString = Integer.toString(userID);
        String fileContent = Files.readString(path);

        // check if userID exists in the file
        if (fileContent.contains("ID " + userIdString)) {
            // if user ID exists, check if any selected items already exist for the same user
            String[] sections = fileContent.split("ID " + userIdString);
            for (int i = 1; i < sections.length; i++) {
                String[] lines = sections[i].split("\n");
                for (String line : lines) {
                    if (line.contains(":")) {
                        String items = line.split(":")[1].trim();
                        Set<String> existingItems = new HashSet<>(Arrays.asList(items.split(",")));
                        // trim items and remove empty ones
                        Set<String> newItems = new HashSet<>(selectedItems);
                        newItems.removeIf(String::isBlank);
                        if (!Collections.disjoint(existingItems, newItems)) {
                            // if any selected items already exist for the same user, display an error message
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Selected items already exist for this user");
                            alert.showAndWait();
                            return;
                        } else {
                            // if no selected items already exist for the same user, append the new items to the existing line
                            StringBuilder sb = new StringBuilder(fileContent);
                            sb.replace(fileContent.indexOf(line), fileContent.indexOf(line) + line.length(),
                                    "ID " + userIdString + ": " + existingItems.toString().replaceAll("\\[|\\]", "") + ", " + newItems.toString().replaceAll("\\[|\\]", ""));
                            Files.write(path, sb.toString().getBytes(), StandardOpenOption.WRITE);
                            // clear the selected items list
                            selectedItems.clear();
                            return;
                        }
                    }
                }
            }
        }

        // if user ID doesn't exist or selected items don't already exist for the same user, append the selected items as a new line
        Set<String> newItems = new HashSet<>(selectedItems);
        newItems.removeIf(item -> item.isBlank());

        if (!newItems.isEmpty()) {
            StringBuilder sb = new StringBuilder(fileContent);
            sb.append("ID ").append(userIdString).append(": ");
            sb.append(newItems.toString().replaceAll("\\[|\\]", "")).append("\n");
            Files.write(path, sb.toString().getBytes(), StandardOpenOption.APPEND);
        }

        // clear the selected items list
        selectedItems.clear();
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
