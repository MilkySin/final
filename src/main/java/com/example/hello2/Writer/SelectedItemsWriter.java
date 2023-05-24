package com.example.hello2.Writer;

import com.example.hello2.Model.SelectedItems;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SelectedItemsWriter {
    public void SelectedItemsWriteFIle(ArrayList<SelectedItems> selectedItemsArrayList) throws IOException {
        File file = new File("src/main/resources/com/example/hello2/Data/selected_items.txt");
        FileWriter fw = new FileWriter(file, false); // set append to false
        BufferedWriter bw = new BufferedWriter(fw);

        for (SelectedItems items : selectedItemsArrayList) {
            if (items.getSelectedItemsList() == null) {
                bw.write(items.getID());
            } else {
                bw.write(items.getID());
                for (String i : items.getSelectedItemsList()) {
                    bw.write("," + i);
                }
            }
            bw.write("\n");
        }
        bw.close();
        fw.close();
    }
}
