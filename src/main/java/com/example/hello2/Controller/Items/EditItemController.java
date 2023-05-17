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

    public Label title;
    public ChoiceBox<String> loanTypeChoiceBox;
    public ChoiceBox<String> rentalStatusChoiceBox;
    public ChoiceBox ItemID;
    public ChoiceBox genreChoiceBox;
    public ChoiceBox RentalTypeChoiceBox;

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


    public void initialize() throws IOException {
        // Initialize loan type choice box with two options
        loanTypeChoiceBox.getItems().addAll("1 Week Loan", "2 Days Loan");
        loanTypeChoiceBox.setValue("loan type");
        // Initialize Rental type choice box with two options
        RentalTypeChoiceBox.getItems().addAll("DVD", "Record", "Game");
        RentalTypeChoiceBox.setValue("rental type");
        
        // Initialize rental status choice box with two options
        rentalStatusChoiceBox.getItems().addAll("Available", "Borrowed");
        rentalStatusChoiceBox.setValue("rental status");
        ItemsFileReader temp = new ItemsFileReader();
        ArrayList<ItemModel> itemlist = temp.readFileItems();

        for (ItemModel item : itemlist) {
            ItemID.getItems().add(item.getID());
            ItemID.setValue("Select Item to Edit");
        }
        ItemID.setOnAction(event -> {
            String searchId = (String) ItemID.getValue();
            for (ItemModel item : itemlist) {
                if (item.getID().equals(searchId)) {
                    itemDetailsArea.setText(item.toString());
                    // Initialize genre options based on the initial value of rental type
                    String initialRentalType = item.getRentalType();
                    updateGenreOptions(initialRentalType);
                    // Set genre options based on the selected rental type
                    RentalTypeChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                            (observable, oldValue, newValue) -> {
                                updateGenreOptions((String) newValue);
                            });
                    break; // Exit the loop once a match is found


                }
            }
        });
    }

    public void saveChanges() throws IOException {
        String searchId = (String) ItemID.getValue();

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
                    item.setGenre((String) genreChoiceBox.getValue());
                }
                if (RentalTypeChoiceBox.getValue() != null) {
                    item.setRentalType((String) RentalTypeChoiceBox.getValue());
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
        Path path = Paths.get("src/main/resources/com/example/hello2/SceneAdmin.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
