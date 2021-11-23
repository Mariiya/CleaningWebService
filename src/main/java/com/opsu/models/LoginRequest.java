package com.opsu.models;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
/**
 * Class login
 * @author group 183
 * @version 2.1
 */

public class LoginRequest {
    /** field email */
    @NotEmpty(message ="Email can not be empty")
    private String email;
    /** field password */
    @Size(min=8,message = "Email length should be from 8 chars")
    private String password;

    /**
     * constructor- create new login request
     * @param nameOrEmail user's name
     * @param password user's password
     */
    public LoginRequest(String nameOrEmail, String password) {
        this.email = nameOrEmail;
        this.password = password;
    }
    /** @return  email */
    public String getEmail() {
        return email;
    }
    /**@param nameOrEmail user's name */
    public void setEmail(String nameOrEmail) {
        this.email = nameOrEmail;
    }
    /** @return  password */
    public String getPassword() {
        return password;
    }
    /**@param password user's password */
    public void setPassword(String password) {
        this.password = password;
    }
}
