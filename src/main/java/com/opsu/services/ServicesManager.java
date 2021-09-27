package com.opsu.services;

import com.opsu.dao.ServiceDao;
import org.springframework.beans.factory.annotation.Autowired;

public class ServicesManager {private final ServiceDao serviceDao;

    @Autowired
    public ServicesManager (ServiceDao serviceDao) {
        this.serviceDao=serviceDao;
    }


    public void create(ServiceDao serviceDao) {

    }
    public void change(ServiceDao serviceDao) {

    }
    public void delete(ServiceDao serviceDao) {

    }

    public void addNew(ServiceDao serviceDao) {

    }
}
