package com.opsu.models;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigInteger;

public class Vendor {
    @Min(value=0,message ="Vendor id is not be correct")
    private BigInteger vendorId;

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

    private User user;

    public BigInteger getId() {
        return vendorId;
    }

    public void setId(BigInteger vendorId) {
        this.vendorId = vendorId;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vendor (BigInteger vendorId, String firstName , String lastName , Boolean individual , User user) {
        this.vendorId = vendorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.individual = individual;
        this.user = user;
    }
}
