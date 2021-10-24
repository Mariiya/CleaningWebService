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

    String CREATE_SERVICECOLLECTION = "MERGE INTO SERVICECOLLECTION old \n" +
            "                            USING (SELECT  seq_next()  serviceCollectionId, \n" +
            "                                          ?            orderId, \n" +
            "                                          ?            serviceId \n" +
            "                                   FROM DUAL) new \n" +
            "                            ON (old.serviceCollectionId = new.serviceCollectionId) \n" +
            "                            WHEN MATCHED THEN \n" +
            "                                UPDATE \n" +
            "                                SET old.orderId = new.orderId, \n" +
            "                                    old.serviceId = new.serviceId \n" +
            "                                WHERE old.orderId <> new.orderId \n" +
            "                                  OR  old.serviceId    <> new.serviceId \n" +
            "                            WHEN NOT MATCHED THEN \n" +
            "                                INSERT (old.serviceCollectionId, old.orderId, old.serviceId) \n" +
            "                                VALUES (SEQ_CURR(), new.orderId, new.serviceId)";

    String UPDATE_SERVICECOLLECTION = "UPDATE SERVICECOLLECTION SET\n" +
            "                orderId = ?, \n" +
            "                serviceId = ? \n" +
            "            WHERE serviceCollectionId = ?";

    String DELETE_SERVICECOLLECTION = "DELETE FROM SERVICECOLLECTION WHERE serviceCollectionId = ?";
}
