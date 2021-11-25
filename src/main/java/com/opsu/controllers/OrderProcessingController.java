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
    public Boolean createOrder(@RequestBody Order order) {
        try {
            processingService.createOrder(order);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
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
    public Collection<Order> getOrders(@RequestParam int page) throws Exception {
        try {
            return processingService.getOrders(page);
        } catch (EmptyDataBaseException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping("/get-by-service")
    public Collection<Order> getOrders(@Valid @RequestParam BigInteger serviceId, @RequestParam int page) {
        try {
            return processingService.getOrders(serviceId, page);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping("/get-by-price")
    public Collection<Order> getOrders(@RequestParam Float minPrice, @RequestParam Float maxPrice, @RequestParam int page) {
        try {
            return processingService.getOrders(minPrice, maxPrice, page);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping("/get-by-status")
    public Collection<Order> getOrders(@RequestParam("status") Status status, @RequestParam int page) {
        try {
            return processingService.getOrders(status, page);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping("/get-by-title")
    public Collection<Order> getOrders(@RequestParam String title, @RequestParam int page) {
        try {
            return processingService.getOrders(title, page);
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

    @PostMapping("/update")
    public Boolean updateOrder(@RequestBody Order order) {
        try {
            processingService.updateOrder(order);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @PostMapping("/reject/{id}")
    public Boolean rejectOrder(@PathVariable BigInteger id) {
        try {
            processingService.rejectOrder(id);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @PostMapping("/cancel/{id}")
    public Boolean cancelOrder(@PathVariable BigInteger id) {
        try {
            processingService.cancelOrder(id);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @PostMapping("/assignOrder")
    public Boolean assignOrder(@RequestParam BigInteger orderId, @RequestParam BigInteger vendorId) {
        try {
            processingService.assignOrder(orderId, vendorId);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @PostMapping("/inProgress/{id}")
    public Boolean inProgressOrder(@PathVariable BigInteger id) {
        try {
            processingService.inProgressOrder(id);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @PostMapping("/suspend/{id}")
    public Boolean suspendOrder(@PathVariable BigInteger id) {
        try {
            processingService.suspendOrder(id);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @PostMapping("/complete/{id}")
    public Boolean completeOrder(@PathVariable BigInteger id) {
        try {
            processingService.completeOrder(id);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @PostMapping("/delete/{id}")
    public Boolean deleteOrder(@PathVariable BigInteger id) {
        try {
            processingService.deleteOrder(id);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @PostMapping("/change-price")
    public Boolean changePrice(@Valid @RequestBody BigInteger id, @Valid @RequestBody Float price) {
        try {
            processingService.changePrice(id, price);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @PostMapping("/create-service")
    public Boolean addSpecialService(@Valid @RequestBody BigInteger orderId, @Valid @RequestBody Service service) {
        try {
            processingService.addSpecialService(orderId, service);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @GetMapping("/get-number")
    public BigInteger getNumberOfRows() {
        try {
            return processingService.getNumberOfOrders();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping("/get-number-price")
    public BigInteger getNumberOfRows(@RequestParam Float price) {
        try {
            return processingService.getNumberOfOrders(price);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping("/get-number-status")
    public BigInteger getNumberOfRows(@RequestParam("status") Status status) {
        try {
            return processingService.getNumberOfOrders(status);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping("/get-number-title")
    public BigInteger getNumberOfRows(@RequestParam String title) {
        try {
            return processingService.getNumberOfOrders(title);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping("/get-number-service")
    public BigInteger getNumberOfRows(@RequestParam BigInteger serviceId){
        try {
            return processingService.getNumberOfOrders(serviceId);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
