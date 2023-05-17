package com.example.hello2.Controller.Items;
//fixed

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
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DeleteItemController {
    public ChoiceBox<String> IDchoicebox;
    public Button delete;
    public Button back;
    public javafx.scene.control.TextArea TextArea;

    public void initialize() throws IOException {
        ItemsFileReader temp = new ItemsFileReader();
        ArrayList<ItemModel> itemlist = temp.readFileItems();

        for (ItemModel item : itemlist) {
            IDchoicebox.getItems().add(item.getID());
            IDchoicebox.setValue("Select Item to Delete");
        }

        IDchoicebox.setOnAction(event -> {
            String searchId = IDchoicebox.getValue();
            for (ItemModel item : itemlist) {
                if (item.getID().equals(searchId)) {
                    TextArea.setText(item.toString());
                    break; // Exit the loop once a match is found
                }
            }
        });
    }


    @FXML
    void Delete() throws IOException {
        ItemsFileReader reader = new ItemsFileReader();
        ItemsFileWriter writer = new ItemsFileWriter();
        String searchId = IDchoicebox.getValue();
        ArrayList<ItemModel> itemlist = reader.readFileItems();
        String selectedID = IDchoicebox.getValue();
        if (selectedID == null || selectedID.equals("Select Item to Delete")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an item to delete.");
            alert.showAndWait();
            return;
        }
        for (int i = 0; i < itemlist.size(); i++) {
            if (itemlist.get(i).getID().equals(searchId)) {
                itemlist.remove(i);
                writer.ItemsWriteFile(itemlist);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Item Successfully deleted.");
                alert.showAndWait();
                break;
            }
        }
    }

    @FXML
    public void Back() throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/SceneAdmin.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }


}
