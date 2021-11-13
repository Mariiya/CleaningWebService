package com.opsu.controllers;


import javassist.NotFoundException;
import com.opsu.models.Vendor;
import com.opsu.services.VendorService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.opsu.secutity.services.UserDetailsImpl;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
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
                return vendorService.getVendorById(id);
            } catch (Exception e){
                log.error(e.getMessage());
                return null;

            }
    }

    @GetMapping("/lastname")
    public Vendor findVendorByLastName( @RequestParam String lastname) {
        try {
            return vendorService.findVendorByLastName(lastname);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }


    @PostMapping("/update")
    public void updateVendor(@AuthenticationPrincipal UserDetailsImpl updater, @Valid
    @RequestBody Vendor vendor) throws NotFoundException {
        vendorService.update(updater,vendor);

    }
}
