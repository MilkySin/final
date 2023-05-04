package com.genie.application.model;

import java.util.HashSet;

public class Guest extends Account{


    public Guest(String ID, String name, String address, String phone, String username, String pass, HashSet<items> listofrental) {
        super(ID, name, address, phone, username, pass, listofrental);
    }
}
