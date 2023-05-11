package com.example.hello2.Model;

import java.util.ArrayList;

public class VIPAccount extends UserModel{

    private int rewardPoints;

    public VIPAccount(String username, String password, String name, String id, String address, int phoneNumber, ArrayList<ItemModel> rentedItemList, int rewardPoints) {
        super(username, password, name, id, address, phoneNumber);
        accountType = "VIP";
        rewardPoints = 0;
    }

    public void rentItem (ItemModel item){
        rentedItemList.add(item.getID());
        rewardPoints += 10;
        if (rewardPoints >= 100){
            System.out.println("Yay you get to rent an item for free"); // implement congratulations in javafx
        }
    }

    @Override
    public String toString() {
        return "VIPAccount{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", accountType='" + accountType + '\'' +
                ", rentedItemList=" + rentedItemList + '\'' +
                ", Reward Point =" + rewardPoints + "}\n\n";
    }
}
