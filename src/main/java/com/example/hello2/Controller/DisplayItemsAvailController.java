package com.example.hello2.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DisplayItemsAvailController {
    @FXML
    public TextArea textArea;

    public Button back;
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
//                String line;
//                String id = "";
//                while ((line = br.readLine()) != null) {
//                    String[] fields = line.split(":\\s");
//                    if (fields[0].equals("ID")) {
//                        id = fields[1];
//                    } else if (fields[0].equals("Rental Status") && fields[1].equals("Available")) {
//                        System.out.println(id);
//                    }
//                }
            String line;
            String id = "", title = "", rentalType = "", loanType = "", copies = "", rentalFee = "";
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(":\\s");
                switch (fields[0]) {
                    case "ID":
                        id = fields[1];
                        break;
                    case "Title":
                        title = fields[1];
                        break;
                    case "Rental Type":
                        rentalType = fields[1];
                        break;
                    case "Loan Type":
                        loanType = fields[1];
                        break;
                    case "Copies":
                        copies = fields[1];
                        break;
                    case "Rental Fee (USD)":
                        rentalFee = fields[1];
                        break;
                    case "Rental Status":
                        if (fields[1].equals("Available")) {
                            sb.append("ID: ").append(id).append("\n");
                            sb.append("Title: ").append(title).append("\n");
                            sb.append("Rental Type: ").append(rentalType).append("\n");
                            sb.append("Loan Type: ").append(loanType).append("\n");
                            sb.append("Copies: ").append(copies).append("\n");
                            sb.append("Rental Fee: ").append(rentalFee).append("\n\n");
                        }

                        textArea.setText(String.valueOf(sb));
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

