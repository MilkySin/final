package com.example.hello2.Reader;

import com.example.hello2.Model.ItemModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ItemsFileReader {

    public ArrayList<ItemModel> itemList = new ArrayList<>(new ItemModel().getItemList());


    public ItemsFileReader() {
    }

    public ArrayList<ItemModel> getItemList() {
        return itemList;
    }

    public ArrayList<ItemModel> readFileItems() throws IOException {
        File file = new File("src/main/resources/com/example/hello2/Data/items.txt");
        FileReader fw = new FileReader(file);
        BufferedReader bw = new BufferedReader(fw);
        String line;
        while ((line = bw.readLine()) != null) {
            String[] field = line.split(",");
            ItemModel item = new ItemModel(field[0], field[1], field[2], field[3], field[4], Integer.parseInt(field[5]),
                    Double.parseDouble(field[6]), field[7]);
            itemList.add(item);
        }
        return itemList;
    }

}
