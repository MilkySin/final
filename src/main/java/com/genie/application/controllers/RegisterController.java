package com.genie.application.controllers;
import com.genie.application.core.Authenticator;

public class RegisterController {

    private Authenticator authenticator;
    private final String email;
    private final String password;

    public RegisterController(String email, String password) {
            this.email = email;
            this.password = password;
    }

    public boolean Validate()  {
        var emailRegexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,7}$";

        if (this.email.matches(emailRegexPattern)) {
            return authenticator.attempt(this.email, this.password);
        }

        return false;
    }

}
