package com.example.hello2.Controller.Items;

import com.example.hello2.Model.ItemModel;
import com.example.hello2.Reader.ItemsFileReader;
import com.example.hello2.Writer.ItemsFileWriter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class AddItemController {
    public ChoiceBox<String> RentalTypeChoiceBox;
    @FXML
    private ChoiceBox<String> loanTypeChoiceBox;

    @FXML
    private TextField idField;

    @FXML
    private TextField titleField;

    @FXML
    private ChoiceBox<String> genreChoiceBox;
    @FXML
    private TextField copiesField;

    @FXML
    private TextField rentalFeeField;

    @FXML
    private ChoiceBox<String> rentalStatusChoiceBox;

    public Button back;

    public void initialize() {
        // Initialize loan type choice box with two options
        loanTypeChoiceBox.getItems().addAll("1 Week Loan", "2 Days Loan");
        loanTypeChoiceBox.setValue("1 Week Loan");

        // Initialize rental status choice box with two options
        rentalStatusChoiceBox.getItems().addAll("Available", "Borrowed");
        rentalStatusChoiceBox.setValue("Available");
        RentalTypeChoiceBox.getItems().addAll("DVD", "Record", "Game");
        RentalTypeChoiceBox.setValue("DVD");

        // Initialize genre options based on the initial value of rental type
        String initialRentalType = RentalTypeChoiceBox.getValue();
        updateGenreOptions(initialRentalType);

        // Set genre options based on the selected rental type
        RentalTypeChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateGenreOptions(newValue));
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
    void addItem() throws IOException {
        // Get input values
        String id = idField.getText();
        String title = titleField.getText();
        String rentalType = RentalTypeChoiceBox.getValue();
        String loanType = loanTypeChoiceBox.getValue();
        String genre = genreChoiceBox.getValue();
        String copiesText = copiesField.getText();
        String rentalFeeText = rentalFeeField.getText();
        String rentalStatus = rentalStatusChoiceBox.getValue();

        // Validate ID format
        if (!Pattern.matches("I\\d{3}-\\d{4}", id)) {
            showAlert(Alert.AlertType.ERROR, "Invalid ID format (e.g., I001-2001)");
            return;
        }

        // Validate title is not empty
        if (title.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Title cannot be empty");
            return;
        }

        // Validate copies is a positive integer
        int copies;
        try {
            copies = Integer.parseInt(copiesText);
            if (copies <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid number of copies");
            return;
        }

        // Validate rental fee is a positive decimal
        double rentalFee;
        try {
            rentalFee = Double.parseDouble(rentalFeeText);
            if (rentalFee <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid rental fee");
            return;
        }

        if (rentalType == null || loanType == null || genre == null || rentalStatus == null) {
            showAlert(Alert.AlertType.ERROR, "Failed to add");
        } else {
            ItemModel item = new ItemModel(id, title, genre, rentalType, loanType, copies, rentalFee, rentalStatus);
            ItemsFileReader read = new ItemsFileReader();
            ItemsFileWriter writer = new ItemsFileWriter();
            read.getItemList().add(item);
            writer.ItemsWriteFile(read.readFileItems());
            showAlert(Alert.AlertType.INFORMATION, "Added Successfully");
        }
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

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
