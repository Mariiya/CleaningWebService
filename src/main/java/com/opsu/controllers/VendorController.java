package com.opsu.controllers;

import com.opsu.models.Vendor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping(value = "/vendor/")
public class VendorController {
    @PostMapping("/create")
    public void createVendor(Vendor vendor) {

    }

    @PostMapping("/update")
    public void updateVendor(Vendor vendor) {

    }
}
