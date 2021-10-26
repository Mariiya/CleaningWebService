package com.opsu.controllers;

import com.opsu.models.Consumer;

import com.opsu.secutity.services.UserDetailsImpl;
import com.opsu.services.AuthorizationService;
import com.opsu.services.ConsumerService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.constraints.NotEmpty;

import java.math.BigInteger;


@RestController
@Validated
@RequestMapping(value = "/consumer/")
public class ConsumerController {
    private static final Logger log = Logger.getLogger(ConsumerController.class.getName());
    private final ConsumerService consumerService;
    private final AuthorizationService authorizationService;
    @Autowired
    public ConsumerController(ConsumerService consumerService,AuthorizationService authorizationService1) {
        this.authorizationService = authorizationService1;
        this.consumerService = consumerService;
    }

    @GetMapping("{id}")
    public Consumer getConsumerById(@PathVariable BigInteger id) {
        try {
            return consumerService.getConsumerById (id);
        } catch (Exception e){
            log.error(e.getMessage());
            return null;

        }

    }

    @GetMapping("/by-lastname")
    public Consumer findConsumerByLastName( @NotEmpty String lastname) {
        try {
            return consumerService.findConsumerByLastName(lastname);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    //@PostMapping("/update")
   // public void update(@AuthenticationPrincipal UserDetailsImpl updater, @Valid @RequestBody Consumer consumer)throws NotFoundException {
     //   consumerService.update(updater,consumer);

    //}
}
