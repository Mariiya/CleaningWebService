
package com.opsu.models;
import com.opsu.models.enumeration.Role;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigInteger;
/**
 * In this class we store information  about consumer
 * @author group 183
 * @version 2.1
 */
public class Consumer extends User {

    /** field firstName
     * field's restriction
     * field can't be empty
     * has limit in size
     * uses specific variables
     */

    @Pattern(regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]')?[A-Z][a-z]*)))*$", message = "Consumer first name format is not correct")
    @Size(min = 2, max = 20, message = "Consumer first name should contain from 2 to 20 digits")
    @NotEmpty(message = "Consumer first name  can not be empty")
    private String firstName;

    /** field lastName
     * field's restriction
     * field can't be empty
     * has limit in size
     * uses specific variables
     */
    @Pattern(regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]')?[A-Z][a-z]*)))*$", message = "Consumer last name format is not correct")
    @Size(min = 2, max = 20, message = "Consumer first name should contain from 2 to 20 digits")
    @NotEmpty(message = "Consumer last name  can not be empty")
    private String lastName;

    /** receives information about consumer name
     * @return firstName consumer name */
    public String getFirstName() {
        return firstName;
    }

    /** inserts information  about consumer name
     * @param firstName consumer name */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**receives information about consumer surname
     *  @return lastName consumer surname*/
    public String getLastName() {
        return lastName;
    }

    /** inserts information  about consumer surname
     * @param lastName consumer surname*/
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /** This constructor creates new consumer using information from this class and inherits information from class User
     * @param firstName consumer name
     * @param lastName consumer surname
     */
    public Consumer(BigInteger id, String phoneNumber, String email, String password, Role role, String firstName, String lastName) {
        super(id, phoneNumber, email, password, role);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
