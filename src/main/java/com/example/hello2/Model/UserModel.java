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
    protected String name;

    protected String id;

    protected String address;
    protected int phoneNumber;
    protected ArrayList<ItemModel> rentedItemList = new ArrayList<>();
    protected String accountType;

    public UserModel(String username, String password, String name, String id, String address, int phoneNumber, ArrayList<ItemModel> rentedItemList) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.id = id;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.rentedItemList = rentedItemList;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCustomerType() {
        return accountType;
    }

    public void rentItem (ItemModel item){};
    public void returnItem(ItemModel item) {
        try {
            rentedItemList.remove(item);
        }
        catch (RuntimeException ex) {
            System.out.println("cannot return item if not borrowed");
            //implement in javafx
        }
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", rentedItemList=" + rentedItemList +
                '}';
    }
}
