package com.example.hello2.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AddItemController {

    public ChoiceBox<String> RentalTypeChoiceBox;
    @FXML
    private ChoiceBox<String> loanTypeChoiceBox;

    @FXML
    private TextField idField;

    @FXML
    private TextField titleField;



    @FXML
    private TextField copiesField;

    @FXML
    private TextField rentalFeeField;

    @FXML
    private ChoiceBox<String> rentalStatusChoiceBox;

    @FXML
    private Button addItemButton;
    public Button back;

    @FXML
    private Label successLabel;

    public void initialize() {
        // Initialize loan type choice box with two options
        loanTypeChoiceBox.getItems().addAll("1 Week Loan", "2 Days Loan");
        loanTypeChoiceBox.setValue("1 Week Loan");

        // Initialize rental status choice box with two options
        rentalStatusChoiceBox.getItems().addAll("Available", "Borrowed");
        rentalStatusChoiceBox.setValue("Available");
        RentalTypeChoiceBox.getItems().addAll("DVD","Record","Game");
        RentalTypeChoiceBox.setValue("DVD");

    }

    @FXML
    void addItem() {
        // Get input values
        String id = idField.getText();
        String title = titleField.getText();
        String rentalType = RentalTypeChoiceBox.getValue();
        String loanType = loanTypeChoiceBox.getValue();
        int copies = Integer.parseInt(copiesField.getText());
        double rentalFee = Double.parseDouble(rentalFeeField.getText());
        String rentalStatus = rentalStatusChoiceBox.getValue();

        try {
            FileWriter writer = new FileWriter("new_items.txt", true);
            writer.write(id + "," + title + ","  + rentalType + "," + loanType + "," + copies + "," + rentalFee +
                                   "," + rentalStatus);
            writer.write("\n");
            writer.close();

            // Show success message
            successLabel.setText("Item added successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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
