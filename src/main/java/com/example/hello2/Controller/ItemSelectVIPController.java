package com.example.hello2.Controller;

import com.example.hello2.Model.ItemModel;
import com.example.hello2.Model.SelectedItems;
import com.example.hello2.Model.UserModel;
import com.example.hello2.Reader.ItemsFileReader;
import com.example.hello2.Reader.SelectedItemsReader;
import com.example.hello2.Reader.UserFileReader;
import com.example.hello2.Writer.ItemsFileWriter;
import com.example.hello2.Writer.SelectedItemsWriter;
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

public class ItemSelectVIPController {

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
    private int userID;
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getUserID(){
        return ID;
    }
    public ItemSelectVIPController() throws IOException {
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    public void initialize() throws IOException {
        UserFileReader userFileReader = new UserFileReader();
        SelectedItemsWriter selectedItemsWriter = new SelectedItemsWriter();

        ArrayList<SelectedItems> con = new SelectedItemsReader().readFileSelectedItems();

        if (con.isEmpty()) {
            for (UserModel user : userFileReader.readFileUser()) {
                SelectedItems con3 = new SelectedItems(user.getId());
                con.add(con3);
                selectedItemsWriter.SelectedItemsWriteFIle(con);
            }
        }
    }

    @FXML
    public void rentItems() throws IOException {
        ItemsFileReader itemsFileReader = new ItemsFileReader();
        ItemsFileWriter itemsFileWriter = new ItemsFileWriter();
        SelectedItemsReader selectedItemsReader = new SelectedItemsReader();
        SelectedItemsWriter selectedItemsWriter = new SelectedItemsWriter();
        List<CheckBox> checkBoxList = new ArrayList<>();

        VBox vbox = new VBox();
        ArrayList<ItemModel> itemModelArrayList = itemsFileReader.readFileItems();

        for (ItemModel items : itemModelArrayList) {
            CheckBox checkBox = new CheckBox(items.toString());
            checkBox.setUserData(items.getID());

            HBox itemBox = new HBox();
            if (items.getCopies() == 0) {
                checkBox.setDisable(true);
            }
            checkBoxList.add(checkBox);
            itemBox.getChildren().addAll(checkBox);
            vbox.getChildren().addAll(itemBox);
        }
        for (SelectedItems items : selectedItemsReader.readFileSelectedItems()) {
            for (CheckBox check : checkBoxList) {
                if (Objects.equals(items.getID(), getUserID())) {
                    for (String sd : items.getSelectedItemsList()) {
                        if (Objects.equals(sd, check.getUserData())) {
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

//        ArrayList<ItemModel> content = reader.getItemList();

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
        ArrayList<String> temp = new ArrayList<>();
        for (CheckBox checkBox : checkBoxList) {
            if (checkBox.isSelected()) {
                temp.add((String) checkBox.getUserData());
            }
        }

        for (SelectedItems list : selectedItemsReader.getSelectedItemsList()) {
            if (list.getSelectedItemsList().isEmpty() && Objects.equals(list.getID(), ID)) {
                list.setSelectedItemsList(temp);
                System.out.println(list.getSelectedItemsList());
                selectedItemsWriter.SelectedItemsWriteFIle(selectedItemsReader.getSelectedItemsList());
                break;
            }
            if (!(list.getSelectedItemsList().isEmpty()) && Objects.equals(list.getID(), ID)) {
                list.getSelectedItemsList().addAll(temp);
                System.out.println(list.getSelectedItemsList());
                selectedItemsWriter.SelectedItemsWriteFIle(selectedItemsReader.getSelectedItemsList());
                break;
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

    public void Return(ActionEvent event) throws IOException {
        SelectedItemsReader selectedItemsReader = new SelectedItemsReader();
        SelectedItemsWriter selectedItemsWriter = new SelectedItemsWriter();

        ItemsFileReader itemsFileReader = new ItemsFileReader();
        ItemsFileWriter itemsFileWriter = new ItemsFileWriter();

        List<CheckBox> checkBoxList = new ArrayList<>();

        VBox vbox = new VBox();

        ArrayList<ItemModel> itemModelArrayList = itemsFileReader.readFileItems();

        for (SelectedItems temp : selectedItemsReader.readFileSelectedItems()) {
            for (ItemModel items : itemModelArrayList) {
                if (temp.getSelectedItemsList().contains(items.getID()) && Objects.equals(temp.getID(), ID)) {
                    CheckBox checkBox = new CheckBox(items.toString());
                    checkBox.setUserData(items.getID());

                    HBox itemBox = new HBox();
                    if (items.getCopies() == 0) {
                        checkBox.setDisable(false);
                    }
                    checkBoxList.add(checkBox);
                    itemBox.getChildren().addAll(checkBox);
                    vbox.getChildren().addAll(itemBox);
                }
            }
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Select an item from the list:");
        alert.getDialogPane().setContent(vbox);
        alert.showAndWait();

        ArrayList<ItemModel> content = itemsFileReader.getItemList();
        for (CheckBox checkBox : checkBoxList) {
            for (ItemModel item : content) {
                if (checkBox.getText().equals(item.toString()) && checkBox.isSelected()) {
                    item.setCopies(item.getCopies() + 1); // decrement the copies value
                    itemsFileWriter.ItemsWriteFile(content); // write the updated items to the file
                    break;
                }
            }
        }
        ArrayList<String> temp = new ArrayList<>();
        for (CheckBox checkBox : checkBoxList) {
            if (checkBox.isSelected()) {
                temp.add((String) checkBox.getUserData());
            }
        }

        for (SelectedItems list : selectedItemsReader.getSelectedItemsList()) {
            if (Objects.equals(list.getID(), ID)) {
                list.getSelectedItemsList().removeAll(temp);
                selectedItemsWriter.SelectedItemsWriteFIle(selectedItemsReader.getSelectedItemsList());
            }
        }
    }
}
