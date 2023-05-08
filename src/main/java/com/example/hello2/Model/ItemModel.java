package com.example.hello2.Model;

import java.util.ArrayList;

public class ItemModel {


//    ID: I123-321
//    Title: FFF
//    Rental Type: DVD
//    Loan Type: 1 Week Loan
//    Copies: 0
//    Rental Fee (USD): 39
//    Rental Status: Borrowed
    private String ID;
    private String title;
    private String rentalType;

    private String loanType;

    private int copies;

    private double fee;

    private String status;


    private static ArrayList<ItemModel> itemList = new ArrayList<>();

    public ItemModel(String ID, String title, String rentalType, String loanType, int copies, double fee,  String status) {
        this.ID = ID;
        this.title = title;
        this.rentalType = rentalType;
        this.loanType = loanType;
        this.copies = copies;
        this.fee = fee;
        this.status=status;
    }

    public ItemModel() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRentalType() {
        return rentalType;
    }

    public void setRentalType(String rentalType) {
        this.rentalType = rentalType;
    }


    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public ArrayList<ItemModel> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<ItemModel> itemList) {
        this.itemList = itemList;
    }
}


