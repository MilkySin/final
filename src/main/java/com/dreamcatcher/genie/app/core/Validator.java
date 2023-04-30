package com.dreamcatcher.genie.app.core;

public class Validator {

    static final String emailRegexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,7}$";

    public static boolean email(String s) {
        if (s == null) {
            return false;
        }
        return s.matches(emailRegexPattern);
    }

    public static boolean password(String s, int start) {
        return s.trim().equals(s) && s.length() > start;
    }

}

