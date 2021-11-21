package com.opsu.services;

import com.opsu.dao.*;
import com.opsu.dao.impl.OrderDaoImpl;
import com.opsu.exceptions.EmptyDataBaseException;
import com.opsu.models.*;
import com.opsu.models.enumeration.Status;
import javassist.NotFoundException;
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
        } else if(order.getAddress().isEmpty()){
            throw new Exception("Order Address is empty");
        } else if(order.getStatus() == null){
            throw new Exception("Order Status is null");
        } else if(order.getStartDate() == null){
            throw new Exception("Order Start Date is null");
        } else if(order.getServices().size() == 0){
            throw new Exception("Order hasn't any service");
        }

        Consumer consumer = consumerDao.getConsumerById(order.getConsumer().getId());
        Collection<Service> serviceCollection = order.getServices();

        order.setConsumer(consumer);

        if(!orderDao.createOrder(order)){
            throw new Exception("Order create exception");
        }

        order = orderDao.getOrderId(order);
        order.setServices(serviceCollection);
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

        if (order.getVendor() != null) {
            Vendor vendor = vendorDao.getVendorById(order.getVendor().getId());
            order.setVendor(vendor);
        }
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

    public Collection<Order> getOrders(int page) throws Exception {
        Collection<Order> orderCollection = orderDao.getOrders(page);
        if(orderCollection.size() == 0){
            throw new EmptyDataBaseException("Order list is empty");
        }
        for(Order order : orderCollection){
            if(Status.STATUS_IN_PROGRES.equals(order.getStatus())) {
                Vendor vendor = vendorDao.getVendorById(order.getVendor().getId());
                order.setVendor(vendor);
            }
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
        }

        return orderCollection;
    }

    public Collection<Order> getOrders(Service service, int page) throws Exception {
        if((service == null)||(service.getId() == null)){
            throw new Exception("Service exception");
        }
        Collection<Order> orderCollection = orderDao.getOrders(service, page);
        if(orderCollection.size() == 0){
            throw new EmptyDataBaseException("Order list is empty");
        }
        for(Order order : orderCollection){
            if (order.getVendor() != null) {
                Vendor vendor = vendorDao.getVendorById(order.getVendor().getId());
                order.setVendor(vendor);
            }
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
        }

        return orderCollection;
    }

    public Collection<Order> getOrders(Float minPrice, Float maxPrice, int page) throws Exception {
        if(minPrice < 0){
            minPrice = 0f;
        }
        if(maxPrice <= 0){
            maxPrice = Float.MAX_VALUE;
        }
        Collection<Order> orderCollection = orderDao.getOrders(minPrice, maxPrice, page);
        if(orderCollection.size() == 0){
            throw new EmptyDataBaseException("Order list is empty");
        }
        for(Order order : orderCollection){
            if (order.getVendor() != null) {
                Vendor vendor = vendorDao.getVendorById(order.getVendor().getId());
                order.setVendor(vendor);
            }
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
        }

        return orderCollection;
    }

    public Collection<Order> getOrders(String title, int page) throws Exception {
        if(title.isEmpty()){
            throw new Exception("Name is empty");
        }
        Collection<Order> orderCollection = orderDao.getOrders(title, page);
        if(orderCollection.size() == 0){
            throw new EmptyDataBaseException("Order list is empty");
        }
        for(Order order : orderCollection){
            if (order.getVendor() != null) {
                Vendor vendor = vendorDao.getVendorById(order.getVendor().getId());
                order.setVendor(vendor);
            }
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
        }

        return orderCollection;
    }

    public Collection<Order> getOrders(Status status, int page) throws Exception {
        if(status == null){
            throw new Exception("Status is empty");
        }
        Collection<Order> orderCollection = orderDao.getOrders(status, page);
        if(orderCollection.size() == 0){
            throw new EmptyDataBaseException("Order list is empty");
        }
        for(Order order : orderCollection){
            if (order.getVendor() != null) {
                Vendor vendor = vendorDao.getVendorById(order.getVendor().getId());
                order.setVendor(vendor);
            }
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
        }

        return orderCollection;
    }

    public Collection<Order> getOrders(BigInteger id) throws Exception {
        if((id == null)||(id.equals(BigInteger.ZERO))){
            throw new Exception("Order exception");
        }
        Collection<Order> orderCollection = orderDao.getOrders(id);
        if(orderCollection.size() == 0){
            throw new EmptyDataBaseException("Order list is empty");
        }
        for(Order order : orderCollection){
            if (order.getVendor() != null) {
                Vendor vendor = vendorDao.getVendorById(order.getVendor().getId());
                order.setVendor(vendor);
            }
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
        }

        return orderCollection;
    }

    public void updateOrder(Order order) throws Exception {
        if(!order.getStatus().equals(Status.STATUS_OPEN)){
            throw new Exception("You can't update this Order");
        }
        if(!orderDao.updateOrder(order)){
            throw new Exception("Order update exception");
        }
    }

    public void assignOrder(BigInteger orderId, BigInteger vendorId) throws Exception {
        if((orderId == null)||(orderId.equals(BigInteger.ZERO))){
            throw new Exception("Order exception");
        }
        if((vendorId == null)||(vendorId.equals(BigInteger.ZERO))){
            throw new Exception("Consumer exception");
        }

        Order order = orderDao.getOrder(orderId);
        Vendor vendor = vendorDao.getVendorById(vendorId);
        if((vendor == null)||(order == null)){
            throw new Exception("Order or Vendor exception");
        }
        order.setVendor(vendor);
        if(!orderDao.updateOrder(order)){
            throw new Exception("Order assign exception");
        }
    }

    public void changePrice(BigInteger id, Float price) throws Exception {
        if((id == null)||(id.equals(BigInteger.ZERO))){
            throw new Exception("Order exception");
        }
        if(price <= 0){
            throw new NumberFormatException("Order price less than zero");
        }
        Order order = orderDao.getOrder(id);
        order.setPrice(price);
        if(!orderDao.updateOrder(order)){
            throw new Exception("Order status update exception");
        }
    }

    public void rejectOrder(BigInteger id) throws Exception {
        if((id == null)||(id.equals(BigInteger.ZERO))){
            throw new Exception("Order exception");
        }
        Order order = orderDao.getOrder(id);
        order.setStatus(Status.STATUS_REJECTED);
        if(!orderDao.updateOrder(order)){
            throw new Exception("Order status update exception");
        }
    }

    public void completeOrder(BigInteger id) throws Exception {
        if((id == null)||(id.equals(BigInteger.ZERO))){
            throw new Exception("Order exception");
        }
        Order order = orderDao.getOrder(id);
        if (order.getStatus().equals(Status.STATUS_OPEN)){
            throw new Exception("You can't complete just opened order");
        }
        order.setStatus(Status.STATUS_COMPLETED);
        order.setEndDate(Date.from(Instant.now()));
        if(!orderDao.updateOrder(order)){
            throw new Exception("Order status update exception");
        }
    }

    public void cancelOrder(BigInteger id) throws Exception {
        if((id == null)||(id.equals(BigInteger.ZERO))){
            throw new Exception("Order exception");
        }
        Order order = orderDao.getOrder(id);
        order.setStatus(Status.STATUS_CANCELED);
        if(!orderDao.updateOrder(order)){
            throw new Exception("Order status update exception");
        }
    }

    public void inProgressOrder(BigInteger id) throws Exception {
        if((id == null)||(id.equals(BigInteger.ZERO))){
            throw new Exception("Order exception");
        }
        Order order = orderDao.getOrder(id);
        order.setStatus(Status.STATUS_IN_PROGRES);
        if(!orderDao.updateOrder(order)){
            throw new Exception("Order status update exception");
        }
    }

    public void suspendOrder(BigInteger id) throws Exception {
        if((id == null)||(id.equals(BigInteger.ZERO))){
            throw new Exception("Order exception");
        }
        Order order = orderDao.getOrder(id);
        order.setStatus(Status.STATUS_SUSPENDED);
        if(!orderDao.updateOrder(order)){
            throw new Exception("Order status update exception");
        }
    }

    public void deleteOrder(BigInteger id) throws Exception {
        if((id == null)||(id.equals(BigInteger.ZERO))){
            throw new Exception("Wrong id");
        }
        if(!orderDao.deleteOrder(id)){
            throw new Exception("Order delete exception");
        }
    }

    public void addSpecialService(BigInteger orderId, Service service) throws Exception {
        if((orderId == null)||(orderId.equals(BigInteger.ZERO))){
            throw new Exception("Order exception");
        }
        if((service == null)||(service.getId() == null)||(service.getId().equals(BigInteger.ZERO))){
            throw new Exception("Service exception");
        }
        Order order = orderDao.getOrder(orderId);
        serviceDao.addNewService(service);
        serviceCollectionDao.createServiceCollection(new ServiceCollection(BigInteger.ZERO, order, service));
    }

    public BigInteger getNumberOfOrders() throws NotFoundException {
        try{
            return orderDao.getNumberOfOrders();
        }
        catch (Exception e){
            throw new NotFoundException("Can't query number of rows");
        }
    }

    public BigInteger getNumberOfOrders(Float price) throws NotFoundException {
        try{
            return orderDao.getNumberOfOrders(price);
        }
        catch (Exception e){
            throw new NotFoundException("Can't query number of rows");
        }
    }

    public BigInteger getNumberOfOrders(Status status) throws NotFoundException {
        try{
            return orderDao.getNumberOfOrders(status);
        }
        catch (Exception e){
            throw new NotFoundException("Can't query number of rows");
        }
    }

    public BigInteger getNumberOfOrders(String title) throws NotFoundException {
        try{
            return orderDao.getNumberOfOrders(title);
        }
        catch (Exception e){
            throw new NotFoundException("Can't query number of rows");
        }
    }
}
