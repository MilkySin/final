package com.dreamcatcher.genie.app.model;

import java.util.HashSet;

public class Account {
    private String ID, Name, Address, Phone, Username, Pass;
    private HashSet<items> Listofrental;

    public Account(String ID, String name, String address, String phone, String username, String pass, HashSet<items> listofrental) {
        this.ID = ID;
        Name = name;
        Address = address;
        Phone = phone;
        Username = username;
        Pass = pass;
        Listofrental = listofrental;
    }



    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public HashSet<items> getListofrental() {
        return Listofrental;
    }

    public void setListofrental(HashSet<items> listofrental) {
        Listofrental = listofrental;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }
}
