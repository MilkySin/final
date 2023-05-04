//package com.example.hello2.Controller;
//
//import javafx.concurrent.Task;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.VBox;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//public class Scene6Controller {
//
//    @FXML
//    public Label selectedItemLabel;
//    @FXML
//    private Label label;
//
//    @FXML
//    private ProgressBar progressBar;
//
//    @FXML
//    private Button viewTextFileButton;
//
//    @FXML
//    public void viewTextFile(ActionEvent event) {
//        Task<List<String>> task = new Task<List<String>>() {
//            @Override
//            protected List<String> call() throws Exception {
//                List<String> lines = Files.readAllLines(Paths.get("C:/Users/ShirinLP/IdeaProjects/Hello2/new_items.txt"));
//                return new ArrayList<String>(lines);
//            }
//        };
//
//        progressBar.progressProperty().bind(task.progressProperty());
//        progressBar.setVisible(true);
//
//        task.setOnSucceeded(e -> {
//            List<String> contentList = task.getValue();
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setHeaderText("Select an item from the list:");
//
//            VBox vbox = new VBox(); // create a container for the RadioButtons
//
//            ToggleGroup group = new ToggleGroup();
//            for (String line : contentList) {
//                if (line.startsWith("ID")) {
//                    String item = line.trim();
//                    RadioButton button = new RadioButton(item);
//                    button.setToggleGroup(group);
//                    vbox.getChildren().add(button); // add the RadioButton to the container
//                }
//            }
//            alert.getDialogPane().setContent(vbox); // set the container as the content of the dialog pane
//
//            Optional<ButtonType> result = alert.showAndWait();
//            if (result.isPresent() && result.get() == ButtonType.OK) {
//                RadioButton selectedButton = (RadioButton) group.getSelectedToggle();
//                String selectedItem = selectedButton.getText();
//                setLabelText(selectedItem);
//            }
//            progressBar.setVisible(false);
//        });
//
//        task.setOnFailed(e -> {
//            Alert alert = new Alert(Alert.AlertType.ERROR, "Error reading file: " + task.getException().getMessage());
//            alert.showAndWait();
//            progressBar.setVisible(false);
//        });
//
//        new Thread(task).start();
//    }
//
//
//
//    public void setLabelText(String text) {
//        selectedItemLabel.setText(text);
//    }
//
//}

package com.example.hello2.Controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Scene6Controller {

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
            for (String line : contentList) {
                if (line.startsWith("ID")) {
                    String item = line.trim();
                    CheckBox checkBox = new CheckBox(item);
                    vbox.getChildren().add(checkBox); // add the CheckBox to the container
                    checkBoxList.add(checkBox);
                }
            }
            alert.getDialogPane().setContent(vbox); // set the container as the content of the dialog pane

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                StringBuilder selectedItemBuilder = new StringBuilder();
                for (CheckBox checkBox : checkBoxList) {
                    if (checkBox.isSelected()) {
                        selectedItemBuilder.append(checkBox.getText()).append(", ");
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
