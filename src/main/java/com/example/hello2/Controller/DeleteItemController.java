package com.example.hello2.Controller;

import com.example.hello2.Model.ItemModel;
import com.example.hello2.Reader.ItemsFileReader;
import com.example.hello2.Writer.ItemsFileWriter;
import javafx.event.ActionEvent;
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

    public void initialize() throws IOException {
        ItemsFileReader temp = new ItemsFileReader();
        ArrayList<ItemModel> itemlist = temp.readFileItems();
        for (ItemModel item : itemlist) {
            IDchoicebox.getItems().add(item.getID());
            IDchoicebox.setValue("Select Item to Delete");

        }
    }

    @FXML
    void Delete() throws IOException {
        ItemsFileReader temp = new ItemsFileReader();
        ItemsFileWriter writee = new ItemsFileWriter();
        String searchId = IDchoicebox.getValue();
        ArrayList<ItemModel> itemlist = temp.readFileItems();
        String selectedID = IDchoicebox.getValue();
        if (selectedID == null || selectedID.equals("Select Item to Delete")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an item to delete.");
            alert.showAndWait();
            return;
        }
        for (int i = 0; i < itemlist.size(); i++) {
            if (itemlist.get(i).getID().equals(searchId)) {
                itemlist.remove(i);
                writee.ItemsWriteFile(itemlist);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Item Successfully deleted.");
                alert.showAndWait();
                break;
            }
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
