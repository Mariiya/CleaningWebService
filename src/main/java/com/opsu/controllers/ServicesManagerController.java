package com.opsu.controllers;

import com.opsu.models.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;

@RestController
@Validated
@RequestMapping(value = "/service/")
public class ServicesManagerController {

    @PostMapping("/create")
    public void addNewService(@Valid @RequestBody Service service) {
    }

    @PostMapping("/update")
    public void changeService(@Valid @RequestBody Service service) {
    }

    @PostMapping("/delete/{id}")
    public void deleteService(@PathVariable String id) {
    }

    @GetMapping()
    public Collection<Service> getAllServices() {
        return Collections.EMPTY_LIST;
    }

    @GetMapping("/{id}")
    public Service getService(@PathVariable String id) {
        return null;
    }
}
