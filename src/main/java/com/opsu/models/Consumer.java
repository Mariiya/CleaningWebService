package com.opsu.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigInteger;

public class Consumer {

    @Pattern(regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]')?[A-Z][a-z]*)))*$",message = "Vendor first name format is not correct")
    @Size(min=2,max=20,message = "Consumer first name should contain from 2 to 20 digits")
    @NotEmpty(message ="Consumer first name  can not be empty")
    private String firstName;

    @Pattern(regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]')?[A-Z][a-z]*)))*$",message = "Vendor first name format is not correct")
    @Size(min=2,max=20,message = "Consumer first name should contain from 2 to 20 digits")
    @NotEmpty(message ="Consumer last name  can not be empty")
    private String lastName;

    @Min(value=0,message ="Consumer id is not be correct")
    private BigInteger consumerId;

    private User user;

    public BigInteger getId() {
        return consumerId;
    }

    public void setId(BigInteger consumerId) {
        this.consumerId= consumerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Consumer(BigInteger consumerId, String firstName, String lastName, User user) {
        this.consumerId = consumerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
    }
}
