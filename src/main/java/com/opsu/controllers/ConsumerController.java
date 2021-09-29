package com.opsu.controllers;

import com.opsu.models.Consumer;
import com.opsu.services.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping(value = "/consumer/")
public class ConsumerController {
    private final ConsumerService consumerService;

    @Autowired
    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @GetMapping("{id}")
    public void getConsumer(@PathVariable String id) {

    }

    @PostMapping("/create")
    public void createConsumer(@Valid @RequestBody Consumer consumer) {

    }

    @PostMapping("/update")
    public void updateConsumer(@Valid @RequestBody Consumer consumer) {

    }
}
