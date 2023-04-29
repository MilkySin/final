package com.dreamcatcher.genie.app.controllers;
import com.dreamcatcher.genie.app.core.Authenticator;

public class SessionController {

    private Authenticator authenticator;
    private final String email;
    private final String password;

    public SessionController(String email, String password) {
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

    public void Login() {
        return;
    }
}
