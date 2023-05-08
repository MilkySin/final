package com.example.hello2.Modal;

import java.util.ArrayList;

public class ItemListModel {

    public ArrayList<ItemModel> itemList = new ArrayList<>();

    public ItemListModel(ArrayList<ItemModel> itemList) {
        this.itemList = itemList;
    }

    public ItemListModel() {
    }

    public ArrayList<ItemModel> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<ItemModel> itemList) {
        this.itemList = itemList;
    }
}
