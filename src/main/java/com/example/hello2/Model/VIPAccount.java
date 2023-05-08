package com.example.hello2.Model;

import java.util.ArrayList;

public class VIPAccount extends UserModel{

    private int rewardPoints;
    public VIPAccount(String username, String password, String name, String id, String address, int phoneNumber, ArrayList<ItemModel> rentedItemList) {
        super(username, password, name, id, address, phoneNumber, rentedItemList);
        accountType = "VIP";
    }

    public void rentItem (ItemModel item){
        rentedItemList.add(item);
        rewardPoints += 10;
        if (rewardPoints >= 100){
            System.out.println("yay you get to rent the item for free"); // implement congratulations in javafx
        }
    }
}
