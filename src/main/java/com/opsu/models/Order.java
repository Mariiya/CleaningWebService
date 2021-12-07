package com.opsu.models;
import com.opsu.models.enumeration.Status;
import javax.validation.constraints.*;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
/**
 * In this class we store information about Order
 * @author group 183
 * @version 2.1
 */
public class Order {

    /** field id
     * has limitation in value
     */
    @Min(value=0,message ="Oder id is not be correct")
    private BigInteger id;

    /** field order title
     * field's restriction
     * field can't be empty
     * has limit in size
     * uses specific variables
     */
    @Pattern(regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]')?[A-Z][a-z]*)))*$",message = "Oder title format is not correct")
    @Size(min=2,max=30,message = "Title should contain from 2 to 30 digits")
    @NotEmpty(message ="Title can not be empty")
    private String title;

    /** field order's description
     * field can't be empty
     */
    @NotEmpty(message ="Description can not be empty")
    private String description;

    /** field order's status*
     * has limitation in value
     */
    @NotNull(message ="Status can not be empty")
    private Status status;

    /** field order's information about consumer */
    private Consumer consumer;
    /** field order's information about vendor */
    private Vendor vendor;

    /** field order's start date*/
    @Past
    private Date startDate;
    /** field order's last day */
    @FutureOrPresent
    private Date endDate;

    /** field order's price
     *has limitation in value (positive)
     *field can't be empty */
    @Positive
    @NotEmpty(message ="Price can not be empty")
    private float price;
    /** field service collection */
    private Collection<Service> services;
    /** field order's address
     * field can't be empty
     */
    @NotEmpty(message ="Address can not be empty")
    private String address;

    /** receives information about order id
     * @return order id */
    public BigInteger getId() {
        return id;
    }
    /** inserts information  about order id
     * @param id  order  */
    public void setId(BigInteger id) {
        this.id = id;
    }

    /** receives information about order title
     * @return order title */
    public String  getTitle() {
        return title;
    }
    /** inserts information  about order title
     * @param title order  */
    public void setTitle(String title) {
        this.title= title;
    }

    /** receives information about order description
     * @return order description*/
    public String getDescription() {
        return description;
    }
    /** inserts information  about order id
     * @param description  order  */
    public void setDescription(String description) {
        this.description = description;
    }

    /** receives information about order start date
     * @return order start date  */
    public Date  getStartDate() {
        return startDate;
    }
    /** inserts information  about order start date
     * @param startDate order  */
    public void setStartDate(Date startDate) {
        this.startDate= startDate;
    }

    /** receives information about order last day
     * @return order last day */
    public Date  getEndDate() {
        return endDate;
    }
    /** inserts information  about order last day
     * @param endDate order  */
    public void setEndDate(Date endDate) {
        this.endDate= endDate;
    }

    /** receives information about order price
     * @return order price */
    public float  getPrice() {return price ; }
    /** inserts information  about order price
     * @param price order  */
    public void setPrice(float price) {
        this.price= price;
    }

    /** receives information about order address
     * @return order address*/
    public String  getAddress() {
        return address;
    }
    /** inserts information  about order address
     * @param address order  */
    public void setAddress(String address) {
        this.address= address;
    }
    /** receives information about consumer
     * @return order consumer */
    public Consumer getConsumer() {
        return consumer;
    }
    /** inserts information  about consumer
     * @param consumer order  */
    public void setConsumer(Consumer consumer) {
        this.consumer= consumer;
    }

    /** receives information about vendor
     * @return  vendor  */
    public Vendor  getVendor() {
        return vendor;
    }
    /**  @param  vendor vendor */
    public void setVendor(Vendor vendor) {
        this.vendor=vendor;
    }

    /** receives information about order's status
     * @return order's status  */
    public Status  getStatus() {
        return status;
    }
    /** inserts information  about vendor
     * @param status order's status*/
    public void setStatus (Status status) {
        this.status= status;
    }

    /**receives information about service collection
     * @return service collection*/
    public Collection<Service> getServices() {
        return services;
    }
    /** inserts information  about service collection
     * @param services service collection*/
    public void setServices(Collection<Service> services) {
        this.services = services;
    }
    /**
     * This constructor creates new order
     * @param id order's id
     * @param title order's name
     * @param status order's status
     * @param description order's description
     * @param address order's address
     * @param consumer consumer
     * @param startDate order's start date
     * @param endDate order's last day
     * @param vendor vendor
     * @param price order's price
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
