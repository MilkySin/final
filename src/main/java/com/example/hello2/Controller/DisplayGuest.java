package com.example.hello2.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class DisplayGuest {
    @FXML
    public TextArea customerTextArea;

    public Button back;



    @FXML
    public void initialize() throws IOException {
        Path path = Paths.get("userinfo.txt");
        File file = new File(path.toUri());

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String Username = "", Password = "", ID = "", Account_Type = "", Address = "", Phonenum = "";
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(":\\s");
                switch (fields[0]) {
                    case "Username":
                        Username = fields[1];
                        break;
                    case "Password":
                        Password = fields[1];
                        break;
                    case "ID":
                        ID = fields[1];
                        break;
                    case "Address":
                        Address = fields[1];
                        break;
                    case "Phone Number":
                        Phonenum = fields[1];
                        break;
                    case "Account Type":
                        if (fields[1].equals("Guest")) {
                            sb.append("Username:  ").append(Username).append("\n");
                            sb.append("Password: ").append(Password).append("\n");
                            sb.append("ID: ").append(ID).append("\n");
                            sb.append("Address: ").append(Address).append("\n");
                            sb.append("Phone NUmber: ").append(Phonenum).append("\n\n");
                        }

                        customerTextArea.setText(String.valueOf(sb));
                }
            }
        }catch(IOException e){
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