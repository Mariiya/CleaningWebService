package com.opsu.controllers;

import com.opsu.dao.OrderDao;
import com.opsu.exceptions.EmptyDataBaseException;
import com.opsu.models.Consumer;
import com.opsu.models.Order;
import com.opsu.models.Service;
import com.opsu.services.OrderProcessingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    private static final Logger log = Logger.getLogger(OrderProcessingController.class.getName());
    private final OrderProcessingService processingService;
    private final OrderDao orderDao;

    @Autowired
    public OrderProcessingController(OrderProcessingService processingService, OrderDao orderDao) {
        this.orderDao = orderDao;
        this.processingService = processingService;
    }

    @PostMapping("/create")
    public void createOrder(@Valid @RequestBody Order order) {
        try {
            processingService.createOrder(order);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable BigInteger id) {
        try {
            return processingService.getOrder(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping("/orders")
    public Collection<Order> getOrders() {
        try {
            return processingService.getOrders();
        } catch (EmptyDataBaseException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping("/by-service")
    public Collection<Order> getOrders(@Valid @RequestBody Service service) {
        try {
            return processingService.getOrders(service);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping("/by-price")
    public Collection<Order> getOrders(@NotNull Float price) {
        try {
            return processingService.getOrders(price);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping("/by-name")
    public Collection<Order> getOrders(@NotNull @NotEmpty String name) {
        try {
            return processingService.getOrders(name);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @PostMapping("/reject/{id}")
    public void rejectOrder(@PathVariable BigInteger id) {
        try {
            processingService.rejectOrder(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @PostMapping("/cancel/{id}")
    public void cancelOrder(BigInteger orderId, @PathVariable String id) {
        try {
            processingService.cancelOrder(orderId, id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @PostMapping("/assignOrder}")
    public void assignOrder(@Valid @RequestBody Order order, @Valid @RequestBody Consumer consumer) {
        try {
            processingService.assignOrder(order, consumer);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @PostMapping("/inProgress/{id}")
    public void inProgressOrder(@PathVariable BigInteger id) {
        try {
            processingService.inProgressOrder(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @PostMapping("/suspend/{id}")
    public void suspendOrder(@PathVariable BigInteger id) {
        try {
            processingService.suspendOrder(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @PostMapping("/complete/{id}")
    public void completeOrder(@PathVariable BigInteger id) {
        try {
            processingService.completeOrder(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @PostMapping("/delete/{order}")
    public void deleteOrder(@PathVariable Order order) {
        try {
            processingService.deleteOrder(order);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @PostMapping("/change-price")
    public void changePrice(@Valid @RequestBody Order order) {
            processingService.changePrice(order);
    }

    @PostMapping("/create-service")
    public void addSpecialService(@Valid @RequestBody Service service, @Valid @RequestBody Order order) {
        processingService.addSpecialService(service, order);
    }
}
