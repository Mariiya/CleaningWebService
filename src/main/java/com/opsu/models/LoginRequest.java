package com.opsu.models;

public class LoginRequest {
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
