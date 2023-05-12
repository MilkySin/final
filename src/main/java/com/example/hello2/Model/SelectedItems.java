package com.example.hello2.Model;

import java.util.ArrayList;

public class SelectedItems {
    private  String ID;
    private ArrayList<String> selectedItemsList;

    public SelectedItems(String ID, ArrayList<String> selectedItemsList) {
        this.ID = ID;
        this.selectedItemsList = selectedItemsList;
    }

    public SelectedItems(String ID) {
        this.ID = ID;
    }

    public SelectedItems() {

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public ArrayList<String> getSelectedItemsList() {
        return selectedItemsList;
    }

    public void setSelectedItemsList(ArrayList<String> selectedItemsList) {
        this.selectedItemsList = selectedItemsList;
    }

    @Override
    public String toString() {
        return ID + "," + selectedItemsList;
    }

}