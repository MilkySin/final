package com.example.hello2.Writer;

import com.example.hello2.Model.ItemModel;
import com.example.hello2.Model.UserModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UsersFileWriter {

    private String Username;
    private String Password;
    private String ID;
    private String Address, AccountType;
    private int PhoneNumber;

    public void writeUsers(ArrayList<UserModel> users) throws IOException {
        File file = new File("userinfo.txt");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        for(UserModel addedUser : users){
            bw.write(addedUser.getUsername() + "," + addedUser.getPassword() + "," + addedUser.getId() + "," + addedUser.getAddress() + "," + addedUser.getAccountType() + "," + addedUser.getPhoneNumber());
            bw.write("\n");
        }
        bw.close();
        fw.close();
    }
    public void FileWriter(ArrayList<UserModel> Userlist) throws IOException {
        File file = new File("userinfo.txt");
        FileWriter fw = new FileWriter(file, false); // set append to false
        BufferedWriter bw = new BufferedWriter(fw);
        for (UserModel user : Userlist) {
            bw.write(user.getUsername() + "," + user.getPassword() + "," + user.getId() + "," + user.getAddress() + "," + user.getAccountType() + "," + user.getPhoneNumber());
            bw.write("\n");
        }
        bw.close();
        fw.close();
    }

}
