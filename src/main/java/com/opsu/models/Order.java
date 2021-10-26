package com.opsu.models;

import com.opsu.models.enumeration.Status;


import javax.validation.constraints.*;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

public class Order {

    @Min(value=0,message ="Oder id is not be correct")
    private BigInteger id;

    @Pattern(regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]')?[A-Z][a-z]*)))*$",message = "Vendor first name format is not correct")
    @Size(min=2,max=30,message = "Title should contain from 2 to 30 digits")
    @NotEmpty(message ="Title can not be empty")
    private String title;

    @NotEmpty(message ="Status can not be empty")
    private Status status;

    private Consumer consumer;
    private Vendor vendor;

    @Past
    private Date startDate;

    @FutureOrPresent
    private Date endDate;

    @Positive
    @NotEmpty(message ="Price can not be empty")
    private float price;

    private Collection<Service> services;

    @NotEmpty(message ="Address can not be empty")
    @Pattern(regexp = "^(?:[a-zA-Z0-9_'^&/+-])+(?:\\.(?:[a-zA-Z0-9_'^&/+-])+)" + "*@(?:(?:\\[?(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))\\.)" +
            "{3}(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)]?)|(?:[a-zA-Z0-9-]+\\.)" + "+(?:[a-zA-Z]){2,}\\?)$",
    message = "Address format is not correct")
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
    public float  getPrice() {return price ; }
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
    public Collection<Service> getServices() {
        return services;
    }
    public void setServices(Collection<Service> services) {
        this.services = services;
    }

    public Order(BigInteger id , String title, Status status , Consumer consumer, Vendor vendor, Date startDate,
                 Date endDate, Collection<Service> services, float price, String address) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.consumer = consumer;
        this.vendor= vendor;
        this.services = services;
        this.price = price;
        this.address= address;
    }
}
