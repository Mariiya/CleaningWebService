package com.opsu.dao;


import com.opsu.models.User;
import com.opsu.models.Vendor;
import javassist.NotFoundException;

import java.math.BigInteger;

public interface VendorDao {

    Vendor findVendorByLastName(String lastName) throws NotFoundException;

    Vendor getVendorById(BigInteger id) throws NotFoundException;

    void create(Vendor vendor);

    void update(Vendor vendor);


    String GET_VENDOR_BY_ID = "SELECT vendorId, individual, firstName, lastName FROM VENDOR WHERE vendorId = ?";

    String GET_VENDOR_BY_LAST_NAME = "SELECT vendorId, individual, firstName, lastName \n" +
            "                        FROM VENDOR WHERE lastName = ? ";

    //Используем merge вместо insert чтобы избежать дубликатов в базе и ошибок при добавленнии еще одного пользвоателя
// безопасно и надежно
    String CREATE_VENDOR = "MERGE INTO VENDOR old\n" +
            "                USING (SELECT  seq_next()  vendorId,\n" +
            "                              ?            lastName,\n" +
            "                              ?            firstName,\n" +
            "                              ?            individual,\n" +
            "                       FROM DUAL) new\n" +
            "                ON (old.vendorId = new.vendorId\n" +
            "                WHEN MATCHED THEN\n" +
            "                    UPDATE\n" +
            "                    SET old.lastName = new.lastName,\n" +
            "                        old.firstName= new.firstName,\n" +
            "                        old.individual     = new.individual\n" +
            "                    WHERE old.lastName <> new.lastName\n" +
            "                      OR  old.individual       <> new.individual\n" +
            "                       OR old.firstName <> new.firstName \n" +
            "                      OR  old.vendorId    <> new.vendorId\n" +
            "                WHEN NOT MATCHED THEN\n" +
            "                    INSERT (old.vendorId, old.lastName, old.individual, old.firstName)\n" +
            "                    VALUES (SEQ_CURR(), new.lastName, new.individual, new.firstName)";

    String UPDATE_VENDOR = "UPDATE vendor SET\n" +
            "lastName = ? \n" +
            "firstName = ? \n" +
            "individual = ? \n" +
            "WHERE vendorId = ?";
}