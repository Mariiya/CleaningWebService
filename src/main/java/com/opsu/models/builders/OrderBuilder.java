package com.opsu.models.builders;

import com.opsu.models.Consumer;
import com.opsu.models.Order;
import com.opsu.models.Vendor;
import com.opsu.models.enumeration.Status;

import java.math.BigInteger;
import java.util.Date;

public class OrderBuilder {
    private BigInteger id;
    private String title;
    private Status status;
    private Consumer consumer;
    private Vendor vendor;
    private Date startDate;
    private Date endDate;
    private float price;
    private String address;


    public OrderBuilder withId(BigInteger id) {
        this.id = id;
        return this;
    }


    public OrderBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public OrderBuilder withStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public OrderBuilder withStatus(Status status) {
        this.status = status;
        return this;
    }

    public OrderBuilder withEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public OrderBuilder withPrice(float price) {
        this.price = price;
        return this;
    }

    public OrderBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public OrderBuilder withConsumer(Consumer consumer) {
        this.consumer = consumer;
        return this;
    }

    public OrderBuilder withVendor(Vendor vendor) {
        this.vendor = vendor;
        return this;
    }

    public Order build() {
        return new Order(id, title, status, consumer, vendor, startDate, endDate, price, address);
    }

}