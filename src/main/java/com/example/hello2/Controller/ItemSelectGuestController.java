package com.example.hello2.Controller;

import com.example.hello2.Model.ItemModel;
import com.example.hello2.Model.SelectedItems;
import com.example.hello2.Reader.ItemsFileReader;
import com.example.hello2.Reader.SelectedItemsReader;
import com.example.hello2.Writer.ItemsFileWriter;
import com.example.hello2.Writer.SelectedItemsWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemSelectGuestController {
    @FXML
    public Label selectedItemLabel;
    public Button Return;
    @FXML
    private Label label;
    private String ID;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button viewTextFileButton;
    public Button back;

    public void setID(String ID) {
        this.ID = ID;
    }
    public String getUserID(){
        return ID;
    }

    @FXML
    public void viewTextFile() throws IOException {
        ItemsFileReader itemsFileReader = new ItemsFileReader();
        ItemsFileWriter itemsFileWriter = new ItemsFileWriter();
        SelectedItemsReader selectedItemsReader = new SelectedItemsReader();
        SelectedItemsWriter selectedItemsWriter = new SelectedItemsWriter();
        List<CheckBox> checkBoxList = new ArrayList<>();

        VBox vbox = new VBox();
        ArrayList<ItemModel> itemModelArrayList = itemsFileReader.readFileItems();

        final int[] selectedCount = {0}; // keep track of selected CheckBox count
        for (ItemModel items : itemModelArrayList) {
            CheckBox checkBox = new CheckBox(items.toString());
            checkBox.setUserData(items.getID());
            HBox itemBox = new HBox();
            if (items.getCopies() == 0) {
                checkBox.setDisable(true);
            } else {
                checkBox.setOnAction((ActionEvent event) -> {
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
            }

            checkBoxList.add(checkBox);
            itemBox.getChildren().addAll(checkBox);
            vbox.getChildren().addAll(itemBox);
        }

        for (SelectedItems items : selectedItemsReader.readFileSelectedItems()) {
            for (CheckBox check : checkBoxList) {
                if (Objects.equals(items.getID(), getUserID())) {
                    for (String i : items.getSelectedItemsList()) {
                        if (Objects.equals(i, check.getUserData())) {
                            check.setSelected(true);
                            check.setDisable(true);
                        }
                    }
                }
            }
        }

            // decrement copies value of selected item
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Select an item from the list:");
        alert.getDialogPane().setContent(vbox);
        alert.showAndWait();

        ArrayList<ItemModel> content = itemsFileReader.getItemList();
        for (CheckBox checkBox : checkBoxList) {
            for (ItemModel item : content) {
                if (checkBox.getText().equals(item.toString()) && checkBox.isSelected()) {
                    item.setCopies(item.getCopies() - 1); // decrement the copies value
                    itemsFileWriter.ItemsWriteFile(content); // write the updated items to the file
                    break;
                }
            }
        }
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

    public void Return(ActionEvent event) {
    }
}

