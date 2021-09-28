package com.opsu.models;

import com.opsu.models.enumeration.Status;



import java.math.BigInteger;
import java.util.Date;

public class Order {
    private BigInteger id;
    private String title;
    private Status status;
    private Consumer consumer;
    private Vendor vendor;
    private Date startDate;
    private Date endDate;
    private float price;
    private String address;


    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String  getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title= title;
    }
    public Date  getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate= startDate;
    }
    public Date  getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate= endDate;
    }
    public float  getPrice() {return price ;
    }
    public void setPrice(float price) {
        this.price= price;
    }
    public String  getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address= address;
    }
    public Consumer getConsumer() {
        return consumer;
    }
    public void setConsumer(Consumer consumer) {
        this.consumer= consumer;
    }
    public Vendor  getVendor() {
        return vendor;
    }
    public void setVendor(Vendor vendor) {
        this.vendor=vendor;
    }

    public Status  getStatus() {
        return status;
    }
    public void setStatus (Status status) {
        this.status= status;
    }
    public Order(BigInteger id , String title, Status status , Consumer consumer, Vendor vendor, Date startDate,
                 Date endDate, float price, String address) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.consumer = consumer;
        this.vendor= vendor;
        this.price = price;
        this.address= address;
    }
}
