package com.opsu.controllers;


import com.opsu.exceptions.EmptyDataBaseException;
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
import java.math.BigInteger;
/**
 * Class role
 * shows under which role user has chosen to sign in
 * @author group 183
 * @version 2.1
 */
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


    /** @param vendor vendor
    * @param updater method update */
    @PostMapping("/update")
    public Vendor updateVendor(@AuthenticationPrincipal UserDetailsImpl updater, @Valid
    @RequestBody Vendor vendor) throws NotFoundException, EmptyDataBaseException {
      return   vendorService.update(updater,vendor);
    }
}
