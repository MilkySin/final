package com.genie.application.model;

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

}
