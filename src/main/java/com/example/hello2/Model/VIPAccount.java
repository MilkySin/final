package com.example.hello2.Model;

import java.util.ArrayList;

public class VIPAccount extends UserModel{

    private int rewardPoints;

    public VIPAccount(String username, String password, String id, String address, String accountType,
                      int phoneNumber) {
        super(username, password, id, address, accountType, phoneNumber);
    }

    public void rentItem (ItemModel item){
        rentedItemList.add(item);
        rewardPoints += 10;
        if (rewardPoints >= 100){
            System.out.println("yay you get to rent the item for free"); // implement congratulations in javafx
        }
    }

    @Override
    public String toString() {
        return "VIPAccount{" + "rewardPoints=" + rewardPoints + ", username='" + username + '\'' + ", password='" + password + '\'' + ", id='" + id + '\'' + ", address='" + address + '\'' + ", phoneNumber=" + phoneNumber + ", accountType='" + accountType + '\'' + ", rentedItemList=" + rentedItemList + ", usersList=" + usersList + '}';
    }
}
