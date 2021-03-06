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
import java.util.*;
/**
 * Class with business logic of order creation, update and search
 */
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

    /** create new order
     * @param order oder
     * @throws Exception
     */
    public void createOrder(Order order) throws Exception {
        if ((order == null)) {
            throw new Exception("Order is null");
        } else if (order.getId() == null || order.getId().equals(BigInteger.ZERO)) {
            throw new Exception("Order ID exception");
        } else if (order.getTitle().isEmpty()) {
            throw new Exception("Order Title is empty");
        } else if (order.getPrice() <= 0) {
            throw new Exception("Order Price cannot be zero or less");
        } else if (order.getAddress().isEmpty()) {
            throw new Exception("Order Address is empty");
        } else if (order.getStatus() == null) {
            throw new Exception("Order Status is null");
        } else if (order.getStartDate() == null) {
            throw new Exception("Order Start Date is null");
        } else if (order.getServices().size() == 0) {
            throw new Exception("Order hasn't any service");
        }

        Consumer consumer = consumerDao.getConsumerById(order.getConsumer().getId());
        Collection<Service> serviceCollection = order.getServices();

        order.setConsumer(consumer);

        if (!orderDao.createOrder(order)) {
            throw new Exception("Order create exception");
        }

        order = orderDao.getOrderId(order);
        order.setServices(serviceCollection);
        for (Service service : order.getServices()) {
            serviceCollectionDao.createServiceCollection(new ServiceCollection(BigInteger.ZERO, order, service));
        }
    }

    /**
     * search order by id
     * @param id order
     * @throws Exception
     */
    public Order getOrder(BigInteger id) throws Exception {
        if ((id == null) || (id.equals(BigInteger.ZERO))) {
            throw new NumberFormatException("Wrong id input");
        }
        Order order = orderDao.getOrder(id);
        if (order == null) {
            throw new EmptyDataBaseException("Order is null");
        }

        if (order.getVendor() != null && order.getVendor().getEmail() != null) {
            Vendor vendor = vendorDao.getVendorById(order.getVendor().getId());
            order.setVendor(vendor);
        }
        Consumer consumer = consumerDao.getConsumerById(order.getConsumer().getId());
        order.setConsumer(consumer);

        Collection<ServiceCollection> serviceCollections = serviceCollectionDao.getServiceCollectionsByOrder(order);
        if (serviceCollections.size() == 0) {
            throw new EmptyDataBaseException("ServiceCollection is null");
        }
        ArrayList<Service> services = new ArrayList<>();
        for (ServiceCollection collection : serviceCollections) {
            services.add(serviceDao.getService(collection.getService().getId()));
        }
        if (services.size() == 0) {
            throw new EmptyDataBaseException("Services is null");
        }
        order.setServices(services);
        return order;
    }

    /**
     * show all orders
     * @param page order
     * @throws Exception
     */
    public Collection<Order> getOrders(int page) throws Exception {
        Collection<Order> orderCollection = orderDao.getOrders(page);
        if (orderCollection.size() == 0) {
            throw new EmptyDataBaseException("Order list is empty");
        }
        for (Order order : orderCollection) {
            if (Status.STATUS_IN_PROGRES.equals(order.getStatus())) {
                Vendor vendor = vendorDao.getVendorById(order.getVendor().getId());
                order.setVendor(vendor);
            }
            Consumer consumer = consumerDao.getConsumerById(order.getConsumer().getId());

            order.setConsumer(consumer);
            Collection<ServiceCollection> serviceCollections = serviceCollectionDao.getServiceCollectionsByOrder(order);
            if (serviceCollections.size() == 0) {
                throw new EmptyDataBaseException("ServiceCollection is null");
            }
            ArrayList<Service> services = new ArrayList<>();
            for (ServiceCollection collection : serviceCollections) {
                services.add(serviceDao.getService(collection.getService().getId()));
            }
            if (services.size() == 0) {
                throw new EmptyDataBaseException("Services is null");
            }
            order.setServices(services);
        }

        return orderCollection;
    }

    /**
     * search and group order by serviceid
     * @param serviceId order
     * @param page order
     * @throws Exception
     */
    public Collection<Order> getOrders(BigInteger serviceId, int page) throws Exception {
        if (serviceId == null || serviceId.equals(BigInteger.ZERO)) {
            throw new NumberFormatException("Wrond id input");
        }
        Collection<Order> orderCollection = orderDao.getOrders(serviceId, page);

        return orderCollection;
    }

    /**
     * search and group order by price
     * @param minPrice
     * @param maxPrice
     * @param page
     * @throws Exception
     */
    public Collection<Order> getOrders(Float minPrice, Float maxPrice, int page) throws Exception {
        if (minPrice < 0) {
            minPrice = 0f;
        }
        if (maxPrice <= 0) {
            maxPrice = Float.MAX_VALUE;
        }
        Collection<Order> orderCollection = orderDao.getOrders(minPrice, maxPrice, page);
        if (orderCollection.size() == 0) {
            throw new EmptyDataBaseException("Order list is empty");
        }
        for (Order order : orderCollection) {
            if (order.getVendor() != null && order.getVendor().getEmail() != null) {
                Vendor vendor = vendorDao.getVendorById(order.getVendor().getId());
                order.setVendor(vendor);
            }
            Consumer consumer = consumerDao.getConsumerById(order.getConsumer().getId());
            order.setConsumer(consumer);
            Collection<ServiceCollection> serviceCollections = serviceCollectionDao.getServiceCollectionsByOrder(order);
            if (serviceCollections.size() == 0) {
                throw new EmptyDataBaseException("ServiceCollection is null");
            }
            ArrayList<Service> services = new ArrayList<>();
            for (ServiceCollection collection : serviceCollections) {
                services.add(serviceDao.getService(collection.getService().getId()));
            }
            if (services.size() == 0) {
                throw new EmptyDataBaseException("Services is null");
            }
            order.setServices(services);
        }

        return orderCollection;
    }

    /**
     * search and group order by title
     * @param title
     * @param page
     * @throws Exception
     */
    public Collection<Order> getOrders(String title, int page) throws Exception {
        if (title.isEmpty()) {
            throw new Exception("Name is empty");
        }
        Collection<Order> orderCollection = orderDao.getOrders(title, page);
        if (orderCollection.size() == 0) {
            throw new EmptyDataBaseException("Order list is empty");
        }
        for (Order order : orderCollection) {
            if (order.getVendor() != null && order.getVendor().getEmail() != null) {
                Vendor vendor = vendorDao.getVendorById(order.getVendor().getId());
                order.setVendor(vendor);
            }
            Consumer consumer = consumerDao.getConsumerById(order.getConsumer().getId());
            order.setConsumer(consumer);
            Collection<ServiceCollection> serviceCollections = serviceCollectionDao.getServiceCollectionsByOrder(order);
            if (serviceCollections.size() == 0) {
                throw new EmptyDataBaseException("ServiceCollection is null");
            }
            ArrayList<Service> services = new ArrayList<>();
            for (ServiceCollection collection : serviceCollections) {
                services.add(serviceDao.getService(collection.getService().getId()));
            }
            if (services.size() == 0) {
                throw new EmptyDataBaseException("Services is null");
            }
            order.setServices(services);
        }

        return orderCollection;
    }

    /**
     * search and group order by status
     * @param status
     * @param page
     * @throws Exception
     */
    public Collection<Order> getOrders(Status status, int page) throws Exception {
        if (status == null) {
            throw new Exception("Status is empty");
        }
        Collection<Order> orderCollection = orderDao.getOrders(status, page);
        if (orderCollection.size() == 0) {
            throw new EmptyDataBaseException("Order list is empty");
        }
        for (Order order : orderCollection) {
            if (order.getVendor() != null && order.getVendor().getEmail() != null) {
                Vendor vendor = vendorDao.getVendorById(order.getVendor().getId());
                order.setVendor(vendor);
            }
            Consumer consumer = consumerDao.getConsumerById(order.getConsumer().getId());
            order.setConsumer(consumer);
            Collection<ServiceCollection> serviceCollections = serviceCollectionDao.getServiceCollectionsByOrder(order);
            if (serviceCollections.size() == 0) {
                throw new EmptyDataBaseException("ServiceCollection is null");
            }
            ArrayList<Service> services = new ArrayList<>();
            for (ServiceCollection collection : serviceCollections) {
                services.add(serviceDao.getService(collection.getService().getId()));
            }
            if (services.size() == 0) {
                throw new EmptyDataBaseException("Services is null");
            }
            order.setServices(services);
        }

        return orderCollection;
    }

    /**
     * search order by id
     * @param id
     * @throws Exception
     */
    public Collection<Order> getOrders(BigInteger id) throws Exception {
        if ((id == null) || (id.equals(BigInteger.ZERO))) {
            throw new Exception("Order exception");
        }
        Collection<Order> orderCollection = orderDao.getOrders(id);
        if (orderCollection.size() == 0) {
            throw new EmptyDataBaseException("Order list is empty");
        }
        for (Order order : orderCollection) {

            try {
                if (order.getVendor() != null && order.getVendor().getId() != null) {
                    Vendor vendor = vendorDao.getVendorById(order.getVendor().getId());
                    order.setVendor(vendor);
                }
            } catch (Exception e) {

            }

            Consumer consumer = consumerDao.getConsumerById(order.getConsumer().getId());
            order.setConsumer(consumer);

            Collection<ServiceCollection> serviceCollections = serviceCollectionDao.getServiceCollectionsByOrder(order);
            if (serviceCollections.size() == 0) {
                throw new EmptyDataBaseException("ServiceCollection is null");
            }
            ArrayList<Service> services = new ArrayList<>();
            for (ServiceCollection collection : serviceCollections) {
                services.add(serviceDao.getService(collection.getService().getId()));
            }
            if (services.size() == 0) {
                throw new EmptyDataBaseException("Services is null");
            }
            order.setServices(services);
        }

        return orderCollection;
    }

    /**
     * search and group order by several parametrs
     * @param minPrice
     * @param maxPrice
     * @param title
     * @param status
     * @param serviceId
     * @param page
     * @throws Exception
     */
    public Collection<Order> getOrders(Float minPrice, Float maxPrice, String title, Status status, BigInteger serviceId, int page) throws Exception {
        Service service = null;
        Collection<Order> orderCollection = null;
        if (serviceId != null && !serviceId.equals(BigInteger.ZERO)) {
            service = serviceDao.getService(serviceId);
        }
        try {
            orderCollection = orderDao.getOrders(minPrice, maxPrice, title, status, service, page);
            Set<Order> result = new HashSet<>();
            for (Order order : orderCollection) {
                Collection<ServiceCollection> serviceCollections = serviceCollectionDao.getServiceCollectionsByOrder(order);
                if (serviceCollections.size() == 0) {
                    throw new EmptyDataBaseException("ServiceCollection is null");
                }
                ArrayList<Service> services = new ArrayList<>();
                for (ServiceCollection collection : serviceCollections) {
                    services.add(serviceDao.getService(collection.getService().getId()));
                }
                if(order.getVendor().getId()!=null && order.getVendor().getId().intValue()!=0 && order.getVendor().getEmail()==null){
                    order.setVendor(vendorDao.getVendorById(order.getVendor().getId()));
                }
                if(order.getConsumer().getId()!=null && order.getConsumer().getId().intValue()!=0 && order.getConsumer().getEmail()==null){
                    order.setConsumer(consumerDao.getConsumerById(order.getConsumer().getId()));
                }
                order.setServices(services);

                for(Service s: order.getServices()){
                    if(s.getId().equals(serviceId) || Objects.requireNonNull(serviceId).intValue() == 0){
                        result.add(order);
                    }
                }
            }
           return result;
        } catch (NotFoundException e) {
            logger.error(e.getMessage());
            throw new EmptyDataBaseException("Orders not found");
        }
    }

    /**
     * updating already existing order
     * @param order
     * @throws Exception
     */
    public void updateOrder(Order order) throws Exception {
        if (!order.getStatus().equals(Status.STATUS_OPEN)) {
            throw new IllegalArgumentException("You can't update this Order");
        }
        if (!orderDao.updateOrder(order)) {
            throw new IllegalArgumentException("Order update exception");
        }
    }

    /**
     * shows who was assigned for order
     * @param orderId
     * @param vendorId
     * @throws Exception
     */
    public Order assignOrder(BigInteger orderId, BigInteger vendorId) throws Exception {
        if ((orderId == null) || (orderId.equals(BigInteger.ZERO))) {
            throw new Exception("Order exception");
        }
        if ((vendorId == null) || (vendorId.equals(BigInteger.ZERO))) {
            throw new Exception("Consumer exception");
        }

        Order order = orderDao.getOrder(orderId);
        order.setStatus(Status.STATUS_IN_PROGRES);
        if (order.getConsumer().getId() != null) {
            order.setConsumer(consumerDao.getConsumerById(order.getConsumer().getId()));
        }
        Vendor vendor = vendorDao.getVendorById(vendorId);
        if ((vendor == null) || (order == null)) {
            throw new Exception("Order or Vendor exception");
        }
        order.setVendor(vendor);
        if (!orderDao.updateOrder(order)) {
            throw new Exception("Order assign exception");
        }
        return order;
    }

    /**
     * change cost
     * @param id
     * @param price
     * @throws Exception
     */
    public void changePrice(BigInteger id, Float price) throws Exception {
        if ((id == null) || (id.equals(BigInteger.ZERO))) {
            throw new Exception("Order exception");
        }
        if (price <= 0) {
            throw new NumberFormatException("Order price less than zero");
        }
        Order order = orderDao.getOrder(id);
        order.setPrice(price);
        if (!orderDao.updateOrder(order)) {
            throw new Exception("Order status update exception");
        }
    }

    /**
     * order rejected by vendor
     * @param id
     * @throws Exception
     */
    public void rejectOrder(BigInteger id) throws Exception {
        if ((id == null) || (id.equals(BigInteger.ZERO))) {
            throw new Exception("Order exception");
        }
        Order order = orderDao.getOrder(id);
        order.setStatus(Status.STATUS_REJECTED);
        if (!orderDao.updateOrder(order)) {
            throw new Exception("Order status update exception");
        }
    }

    /**
     * order has been finished successfully
     * @param id
     * @throws Exception
     */
    public void completeOrder(BigInteger id) throws Exception {
        if ((id == null) || (id.equals(BigInteger.ZERO))) {
            throw new Exception("Order exception");
        }
        Order order = orderDao.getOrder(id);
        if (order.getStatus().equals(Status.STATUS_OPEN)) {
            throw new Exception("You can't complete just opened order");
        }
        order.setStatus(Status.STATUS_COMPLETED);
        order.setEndDate(Date.from(Instant.now()));
        if (!orderDao.updateOrder(order)) {
            throw new Exception("Order status update exception");
        }
    }

    /**
     * order has been canseled by consumer
     * @param id
     * @throws Exception
     */
    public void cancelOrder(BigInteger id) throws Exception {
        if ((id == null) || (id.equals(BigInteger.ZERO))) {
            throw new Exception("Order exception");
        }
        Order order = orderDao.getOrder(id);
        order.setStatus(Status.STATUS_CANCELED);
        if (!orderDao.updateOrder(order)) {
            throw new Exception("Order status update exception");
        }
    }

    /**
     * order is ongoing
     * @param id order
     * @throws Exception
     */
    public void inProgressOrder(BigInteger id) throws Exception {
        if ((id == null) || (id.equals(BigInteger.ZERO))) {
            throw new Exception("Order exception");
        }
        Order order = orderDao.getOrder(id);
        order.setStatus(Status.STATUS_IN_PROGRES);
        if (!orderDao.updateOrder(order)) {
            throw new Exception("Order status update exception");
        }
    }

    /**
     * order has been suspended by consumer
     * @param id order
     * @throws Exception
     */
    public void suspendOrder(BigInteger id) throws Exception {
        if ((id == null) || (id.equals(BigInteger.ZERO))) {
            throw new Exception("Order exception");
        }
        Order order = orderDao.getOrder(id);
        order.setStatus(Status.STATUS_SUSPENDED);
        if (!orderDao.updateOrder(order)) {
            throw new Exception("Order status update exception");
        }
    }

    /**
     * delete order
     * @param id order
     * @throws Exception
     */
    public void deleteOrder(BigInteger id) throws Exception {
        if ((id == null) || (id.equals(BigInteger.ZERO))) {
            throw new Exception("Wrong id");
        }
        Order order = orderDao.getOrder(id);
        Collection<ServiceCollection> serviceCollections = serviceCollectionDao.getServiceCollectionsByOrder(order);
        for (ServiceCollection idS : serviceCollections) {
            serviceCollectionDao.deleteServiceCollection(idS);
        }
        if (!orderDao.deleteOrder(id)) {
            throw new Exception("Order delete exception");
        }
    }

    /**
     * consumer wants additional service
     * @param orderId
     * @param service
     * @throws Exception
     */
    public void addSpecialService(BigInteger orderId, Service service) throws Exception {
        if ((orderId == null) || (orderId.equals(BigInteger.ZERO))) {
            throw new Exception("Order exception");
        }
        if ((service == null) || (service.getId() == null) || (service.getId().equals(BigInteger.ZERO))) {
            throw new Exception("Service exception");
        }
        Order order = orderDao.getOrder(orderId);
        serviceDao.addNewService(service);
        serviceCollectionDao.createServiceCollection(new ServiceCollection(BigInteger.ZERO, order, service));
    }

    /**
     * Request for counting amount of orders
     * @throws NotFoundException
     */
    public BigInteger getNumberOfOrders() throws NotFoundException {
        try {
            return orderDao.getNumberOfOrders();
        } catch (Exception e) {
            throw new NotFoundException("Can't query number of rows");
        }
    }

    /**
     * Request for counting amount of orders by price
     * @param price
     * @return
     * @throws NotFoundException
     */
    public BigInteger getNumberOfOrders(Float price) throws NotFoundException {
        try {
            return orderDao.getNumberOfOrders(price);
        } catch (Exception e) {
            throw new NotFoundException("Can't query number of rows");
        }
    }

    /**
     * Request for counting amount of orders by status
     * @param status
     * @return
     * @throws NotFoundException
     */
    public BigInteger getNumberOfOrders(Status status) throws NotFoundException {
        try {
            return orderDao.getNumberOfOrders(status);
        } catch (Exception e) {
            throw new NotFoundException("Can't query number of rows");
        }
    }

    /**
     * Request for counting amount of orders title
     * @param title
     * @return
     * @throws NotFoundException
     */
    public BigInteger getNumberOfOrders(String title) throws NotFoundException {
        try {
            return orderDao.getNumberOfOrders(title);
        } catch (Exception e) {
            throw new NotFoundException("Can't query number of rows");
        }
    }

    /**
     * Request for counting amount of orders service id
     * @param serviceId
     * @return
     * @throws NotFoundException
     */
    public BigInteger getNumberOfOrders(BigInteger serviceId) throws NotFoundException {
        try {
            return orderDao.getNumberOfOrders(serviceId);
        } catch (Exception e) {
            throw new NotFoundException("Can't query number of rows");
        }
    }

    /**
     * Request for counting amount of orders several parameters
     * @param minPrice
     * @param maxPrice
     * @param title
     * @param status
     * @param serviceId
     * @return
     * @throws NotFoundException
     */
    public BigInteger getNumberOfOrders(Float minPrice, Float maxPrice, String title, Status status, BigInteger serviceId) throws NotFoundException {
        try {
            Service service = null;
            if (serviceId != null && !serviceId.equals(BigInteger.ZERO)) {
                service = serviceDao.getService(serviceId);
            }
            BigInteger result =  orderDao.getNumberOfOrders(minPrice, maxPrice, title, status, service);
            return result;
        } catch (Exception e) {
            throw new NotFoundException("Can't query number of rows");
        }
    }
}
