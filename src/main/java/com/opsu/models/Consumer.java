package com.opsu.models;

import java.math.BigInteger;

public class Consumer {
    private String firstName;
    private String lastName;
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
