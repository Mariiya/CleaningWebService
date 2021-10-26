package com.opsu.controllers;

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

@RestController
@Validated
@RequestMapping(value = "/order/")
public class OrderProcessingController {
    private static final Logger log = Logger.getLogger(OrderProcessingController.class.getName());
    private final OrderProcessingService processingService;

    @Autowired
    public OrderProcessingController(OrderProcessingService processingService) {
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
    public Collection<Order> getOrders() throws Exception {
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

    @PostMapping("/reject/{order}")
    public void rejectOrder(@PathVariable Order order) {
        try {
            processingService.rejectOrder(order);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @PostMapping("/cancel/{order}")
    public void cancelOrder(@PathVariable Order order) {
        try {
            processingService.cancelOrder(order);
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

    @PostMapping("/inProgress/{order}")
    public void inProgressOrder(@PathVariable Order order) {
        try {
            processingService.inProgressOrder(order);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @PostMapping("/suspend/{order}")
    public void suspendOrder(@PathVariable Order order) {
        try {
            processingService.suspendOrder(order);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @PostMapping("/complete/{order}")
    public void completeOrder(@PathVariable Order order) {
        try {
            processingService.completeOrder(order);
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
    public void changePrice(@Valid @RequestBody Order order, @Valid @RequestBody Float price) {
        try {
            processingService.changePrice(order, price);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @PostMapping("/create-service")
    public void addSpecialService(@Valid @RequestBody Service service, @Valid @RequestBody Order order) {
        try {
            processingService.addSpecialService(service, order);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
