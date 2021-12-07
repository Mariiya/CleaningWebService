package com.opsu.dao;

import com.opsu.models.Order;
import com.opsu.models.ServiceCollection;
import javassist.NotFoundException;

import java.math.BigInteger;
import java.util.Collection;
/**
 * This is abstract interface  for our data base mainly table Service Collection
 * Here using SQL language we describe what operations/commands  can be applied for table Service Collection
 * @author group 183
 * @version 2.1
 */
public interface ServiceCollectionDao {

    /** search service collection by id
     * @param id service collection */
    ServiceCollection getServiceCollectionById(BigInteger id) throws NotFoundException;
    /** search all  service collection */
    Collection<ServiceCollection> getServiceCollections() throws NotFoundException;
    /** search service collection by order
     * @param order service collection */
    Collection<ServiceCollection> getServiceCollectionsByOrder(Order order) throws NotFoundException;
    /** create new service collection
     * @param serviceCollection service collection */
    void createServiceCollection(ServiceCollection serviceCollection);
    /** updating already existing service collection
     * @param serviceCollection service collection */
    void updateServiceCollection(ServiceCollection serviceCollection);
    /** deleting already existing service collection
     * @param serviceCollection service collection */
    boolean deleteServiceCollection(ServiceCollection serviceCollection);

    /**Request for finding service collection by id*/
    String GET_SERVICECOLLECTION_BY_ID = "SELECT serviceCollectionId, orderId, serviceId FROM SERVICECOLLECTION WHERE serviceCollectionId = ?";
    /**Request for finding all service collection*/
    String GET_SERVICECOLLECTIONS = "SELECT serviceCollectionId, orderId, serviceId FROM SERVICECOLLECTION";
    /**Request for finding service collection by order*/
    String GET_SERVICECOLLECTIONS_BY_ORDER = "SELECT serviceCollectionId, orderId, serviceId FROM SERVICECOLLECTION WHERE orderId = ?";
    /**Request for creating new service collection */
    String CREATE_SERVICECOLLECTION = "INSERT INTO SERVICECOLLECTION (serviceCollectionId, orderId, serviceId) \n" +
            "                          VALUES (DEFAULT, ?, ?)";

    /**Request for changing information about already existing service collection */
    String UPDATE_SERVICECOLLECTION = "UPDATE SERVICECOLLECTION SET\n" +
            "                orderId = ?, \n" +
            "                serviceId = ? \n" +
            "            WHERE serviceCollectionId = ?";
    /**Request for deleting information about already existing service collection */
    String DELETE_SERVICECOLLECTION = "DELETE FROM SERVICECOLLECTION WHERE serviceCollectionId = ?";
}
