package com.opsu.services;

import com.opsu.controllers.ServicesManagerController;
import com.opsu.dao.ServiceDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.opsu.models.Service;

import java.math.BigInteger;
import java.util.Collection;
/**
 * Class with business logic of service creation, update and search
 */
@org.springframework.stereotype.Service
public class ServicesManager {
    private static final Logger log = Logger.getLogger(ServicesManager.class.getName());
    private final ServiceDao serviceDao;

    @Autowired
    public ServicesManager (ServiceDao serviceDao) {
        this.serviceDao=serviceDao;
    }

    /**
     * create new servise
     * @param service
     * @throws Exception
     */
    public void addNewService(Service service) throws Exception {
        try{
            serviceDao.addNewService(service);
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new Exception("Error creating Service");
        }
    }

    /**
     * add some extra service
     * @param service
     * @return
     * @throws Exception
     */
    public Service addNewCustomService(Service service) throws Exception {
        try{
            serviceDao.addNewCustomService(service);
            return serviceDao.getCustomService(service);
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new Exception("Error creating Service");
        }
    }

    /**
     * update already existing service
     * @param service
     * @throws Exception
     */
    public void updateService(Service service) throws Exception {
        try{
            serviceDao.updateService(service);
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new Exception("Error creating Service");
        }
    }

    /**
     * delete service
     * @param id service
     * @throws Exception
     */
    public void deleteService(BigInteger id) throws Exception {
        try{
            serviceDao.deleteService(id);
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new Exception("Error creating Service");
        }
    }

    /**
     * search service by id
     * @param id service
     * @return
     * @throws Exception
     */
    public Service getService(BigInteger id) throws Exception {
        try{
            return serviceDao.getService(id);
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new Exception("Error creating Service");
        }
    }

    /**
     * see all services
     * @return
     * @throws Exception
     */
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
