package com.opsu.models;


import java.math.BigInteger;

public class Vendor {
    private BigInteger vendorId;
    private  String firstName ;
    private  String lastName ;
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
