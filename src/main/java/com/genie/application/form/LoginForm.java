package com.genie.application.form;

import com.genie.application.core.Validator;

import java.util.ArrayList;
import java.util.List;

public class LoginForm {
    protected List<String> errors = new ArrayList<>();
    public boolean validate(String username, String password) {
        if (!Validator.string(username, 5)) {
            this.errors.add("Please provide a valid username");
        }
        if (!Validator.string(password, 5)) {
            this.errors.add("Please provide a valid password");
        }
        return this.errors.isEmpty();
    }
    public List<String> getErrors() {
        return this.errors;
    }
    public void addCustomError(String message) {
        this.errors.add(message);
    }

    public String format() {
        return String.join("\n", this.errors);
    }
}
