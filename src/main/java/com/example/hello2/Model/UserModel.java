package com.example.hello2.Model;

import java.util.ArrayList;

public class UserModel {
//    Username: shirin
//    Password: 09SHhi!67
//    ID: C987
//    Address: dfg
//    Phone Number: 09876
//    Account Type: Regular
    protected String username;

    protected String password;

    protected String id;

    protected String address;
    protected int phoneNumber;
    protected String accountType;
    protected ArrayList<ItemModel> rentedItemList;
    protected static ArrayList<UserModel> usersList = new ArrayList<>();

    public UserModel(String username, String password, String id, String address, String accountType, int phoneNumber) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<ItemModel> getRentedItemList() {
        return rentedItemList;
    }

    public void setRentedItemList(ArrayList<ItemModel> rentedItemList) {
        this.rentedItemList = rentedItemList;
    }


    public void rentItem (ItemModel item){}
    public void returnItem(ItemModel item) {
        try {
            rentedItemList.remove(item);
        }
        catch (RuntimeException ex) {
            System.out.println("cannot return item if not borrowed");
            //implement in javafx
        }
    }

    public ArrayList<UserModel> getUsersList() {
        return usersList;
    }

    public void setUsersList(ArrayList<UserModel> users) {
        this.usersList = users;
    }

    @Override
    public String toString() {
        return "UserModel{" + "username='" + username + '\'' + ", password='" + password + '\'' + ", id='" + id + '\'' + ", address='" + address + '\'' + ", phoneNumber=" + phoneNumber + ", accountType='" + accountType + '\'' + ", rentedItemList=" + rentedItemList + ", usersList=" + usersList + '}';
    }
}
