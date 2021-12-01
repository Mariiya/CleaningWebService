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
/**
 * The controller provides end points for processing requests from the frontend for the object Order
 * @author group 183
 * @version 2.1
 */
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
    /** create new order
     * @param order  order */
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
    /** search order by id
     * @param id order */
    @GetMapping("/{id}")
    public Order getOrder(@PathVariable BigInteger id) {
        try {
            return processingService.getOrder(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
    /** show all orders
     * @param page order */
    @GetMapping("/orders")
    public Collection<Order> getOrders(@RequestParam int page) throws Exception {
        try {
            return processingService.getOrders(page);
        } catch (EmptyDataBaseException e) {
            log.error(e.getMessage());
            return null;
        }
    }
    /** search and group order by several parametrs
     * @param maxPrice order
     * @param minPrice  order
     * @param serviceId order
     * @param status  order
     * @param title order
     * @param page  order */
    @GetMapping("/get-by-multiparams")
    public Collection<Order> getOrders(@RequestParam Float minPrice, @RequestParam Float maxPrice, @RequestParam String title, @RequestParam("status") Status status, @RequestParam BigInteger serviceId, int page) throws Exception {
        try {

            return processingService.getOrders(minPrice, maxPrice, title, status, serviceId, page);
        } catch (EmptyDataBaseException e) {
            log.error(e.getMessage());
            return null;
        }
    }
    /** search and group order by serviceid
     * @param serviceId order
     * @param page  order */
    @GetMapping("/get-by-service")
    public Collection<Order> getOrders(@Valid @RequestParam BigInteger serviceId, @RequestParam int page) {
        try {
            return processingService.getOrders(serviceId, page);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
    /** search and group order by price
     * @param maxPrice order
     * @param minPrice  order
     * @param page  order */
    @GetMapping("/get-by-price")
    public Collection<Order> getOrders(@RequestParam Float minPrice, @RequestParam Float maxPrice, @RequestParam int page) {
        try {
            return processingService.getOrders(minPrice, maxPrice, page);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
    /** search and group order by status
     * @param status order
     * @param page  order */
    @GetMapping("/get-by-status")
    public Collection<Order> getOrders(@RequestParam("status") Status status, @RequestParam int page) {
        try {
            return processingService.getOrders(status, page);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
    /** search and group order by title
     * @param title order
     * @param page  order */
    @GetMapping("/get-by-title")
    public Collection<Order> getOrders(@RequestParam String title, @RequestParam int page) {
        try {
            return processingService.getOrders(title, page);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
    /** search and group order by userid
     * @param id order
     */
    @GetMapping("/get-by-user")
    public Collection<Order> getOrders(@RequestParam BigInteger id) {
        try {
            return processingService.getOrders(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
    /**updating already existing order
     * @param order  order
     */
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

    /**vendor rejects order
     * @param id   order
     */
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
    /**consumer canceled order
     * @param id   order
     */
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
    /**vendor chooses order
     * @param orderId   order
     * @param vendorId vendor
     */
    @PostMapping("/assignOrder")
    public Order assignOrder(@RequestParam BigInteger orderId, @RequestParam BigInteger vendorId) throws Exception {
        try {
            return  processingService.assignOrder(orderId, vendorId);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
    /**order has not been finished
     * @param id   order
     */
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
    /**order has been suspended by consumer
     * @param id   order
     */
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
    /**order has been finished
     * @param id   order
     */
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
    /**delete order
     * @param id  order */
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
    /**ability to change price
     * @param id  order
     * @param price  order*/
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
    /**add custom service
     * @param orderId order
     * @param service service */
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
    /**count amount of all orders */
    @GetMapping("/get-number")
    public BigInteger getNumberOfRows() {
        try {
            return processingService.getNumberOfOrders();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
    /**count amount of orders grouped by price
     * @param price order */
    @GetMapping("/get-number-price")
    public BigInteger getNumberOfRows(@RequestParam Float price) {
        try {
            return processingService.getNumberOfOrders(price);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
    /**count amount of orders grouped by status
     * @param status order */
    @GetMapping("/get-number-status")
    public BigInteger getNumberOfRows(@RequestParam("status") Status status) {
        try {
            return processingService.getNumberOfOrders(status);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
    /**count amount of orders grouped by title
     * @param title order */
    @GetMapping("/get-number-title")
    public BigInteger getNumberOfRows(@RequestParam String title) {
        try {
            return processingService.getNumberOfOrders(title);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
    /**count amount of orders grouped by serviceid
     * @param serviceId order */
    @GetMapping("/get-number-service")
    public BigInteger getNumberOfRows(@RequestParam BigInteger serviceId){
        try {
            return processingService.getNumberOfOrders(serviceId);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
    /**count amount of orders grouped by several parameters
     * @param maxPrice order
     * @param minPrice order
     * @param title order
     * @param status order
     * @param serviceId  order*/
    @GetMapping("/get-number-multiparam")
    public BigInteger getNumberOfRows(@RequestParam Float minPrice, @RequestParam Float maxPrice, @RequestParam String title, @RequestParam("status") Status status, @RequestParam BigInteger serviceId){
        try {
            return processingService.getNumberOfOrders(minPrice, maxPrice, title, status, serviceId);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
