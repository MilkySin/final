package com.example.hello2.Reader;

import com.example.hello2.Model.UserModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class UserFileReader {
    public Path filePath = Paths.get("userinfo.txt");

    private static ArrayList<UserModel> userList  = new ArrayList<>();

    public UserFileReader(Path filePath) {
        this.filePath = filePath;
    }

    public UserFileReader() {
    }

    public ArrayList<UserModel> readUser() {
        return userList;
    }


}
