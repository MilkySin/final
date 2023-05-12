package com.example.hello2.Model.User;

import com.example.hello2.Model.ItemModel;
import com.example.hello2.Model.UserModel;

import java.util.ArrayList;

public class VIPAccount extends UserModel {
    private int numReturned;
    private int rewardPoints;

    public VIPAccount(String username, String password, String name, String id, String address, int phoneNumber, ArrayList<ItemModel> rentedItemList) {
        super(username, password, name, id, address, phoneNumber);
        numReturned = 0;
        accountType = "VIP";
        rewardPoints = 0;
    }

    public void rentItem (ItemModel item){
        rentedItemList.add(item.getID());
        rewardPoints += 10;
        if (rewardPoints >= 100){
            System.out.println("yay you get to rent the item for free"); // implement congratulations in javafx
        }
    }

    @Override
    public String toString() {
        return "VIPAccount{" +
                "numReturned=" + numReturned +
                ", rewardPoints=" + rewardPoints +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}