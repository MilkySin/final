package com.example.hello2.Controller;

//Fixed
import com.example.hello2.Model.ItemModel;
import com.example.hello2.Reader.ItemsFileReader;
import com.example.hello2.Writer.ItemsFileWriter;
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

    public Button back;
    public Text text;

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
    void addItem() throws IOException {
        // Get input values
        String id = idField.getText();
        String title = titleField.getText();
        String rentalType = RentalTypeChoiceBox.getValue();
        String loanType = loanTypeChoiceBox.getValue();
        int copies = Integer.parseInt(copiesField.getText());
        double rentalFee = Double.parseDouble(rentalFeeField.getText());
        String rentalStatus = rentalStatusChoiceBox.getValue();

        if (id == null || title == null || rentalType == null || loanType == null || rentalStatus == null) {
            // handle the null values here
            text.setFill(Color.RED);
            text.setText("Failed to add");
        }else {
            ItemModel item = new ItemModel(id, title, rentalType, loanType, copies, rentalFee, rentalStatus);
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
