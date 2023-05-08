package com.example.hello2.Writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ItemsFileWriter {
    private String ID;
    private String title;
    private String rentalType;

    private String loanType;

    private int copies;

    private double fee;

    private String status;
    public void FileWriter(String ID, String title, String rentalType, String loanType, int copies, double fee) throws IOException {

        File file = new File("new_items.txt");
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(ID + "," + title + "," + rentalType + "," + loanType + "," + copies + "," + fee);
        bw.write("\n");
        bw.close();
        fw.close();
    }

}
