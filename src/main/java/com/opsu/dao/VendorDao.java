package com.opsu.dao;


import com.opsu.models.User;
import com.opsu.models.Vendor;
import javassist.NotFoundException;

import java.math.BigInteger;

public interface VendorDao {

    Vendor findVendorByLastName(String lastName) throws NotFoundException;

    Vendor getVendorById(BigInteger id) throws NotFoundException;

    void create(Vendor vendor);
    void save(Vendor vendor);


    String GET_VENDOR_BY_ID = "SELECT individual, firstName, lastName, vendor.userId,\n" +
            "     users.email, users.password, users.phoneNumber, users.role\n" +
            "            FROM VENDOR\n" +
            "            LEFT JOIN users ON vendor.userId = users.userId\n" +
            "            WHERE vendor.userId = ?\n";

    String GET_VENDOR_BY_LAST_NAME = "SELECT individual, firstName, lastName, vendor.userId,\n" +
            "     users.email, users.password, users.phoneNumber, users.role\n" +
            "            FROM VENDOR\n" +
            "            LEFT JOIN users ON vendor.userId = users.userId\n" +
            "            WHERE lastName = ?\n ";

    //Используем merge вместо insert чтобы избежать дубликатов в базе и ошибок при добавленнии еще одного пользвоателя
// безопасно и надежно
    String CREATE_VENDOR = "MERGE INTO VENDOR old\n" +
            "                   USING (select ?            lastName,\n" +
            "                                 ?            firstName,\n" +
            "                                 ?            individual,\n" +
            "                                 ?            userId\n" +
            "                          FROM DUAL) new\n" +
            "                   ON (old.userId = new.userId)\n" +
            "                   WHEN MATCHED THEN\n" +
            "                       UPDATE\n" +
            "                       SET old.lastName = new.lastName,\n" +
            "                           old.firstName= new.firstName,\n" +
            "                           old.individual     = new.individual\n" +
            "                       WHERE old.lastName <> new.lastName\n" +
            "                         OR  old.individual       <> new.individual\n" +
            "                        OR old.firstName <> new.firstName\n" +
            "                         OR  old.userId <> new.userId\n" +
            "                   WHEN NOT MATCHED THEN\n" +
            "                       INSERT (old.lastName, old.individual, old.firstName, old.userId)\n" +
            "                       VALUES (new.lastName, new.individual, new.firstName, new.userId)";

}
