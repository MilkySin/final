package com.example.hello2.Reader;
//Fixed and is working correctly

import com.example.hello2.Model.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class UserFileReader {
    public Path filePath = Paths.get("userinfo.txt");

    private ArrayList<UserModel> userList  = new ArrayList<>();

    public UserFileReader(Path filePath) {
        this.filePath = filePath;
    }

    public UserFileReader() {
    }

    public ArrayList<UserModel> getUserList(){
        return userList;
    }
    public ArrayList<UserModel> readUser() throws IOException {
        File file = new File("userinfo.txt");
        FileReader fw = new FileReader(file);
        BufferedReader bw = new BufferedReader(fw);
        String line;
        while((line = bw.readLine()) != null){
            String[] field = line.split(",");
            UserModel user = new UserModel(field[0], field[1],field[2],field[3],field[4],Integer.parseInt(field[5]));

            /*if (field[4].equals("Guest")){
                GuestAccount guestUser = new GuestAccount(field[6],field[7],field[1],field[0],field[2],Integer.parseInt(field[3]),new ArrayList<>());
            } else if (field[4].equals("Regular")){
                RegularAccount regUser = new RegularAccount(field[6],field[7],field[1],field[0],field[2],Integer.parseInt(field[3]),new ArrayList<>());
            } else if (field[4].equals("VIP")){
                VIPAccount VIPUser = new VIPAccount(field[6],field[7],field[1],field[0],field[2],Integer.parseInt(field[3]),new ArrayList<>());
            }*/

            userList.add(user);
        }
        return userList;
    }
}
