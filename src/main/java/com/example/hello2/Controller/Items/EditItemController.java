package com.example.hello2.Controller.Items;

//Fixed

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

import com.example.hello2.Model.ItemModel;
import com.example.hello2.Reader.ItemsFileReader;
import com.example.hello2.Writer.ItemsFileWriter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class EditItemController {

    public TextField itemIdField;

    public ChoiceBox<String> loanTypeChoiceBox;
    public ChoiceBox<String> rentalStatusChoiceBox;
    public ChoiceBox<String> ItemID;
    public ChoiceBox<String> genreChoiceBox;
    public ChoiceBox<String> RentalTypeChoiceBox;

    @FXML
    private TextArea itemDetailsArea;
    @FXML
    private TextField rentalFeeField;
    @FXML
    private TextField copiesField;

    public Button back;
    @FXML
    private TextField titleField;


    public void initialize() throws IOException {
        // Initialize loan type choice box with two options
        loanTypeChoiceBox.getItems().addAll("1 Week Loan", "2 Days Loan");
        loanTypeChoiceBox.setValue("1 Week Loan");
        // Initialize Rental type choice box with two options
        RentalTypeChoiceBox.getItems().addAll("DVD", "Record", "Game");
        RentalTypeChoiceBox.setValue("DVD");

        // Initialize rental status choice box with two options
        rentalStatusChoiceBox.getItems().addAll("Available", "Borrowed");
        rentalStatusChoiceBox.setValue("Available");
        ItemsFileReader temp = new ItemsFileReader();
        ArrayList<ItemModel> itemList = temp.readFileItems();

        for (ItemModel item : itemList) {
            ItemID.getItems().add(item.getID());
            ItemID.setValue("Select Item to Edit");
        }
        ItemID.setOnAction(event -> {
            String searchId = ItemID.getValue();
            for (ItemModel item : itemList) {
                if (item.getID().equals(searchId)) {
                    itemDetailsArea.setText(item.toString());
                    // Initialize genre options based on the initial value of rental type
                    String initialRentalType = item.getRentalType();
                    updateGenreOptions(initialRentalType);
                    // Set genre options based on the selected rental type
                    RentalTypeChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                            (observable, oldValue, newValue) -> updateGenreOptions((newValue)));
                    break; // Exit the loop once a match is found


                }
            }
        });
    }

    public void saveChanges() throws IOException {
        String searchId = ItemID.getValue();

        ItemsFileReader reader = new ItemsFileReader();
        ItemsFileWriter writer = new ItemsFileWriter();

        for (ItemModel item : reader.readFileItems()) {
            if (Objects.equals(item.getID(), searchId)) {
                if (itemIdField != null && !itemIdField.getText().isEmpty()) {
                    item.setID(itemIdField.getText());
                }

                if (!rentalFeeField.getText().isEmpty()) {
                    item.setFee(Double.parseDouble(rentalFeeField.getText()));
                }

                if (!copiesField.getText().isEmpty()) {
                    item.setCopies(Integer.parseInt(copiesField.getText()));
                }

                if (!titleField.getText().isEmpty()) {
                    item.setTitle(titleField.getText());
                }

                if (rentalStatusChoiceBox.getValue() != null) {
                    item.setStatus(rentalStatusChoiceBox.getValue());
                }

                if (loanTypeChoiceBox.getValue() != null) {
                    item.setLoanType(loanTypeChoiceBox.getValue());
                }
                if (genreChoiceBox.getValue() != null) {
                    item.setGenre(genreChoiceBox.getValue());
                }
                if (RentalTypeChoiceBox.getValue() != null) {
                    item.setRentalType((RentalTypeChoiceBox.getValue()));
                }
                itemDetailsArea.setText(item.toString());

            }
        }

        writer.ItemsWriteFile(reader.getItemList());

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Changes saved successfully.");
        alert.showAndWait();
    }

    private void updateGenreOptions(String rentalType) {
        if (rentalType.equals("DVD") || rentalType.equals("Record")) {
            genreChoiceBox.getItems().setAll("Action", "Drama", "Horror", "Comedy");
        } else if (rentalType.equals("Game")) {
            genreChoiceBox.getItems().setAll("None");
        } else {
            genreChoiceBox.getItems().clear();
        }
        genreChoiceBox.setValue(genreChoiceBox.getItems().get(0));
    }

    @FXML
    public void Back() throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/FXML/SceneAdmin.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
