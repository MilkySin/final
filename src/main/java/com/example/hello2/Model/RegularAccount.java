package com.example.hello2.Model;

import java.util.ArrayList;

public class RegularAccount extends UserModel {
    private int numReturned;


    public RegularAccount(String username, String password, String id, String address, String accountType, int phoneNumber) {
        super(username, password, id, address, accountType, phoneNumber);
    }


    public int getNumReturned() {
        return numReturned;
    }

    public void setNumReturned(int numReturned) {
        this.numReturned = numReturned;
    }

    public void rentItem (ItemModel item){
        rentedItemList.add(item);
    }


    @Override
    public String toString() {
        return "RegularAccount{" + "numReturned=" + numReturned + ", username='" + username + '\'' + ", password='" + password + '\'' + ", id='" + id + '\'' + ", address='" + address + '\'' + ", phoneNumber=" + phoneNumber + ", accountType='" + accountType + '\'' + ", rentedItemList=" + rentedItemList + ", usersList=" + usersList + '}';
    }
}
