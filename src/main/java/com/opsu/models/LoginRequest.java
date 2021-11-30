package com.opsu.models;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
/**
 * In this class we store information about  login process
 * @author group 183
 * @version 2.1
 */

public class LoginRequest {
    /** field email
     *field can't be empty
     */
    @NotEmpty(message ="Email can not be empty")
    private String email;
    /** field password
     *has limit in size
     */
    @Size(min=8,message = "Email length should be from 8 chars")
    private String password;

    /**
     * This constructor creates new login request
     * @param nameOrEmail user's name
     * @param password user's password
     */
    public LoginRequest(String nameOrEmail, String password) {
        this.email = nameOrEmail;
        this.password = password;
    }
    /** receives information about email or email
     * @return  email */
    public String getEmail() {
        return email;
    }
    /**inserts information  about email or name
     * @param nameOrEmail user's name */
    public void setEmail(String nameOrEmail) {
        this.email = nameOrEmail;
    }
    /** receives information about user's password
     *  @return  password */
    public String getPassword() {
        return password;
    }
    /**inserts information  about user's password
     * @param password user's password */
    public void setPassword(String password) {
        this.password = password;
    }
}
