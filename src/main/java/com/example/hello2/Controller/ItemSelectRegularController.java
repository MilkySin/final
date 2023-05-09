package com.example.hello2.Controller;

import com.example.hello2.Model.ItemModel;
import com.example.hello2.Reader.ItemsFileReader;
import com.example.hello2.Writer.ItemsFileWriter;
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
    public void viewTextFile() throws IOException {

        ItemsFileReader reader = new ItemsFileReader();
        ItemsFileWriter writer = new ItemsFileWriter();
        VBox vbox = new VBox();
        List<CheckBox> checkBoxList = new ArrayList<>(); // keep track of selected CheckBoxes

        for (ItemModel items : reader.readFileItems()) {
            CheckBox checkBox = new CheckBox(items.toString());
            HBox itemBox = new HBox();
            if (items.getCopies() == 0) {
                checkBox.setDisable(true);
            }
            checkBoxList.add(checkBox);
            itemBox.getChildren().addAll(checkBox);
            vbox.getChildren().addAll(itemBox);
        }

        // decrement copies value of selected item
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Select an item from the list:");
        alert.getDialogPane().setContent(vbox);
        alert.showAndWait();

        ArrayList<ItemModel> content = reader.getItemList();

        for (CheckBox checkBox : checkBoxList) {
            for (ItemModel item : content) {
                if (checkBox.getText().equals(item.toString()) && checkBox.isSelected()) {
                    item.setCopies(item.getCopies() - 1); // decrement the copies value
                    writer.ItemsWriteFile(content); // write the updated items to the file
                    break;
                }
            }
        }
    }

    public void setLabelText(String text) {
        selectedItemLabel.setText(text) ;
    }

    // save the selected items to a file
    public void saveSelectedItems() throws IOException {

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
