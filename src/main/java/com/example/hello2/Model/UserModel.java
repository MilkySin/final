package com.example.hello2.Model;

import java.util.ArrayList;

public class UserModel {
//    Username: shirin
//    Password: 09SHhi!67
//    ID: C987
//    Address: dfg
//    Phone Number: 09876
//    Account Type: Regular
    private String Username;
    private String Password;
    private String ID;
    private String Address, AccountType;
    private int PhoneNumber;
    private ArrayList<UserModel> Userlist=new ArrayList<>();

    public UserModel(String username, String password, String ID, String address, String accountType, int phoneNumber) {
        Username = username;
        Password = password;
        this.ID = ID;
        Address = address;
        AccountType = accountType;
        PhoneNumber = phoneNumber;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String accountType) {
        AccountType = accountType;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public UserModel() {
    }
}
