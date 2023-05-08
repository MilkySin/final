package com.example.hello2.Modal;

import java.util.ArrayList;

public class RegularAccount extends UserModel {
    private int numReturned;

    public RegularAccount(String username, String password, String name, String id, String address, int phoneNumber, ArrayList<ItemModel> rentedItemList) {
        super(username, password, name, id, address, phoneNumber, rentedItemList);
        numReturned = 0;
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
        return "RegularAccount{" +
                "numReturned=" + numReturned +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", rentedItemList=" + rentedItemList +
                '}';
    }
}
