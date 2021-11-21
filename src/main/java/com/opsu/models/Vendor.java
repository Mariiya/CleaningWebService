/**
 * Class vendor
 * @autor group 183
 * @version 2.1
 */
package com.opsu.models;
import com.opsu.models.enumeration.Role;
import javax.validation.constraints.*;
import java.math.BigInteger;


public class Vendor extends User{
    /** field firstName */
    @Size(min=2,max=20,message = "Vendor first name should contain from 2 to 20 digits")
    @NotEmpty(message ="Vendor first name  can not be empty")
    private  String firstName;

    /** field lastName */
    @Size(min=2,max=20,message = "Vendor last name should contain from 2 to 20 digits")
    @NotEmpty(message ="Vendor last name  can not be empty")
    private  String lastName ;

    /** field individual */
    @NotNull(message ="Vendor individual should not be empty")
    private  Boolean individual;

    /** @return firstName vendor name */
    public String  getFirstName() {
        return firstName;
    }
    /** @param firstName vendor  name */
    public void setFirstName(String firstName) {
        this.firstName= firstName;
    }

    /** @return lastName vendor surname */
    public String  getLastName() {
        return lastName;
    }
    /** @param lastName vendor  surname */
    public void setLastName(String lastName) {
        this.lastName= lastName;
    }

    /** @return individual vendor  */
    public Boolean getIndividual() {
        return individual;
    }
    /** @param individual  vendor */
    public void setIndividual(boolean individual) {
        this.individual= individual;
    }

    /**
     * constructor- create vendor
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
