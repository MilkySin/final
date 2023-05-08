package com.example.hello2.Controller;

import com.example.hello2.Modal.ItemModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class AFileReader {
    public Path filePath = Paths.get("new_items.txt");

    public ArrayList<ItemModel> itemList  = new ArrayList<ItemModel>();

    public AFileReader(Path filePath) {
        this.filePath = filePath;
    }

    public AFileReader() {
    }

    public ArrayList<ItemModel> readItems() {
//        List<String> itemList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(filePath.toUri())));
            String line = reader.readLine();
//            System.out.println(line);

            while (line != null) {
//                itemList.add(line);
//                line.split(",");

                String[] temp = line.split(",");
//                System.out.println(toString(line.split(",")));

                ItemModel item = new ItemModel(temp[0],temp[1],temp[2], temp[3], Integer.parseInt(temp[4]), Double.parseDouble(temp[5]),
                                               temp[6]);
                itemList.add(item);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemList;
    }

}
