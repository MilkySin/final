package com.example.hello2.Writer;

import com.example.hello2.Model.ItemModel;
import com.example.hello2.Model.SelectedItems;
import com.example.hello2.Model.UserModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SelectedItemsWriter {
    public void SelectedItemsWriteFIle(ArrayList<SelectedItems> Items) throws IOException {
        File file = new File("selected_items.txt");
        FileWriter fw = new FileWriter(file, false); // set append to false
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(Items.get(0).getID());
        for(SelectedItems items : Items){
            for(String i : items.getSelectedItemsList()){
                bw.write("," + i);
            }
        }
        bw.write("\n");
        bw.close();
        fw.close();
    }
}
