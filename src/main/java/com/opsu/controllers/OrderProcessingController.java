package com.opsu.controllers;

import com.opsu.models.Consumer;
import com.opsu.models.Order;
import com.opsu.models.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.text.resources.CollationData;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;

@RestController
@Validated
@RequestMapping(value = "/order/")
public class OrderProcessingController {

    @PostMapping("/cancel/{id}")
    public void cancelOrder(BigInteger orderId, @PathVariable String id) {}

    @GetMapping("/orders")
    public Collection<Order> getOrders() {
        return Collections.EMPTY_LIST;
    }

    @GetMapping("/by-service")
    public Collection<Order> getOrders(@Valid @RequestBody Service service) {
        return Collections.EMPTY_LIST;
    }

    @GetMapping("/by-price")
    public Collection<Order> getOrders(@NotNull Double price) {
        return Collections.EMPTY_LIST;
    }

    @GetMapping("/by-name")
    public Collection<Order> getOrders(@NotNull @NotEmpty String name) {
        return Collections.EMPTY_LIST;
    }

    @PostMapping("/reject/{id}")
    public void rejectOrder(@PathVariable BigInteger id) {    }

    @PostMapping("/create")
    public void createOrder(@Valid @RequestBody Order order) {    }

    @PostMapping("/create-service")
    public void addSpecialService(@Valid @RequestBody Service service, @Valid @RequestBody Order order) {    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable BigInteger id) {return null; }

    @PostMapping("/create-service")
    public void assignOrder(@Valid @RequestBody Order order, @Valid @RequestBody Consumer consumer) {    }

    @PostMapping("/complete/{id}")
    public void completeOrder(@PathVariable BigInteger id) {    }

    @PostMapping("/change-price")
    public void changePrice(@Valid @RequestBody Order order) {    }
}