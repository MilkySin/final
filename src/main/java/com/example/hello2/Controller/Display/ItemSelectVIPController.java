package com.example.hello2.Controller.Display;

import com.example.hello2.Model.ItemModel;
import com.example.hello2.Model.SelectedItems;
import com.example.hello2.Model.UserModel;
import com.example.hello2.Reader.ItemsFileReader;
import com.example.hello2.Reader.SelectedItemsReader;
import com.example.hello2.Reader.UserFileReader;
import com.example.hello2.Writer.ItemsFileWriter;
import com.example.hello2.Writer.SelectedItemsWriter;
import com.example.hello2.Writer.UsersFileWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ItemSelectVIPController {

    @FXML
    public Label selectedItemLabel;
    public Button Return;
    @FXML
    private String ID;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Button viewTextFileButton;

    public Button back;
    public Text Welcome;
    public Text Balance;
    public Text Points;
    @FXML
    private Button freebutton;

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUserID() {
        return ID;
    }

    public ItemSelectVIPController() throws IOException {
    }

    public void setInitialize() throws IOException {
        UserFileReader userFileReader = new UserFileReader();
        SelectedItemsWriter selectedItemsWriter = new SelectedItemsWriter();

        ArrayList<SelectedItems> selectedItemsArrayList = new SelectedItemsReader().readFileSelectedItems();

        for (UserModel user : userFileReader.readFileUser()) {
            if (Objects.equals(user.getId(), getUserID())) {
                Balance.setText("Balance: $" + user.getBalance());
                Welcome.setText("Welcome: " + user.getUsername());
                Points.setText("Current Points: " + (user.getNumReturned() * 10));
                freebutton.setVisible(user.getNumReturned() >= 10);
            }
        }

        if (!selectedItemsArrayList.isEmpty()) {
            ArrayList<String> temp = new ArrayList<>();
            for (SelectedItems items : selectedItemsArrayList) {
                temp.add(items.getID());
            }

            for (UserModel user : userFileReader.getUserList()) {
                if (!temp.contains(user.getId())) {
                    SelectedItems selectedItems = new SelectedItems(user.getId());
                    selectedItemsArrayList.add(selectedItems);
                    selectedItemsWriter.SelectedItemsWriteFIle(selectedItemsArrayList);
                }
            }
        }

        if (selectedItemsArrayList.isEmpty()) {
            for (UserModel user : userFileReader.getUserList()) {
                SelectedItems selectedItems = new SelectedItems(user.getId());
                selectedItemsArrayList.add(selectedItems);
                selectedItemsWriter.SelectedItemsWriteFIle(selectedItemsArrayList);
            }
        }
    }

    public void freeItem() throws IOException {
        ItemsFileReader itemsFileReader = new ItemsFileReader();
        ItemsFileWriter itemsFileWriter = new ItemsFileWriter();
        SelectedItemsReader selectedItemsReader = new SelectedItemsReader();
        SelectedItemsWriter selectedItemsWriter = new SelectedItemsWriter();
        UsersFileWriter usersFileWriter = new UsersFileWriter();
        UserFileReader userFileReader = new UserFileReader();
        List<CheckBox> checkBoxList = new ArrayList<>();

        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(10); // Set horizontal gap between elements
        flowPane.setVgap(10); // Set vertical gap between elements
        flowPane.setAlignment(Pos.TOP_LEFT);
        flowPane.setPrefSize(530, 400);
        ScrollPane scrollPane = new ScrollPane();

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        ArrayList<ItemModel> itemModelArrayList = itemsFileReader.readFileItems();

        int points = 0;
        for (UserModel user : userFileReader.readFileUser()) {
            if (Objects.equals(user.getId(), getUserID())) {
                points = user.getNumReturned();
            }
        }

        if (points < 100) {
            freebutton.setVisible(false);
        }


        final int[] selectedCount = {0};
        for (ItemModel items : itemModelArrayList) {
            CheckBox checkBox = new CheckBox(items.toString());
            checkBox.setUserData(items.getID());
            HBox itemBox = new HBox();

            if (items.getCopies() == 0) {
                checkBox.setDisable(true);
            } else {
                int finalPoints = points;
                checkBox.setOnAction((ActionEvent event) -> {
                    if (checkBox.isSelected()) {
                        if (selectedCount[0] < finalPoints / 10) {
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
            flowPane.getChildren().addAll(itemBox);
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

        scrollPane.setContent(flowPane);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Select free items from the list:");
        alert.getDialogPane().setContent(scrollPane);
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
        ArrayList<String> tempArray = new ArrayList<>();
        for (CheckBox checkBox : checkBoxList) {
            if (checkBox.isSelected()) {
                tempArray.add((String) checkBox.getUserData());
            }
        }


        for (UserModel user : userFileReader.getUserList()) {
            if (Objects.equals(user.getId(), getUserID())) {
                user.setNumReturned(user.getNumReturned() - tempArray.size() * 10);
                Points.setText("Current Points: " + (user.getNumReturned() * 10));
                usersFileWriter.UserWriteFile(userFileReader.getUserList());
            }
        }

        for (SelectedItems list : selectedItemsReader.getSelectedItemsList()) {
            if (list.getSelectedItemsList().isEmpty() && Objects.equals(list.getID(), ID)) {
                list.setSelectedItemsList(tempArray);
                System.out.println(list.getSelectedItemsList());
                selectedItemsWriter.SelectedItemsWriteFIle(selectedItemsReader.getSelectedItemsList());
                break;
            }
            if (!(list.getSelectedItemsList().isEmpty()) && Objects.equals(list.getID(), ID)) {
                list.getSelectedItemsList().addAll(tempArray);
                System.out.println(list.getSelectedItemsList());
                selectedItemsWriter.SelectedItemsWriteFIle(selectedItemsReader.getSelectedItemsList());
                break;
            }
        }
    }

    @FXML
    public void rentItems() throws IOException {
        ItemsFileReader itemsFileReader = new ItemsFileReader();
        ItemsFileWriter itemsFileWriter = new ItemsFileWriter();
        SelectedItemsReader selectedItemsReader = new SelectedItemsReader();
        SelectedItemsWriter selectedItemsWriter = new SelectedItemsWriter();
        UsersFileWriter usersFileWriter = new UsersFileWriter();
        UserFileReader userFileReader = new UserFileReader();
        List<CheckBox> checkBoxList = new ArrayList<>();

        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(10); // Set horizontal gap between elements
        flowPane.setVgap(10); // Set vertical gap between elements
        flowPane.setAlignment(Pos.TOP_LEFT);
        flowPane.setPrefSize(530, 400);
        ScrollPane scrollPane = new ScrollPane();

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
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
            flowPane.getChildren().addAll(itemBox);
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
        scrollPane.setContent(flowPane);

        // decrement copies value of selected item
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Select an item from the list:");
        alert.getDialogPane().setContent(scrollPane);
        alert.showAndWait();


        float total = 0;
        for (ItemModel items : itemModelArrayList) {
            for (CheckBox checkBox : checkBoxList) {
                if (Objects.equals(checkBox.getUserData(), items.getID()) && checkBox.isSelected()) {
                    total += items.getFee();
                }
            }
        }

        for (UserModel user : userFileReader.readFileUser()) {
            if (Objects.equals(user.getId(), getUserID())) {
                if (user.getBalance() >= total) {
                    user.setBalance(user.getBalance() - total);
                    usersFileWriter.UserWriteFile(userFileReader.getUserList());
                    Balance.setText("Balance: $" + user.getBalance());
                } else {
                    Alert alerts = new Alert(Alert.AlertType.ERROR);
                    alerts.setTitle("Insufficient Balance");
                    alerts.setHeaderText(null);
                    alerts.setContentText("Not enough money");
                    alerts.showAndWait();
                    return;
                }
            }
        }
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
        ArrayList<String> tempArray = new ArrayList<>();
        for (CheckBox checkBox : checkBoxList) {
            if (checkBox.isSelected()) {
                tempArray.add((String) checkBox.getUserData());
            }
        }

        for (SelectedItems list : selectedItemsReader.getSelectedItemsList()) {
            if (list.getSelectedItemsList().isEmpty() && Objects.equals(list.getID(), ID)) {
                list.setSelectedItemsList(tempArray);
                System.out.println(list.getSelectedItemsList());
                selectedItemsWriter.SelectedItemsWriteFIle(selectedItemsReader.getSelectedItemsList());
                break;
            }
            if (!(list.getSelectedItemsList().isEmpty()) && Objects.equals(list.getID(), ID)) {
                list.getSelectedItemsList().addAll(tempArray);
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

        UsersFileWriter usersFileWriter = new UsersFileWriter();
        UserFileReader userFileReader = new UserFileReader();

        List<CheckBox> checkBoxList = new ArrayList<>();

        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(10); // Set horizontal gap between elements
        flowPane.setVgap(10); // Set vertical gap between elements
        flowPane.setAlignment(Pos.TOP_LEFT);
        flowPane.setPrefSize(530, 400);
        ScrollPane scrollPane = new ScrollPane();

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

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
                    flowPane.getChildren().addAll(itemBox);
                }
            }
        }

        scrollPane.setContent(flowPane);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Select an item from the list:");
        alert.getDialogPane().setContent(scrollPane);
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
        ArrayList<String> tempArray = new ArrayList<>();
        for (CheckBox checkBox : checkBoxList) {
            if (checkBox.isSelected()) {
                tempArray.add((String) checkBox.getUserData());
            }
        }

        for (UserModel temp : userFileReader.readFileUser()) {
            for (SelectedItems list : selectedItemsReader.getSelectedItemsList()) {
                if (Objects.equals(list.getID(), ID) && Objects.equals(temp.getId(), ID) && !tempArray.isEmpty()) {
                    list.getSelectedItemsList().removeAll(tempArray);
                    temp.setNumReturned(temp.getNumReturned() + tempArray.size());
                    Points.setText("Current Points: " + (temp.getNumReturned() * 10));
                    freebutton.setVisible(temp.getNumReturned() >= 10);
                    usersFileWriter.UserWriteFile(userFileReader.getUserList());
                    selectedItemsWriter.SelectedItemsWriteFIle(selectedItemsReader.getSelectedItemsList());
                }
            }
        }
    }
}
