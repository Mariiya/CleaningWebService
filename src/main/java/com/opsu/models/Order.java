package com.opsu.models;
import com.opsu.models.enumeration.Status;
import javax.validation.constraints.*;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
/**
 * Class Oder
 * @author group 183
 * @version 2.1
 */
public class Order {
    /** field id */
    @Min(value=0,message ="Oder id is not be correct")
    private BigInteger id;
    /** field oder title*/
    @Pattern(regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]')?[A-Z][a-z]*)))*$",message = "Oder title format is not correct")
    @Size(min=2,max=30,message = "Title should contain from 2 to 30 digits")
    @NotEmpty(message ="Title can not be empty")
    private String title;

    /** field oder's description*/
    @NotEmpty(message ="Description can not be empty")
    private String description;

    /** field oder's status*/
    @NotNull(message ="Status can not be empty")
    private Status status;

    private Consumer consumer;
    private Vendor vendor;

    /** field oder's start date */
    @Past
    private Date startDate;
    /** field oder's last day */
    @FutureOrPresent
    private Date endDate;

    /** field oder's price */
    @Positive
    @NotEmpty(message ="Price can not be empty")
    private float price;
    /** field service collection */
    private Collection<Service> services;
    /** field oder's address */
    @NotEmpty(message ="Address can not be empty")
    //@Pattern(regexp = "^(?:[a-zA-Z0-9_'^&/+-])+(?:\\.(?:[a-zA-Z0-9_'^&/+-])+)" + "*@(?:(?:\\[?(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))\\.)" +
    //       "{3}(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)]?)|(?:[a-zA-Z0-9-]+\\.)" + "+(?:[a-zA-Z]){2,}\\?)$",
   //message = "Address format is not correct")
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

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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

    /** @return  vendor  */
    public Vendor  getVendor() {
        return vendor;
    }
    /**  @param  vendor vendor */
    public void setVendor(Vendor vendor) {
        this.vendor=vendor;
    }

    /** @return oder's status  */
    public Status  getStatus() {
        return status;
    }
    /** @param status oder's status*/
    public void setStatus (Status status) {
        this.status= status;
    }
    /**@return service collection*/
    public Collection<Service> getServices() {
        return services;
    }
    /** @param services service collection*/
    public void setServices(Collection<Service> services) {
        this.services = services;
    }
    /**
     * constructor- create oder
     * @param id oder's id
     * @param title oder's name
     * @param status oder's status
     * @param description oder's description
     * @param address oder's address
     * @param consumer consumer
     * @param startDate oder's start date
     * @param endDate oder's last day
     * @param vendor vendor
     * @param price oder's price
     * @param services service collection
     */
    public Order(BigInteger id , String title, String description, Status status , Consumer consumer, Vendor vendor, Date startDate,
                 Date endDate, Collection<Service> services, float price, String address) {
        this.id = id;
        this.title = title;
        this.description = description;
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
