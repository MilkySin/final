package com.example.hello2.Reader;

import com.example.hello2.Model.ItemModel;
import com.example.hello2.Model.SelectedItems;
import com.example.hello2.Model.UserModel;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class SelectedItemsReader {
    public ArrayList<String> selectedItemsList = new ArrayList<>();

    public SelectedItems readFileSelectedItems() throws IOException {
        File file = new File("selected_items.txt");
        FileReader fw = new FileReader(file);
        BufferedReader bw = new BufferedReader(fw);
        String line;
        SelectedItems selectedItems = null;
        while ((line = bw.readLine()) != null) {
            String[] field = line.split(",");
            selectedItemsList.addAll(Arrays.asList(field).subList(1, field.length));
            selectedItems = new SelectedItems(field[0], selectedItemsList);
        }
        return selectedItems;
    }
}
