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
//            String line;
//            String id;
//            while ((line = br.readLine()) != null) {
//                String[] fields = line.split(":\\s");
//                if (fields.length >= 2 && fields[1].equals("Available")) {
//                        System.out.println(line);
//                }
//            }
            String line;
            String id = "";
            boolean available = false;
            while ((line = br.readLine()) != null) {
                if (line.contains("Rental Status: Available")) {
                    available = true;
                }
                if (available) {
                    System.out.println(line);
                    if (line.contains("ID:")) {
                        id = line.split(":\\s")[1];
                        System.out.println("ID: " + id + "\n");

                    } else if (line.isEmpty()) {
                        System.out.println("ID: " + id + "\n");
                        available = false;
                    }
                }
            }
        }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

