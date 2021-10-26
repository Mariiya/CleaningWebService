package com.opsu.models;

import com.opsu.models.enumeration.Role;

import javax.validation.constraints.*;
import java.math.BigInteger;

public class User {
    @Min(value = 0, message = "User id is not be correct")
    private BigInteger id;
    @NotEmpty(message = "User name number can not be empty")
    @Size(min = 13, max = 13, message = "User number should contain 13 digits")
    private String phoneNumber;
    @NotEmpty(message = "User email can not be empty")
    @Pattern(regexp = "^(?:[a-zA-Z0-9_'^&/+-])+(?:\\.(?:[a-zA-Z0-9_'^&/+-])+)" + "*@(?:(?:\\[?(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))\\.)" +
            "{3}(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\]?)|(?:[a-zA-Z0-9-]+\\.)" + "+(?:[a-zA-Z]){2,}\\.?)$",
            message = "Email format is not correct")
    private String email;

    @NotEmpty
    private String password;
    @NotNull
    private Role role;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User(BigInteger id, String phoneNumber, String email, String password, Role role) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
