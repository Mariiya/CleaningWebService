package com.opsu.services;

import com.opsu.dao.*;
import com.opsu.exceptions.EmptyDataBaseException;
import com.opsu.models.*;
import com.opsu.models.enumeration.Status;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigInteger;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
        if((order == null)){
            throw new Exception("Order is null");
        } else if(order.getId() == null || order.getId().equals(BigInteger.ZERO)){
            throw new Exception("Order ID exception");
        } else if(order.getTitle().isEmpty()){
            throw new Exception("Order Title is empty");
        } else if(order.getPrice() <= 0){
            throw new Exception("Order Price cannot be zero or less");
        } else if(order.getConsumer() == null){
            throw new Exception("Order without a Consumer");
        } else if(order.getAddress().isEmpty()){
            throw new Exception("Order Address is empty");
        } else if(order.getStatus() == null){
            throw new Exception("Order Status is null");
        } else if(order.getStartDate() == null){
            throw new Exception("Order Start Date is null");
        } else if(order.getServices().size() == 0){
            throw new Exception("Order hasn't any service");
        }
        if(!orderDao.createOrder(order)){
            throw new Exception("Order create exception");
        }
        for(Service service : order.getServices()){
            serviceCollectionDao.createServiceCollection(new ServiceCollection(BigInteger.ZERO, order, service));
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

        Collection<ServiceCollection> serviceCollections = serviceCollectionDao.getServiceCollectionsByOrder(order);
        if(serviceCollections.size() == 0){
            throw new EmptyDataBaseException("ServiceCollection is null");
        }
        ArrayList<Service> services = new ArrayList<>();
        for(ServiceCollection collection : serviceCollections){
            services.add(serviceDao.getService(collection.getService().getId()));
        }
        if(services.size() == 0){
            throw new EmptyDataBaseException("Services is null");
        }
        order.setServices(services);
        return order;
    }

    public Collection<Order> getOrders() throws Exception {
        Collection<Order> orderCollection = orderDao.getOrders();
        if(orderCollection.size() == 0){
            throw new EmptyDataBaseException("Order list is empty");
        }
        for(Order order : orderCollection){
            Vendor vendor = vendorDao.getVendorById(order.getVendor().getId());
            Consumer consumer = consumerDao.getConsumerById(order.getConsumer().getId());
            order.setVendor(vendor);
            order.setConsumer(consumer);
            Collection<ServiceCollection> serviceCollections = serviceCollectionDao.getServiceCollectionsByOrder(order);
            if(serviceCollections.size() == 0){
                throw new EmptyDataBaseException("ServiceCollection is null");
            }
            ArrayList<Service> services = new ArrayList<>();
            for(ServiceCollection collection : serviceCollections){
                services.add(serviceDao.getService(collection.getService().getId()));
            }
            if(services.size() == 0){
                throw new EmptyDataBaseException("Services is null");
            }
            order.setServices(services);
        }

        return orderCollection;    }

    public Collection<Order> getOrders(Service service) throws Exception {
        if((service == null)||(service.getId() == null)){
            throw new Exception("Service exception");
        }
        Collection<Order> orderCollection = orderDao.getOrders(service);
        if(orderCollection.size() == 0){
            throw new EmptyDataBaseException("Order list is empty");
        }
        for(Order order : orderCollection){
            Vendor vendor = vendorDao.getVendorById(order.getVendor().getId());
            Consumer consumer = consumerDao.getConsumerById(order.getConsumer().getId());
            order.setVendor(vendor);
            order.setConsumer(consumer);
            Collection<ServiceCollection> serviceCollections = serviceCollectionDao.getServiceCollectionsByOrder(order);
            if(serviceCollections.size() == 0){
                throw new EmptyDataBaseException("ServiceCollection is null");
            }
            ArrayList<Service> services = new ArrayList<>();
            for(ServiceCollection collection : serviceCollections){
                services.add(serviceDao.getService(collection.getService().getId()));
            }
            if(services.size() == 0){
                throw new EmptyDataBaseException("Services is null");
            }
            order.setServices(services);
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
        for(Order order : orderCollection){
            Vendor vendor = vendorDao.getVendorById(order.getVendor().getId());
            Consumer consumer = consumerDao.getConsumerById(order.getConsumer().getId());
            order.setVendor(vendor);
            order.setConsumer(consumer);
            Collection<ServiceCollection> serviceCollections = serviceCollectionDao.getServiceCollectionsByOrder(order);
            if(serviceCollections.size() == 0){
                throw new EmptyDataBaseException("ServiceCollection is null");
            }
            ArrayList<Service> services = new ArrayList<>();
            for(ServiceCollection collection : serviceCollections){
                services.add(serviceDao.getService(collection.getService().getId()));
            }
            if(services.size() == 0){
                throw new EmptyDataBaseException("Services is null");
            }
            order.setServices(services);
        }

        return orderCollection;
    }

    public Collection<Order> getOrders(String title) throws Exception {
        if(title.isEmpty()){
            throw new Exception("Name is empty");
        }
        Collection<Order> orderCollection = orderDao.getOrders(title);
        if(orderCollection.size() == 0){
            throw new EmptyDataBaseException("Order list is empty");
        }
        for(Order order : orderCollection){
            Vendor vendor = vendorDao.getVendorById(order.getVendor().getId());
            Consumer consumer = consumerDao.getConsumerById(order.getConsumer().getId());
            order.setVendor(vendor);
            order.setConsumer(consumer);
            Collection<ServiceCollection> serviceCollections = serviceCollectionDao.getServiceCollectionsByOrder(order);
            if(serviceCollections.size() == 0){
                throw new EmptyDataBaseException("ServiceCollection is null");
            }
            ArrayList<Service> services = new ArrayList<>();
            for(ServiceCollection collection : serviceCollections){
                services.add(serviceDao.getService(collection.getService().getId()));
            }
            if(services.size() == 0){
                throw new EmptyDataBaseException("Services is null");
            }
            order.setServices(services);
        }

        return orderCollection;
    }

    public void assignOrder(Order order, Consumer consumer) throws Exception {
        if((order == null)||(order.getId() == null)){
            throw new Exception("Order exception");
        }
        order.setConsumer(consumer);
        if(!orderDao.updateOrder(order)){
            throw new Exception("Order assign exception");
        }
    }

    public void changePrice(Order order, Float price) throws Exception {
        if((order == null)||(order.getId() == null)){
            throw new Exception("Order exception");
        }
        if(price <= 0){
            throw new NumberFormatException("Order price less than zero");
        }
        order.setPrice(price);
        if(!orderDao.updateOrder(order)){
            throw new Exception("Order status update exception");
        }
    }

    public void rejectOrder(Order order) throws Exception {
        if(order == null){
            throw new EmptyDataBaseException("Order is null");
        }
        order.setStatus(Status.STATUS_REJECTED);
        if(!orderDao.updateOrder(order)){
            throw new Exception("Order status update exception");
        }
    }

    public void completeOrder(Order order) throws Exception {
        if(order == null){
            throw new EmptyDataBaseException("Order is null");
        } else if (order.getStatus().equals(Status.STATUS_OPEN)){
            throw new EmptyDataBaseException("You can't complete just opened order");
        }
        order.setStatus(Status.STATUS_COMPLETED);
        order.setEndDate(Date.from(Instant.now()));
        if(!orderDao.updateOrder(order)){
            throw new Exception("Order status update exception");
        }
    }

    public void cancelOrder(Order order) throws Exception {
        if(order == null){
            throw new EmptyDataBaseException("Order is null");
        }
        order.setStatus(Status.STATUS_CANCELED);
        if(!orderDao.updateOrder(order)){
            throw new Exception("Order status update exception");
        }
    }

    public void inProgressOrder(Order order) throws Exception {
        if(order == null){
            throw new EmptyDataBaseException("Order is null");
        }
        order.setStatus(Status.STATUS_IN_PROGRES);
        if(!orderDao.updateOrder(order)){
            throw new Exception("Order status update exception");
        }
    }

    public void suspendOrder(Order order) throws Exception {
        if(order == null){
            throw new EmptyDataBaseException("Order is null");
        }
        order.setStatus(Status.STATUS_SUSPENDED);
        if(!orderDao.updateOrder(order)){
            throw new Exception("Order status update exception");
        }
    }

    public void deleteOrder(Order order) throws Exception {
        if((order == null)||(order.getId() == null)||(order.getId().equals(BigInteger.ZERO))){
            throw new Exception("Order exception");
        }
        if(!orderDao.deleteOrder(order)){
            throw new Exception("Order delete exception");
        }
    }

    public void addSpecialService(Service service, Order order) throws Exception {
        if((order == null)||(order.getId() == null)||(order.getId().equals(BigInteger.ZERO))){
            throw new Exception("Order exception");
        }
        if((service == null)||(service.getId() == null)||(service.getId().equals(BigInteger.ZERO))){
            throw new Exception("Service exception");
        }
        serviceDao.addNewService(service);
        serviceCollectionDao.createServiceCollection(new ServiceCollection(BigInteger.ZERO, order, service));
    }
}
