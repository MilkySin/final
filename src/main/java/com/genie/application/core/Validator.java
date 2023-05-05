package com.genie.application.core;

public class Validator {

    static final String emailRegexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,7}$";

    public static boolean email(String s) {
        if (s == null) {
            return false;
        }
        return s.matches(emailRegexPattern);
    }

    public static boolean string(String s) {
        return s.trim().equals(s);
    }

    public static boolean string(String s, int length) {
        return s.trim().equals(s) && s.length() >= length;
    }

}

