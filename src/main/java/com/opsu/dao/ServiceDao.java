package com.opsu.dao;

import com.opsu.models.Service;
import javassist.NotFoundException;

import java.math.BigInteger;
import java.util.Collection;

public interface ServiceDao {

    void addNewService(Service service);

    void addNewCustomService(Service service);

    void updateService(Service service);

    boolean deleteService(BigInteger id);

    Service getService(BigInteger id) throws NotFoundException;

    Collection<Service> getServices() throws NotFoundException;

    String ADD_NEW_SERVICE = "MERGE INTO SERVICE old\n" +
            "                USING (SELECT  seq_next()  SERVICEID,\n" +
            "                              ?            name,\n" +
            "                              ?            description,\n" +
            "                              ?            custom      \n" +
            "                       FROM DUAL) new\n" +
            "                ON (old.SERVICEID = new.SERVICEID)\n" +
            "                WHEN MATCHED THEN\n" +
            "                    UPDATE\n" +
            "                    SET old.name = new.name,\n" +
            "                        old.description = new.description,\n" +
            "                        old.custom = new.custom\n" +
            "                    WHERE old.name <> new.name\n" +
            "                      OR  old.description <> new.description\n" +
            "                      OR  old.custom <> new.custom" +
            "                WHEN NOT MATCHED THEN\n" +
            "                    INSERT (old.SERVICEID, old.name, old.description, old.custom)\n" +
            "                    VALUES (SEQ_CURR(), new.name, new.description, new.custom)";

    String UPDATE_SERVICE = "UPDATE service SET\n" +
            "                name = ?, \n" +
            "                description = ? \n" +
            "            WHERE serviceId = ?";

    String GET_SERVICE_BY_ID = "SELECT serviceId, name, description FROM service WHERE serviceId = ?";

    String DELETE_SERVICE = "DELETE FROM SERVICE WHERE serviceID = ?";

    String GET_SERVICES = "SELECT serviceId, name, description FROM service WHERE custom = 0";

    String ADD_NEW_CUSTOM_SERVICE = "MERGE INTO SERVICE old\n" +
            "                USING (SELECT  seq_next()  SERVICEID,\n" +
            "                              ?            name,\n" +
            "                              ?            description,\n" +
            "                              ?            custom      \n" +
            "                       FROM DUAL) new\n" +
            "                ON (old.SERVICEID = new.SERVICEID)\n" +
            "                WHEN MATCHED THEN\n" +
            "                    UPDATE\n" +
            "                    SET old.name = new.name,\n" +
            "                        old.description = new.description,\n" +
            "                        old.custom = new.custom\n" +
            "                    WHERE old.name <> new.name\n" +
            "                      OR  old.description    <> new.description\n" +
            "                      OR old.custom <> new.custom" +
            "                WHEN NOT MATCHED THEN\n" +
            "                    INSERT (old.SERVICEID, old.name, old.description, old.custom)\n" +
            "                    VALUES (SEQ_CURR(), new.name, new.description, new.custom)";
}
