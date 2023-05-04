package com.example.hello2.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.io.IOException;

public class Scene7Controller {

    public ChoiceBox RentalTypeChoiceBox;
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
        String rentalType = (String) RentalTypeChoiceBox.getValue();
        String loanType = loanTypeChoiceBox.getValue();
        int copies = Integer.parseInt(copiesField.getText());
        double rentalFee = Double.parseDouble(rentalFeeField.getText());
        String rentalStatus = rentalStatusChoiceBox.getValue();

        // Write to file
        try {
            FileWriter writer = new FileWriter("new_items.txt", true);
            writer.write("ID: " + id + "\n");
            writer.write("Title: " + title + "\n");
            writer.write("Rental Type: " + rentalType + "\n");
            writer.write("Loan Type: " + loanType + "\n");
            writer.write("Copies: " + copies + "\n");
            writer.write("Rental Fee (USD): " + rentalFee + "\n");
            writer.write("Rental Status: " + rentalStatus + "\n\n");
            writer.close();

            // Show success message
            successLabel.setText("Item added successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
