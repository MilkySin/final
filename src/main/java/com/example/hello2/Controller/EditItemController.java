package com.example.hello2.Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.hello2.Model.ItemModel;
import com.example.hello2.Reader.ItemsFileReader;
import com.example.hello2.Writer.ItemsFileWriter;
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


    public void initialize() {
        // Initialize loan type choice box with two options
        loanTypeChoiceBox.getItems().addAll("1 Week Loan", "2 Days Loan");
        loanTypeChoiceBox.setValue("1 Week Loan");

        // Initialize rental status choice box with two options
        rentalStatusChoiceBox.getItems().addAll("Available", "Borrowed");
        rentalStatusChoiceBox.setValue("Available");
    }
    public String toString(ItemModel i ){
        return "ID: "+ i.getID()+ " \n"+ "Title: "+i.getTitle()+"\n"+ "Type: "+ i.getRentalType()+"\n"+ "Loan type: "+i.getLoanType()+"\n"+"Copies: "+
                i.getCopies()+"\n"+"fee: "+i.getFee()+"\n"+" Availability: "+ i.getStatus()+"\n";
    }

    public void searchItem(ActionEvent event) {
        ItemsFileReader temp = new ItemsFileReader();
        ArrayList<ItemModel> itemlist = temp.readItems();
        String searchId = searchIdField.getText();
        String itemDetails = "";
        for (ItemModel item : itemlist) {
            if (item.getID().equals(searchId)) {
                itemDetailsArea.setText(toString(item));
//                itemDetails += item + "\n";
//                itemDetailsArea.setText(itemDetails);
//                break;
            }
        }

    }
    public void saveChanges(ActionEvent event) throws IOException {
        String searchId = searchIdField.getText();

        ArrayList<ItemModel> itemlist =  new ItemsFileReader().readItems();
        ItemsFileWriter write = new ItemsFileWriter();
//        ItemModel temp=new ItemModel();
//        temp.setID(searchId);
        for (int i = 0; i < itemlist.size(); i++) {
            if (itemlist.get(i).getID().equals(searchId)) {
                itemlist.get(i).setID(itemIdField.getText());
                itemlist.get(i).setFee(Double.parseDouble(rentalFeeField.getText()));
                itemlist.get(i).setCopies(Integer.parseInt(copiesField.getText()));
                itemlist.get(i).setTitle(titleField.getText());
                itemlist.get(i).setStatus((String) rentalStatusChoiceBox.getValue());
                itemlist.get(i).setLoanType((String)loanTypeChoiceBox.getValue());
                write.FileWriter(itemlist);
                break;
            }
        }
//        for (ItemModel tems: itemList){
//
//            if(Objects.equals(tems.getID(), temp.getID())){
//                tems.setID(itemIdField.getText());
//                tems.setFee(Double.parseDouble(rentalFeeField.getText()));
//                tems.setCopies(Integer.parseInt(copiesField.getText()));
//                tems.setTitle(titleField.getText());
//                tems.setStatus((String) rentalStatusChoiceBox.getValue());
//                tems.setLoanType((String)loanTypeChoiceBox.getValue());
//            }
//
//        }


        write.FileWriter(itemlist);
//
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Changes saved successfully.");
                alert.showAndWait();

            }



//    private void writeItems(List<String> itemList) {
//        try {
//            writeUsers writer = new writeUsers(new File(filePath.toUri()));
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
