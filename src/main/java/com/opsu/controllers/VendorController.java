package com.opsu.controllers;

import com.opsu.models.Vendor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping(value = "/vendor/")
public class VendorController {

    @GetMapping("{id}")
    public void getVendor(@PathVariable String id) {

    }

    @PostMapping("/update")
    public void updateVendor(Vendor vendor) {

    }
}
