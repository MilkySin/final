package com.example.hello2.Writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UsersFileWriter {

    private String Username;
    private String Password;
    private String ID;
    private String Address, AccountType;
    private int PhoneNumber;

    public void FileWriter(String username, String password, String ID, String accountType, String address, int number) throws IOException {

        File file = new File("userinfo.txt");
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(username + "," + password + "," + ID + "," + accountType + "," + address + "," + number);
        bw.write("\n");
        bw.close();
        fw.close();
    }
}
