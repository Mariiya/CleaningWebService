package com.opsu.services;

import com.opsu.controllers.ServicesManagerController;
import com.opsu.dao.ServiceDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.opsu.models.Service;

import java.math.BigInteger;
import java.util.Collection;

@org.springframework.stereotype.Service
public class ServicesManager {
    private static final Logger log = Logger.getLogger(ServicesManager.class.getName());
    private final ServiceDao serviceDao;

    @Autowired
    public ServicesManager (ServiceDao serviceDao) {
        this.serviceDao=serviceDao;
    }


    public void addNewService(Service service) throws Exception {
        try{
            serviceDao.addNewService(service);
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new Exception("Error creating Service");
        }
    }
    public void addNewCustomService(Service service) throws Exception {
        try{
            serviceDao.addNewCustomService(service);
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new Exception("Error creating Service");
        }
    }
    public void updateService(Service service) throws Exception {
        try{
            serviceDao.updateService(service);
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new Exception("Error creating Service");
        }
    }
    public void deleteService(BigInteger id) throws Exception {
        try{
            serviceDao.deleteService(id);
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new Exception("Error creating Service");
        }
    }

    public Service getService(BigInteger id) throws Exception {
        try{
            return serviceDao.getService(id);
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new Exception("Error creating Service");
        }
    }

    public Collection<Service> getAll() throws Exception {
        try{
            return serviceDao.getServices();
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new Exception("Error creating Service");
        }
    }
}
