package com.example.hello2.Modal;

import java.util.ArrayList;

public class UserListModel {
    public ArrayList<UserModel> Userlist=new ArrayList<>();
    public UserListModel(ArrayList<UserModel> Userlist){this.Userlist=Userlist;}

    public UserListModel() {
    }

    public ArrayList<UserModel> getUserlist() {
        return Userlist;
    }

    public void setUserlist(ArrayList<UserModel> userlist) {
        Userlist = userlist;
    }
}
