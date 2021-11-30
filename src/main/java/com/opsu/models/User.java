
package com.opsu.models;
import com.opsu.models.enumeration.Role;
import javax.validation.constraints.*;
import java.math.BigInteger;
/**
 * In this class we store information  about user
 * @author group 183
 * @version 2.1
 */

public class User {
    /** field id
     * field's restriction
     * has limitation in value
     */
    @Min(value = 0, message = "User id is not be correct")
    private BigInteger id;
    /** field phone number
     * can't be empty
     * has limit in size
     */
    @NotEmpty(message = "User name number can not be empty")
    @Size(min = 13, max = 13, message = "User number should contain 13 digits")
    private String phoneNumber;

    /** field email
     * field's restriction
     * can't be empty
     */
    @NotEmpty(message = "User email can not be empty")
    private String email;

    /** field password
     * field's restriction
     * can't be empty
     */
    @NotEmpty
    private String password;

    /** field role
     * field's restriction
     * role has to be chosen */
    @NotNull
    private Role role;

    /** receives information about user's id
     * @return id user id */
    public BigInteger getId() {
        return id;
    }
    /** inserts information  about user's id
     * @param id  user */
    public void setId(BigInteger id) {
        this.id = id;
    }

    /** receives information about user's phone number
     * @return user's phoneNumber */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /** inserts information  about user's phone number
     * @param phoneNumber user */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /** receives information about user's email
     * @return user's email*/
    public String getEmail() {
        return email;
    }
    /** inserts information  about user's email
     * @param email user */
    public void setEmail(String email) {
        this.email = email;
    }

    /** receives information about user's password
     * @return user's password*/
    public String getPassword() {
        return password;
    }
    /** inserts information  about user's password
     * @param password user */
    public void setPassword(String password) {
        this.password = password;
    }

    /** receives information about user's role
     * @return user's role*/
    public Role getRole() {
        return role;
    }
    /** inserts information  about user's role
     * @param role  user */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * This constructor creates new User
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
