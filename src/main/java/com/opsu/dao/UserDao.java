package com.opsu.dao;

import com.opsu.exceptions.EmptyDataBaseException;
import com.opsu.models.User;
import javassist.NotFoundException;

import java.math.BigInteger;

public interface UserDao {

    User findByPhoneNumberOrEmail(String numberOrEmail) throws NotFoundException;

    boolean save(User user) throws EmptyDataBaseException;

    Boolean existsByPhoneNumber(String number);

    Boolean existsByEmail(String email);

    User getUserById(BigInteger id) throws NotFoundException;

    String GET_USER_BY_PHONE_NUMBER_OR_EMAIL = "SELECT userId, PHONENUMBER, email, password, role\n" +
            "             FROM USERS WHERE email = ? OR PHONENUMBER = ?";

    String GET_USER_BY_ID = "SELECT userId, PHONENUMBER, email, password, role\n" +
            "             FROM USERS WHERE userId = ?";

    String IS_USER_BY_PHONE_NUMBER = "select count(userId)\n" +
            "    from  USERS\n" +
            "    where phoneNumber = ?";

    String IS_USER_BY_EMAIL = "select count(userId)\n" +
            "    from  USERS\n" +
            "    where email = ?";

    //Используем merge вместо insert чтобы избежать дубликатов в базе и ошибок при добавленнии еще одного пользвоателя
// безопасно и надежно
    String CREATE_USER = "MERGE INTO USERS old\n" +
            "                USING (SELECT  seq_next()  USERID,\n" +
            "                              ?            phoneNumber,\n" +
            "                              ?            EMAIL,\n" +
            "                              ?            PASSWORD,\n" +
            "                              ?            ROLE\n" +
            "                       FROM DUAL) new\n" +
            "                ON (old.USERID = new.USERID\n" +
            "                    OR old.EMAIL = new.EMAIL)\n" +
            "                WHEN MATCHED THEN\n" +
            "                    UPDATE\n" +
            "                    SET old.phoneNumber = new.phoneNumber,\n" +
            "                        old.PASSWORD = new.PASSWORD,\n" +
            "                        old.ROLE     = new.ROLE\n" +
            "                    WHERE old.phoneNumber <> new.phoneNumber\n" +
            "                      OR  old.PASSWORD    <> new.PASSWORD\n" +
            "                      OR  old.ROLE        <> new.ROLE\n" +
            "                      OR  old.EMAIL       <> new.EMAIL\n" +
            "                      OR  old.USERID     <> new.USERID\n" +
            "                WHEN NOT MATCHED THEN\n" +
            "                    INSERT (old.USERID, old.phoneNumber, old.EMAIL, old.PASSWORD,old.ROLE)\n" +
            "                    VALUES (SEQ_CURR(), new.phoneNumber, new.EMAIL, new.PASSWORD, new.ROLE)";
}
