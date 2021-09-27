package com.opsu.controllers;

import com.opsu.models.Consumer;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping(value = "/consumer/")
public class ConsumerController {

    @PostMapping("/create")
    public void createConsumer(@Valid @RequestBody Consumer consumer) {

    }

    @PostMapping("/update")
    public void updateConsumer(@Valid @RequestBody Consumer consumer) {

    }
}
