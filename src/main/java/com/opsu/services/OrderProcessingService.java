package com.opsu.services;

import com.opsu.dao.OrderDao;
import com.opsu.exceptions.EmptyDataBaseException;
import com.opsu.models.Consumer;
import com.opsu.models.Order;
import com.opsu.models.Service;
import com.opsu.models.enumeration.Status;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;

@org.springframework.stereotype.Service
public class OrderProcessingService {
    private static Logger logger = Logger.getLogger(OrderProcessingService.class.getName());
    private final OrderDao orderDao;

    @Autowired
    public OrderProcessingService(OrderDao orderDao) {
        this.orderDao = orderDao;
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

    public void changePrice(Order order, float price) throws Exception {
        if((order == null)||(order.getId() == null)){
            throw new NumberFormatException("Order exception");
        }
        order.setPrice(price);
        if(!orderDao.updateOrder(order)){
            throw new Exception("Order assign exception");
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

    public void inProgressOrder(BigInteger orderId, String id) throws Exception {
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

    public void suspendOrder(BigInteger orderId, String id) throws Exception {
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

    public void addSpecialService(Service service, Order order) {    }
}
