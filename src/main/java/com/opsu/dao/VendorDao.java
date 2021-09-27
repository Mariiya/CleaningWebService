package com.opsu.dao;


import com.opsu.models.Vendor;

public interface VendorDao {

    Vendor findByUsernameOrEmail(String lastName);

    void save(Vendor vendor);

    Boolean isIndividual(String individual);

    String GET_VENDOR_BY_LAST_NAME = "";
    String IS_VENDOR_INDIVIDUAL = "";
    String SAVE_NEW_Vendor = "INSERT INTO VENDOR ( firstName, lastName, individual) VALUES (?, ?, ?)";
}
