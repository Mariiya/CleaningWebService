package com.opsu.controllers;

import com.opsu.models.Consumer;
import com.opsu.models.Vendor;
import com.opsu.services.VendorService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.math.BigInteger;
@RestController
@Validated
@RequestMapping(value = "/vendor/")
public class VendorController {
    private static final Logger log = Logger.getLogger(VendorController.class.getName());
    private final VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {

        this.vendorService = vendorService;
    }

    @GetMapping("{id}")
        public Vendor getVendorById(@PathVariable BigInteger id) {
            try {
                return vendorService.getVendorById (id);
            } catch (Exception e){
                log.error(e.getMessage());
                return null;

            }
    }

    @PostMapping("/update")
    public void updateVendor(Vendor vendor) {

    }
}
