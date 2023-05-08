package com.example.hello2.Model;

import java.util.ArrayList;

public class GuestAccount extends UserModel {
    private final int rentLimit = 2;
    private int numReturned;


    public GuestAccount(String username, String password, String name, String id, String address, int phoneNumber, ArrayList<ItemModel> rentedItemList) {
        super(username, password, name, id, address, phoneNumber, rentedItemList);
        numReturned = 0;
        accountType = "Guest";
    }

    public int getNumReturned() {
        return numReturned;
    }

    public void setNumReturned(int numReturned) {
        this.numReturned = numReturned;
    }

    @Override
    public void rentItem (ItemModel item){
        // Can't rent more than limit
        if (rentedItemList.size() > rentLimit){
            System.out.println("cannot rent over the rent limit of 2");
            return;
            // display error in javafx
        }

        // Guest can only borrow 1 week
        if (item.getLoanType().equals("1-week")){
            rentedItemList.add(item);
            // set item rented status to "borrowed"
        } else {
            System.out.println("placeholder"); // display error in javafx
        }
    }

    @Override
    public String toString() {
        return "GuestAccount{" +
                "rentLimit=" + rentLimit +
                ", numReturned=" + numReturned +
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
