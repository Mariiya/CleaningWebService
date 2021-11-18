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
    public void addNewService(@Valid @RequestBody Service service) {
        try{
            servicesManager.addNewService(service);
        }
        catch (Exception e){
            log.error(e.getMessage());
        }
    }

    @PostMapping("/custom")
    public void addNewCustomService(@Valid @RequestBody Service service) {
        try{
            servicesManager.addNewCustomService(service);
        }
        catch (Exception e){
            log.error(e.getMessage());
        }
    }

    @PostMapping("/update")
    public void changeService(@Valid @RequestBody Service service) {
        try{
            servicesManager.updateService(service);
        }
        catch (Exception e){
            log.error(e.getMessage());
        }
    }

    @PostMapping("/delete/{id}")
    public void deleteService(@PathVariable BigInteger id) {
        try{
            servicesManager.deleteService(id);
        }
        catch (Exception e){
            log.error(e.getMessage());
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
