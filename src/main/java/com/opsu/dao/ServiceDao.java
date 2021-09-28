package com.opsu.dao;

import com.opsu.models.Service;
import javassist.NotFoundException;

import java.math.BigInteger;
import java.util.List;

public interface ServiceDao {

    void addNewService(Service service);

    void changeService(Service service);

    boolean deleteService(Service service);

    Service getService(BigInteger id) throws NotFoundException;

    List<Service> getServices() throws NotFoundException;

    String ADD_NEW_SERVICE = "MERGE INTO SERVICE old\n" +
            "                USING (SELECT  seq_next()  SERVICEID,\n" +
            "                              ?            name,\n" +
            "                              ?            description,\n" +
            "                       FROM DUAL) new\n" +
            "                ON (old.SERVICEID = new.SERVICEID)\n" +
            "                WHEN MATCHED THEN\n" +
            "                    UPDATE\n" +
            "                    SET old.name = new.name,\n" +
            "                        old.description = new.description,\n" +
            "                    WHERE old.name <> new.name\n" +
            "                      OR  old.description    <> new.description\n" +
            "                WHEN NOT MATCHED THEN\n" +
            "                    INSERT (old.SERVICEID, old.name, old.description)\n" +
            "                    VALUES (SEQ_CURR(), new.name, new.description)";

    String CHANGE_SERVICE = "UPDATE service SET\\n\" +\n" +
            "                name = ? \n" +
            "                description = ? \n" +
            "            WHERE serviceId = ?";

    String GET_SERVICE_BY_ID = "SELECT name, description FROM service WHERE serviceId = ?";

    String DELETE_SERVICE = "DELETE FROM SERVICE WHERE serviceID = ?";

    String GET_SERVICES = "SELECT serviceId, name, description FROM service";

}
