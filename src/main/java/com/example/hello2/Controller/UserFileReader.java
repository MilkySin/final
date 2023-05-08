package com.example.hello2.Controller;

import com.example.hello2.Modal.ItemModel;
import com.example.hello2.Modal.UserModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class UserFileReader {
    public Path filePath = Paths.get("userinfo.txt");

    public ArrayList<UserModel> userList  = new ArrayList<>();

    public UserFileReader(Path filePath) {
        this.filePath = filePath;
    }

    public UserFileReader() {
    }

    public ArrayList<UserModel> readUser() {
//        List<String> itemList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(filePath.toUri())));
            String line = reader.readLine();
//            System.out.println(line);

            while (line != null) {
//                itemList.add(line);
//                line.split(",");

                String[] temp = line.split(",");
//                System.out.println(toString(line.split(",")));


//                private String Username;
//                private String Password;
//                private String ID;
//                private String Address, AccountType;
//                private int PhoneNumber;
                UserModel item = new UserModel(temp[0],temp[1],temp[2], temp[3], temp[4], Integer.parseInt(temp[5]));

                userList.add(item);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }

}
