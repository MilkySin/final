package com.genie.application.core;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class Authenticator {

    private Database db = null;

    public Authenticator(Database db) {
        this.db = db;
    }

    public boolean attempt(String email, String password) {
        try {
            var data = db.query("Select * From user where email = ?", email).get();
            if (data.next()) {
                var result = BCrypt.verifyer(BCrypt.Version.VERSION_2Y).verify(password.getBytes(StandardCharsets.UTF_8), data.getBytes("password"));
                return result.verified;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
