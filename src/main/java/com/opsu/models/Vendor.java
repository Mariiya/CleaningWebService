package com.opsu.models;


public class Vendor {

private  String firstName ;
private  String lastName ;
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
    public Vendor (String firstName , String lastName , Boolean individual ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.individual = individual;
    }
}
