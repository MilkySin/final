package com.genie.application.core;


public class Authenticator {

    private Database db = null;

    public Authenticator(Database db) {
        this.db = db;
    }

    public boolean attempt(String email, String password) {
        return true;
    }
}
