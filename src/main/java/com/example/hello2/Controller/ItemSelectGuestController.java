package com.example.hello2.Controller;

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

    //Read through both files, if selected is empty, add users from user lists
    public void initialize() throws IOException {
        UserFileReader userFileReader = new UserFileReader();
        SelectedItemsWriter selectedItemsWriter = new SelectedItemsWriter();

        ArrayList<SelectedItems> selectedItemsArrayList = new SelectedItemsReader().readFileSelectedItems();

        if (!selectedItemsArrayList.isEmpty()) {
            ArrayList<String> temp = new ArrayList<>();
            for (SelectedItems items : selectedItemsArrayList) {
                temp.add(items.getID());
            }

            for (UserModel user : userFileReader.readFileUser()) {
                if(!temp.contains(user.getId())){
                    SelectedItems selectedItems = new SelectedItems(user.getId());
                    selectedItemsArrayList.add(selectedItems);
                    selectedItemsWriter.SelectedItemsWriteFIle(selectedItemsArrayList);
                }
            }
        }

        if (selectedItemsArrayList.isEmpty()) {
            for (UserModel user : userFileReader.readFileUser()) {
                SelectedItems selectedItems = new SelectedItems(user.getId());
                selectedItemsArrayList.add(selectedItems);
                selectedItemsWriter.SelectedItemsWriteFIle(selectedItemsArrayList);
            }
            System.out.println(selectedItemsArrayList);
        }
    }

    @FXML
    public void rentItems() throws IOException {
        ItemsFileReader itemsFileReader = new ItemsFileReader();
        ItemsFileWriter itemsFileWriter = new ItemsFileWriter();
        SelectedItemsReader selectedItemsReader = new SelectedItemsReader();
        SelectedItemsWriter selectedItemsWriter = new SelectedItemsWriter();
        List<CheckBox> checkBoxList = new ArrayList<>();

        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(10); // Set horizontal gap between elements
        flowPane.setVgap(10); // Set vertical gap between elements
        flowPane.setAlignment(Pos.TOP_LEFT);
        flowPane.setPrefSize(530,400);
        ScrollPane scrollPane = new ScrollPane();

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        ArrayList<ItemModel> itemModelArrayList = itemsFileReader.readFileItems();

        // keep track of selected CheckBox count
        final int[] selectedCount = {0};
        for (ItemModel items : itemModelArrayList) {
            CheckBox checkBox = new CheckBox(items.toString());
            checkBox.setUserData(items.getID());
            HBox itemBox = new HBox();

            if (items.getCopies() == 0 || Objects.equals(items.getLoanType(), "2 Days Loan")) {
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
        alert.setHeaderText("Select an item from the list:");
        alert.getDialogPane().setContent(scrollPane);
        alert.showAndWait();

        // decrement copies value of selected item
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

        UserFileReader userFileReader = new UserFileReader();
        UsersFileWriter usersFileWriter = new UsersFileWriter();

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
        ArrayList<String> tempArray = new ArrayList<>();
        for (CheckBox checkBox : checkBoxList) {
            if (checkBox.isSelected()) {
                tempArray.add((String) checkBox.getUserData());
            }
        }

        for(UserModel temp : userFileReader.readFileUser()) {
            for (SelectedItems list : selectedItemsReader.getSelectedItemsList()) {
                if (Objects.equals(list.getID(), ID) && Objects.equals(temp.getId(), ID) && !tempArray.isEmpty()) {
                    list.getSelectedItemsList().removeAll(tempArray);
                    temp.setNumReturned(temp.getNumReturned() + tempArray.size());
                    if(temp.getNumReturned() == 3){
                        temp.setAccountType("Regular");
                        temp.setNumReturned(0);
                    }
                    usersFileWriter.UserWriteFile(userFileReader.getUserList());
                    selectedItemsWriter.SelectedItemsWriteFIle(selectedItemsReader.getSelectedItemsList());
                }
            }
        }
    }
}

