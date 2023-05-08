package com.example.hello2.Model;

import java.util.ArrayList;

public class PromoteAccount {
    public RegularAccount Promote (GuestAccount account) {
        if(account.getNumReturned() < 3){
            System.out.println("You need to have borrowed and returned 3 or more items to promote your account!");
        } else {
            String username = account.getUsername();
            String password = account.getPassword();
            String id = account.getId();
            String name = account.getName();
            String address = account.getAddress();
            int phoneNumber = account.getPhoneNumber();
            ArrayList<ItemModel> rentedItemList = account.getRentedItemList();
            return new RegularAccount(username, password, id, name, address, phoneNumber, rentedItemList);
        }
        return null;
    }

    public VIPAccount Promote (RegularAccount account) {
        if(account.getNumReturned() < 5){
            System.out.println("You need to have borrowed and returned 5 or more items to promote your account!");
        } else {
            String username = account.getUsername();
            String password = account.getPassword();
            String id = account.getId();
            String name = account.getName();
            String address = account.getAddress();
            int phoneNumber = account.getPhoneNumber();
            ArrayList<ItemModel> rentedItemList = account.getRentedItemList();
            return new VIPAccount(username, password, id, name, address, phoneNumber, rentedItemList);
        }
        return null;
    }
}
