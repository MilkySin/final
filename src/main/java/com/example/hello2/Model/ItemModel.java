package com.example.hello2.Model;

import java.util.ArrayList;

public class ItemModel {
    private String ID;
    private String title;
    private String rentalType;

    private String loanType;

    private int copies;

    private double fee;
    private String genre;

    private String status;

    private ArrayList<ItemModel> itemList = new ArrayList<>();
    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public ItemModel(String ID, String title, String genre, String rentalType, String loanType, int copies,
                     double fee, String status) {
        this.ID = ID;
        this.title = title;
        this.genre = genre;
        this.rentalType = rentalType;
        this.loanType = loanType;
        this.copies = copies;
        this.fee = fee;
        this.status = status;
    }

    public ItemModel() {
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    @Override
    public String toString() {
        return "ID: " + ID + "\n" + "Title: " + title + "\n" + "Genre: " + genre + "\n" + "Rental Type: " + rentalType + "\n" + "Loan Type: " + loanType + "\n" + "Copies: " + copies + "\n" + "Rental Fee (USD): " + fee + "\n" + "Rental Status: " + status + "\n\n";
    }
}


