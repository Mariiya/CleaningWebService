
package com.opsu.models;
import com.opsu.models.enumeration.Role;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigInteger;
/**
 * Class consumer
 * @author group 183
 * @version 2.1
 */
public class Consumer extends User {

    /** field firstName */

    @Pattern(regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]')?[A-Z][a-z]*)))*$", message = "Vendor first name format is not correct")
    @Size(min = 2, max = 20, message = "Consumer first name should contain from 2 to 20 digits")
    @NotEmpty(message = "Consumer first name  can not be empty")
    private String firstName;

    /** field lastName */
    @Pattern(regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]')?[A-Z][a-z]*)))*$", message = "Vendor first name format is not correct")
    @Size(min = 2, max = 20, message = "Consumer first name should contain from 2 to 20 digits")
    @NotEmpty(message = "Consumer last name  can not be empty")
    private String lastName;

    /** @return firstName consumer name */
    public String getFirstName() {
        return firstName;
    }
    /** @param firstName consumer name */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /** @return lastName consumer surname*/
    public String getLastName() {
        return lastName;
    }
    /** @param lastName consumer surname*/
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @param firstName consumer name
     * @param lastName consumer surname
     */
    public Consumer(BigInteger id, String phoneNumber, String email, String password, Role role, String firstName, String lastName) {
        super(id, phoneNumber, email, password, role);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
