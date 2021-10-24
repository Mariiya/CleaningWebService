package com.opsu.models;


import com.opsu.models.enumeration.Role;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigInteger;

public class Vendor extends User{

    @Pattern(regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]+\\')?[A-Z][a-z]*)))*$",message = "Vendor first name format is not correct")
    @Size(min=2,max=20,message = "Vendor first name should contain from 2 to 20 digits")
    @NotEmpty(message ="Vendor first name  can not be empty")
    private  String firstName ;

    @Pattern(regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]+\\')?[A-Z][a-z]*)))*$",message = "Vendor first name format is not correct")
    @Size(min=2,max=20,message = "Vendor last name should contain from 2 to 20 digits")
    @NotEmpty(message ="Vendor last name  can not be empty")
    private  String lastName ;

    @NotEmpty(message ="Vendor individual should not be empty")
    private  Boolean individual;

    public String  getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName= firstName;
    }

    public String  getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName= lastName;
    }

    public Boolean getIndividual() {
        return individual;
    }
    public void setIndividual(boolean individual) {
        this.individual= individual;
    }

    public Vendor (BigInteger id, String phoneNumber, String email, String password, Role role, String firstName , String lastName , Boolean individual) {
      super(id, phoneNumber, email, password, role);
        this.firstName = firstName;
        this.lastName = lastName;
        this.individual = individual;
    }
}
