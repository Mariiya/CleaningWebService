package com.opsu.dao;


import com.opsu.exceptions.EmptyDataBaseException;
import com.opsu.models.Vendor;
import javassist.NotFoundException;

import java.math.BigInteger;

public interface VendorDao {

    Vendor getVendorById(BigInteger id) throws NotFoundException;
     boolean save(Vendor vendor) throws EmptyDataBaseException;

     boolean update(Vendor vendor) throws EmptyDataBaseException;

    String GET_VENDOR_BY_ID = "SELECT individual, firstName, lastName, vendor.userId,\n" +
            "     users.email, users.password, users.phoneNumber, users.role\n" +
            "            FROM VENDOR\n" +
            "            LEFT JOIN users ON vendor.userId = users.userId\n" +
            "            WHERE vendor.userId = ?\n";

    String CREATE_VENDOR = "INSERT INTO VENDOR  VALUES (?, ?, ?, ?)";
    String UPDATE_VENDOR = "UPDATE VENDOR SET firstName = ?, lastName = ? where userid = ?";

}
