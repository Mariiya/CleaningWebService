package com.opsu.dao;


import com.opsu.models.User;
import com.opsu.models.Vendor;
import javassist.NotFoundException;

import java.math.BigInteger;

public interface VendorDao {

    Vendor findVendorByLastName(String lastName) throws NotFoundException;

    Vendor getVendorById(BigInteger id) throws NotFoundException;

    void create(Vendor vendor);



    String GET_VENDOR_BY_ID = "SELECT vendorId, individual, firstName, lastName, vendor.userId," +
            "users.userId, users.email, users.password, users.phoneNumber, users.role" +
            "FROM VENDOR" +
            "LEFT JOIN users ON vendor.userId = users.userId" +
            "WHERE vendorId = ?";

    String GET_VENDOR_BY_LAST_NAME = "SELECT vendorId, individual, firstName, lastName, vendor.userId,\n" +
            "users.userId, users.email, users.password, users.phoneNumber, users.role" +
            "FROM VENDOR" +
            "LEFT JOIN users ON vendor.userId = users.userId" +
            "WHERE lastName = ? ";

    //Используем merge вместо insert чтобы избежать дубликатов в базе и ошибок при добавленнии еще одного пользвоателя
// безопасно и надежно
    String CREATE_VENDOR = "MERGE INTO VENDOR old\n" +
            "                USING (SELECT  seq_next()  vendorId,\n" +
            "                              ?            lastName,\n" +
            "                              ?            firstName,\n" +
            "                              ?            individual\n" +
            "                              ?            userId\n" +
            "                       FROM DUAL) new\n" +
            "                ON (old.vendorId = new.vendorId)\n" +
            "                WHEN MATCHED THEN\n" +
            "                    UPDATE\n" +
            "                    SET old.lastName = new.lastName,\n" +
            "                        old.firstName= new.firstName,\n" +
            "                        old.individual     = new.individual,\n" +
            "                        old.userId = new.userId" +
            "                    WHERE old.lastName <> new.lastName\n" +
            "                      OR  old.individual       <> new.individual\n" +
            "                       OR old.firstName <> new.firstName \n" +
            "                      OR  old.vendorId    <> new.vendorId\n" +
            "                      OR  old.userId <> new.userId\n" +
            "                WHEN NOT MATCHED THEN\n" +
            "                    INSERT (old.vendorId, old.lastName, old.individual, old.firstName, old.userId)\n" +
            "                    VALUES (SEQ_CURR(), new.lastName, new.individual, new.firstName, new.userId)";

}