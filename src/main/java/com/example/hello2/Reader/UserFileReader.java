package com.example.hello2.Reader;
//Fixed and is working correctly

import com.example.hello2.Model.UserModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class UserFileReader {
    public ArrayList<UserModel> userList = new ArrayList<>();

    public ArrayList<UserModel> getUserList() {
        return userList;
    }

    public ArrayList<UserModel> readFileUser() throws IOException {
        File file = new File("src/main/resources/com/example/hello2/Data/userinfo.txt");
        FileReader fw = new FileReader(file);
        BufferedReader bw = new BufferedReader(fw);
        String line;
        while ((line = bw.readLine()) != null) {
            String[] field = line.split(",");

            // Check if the array has enough elements
            if (field.length >= 8) {
                UserModel user = new UserModel(field[0], field[1], field[2], field[3], field[4], field[5],
                        Double.parseDouble(field[7]));
                user.setNumReturned(Integer.parseInt(field[6]));
                userList.add(user);
            } else {
                // Handle the case where the line does not have enough fields
                // You can log an error message or handle it in any other appropriate way
                System.err.println("Invalid line: " + line);
            }
        }
        return userList;

    }
}