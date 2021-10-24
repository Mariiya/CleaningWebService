package com.opsu.models;

import javax.validation.constraints.Min;
import java.math.BigInteger;

public class ServiceCollection {
    private BigInteger id;
    private Order order;
    private Service service;

    public ServiceCollection(BigInteger id, Order order, Service service) {
        this.id = id;
        this.order = order;
        this.service = service;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
