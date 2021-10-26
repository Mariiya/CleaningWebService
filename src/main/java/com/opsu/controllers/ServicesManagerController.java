package com.opsu.controllers;

import com.opsu.models.Service;
import com.opsu.services.ServicesManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.Collection;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/service/")
public class ServicesManagerController {
    private static final Logger log = Logger.getLogger(ServicesManagerController.class.getName());
    private final ServicesManager servicesManager;

    @Autowired
    public ServicesManagerController(ServicesManager servicesManager){
        this.servicesManager = servicesManager;
    }

    @PostMapping("/create")
    public Boolean addNewService(@Valid @RequestBody Service service) {
        try{
            servicesManager.addNewService(service);
            return true;
        }
        catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    @PostMapping("/custom")
    public Service addNewCustomService(@Valid @RequestBody Service service) {
        try{
            return servicesManager.addNewCustomService(service);
        }
        catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    @PostMapping("/update")
    public Boolean changeService(@Valid @RequestBody Service service) {
        try{
            servicesManager.updateService(service);
            return true;
        }
        catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    @PostMapping("/delete/{id}")
    public Boolean deleteService(@PathVariable BigInteger id) {
        try{
            servicesManager.deleteService(id);
            return true;
        }
        catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    @GetMapping("/services")
    public Collection<Service> getAllServices() {
        try{
            return servicesManager.getAll();
        }
        catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping("/{id}")
    public Service getService(@PathVariable BigInteger id) {
        try{
            return servicesManager.getService(id);
        }
        catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }
}
