
package com.opsu.models;
import java.math.BigInteger;
/**
 * Class ServiceCollection
 * @author group 183
 * @version 2.1
 */
public class ServiceCollection {
    /** field id */
    private BigInteger id;
    /** field oder */
    private Order order;
    /** field service */
    private Service service;

    /**
     * constructor- create service collection
     * @param id service collection id
     * @param order oder
     * @param service  service
     */
    public ServiceCollection(BigInteger id, Order order, Service service) {
        this.id = id;
        this.order = order;
        this.service = service;
    }
    /** @return service collection id */
    public BigInteger getId() {
        return id;
    }
    /** @param id  service collection */
    public void setId(BigInteger id) {
        this.id = id;
    }

    /** @return oder*/
    public Order getOrder() {
        return order;
    }
    /** @param order  service collection */
    public void setOrder(Order order) {
        this.order = order;
    }

    /** @return service  */
    public Service getService() {
        return service;
    }
    /** @param  service collection */
    public void setService(Service service) {
        this.service = service;
    }
}
