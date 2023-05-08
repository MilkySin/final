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
    public void FileWriter(String ID, String title, String rentalType, String loanType, int copies, double fee,String Availability) throws IOException {

        File file = new File("new_items.txt");
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        ItemModel item = new ItemModel(ID, title,rentalType,loanType,copies,fee,Availability);
        item.getItemList().add(item);
        for (ItemModel items : item.getItemList()) {
            bw.write(item.getID() + "," + item.getTitle() + "," + item.getRentalType() + "," + item.getLoanType() + "," + item.getCopies() + "," + item.getFee()+","+item.getStatus());
            bw.write("\n");
        }
        bw.close();
        fw.close();
    }


    public void FileWriter(ArrayList<ItemModel> itemlist) throws IOException {
        File file = new File("new_items.txt");
        FileWriter fw = new FileWriter(file, false); // set append to false
        BufferedWriter bw = new BufferedWriter(fw);
        for (ItemModel item : itemlist) {
            bw.write(item.getID() + "," + item.getTitle() + "," + item.getRentalType() + "," + item.getLoanType() + "," + item.getCopies() + "," + item.getFee()+","+item.getStatus());
            bw.write("\n");
        }
        bw.close();
        fw.close();
    }

}
