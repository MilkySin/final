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

    public ArrayList<ItemModel> readItems() {
        return itemList;
    }

}
