package com.example.hello2.Writer;

import com.example.hello2.Model.UserModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UsersFileWriter {

    public void UserWriteFile(ArrayList<UserModel> userModelArrayList) throws IOException {
        File file = new File("src/main/resources/com/example/hello2/Data/userinfo.txt");
        FileWriter fw = new FileWriter(file, false); // set append to false
        BufferedWriter bw = new BufferedWriter(fw);
        for (UserModel user : userModelArrayList) {
            bw.write(
                    user.getUsername() + "," + user.getPassword() + "," + user.getId() + "," + user.getAddress() + ","
                            + user.getAccountType() + "," + user.getPhoneNumber() + "," + user.getNumReturned() + "," + String.format("%.2f", user.getBalance()));
            bw.write("\n");
        }
        bw.close();
        fw.close();
    }

}
