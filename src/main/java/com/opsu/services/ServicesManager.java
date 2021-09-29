package com.opsu.services;

import com.opsu.dao.ServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicesManager {private final ServiceDao serviceDao;

    @Autowired
    public ServicesManager (ServiceDao serviceDao) {
        this.serviceDao=serviceDao;
    }


    public void create(Service service) {

    }
    public void change(Service service) {

    }
    public void delete(Service service) {

    }

    public void addNew(Service service) {

    }

    public void getAll(Service service) {

    }
}
