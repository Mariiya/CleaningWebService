
package com.opsu.models;
import java.math.BigInteger;
/**
 * In this class we store information  about serviceCollections
 * @author group 183
 * @version 2.1
 */
public class ServiceCollection {
    /** field id */
    private BigInteger id;
    /** field order */
    private Order order;
    /** field service */
    private Service service;

    /**
     * This constructor creates new service collection
     * @param id service collection id
     * @param order order
     * @param service  service
     */
    public ServiceCollection(BigInteger id, Order order, Service service) {
        this.id = id;
        this.order = order;
        this.service = service;
    }
    /** receives information about service collection id
     * @return service collection id */
    public BigInteger getId() {
        return id;
    }
    /** inserts information  about service collection id
     * @param id  service collection */
    public void setId(BigInteger id) {
        this.id = id;
    }

    /** receives information about service collection order
     *  @return order*/
    public Order getOrder() {
        return order;
    }
    /** inserts information  about service collection order
     * @param order  service collection */
    public void setOrder(Order order) {
        this.order = order;
    }

    /** receives information about service collection service
     * @return service  */
    public Service getService() {
        return service;
    }
    /** inserts information  about service collection
     * @param  service collection */
    public void setService(Service service) {
        this.service = service;
    }
}
