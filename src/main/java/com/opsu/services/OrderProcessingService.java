package com.opsu.services;

import com.opsu.models.Consumer;
import com.opsu.models.Order;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;

@Service
public class OrderProcessingService {

    public void cancelOrder(BigInteger orderId, String id) {}

    public Collection<Order> getOrders() {
        return Collections.EMPTY_LIST;
    }

    public Collection<Order> getOrders(Service service) {
        return Collections.EMPTY_LIST;
    }

    public Collection<Order> getOrders(Double price) {
        return Collections.EMPTY_LIST;
    }

    public Collection<Order> getOrders(String name) {
        return Collections.EMPTY_LIST;
    }

    public void rejectOrder(BigInteger id) {    }

    public void createOrder(Order order) {    }

    public void addSpecialService(Service service, Order order) {    }

    public Order getOrder(BigInteger id) {return null; }

    public void assignOrder(Order order, Consumer consumer) {    }

    public void completeOrder(BigInteger id) {    }

    public void changePrice(Order order) {    }
}
