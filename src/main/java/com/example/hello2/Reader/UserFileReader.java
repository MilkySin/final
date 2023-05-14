package com.example.hello2.Reader;
//Fixed and is working correctly

import com.example.hello2.Model.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class UserFileReader {
    public Path filePath = Paths.get("userinfo.txt");
    private ArrayList<UserModel> userList = new ArrayList<>();

    public UserFileReader(Path filePath) {
        this.filePath = filePath;
    }

    public UserFileReader() {
    }

    public ArrayList<UserModel> getUserList() {
        return userList;
    }

    public ArrayList<UserModel> readFileUser() throws IOException {
        File file = new File("userinfo.txt");
        FileReader fw = new FileReader(file);
        BufferedReader bw = new BufferedReader(fw);
        String line;
        while ((line = bw.readLine()) != null) {
            String[] field = line.split(",");
            UserModel user = new UserModel(field[0], field[1], field[2], field[3], field[4], Integer.parseInt(field[5]),
                                           Integer.parseInt(field[6]));
            user.setNumReturned(Integer.parseInt(field[6]));
            userList.add(user);
        }
        return userList;
    }
}
