package com.example.hello2.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DisplayItemsController {
    @FXML
    public TextArea selectedItemLabel;

    @FXML
    public void initialize() throws Exception {
        StringBuilder fileContents = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("new_items.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                fileContents.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(fileContents);
        selectedItemLabel.setText(String.valueOf(fileContents));
    }


}
