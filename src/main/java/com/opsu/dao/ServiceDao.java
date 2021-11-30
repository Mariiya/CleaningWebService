package com.opsu.dao;

import com.opsu.models.Service;
import javassist.NotFoundException;

import java.math.BigInteger;
import java.util.Collection;
/**
 * This is abstract interface  for our data base mainly table Service
 * Here using SQL language we describe what operations/commands  can be applied for table Service
 * @author group 183
 * @version 2.1
 */
public interface ServiceDao {

    /** add  service
     * @param service service*/
    void addNewService(Service service);
    /** add new special service
     * @param service service*/
    void addNewCustomService(Service service);
    /**updating already existing service
     * @param service  service */
    void updateService(Service service);
    /**delete service
     * @param id  service */
    boolean deleteService(BigInteger id);
    /** search service by id
     * @param id service */
    Service getService(BigInteger id) throws NotFoundException;
    /** show all services*/
    Collection<Service> getServices() throws NotFoundException;
    /** finding service by special service
     * @param service service*/
    Service getCustomService(Service service) throws NotFoundException;

    /**Request for adding information about new service */
    String ADD_NEW_SERVICE = "INSERT INTO SERVICE (SERVICEID, name, description, isCustom)\n" +
            "                 VALUES (DEFAULT, ?, ?, false)";

    /**Request for changing information about already existing service */
    String UPDATE_SERVICE = "UPDATE service SET\n" +
            "                name = ?, \n" +
            "                description = ? \n" +
            "            WHERE serviceId = ?";
    /**Request for finding service by id */
    String GET_SERVICE_BY_ID = "SELECT serviceId, name, description, isCustom FROM service WHERE serviceId = ?";
    /**Request for deleting information about already existing service */
    String DELETE_SERVICE = "DELETE FROM SERVICE WHERE serviceID = ?";
    /**Request for showing all services */
    String GET_SERVICES = "SELECT serviceId, name, description, isCustom FROM service WHERE isCustom = false";
    /**Request for  adding information about new custom service  */
    String ADD_NEW_CUSTOM_SERVICE = "INSERT INTO SERVICE (SERVICEID, name, description, isCustom)\n" +
                                    "VALUES (DEFAULT, ?, ?, true)";
    /**Request for getting information about custom service  with specific id */
    String GET_CUSTOM_SERVICE_WITH_ID = "SELECT serviceId, name, description, isCustom\n" +
            "      FROM (Select *, ROW_NUMBER() OVER (ORDER BY serviceid) r  from service \n" +
            "          WHERE name = ?   AND description = ? AND isCustom = true) t\n" +
            "      where t.r<=1 order by serviceId DESC ";
}
