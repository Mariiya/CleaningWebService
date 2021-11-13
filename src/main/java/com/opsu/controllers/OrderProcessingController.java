package com.opsu.controllers;

import com.opsu.exceptions.EmptyDataBaseException;
import com.opsu.models.Consumer;
import com.opsu.models.Order;
import com.opsu.models.Service;
import com.opsu.models.enumeration.Status;
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
    public void createOrder(@RequestBody Order order) {
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

    @GetMapping("/get-by-service")
    public Collection<Order> getOrders(@Valid @RequestBody Service service) {
        try {
            return processingService.getOrders(service);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping("/get-by-price")
    public Collection<Order> getOrders(@RequestParam @NotNull Float price) {
        try {
            return processingService.getOrders(price);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping("/get-by-status")
    public Collection<Order> getOrders(@RequestParam("status") Status status) {
        try {
            return processingService.getOrders(status);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping("/get-by-title")
    public Collection<Order> getOrders(@RequestParam String title) {
        try {
            return processingService.getOrders(title);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping("/get-by-user")
    public Collection<Order> getOrders(@RequestParam BigInteger id) {
        try {
            return processingService.getOrders(id);
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
    public void cancelOrder(@PathVariable BigInteger id) {
        try {
            processingService.cancelOrder(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @PostMapping("/assignOrder")
    public void assignOrder(@RequestParam BigInteger orderId, @RequestParam BigInteger consumerId) {
        try {
            processingService.assignOrder(orderId, consumerId);
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

    @PostMapping("/delete/{id}")
    public void deleteOrder(@PathVariable BigInteger id) {
        try {
            processingService.deleteOrder(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @PostMapping("/change-price")
    public void changePrice(@Valid @RequestBody BigInteger id, @Valid @RequestBody Float price) {
        try {
            processingService.changePrice(id, price);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @PostMapping("/create-service")
    public void addSpecialService(@Valid @RequestBody BigInteger orderId,@Valid @RequestBody Service service) {
        try {
            processingService.addSpecialService(orderId, service);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
