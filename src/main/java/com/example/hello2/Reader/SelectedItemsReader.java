package com.example.hello2.Reader;

import com.example.hello2.Model.SelectedItems;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SelectedItemsReader {
    public ArrayList<SelectedItems> selectedItemsList = new ArrayList<>();

    public ArrayList<SelectedItems> getSelectedItemsList() {
        return selectedItemsList;
    }

    public ArrayList<SelectedItems> readFileSelectedItems() throws IOException {
        File file = new File("selected_items.txt");
        FileReader fw = new FileReader(file);
        BufferedReader bw = new BufferedReader(fw);
        String line;
        while ((line = bw.readLine()) != null) {
            String[] field = line.split(",");
            String ID = field[0];
            ArrayList<String> ItemsList = new ArrayList<>(Arrays.asList(field).subList(1, field.length));
            SelectedItems selectedItems = new SelectedItems(ID, ItemsList);
            selectedItemsList.add(selectedItems);
        }
        return selectedItemsList;
    }
}
