package com.example.hello2.Controller;

//Fixed
import com.example.hello2.Model.ItemModel;
import com.example.hello2.Reader.ItemsFileReader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DisplayItemsController {
    @FXML
    public TextArea selectedItemLabel;
    public Button back;

    @FXML
    public void initialize() throws Exception {
        ItemsFileReader reader = new ItemsFileReader();
        StringBuilder fileContents = new StringBuilder();

        for(ItemModel items : reader.readFileItems()){
            fileContents.append(items.toString());
        }
        selectedItemLabel.setText(fileContents.toString());
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
