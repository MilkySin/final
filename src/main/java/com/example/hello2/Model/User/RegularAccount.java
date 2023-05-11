package com.example.hello2.Model.User;

import com.example.hello2.Model.ItemModel;
import com.example.hello2.Model.UserModel;

import java.util.ArrayList;

public class RegularAccount extends UserModel {
    private int numReturned;

    public RegularAccount(String username, String password, String name, String id, String address, int phoneNumber, ArrayList<ItemModel> rentedItemList) {
        super(username, password, name, id, address, phoneNumber);
        numReturned = 0;
        accountType = "Regular";
    }

    public int getNumReturned() {
        return numReturned;
    }

    public void setNumReturned(int numReturned) {
        this.numReturned = numReturned;
    }

    public void rentItem (ItemModel item){
        rentedItemList.add(item.getID());
    }

    @Override
    public String toString() {
        return "RegularAccount{" +
                "numReturned=" + numReturned +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}
