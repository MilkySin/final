package com.example.hello2.Model;

public class UserModel {
    //    Username: shirin
//    Password: 09SHhi!67
//    ID: C987
//    Address: dfg
//    Phone Number: 09876
//    Account Type: Regular
    protected String username;

    protected String password;

    protected String id;

    protected String address;
    protected int phoneNumber;
    protected String accountType;
    protected float balance;
    protected int numReturned;

    public UserModel(String username, String password, String id, String address, String accountType, int phoneNumber
            , float balance) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.accountType = accountType;
        this.balance = balance;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getNumReturned() {
        return numReturned;
    }

    public void setNumReturned(int numReturned) {
        this.numReturned = numReturned;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nUsername: " + username + "\nPassword: " + password + "\nAddress: " + address +
                "\nPhone Number: " + phoneNumber + "\nAccount Type: " + accountType + "\n\n";
    }
}
