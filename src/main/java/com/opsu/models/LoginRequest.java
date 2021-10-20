package com.opsu.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LoginRequest {
    @NotEmpty(message ="Email can not be empty")
    @Size(min=50,message = "Email length should be from 1 to 50  digits")
    @Pattern(regexp = "^(?:[a-zA-Z0-9_'^&/+-])+(?:\\.(?:[a-zA-Z0-9_'^&/+-])+)" + "*@(?:(?:\\[?(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))\\.)" +
            "{3}(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\]?)|(?:[a-zA-Z0-9-]+\\.)" + "+(?:[a-zA-Z]){2,}\\.?)$",
            message = "Email format is not correct")
    private String email;
    private String password;

    public LoginRequest(String nameOrEmail, String password) {
        this.email = nameOrEmail;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String nameOrEmail) {
        this.email = nameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
