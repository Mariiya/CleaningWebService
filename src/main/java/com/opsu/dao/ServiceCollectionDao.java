package com.opsu.dao;

import com.opsu.models.Order;
import com.opsu.models.ServiceCollection;
import javassist.NotFoundException;

import java.math.BigInteger;
import java.util.Collection;

public interface ServiceCollectionDao {

    ServiceCollection getServiceCollectionById(BigInteger id) throws NotFoundException;

    Collection<ServiceCollection> getServiceCollections() throws NotFoundException;

    Collection<ServiceCollection> getServiceCollectionsByOrder(Order order) throws NotFoundException;

    void createServiceCollection(ServiceCollection serviceCollection);

    void updateServiceCollection(ServiceCollection serviceCollection);

    boolean deleteServiceCollection(ServiceCollection serviceCollection);

    String GET_SERVICECOLLECTION_BY_ID = "SELECT serviceCollectionId, orderId, serviceId FROM SERVICECOLLECTION WHERE serviceCollectionId = ?";

    String GET_SERVICECOLLECTIONS = "SELECT serviceCollectionId, orderId, serviceId FROM SERVICECOLLECTION";

    String GET_SERVICECOLLECTIONS_BY_ORDER = "SELECT serviceCollectionId, orderId, serviceId FROM SERVICECOLLECTION WHERE orderId = ?";

    String CREATE_SERVICECOLLECTION = "INSERT INTO SERVICECOLLECTION (serviceCollectionId, orderId, serviceId) \n" +
            "                          VALUES (SEQ.nextval, ?, ?)";

    String UPDATE_SERVICECOLLECTION = "UPDATE SERVICECOLLECTION SET\n" +
            "                orderId = ?, \n" +
            "                serviceId = ? \n" +
            "            WHERE serviceCollectionId = ?";

    String DELETE_SERVICECOLLECTION = "DELETE FROM SERVICECOLLECTION WHERE serviceCollectionId = ?";
}
