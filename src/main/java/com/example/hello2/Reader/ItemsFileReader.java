package com.example.hello2.Reader;

import com.example.hello2.Model.ItemModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class ItemsFileReader {
    public Path filePath = Paths.get("new_items.txt");

    public ArrayList<ItemModel> itemList  = new ArrayList<>(new ItemModel().getItemList());

    public ItemsFileReader(Path filePath) {
        this.filePath = filePath;
    }

    public ItemsFileReader() {
    }

    public ArrayList<ItemModel> getItemList(){
        return itemList;
    }
        // public ArrayList<ItemModel> readFileItems() {
        //  return itemList;
   // }
    public ArrayList<ItemModel> readFileItems() throws IOException {
        File file = new File("new_items.txt");
        FileReader fw = new FileReader(file);
        BufferedReader bw = new BufferedReader(fw);
        String line;
        while((line = bw.readLine()) != null){
            String[] field = line.split(",");
            ItemModel item = new ItemModel(field[0], field[1],field[2],field[3],Integer.parseInt(field[4]),Double.parseDouble(field[5]),field[6]);
            itemList.add(item);
        }
        return itemList;
    }

}
