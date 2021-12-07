
package com.opsu.models;
import com.opsu.models.enumeration.Role;
import javax.validation.constraints.*;
import java.math.BigInteger;
/**
 * In this class we store information  about vendor
 * @author group 183
 * @version 2.1
 */

public class Vendor extends User{
    /** field firstName
     * field's restriction
     * can't be empty
     * has limit in size
     */
    @Size(min=2,max=20,message = "Vendor first name should contain from 2 to 20 digits")
    @NotEmpty(message ="Vendor first name  can not be empty")
    private  String firstName;

    /** field lastName
     * field's restriction
     * can't be empty
     * has limit in size */
    @Size(min=2,max=20,message = "Vendor last name should contain from 2 to 20 digits")
    @NotEmpty(message ="Vendor last name  can not be empty")
    private  String lastName ;

    /** field individual
     * is this a company or is it a person
     */
    @NotNull(message ="Vendor individual should not be empty")
    private  Boolean individual;

    /** receives information about vendor name
     * @return firstName vendor name */
    public String  getFirstName() {
        return firstName;
    }
    /** inserts information  about vendor name
     * @param firstName vendor  name */
    public void setFirstName(String firstName) {
        this.firstName= firstName;
    }

    /** receives information about vendor surname
     * @return lastName vendor surname */
    public String  getLastName() {
        return lastName;
    }
    /** inserts information  about vendor surname
     * @param lastName vendor  surname */
    public void setLastName(String lastName) {
        this.lastName= lastName;
    }

    /** receives information about vendor
     * @return individual vendor  */
    public Boolean getIndividual() {
        return individual;
    }
    /** inserts information  about vendor
     * @param individual  vendor */
    public void setIndividual(boolean individual) {
        this.individual= individual;
    }

    /**
     * This constructor creates new vendor using information from this class and inherits information from class User
     * @param firstName vendor name
     * @param lastName vendor surname
     * @param individual  vendor
     */
    public Vendor (BigInteger id, String phoneNumber, String email, String password, Role role, String firstName , String lastName , Boolean individual) {
      super(id, phoneNumber, email, password, role);

        this.firstName = firstName;
        this.lastName = lastName;
        this.individual = individual;
    }
}
