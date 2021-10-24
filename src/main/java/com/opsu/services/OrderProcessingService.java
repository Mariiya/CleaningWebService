package com.opsu.services;

import com.opsu.dao.*;
import com.opsu.exceptions.EmptyDataBaseException;
import com.opsu.models.Consumer;
import com.opsu.models.Order;
import com.opsu.models.Service;
import com.opsu.models.Vendor;
import com.opsu.models.enumeration.Status;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigInteger;
import java.util.Collection;

@org.springframework.stereotype.Service
public class OrderProcessingService {
    private static Logger logger = Logger.getLogger(OrderProcessingService.class.getName());
    private final OrderDao orderDao;
    private final ServiceDao serviceDao;
    private final ServiceCollectionDao serviceCollectionDao;
    private final VendorDao vendorDao;
    private final ConsumerDao consumerDao;

    @Autowired
    public OrderProcessingService(OrderDao orderDao, ServiceDao serviceDao, ServiceCollectionDao serviceCollectionDao, VendorDao vendorDao, ConsumerDao consumerDao) {
        this.orderDao = orderDao;
        this.serviceDao = serviceDao;
        this.serviceCollectionDao = serviceCollectionDao;
        this.vendorDao = vendorDao;
        this.consumerDao = consumerDao;
    }

    public void createOrder(Order order) throws Exception {
        if((order == null)||(order.getId() == null)){
            throw new Exception("Order exception");
        }
        if(!orderDao.createOrder(order)){
            throw new Exception("Order create exception");
        }
    }

    public Order getOrder(BigInteger id) throws Exception {
        if((id == null)||(id.equals(BigInteger.ZERO))){
            throw new NumberFormatException("Wrong id input");
        }
        Order order = orderDao.getOrder(id);
        if(order == null){
            throw new EmptyDataBaseException("Order is null");
        }
        Vendor vendor = vendorDao.getVendorById(order.getVendor().getId());
        order.setVendor(vendor);
        Consumer consumer = consumerDao.getConsumerById(order.getConsumer().getId());
        order.setConsumer(consumer);

        return order;
    }

    public Collection<Order> getOrders() throws EmptyDataBaseException {
        try{
            return orderDao.getOrders();
        }
        catch (Exception ex){
            String message = ex.getMessage();
            throw new EmptyDataBaseException(message);
        }
    }

    public Collection<Order> getOrders(Service service) throws Exception {
        if((service == null)||(service.getId() == null)){
            throw new Exception("Service exception");
        }
        Collection<Order> orderCollection = orderDao.getOrders(service);
        for(Order order : orderCollection){

        }
        if(orderCollection.size() == 0){
            throw new EmptyDataBaseException("Order list is empty");
        }

        return orderCollection;
    }

    public Collection<Order> getOrders(float price) throws Exception {
        if(price < 0){
            throw new NumberFormatException();
        }
        Collection<Order> orderCollection = orderDao.getOrders(price);
        if(orderCollection.size() == 0){
            throw new EmptyDataBaseException("Order list is empty");
        }

        return orderCollection;
    }

    public Collection<Order> getOrders(String name) throws Exception {
        if(name.isEmpty()){
            throw new Exception("Name is empty");
        }
        Collection<Order> orderCollection = orderDao.getOrders(name);
        if(orderCollection.size() == 0){
            throw new EmptyDataBaseException("Order list is empty");
        }

        return orderCollection;
    }

    public void assignOrder(Order order, Consumer consumer) throws Exception {
        if((order == null)||(order.getId() == null)){
            throw new NumberFormatException("Order exception");
        }
        order.setConsumer(consumer);
        if(!orderDao.updateOrder(order)){
            throw new Exception("Order assign exception");
        }
    }

    public void changePrice(Order order) {
        if((order == null)||(order.getId() == null)){
            throw new NumberFormatException("Order exception");
        }

    }

    public void rejectOrder(BigInteger id) throws Exception {
        if((id == null)||(id.equals(BigInteger.ZERO))){
            throw new NumberFormatException("Wrong id input");
        }
        Order order = orderDao.getOrder(id);
        if(order == null){
            throw new EmptyDataBaseException("Order is null");
        }
        order.setStatus(Status.STATUS_REJECTED);
        if(!orderDao.updateOrder(order)){
            throw new Exception("Order status update exception");
        }
    }

    public void completeOrder(BigInteger id) throws Exception {
        if((id == null)||(id.equals(BigInteger.ZERO))){
            throw new NumberFormatException("Wrong id input");
        }
        Order order = orderDao.getOrder(id);
        if(order == null){
            throw new EmptyDataBaseException("Order is null");
        } else if (order.getStatus().equals(Status.STATUS_OPEN)){
            throw new EmptyDataBaseException("You can't complete just opened order");
        }
        order.setStatus(Status.STATUS_COMPLETED);
        if(!orderDao.updateOrder(order)){
            throw new Exception("Order status update exception");
        }
    }

    public void cancelOrder(BigInteger orderId, String id) throws Exception {
        if((orderId == null)||(orderId.equals(BigInteger.ZERO))){
            throw new NumberFormatException("Wrong id input");
        }
        Order order = orderDao.getOrder(orderId);
        if(order == null){
            throw new EmptyDataBaseException("Order is null");
        }
        order.setStatus(Status.STATUS_CANCELED);
        if(!orderDao.updateOrder(order)){
            throw new Exception("Order status update exception");
        }
    }

    public void inProgressOrder(BigInteger orderId) throws Exception {
        if((orderId == null)||(orderId.equals(BigInteger.ZERO))){
            throw new NumberFormatException("Wrong id input");
        }
        Order order = orderDao.getOrder(orderId);
        if(order == null){
            throw new EmptyDataBaseException("Order is null");
        }
        order.setStatus(Status.STATUS_IN_PROGRES);
        if(!orderDao.updateOrder(order)){
            throw new Exception("Order status update exception");
        }
    }

    public void suspendOrder(BigInteger orderId) throws Exception {
        if((orderId == null)||(orderId.equals(BigInteger.ZERO))){
            throw new NumberFormatException("Wrong id input");
        }
        Order order = orderDao.getOrder(orderId);
        if(order == null){
            throw new EmptyDataBaseException("Order is null");
        }
        order.setStatus(Status.STATUS_SUSPENDED);
        if(!orderDao.updateOrder(order)){
            throw new Exception("Order status update exception");
        }
    }

    public void deleteOrder(Order order) throws Exception {
        if((order.getId() == null)||(order.getId().equals(BigInteger.ZERO))){
            throw new NumberFormatException("Wrong id input");
        }
        if(!orderDao.deleteOrder(order)){
            throw new Exception("Order delete exception");
        }
    }

    public void addSpecialService(Service service, Order order) {

    }
}
