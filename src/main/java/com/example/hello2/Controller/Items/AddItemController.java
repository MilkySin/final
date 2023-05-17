package com.example.hello2.Controller.Items;

//Fixed

import com.example.hello2.Model.ItemModel;
import com.example.hello2.Reader.ItemsFileReader;
import com.example.hello2.Writer.ItemsFileWriter;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
    public Text text;


    private final ListProperty<String> rentalType = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final ListProperty<String> genreOptions = new SimpleListProperty<>(FXCollections.observableArrayList());

    public void Update() {

    }

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
        RentalTypeChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateGenreOptions(newValue);
        });
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

        int copies = Integer.parseInt(copiesField.getText());
        double rentalFee = Double.parseDouble(rentalFeeField.getText());
        String rentalStatus = rentalStatusChoiceBox.getValue();

        if (id == null || title == null || genre == null || rentalType == null || loanType == null || rentalStatus == null) {
            // handle the null values here
            text.setFill(Color.RED);
            text.setText("Failed to add");
        } else {
            ItemModel item = new ItemModel(id, title, genre, rentalType, loanType, copies, rentalFee, rentalStatus);
            ItemsFileReader read = new ItemsFileReader();
            ItemsFileWriter writer = new ItemsFileWriter();
            read.getItemList().add(item);
            writer.ItemsWriteFile(read.readFileItems());
            text.setFill(Color.RED);
            text.setText("Added Successfully");
        }
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
