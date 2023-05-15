package com.example.hello2.Writer;

import com.example.hello2.Model.ItemModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ItemsFileWriter {
    private String ID;
    private String title;
    private String rentalType;
    private String loanType;
    private String Availability;
    private int copies;
    private double fee;
    private String status;

    public void ItemsWriteFile(ArrayList<ItemModel> itemlist) throws IOException {
        File file = new File("items.txt");
        FileWriter fw = new FileWriter(file, false); // set append to false
        BufferedWriter bw = new BufferedWriter(fw);
        for (ItemModel item : itemlist) {
            if (item.getCopies() == 0) {
                item.setStatus("Borrowed");

            } else item.setStatus("Available");
            bw.write(
                    item.getID() + "," + item.getTitle() + "," + item.getGenre() + "," + item.getRentalType() + "," + item.getLoanType() + "," + item.getCopies() + "," + item.getFee() + "," + item.getStatus());
            bw.write("\n");
        }
        bw.close();
        fw.close();
    }

}
