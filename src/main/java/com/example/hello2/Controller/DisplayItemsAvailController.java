package com.example.hello2.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DisplayItemsAvailController {
    @FXML
    public TextArea textArea;

    @FXML
    public void initialize() throws IOException {
        Path path = Paths.get("new_items.txt");
        File file = new File(path.toUri());

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
//
//            while ((line = br.readLine()) != null) {
//                if (line.contains("Rental Status: Available")) {
//                    String prevLine = "";
//                    while ((prevLine = br.readLine()) != null) {
//                        if (prevLine.contains("ID:")) {
//                            id = prevLine.split(":\\s")[1];
//                            System.out.println(id);
//                        }
//                    }
//                }
            while ((line = br.readLine()) != null) {
                if (line.contains("Borrowed")) {
                    String id = null;
                    while ((line = br.readLine()) != null) {
                        if (line.contains("ID:")) {
                            id = line.split(":\\s")[1];
                            break;
                        }
                    }
                    if (id != null) {
                        System.out.println(id);
                    }
                }
            }
        }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

