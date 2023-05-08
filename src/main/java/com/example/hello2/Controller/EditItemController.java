package com.example.hello2.Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.example.hello2.Model.ItemModel;
import com.example.hello2.Reader.ItemsFileReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class EditItemController {

    public TextField itemIdField;
    public Button searchItemButton;

    public Label title;
    public ChoiceBox loanTypeChoiceBox;
    public ChoiceBox rentalStatusChoiceBox;
    @FXML
    private TextField searchIdField;

    @FXML
    private TextArea itemDetailsArea;

    @FXML
    private TextField rentalFeeField;

    @FXML
    private TextField copiesField;

    public Button back;

    @FXML
    private TextField titleField;

    @FXML
    private Button saveChangesButton;

    private Path filePath = Paths.get("new_items.txt");
    public void initialize() {
        // Initialize loan type choice box with two options
        loanTypeChoiceBox.getItems().addAll("1 Week Loan", "2 Days Loan");
        loanTypeChoiceBox.setValue("1 Week Loan");

        // Initialize rental status choice box with two options
        rentalStatusChoiceBox.getItems().addAll("Available", "Borrowed");
        rentalStatusChoiceBox.setValue("Available");
    }

    public void searchItem(ActionEvent event) {
        ItemsFileReader temp = new ItemsFileReader();
        ArrayList<ItemModel> itemlist = temp.readItems();
        String searchId = searchIdField.getText();
        String itemDetails = "";
        for (ItemModel item : itemlist) {
            if (item.getID().equals(searchId)) {
                itemDetails += item.toString() + "\n";
                itemDetailsArea.setText(itemDetails);
                break;
            }
        }

    }
    public void saveChanges(ActionEvent event) {
        String searchId = searchIdField.getText();
        ArrayList<ItemModel> itemList =  new ItemsFileReader().readItems();
        ItemModel temp=new ItemModel();
        temp.setID(searchId);
        for (ItemModel tems: itemList){

            if(tems.getID() == temp.getID()){
                temp.setID(itemIdField.getText());
                temp.setFee(Double.parseDouble(rentalFeeField.getText()));
                temp.setCopies(Integer.parseInt(copiesField.getText()));
                temp.setTitle(titleField.getText());
                temp.setStatus((String) rentalStatusChoiceBox.getValue());
                temp.setLoanType((String)loanTypeChoiceBox.getValue());
            }
        }
//
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Changes saved successfully.");
                alert.showAndWait();

            }



//    private void writeItems(List<String> itemList) {
//        try {
//            FileWriter writer = new FileWriter(new File(filePath.toUri()));
//            for (String line : itemList) {
//                writer.write(line + "\n");
//            }
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @FXML
    public void Back(ActionEvent event) throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/SceneAdmin.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
