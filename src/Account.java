import java.util.*;

import java.io.*;
import java.util.ArrayList;

public class Account {
    private String ID, Name, Address, Phone;
    private HashMap<String, String> credentials;
    private HashSet<items> Listofrental;

    public Account(String ID, String name, String address, String phone, HashMap<String, String> credentials, HashSet<items> listofrental) {
        this.ID = ID;
        Name = name;
        Address = address;
        Phone = phone;
        this.credentials = credentials;
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

    public HashMap<String, String> getCredentials() {
        return credentials;
    }

    public void setCredentials(HashMap<String, String> credentials) throws Exception {
        BufferedWriter brw = new BufferedWriter(new FileWriter(RentalSys.file));
        this.credentials = credentials;
    }
}
