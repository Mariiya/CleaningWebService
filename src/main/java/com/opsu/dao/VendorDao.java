package com.opsu.dao;


import com.opsu.exceptions.EmptyDataBaseException;
import com.opsu.models.Vendor;
import javassist.NotFoundException;

import java.math.BigInteger;
/**
 * This is abstract interface  for our data base mainly table Vendor
 * Here using SQL language we describe what operations/commands  can be applied for table Vendor
 * @author group 183
 * @version 2.1
 */
public interface VendorDao {

    /** search vendor by id
     * @param id vendor */
     Vendor getVendorById(BigInteger id) throws NotFoundException;
    /**creating new vendor
     * @param vendor vendor*/
     boolean save(Vendor vendor) throws EmptyDataBaseException;
    /** updating already existing vendor
     * @param vendor vendor*/
     boolean update(Vendor vendor) throws EmptyDataBaseException;

    /**Request for finding vendor by id*/
    String GET_VENDOR_BY_ID = "SELECT individual, firstName, lastName, vendor.userId,\n" +
            "     users.email, users.password, users.phoneNumber, users.role\n" +
            "            FROM VENDOR\n" +
            "            LEFT JOIN users ON vendor.userId = users.userId\n" +
            "            WHERE vendor.userId = ?\n";
    /**Request for creating new vendor */
    String CREATE_VENDOR = "INSERT INTO VENDOR  VALUES (?, ?, ?, ?)";
    /**Request for changing information about already existing vendor*/
    String UPDATE_VENDOR = "UPDATE VENDOR SET firstName = ?, lastName = ? where userid = ?";

}
