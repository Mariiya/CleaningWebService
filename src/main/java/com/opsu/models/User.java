/**
 * Class vendor
 * @autor group 183
 * @version 2.1
 */
package com.opsu.models;

import com.opsu.models.enumeration.Role;

import javax.validation.constraints.*;
import java.math.BigInteger;

public class User {
    /** field id */
    @Min(value = 0, message = "User id is not be correct")
    private BigInteger id;
    /** field phone number*/
    @NotEmpty(message = "User name number can not be empty")
    @Size(min = 13, max = 13, message = "User number should contain 13 digits")
    private String phoneNumber;

    /** field email */
    @NotEmpty(message = "User email can not be empty")
    // @Pattern(regexp = "^(?:[a-zA-Z0-9_'^&/+-])+(?:\\.(?:[a-zA-Z0-9_'^&/+-])+)" + "*@(?:(?:\\[?(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))\\.)" +
    //       "{3}(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\]?)|(?:[a-zA-Z0-9-]+\\.)" + "+(?:[a-zA-Z]){2,}\\.?)$",
    //       message = "Email format is not correct")
    private String email;

    /** field password */
    @NotEmpty
    private String password;
    /** field role*/
    @NotNull
    private Role role;

    /** @return id user id */
    public BigInteger getId() {
        return id;
    }
    /** @param id  user */
    public void setId(BigInteger id) {
        this.id = id;
    }

    /** @return user's phoneNumber */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /** @param phoneNumber user */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /** @return user's email*/
    public String getEmail() {
        return email;
    }
    /** @param email user */
    public void setEmail(String email) {
        this.email = email;
    }

    /** @return user's password*/
    public String getPassword() {
        return password;
    }
    /** @param password user */
    public void setPassword(String password) {
        this.password = password;
    }

    /** @return user's role*/
    public Role getRole() {
        return role;
    }
    /** @param role  user */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * constructor - create new user
     *   @param id user
     *   @param phoneNumber user
     *   @param email user
     *   @param password user
     *   @param role  user
     */
    public User(BigInteger id, String phoneNumber, String email, String password, Role role) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
