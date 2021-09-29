package com.opsu.dao;

import com.opsu.models.ServiceCollection;

public interface ServiceCollectionDao {

    ServiceCollection getServiceCollection();

    void createServiceCollection();

    void updateServiceCollection();

    void deleteServiceCollection();

    String GET_SERVICECOLLECTION_BY_ID = "";

    String CREATE_SERVICECOLLECTION = "";

    String UPDATE_SERVICECOLLECTION = "";

    String DELETE_SERVICECOLLECTION = "";
}
