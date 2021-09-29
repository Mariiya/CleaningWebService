package com.opsu.models;

import java.math.BigInteger;

public class ServiceCollection {
    private BigInteger id;
    private BigInteger orderId;
    private BigInteger serviceId;

    public ServiceCollection(BigInteger id, BigInteger orderId, BigInteger serviceId) {
        this.id = id;
        this.orderId = orderId;
        this.serviceId = serviceId;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getOrderId() {
        return orderId;
    }

    public void setOrderId(BigInteger orderId) {
        this.orderId = orderId;
    }

    public BigInteger getServiceId() {
        return serviceId;
    }

    public void setServiceId(BigInteger serviceId) {
        this.serviceId = serviceId;
    }
}
