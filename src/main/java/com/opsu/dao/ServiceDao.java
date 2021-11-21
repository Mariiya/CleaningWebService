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

    Service getCustomService(Service service) throws NotFoundException;

    String ADD_NEW_SERVICE = "INSERT INTO SERVICE (SERVICEID, name, description, isCustom)\n" +
            "                 VALUES (SEQ.nextval, ?, ?, 0)";

    String UPDATE_SERVICE = "UPDATE service SET\n" +
            "                name = ?, \n" +
            "                description = ? \n" +
            "            WHERE serviceId = ?";

    String GET_SERVICE_BY_ID = "SELECT serviceId, name, description, isCustom FROM service WHERE serviceId = ?";

    String DELETE_SERVICE = "DELETE FROM SERVICE WHERE serviceID = ?";

    String GET_SERVICES = "SELECT serviceId, name, description, isCustom FROM service WHERE isCustom = 0";

    String ADD_NEW_CUSTOM_SERVICE = "INSERT INTO SERVICE (SERVICEID, name, description, isCustom)\n" +
                                    "VALUES (SEQ.nextval, ?, ?, 1)";
    String GET_CUSTOM_SERVICE_WITH_ID = "SELECT \n" +
            "                                            serviceId, name, description, isCustom \n" +
            "                                        FROM service \n" +
            "                                        WHERE \n" +
            "                                            name = ?   AND description = ? AND isCustom = 1\n" +
            "       and rownum=1 order by serviceId DESC ";
}
