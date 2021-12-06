package com.opsu.services;

import com.opsu.dao.VendorDao;


import com.opsu.exceptions.EmptyDataBaseException;
import com.opsu.models.User;
import com.opsu.models.Vendor;
import com.opsu.secutity.services.UserDetailsImpl;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.math.BigInteger;
/**
 * Class with business logic of vendor creation, update and search
 */
@Service
public class VendorService {
    private final VendorDao vendorDao;
    private final AuthorizationService authorizationService;
    private NotificationService notificationService;

    @Autowired
    public VendorService(VendorDao vendorDao, AuthorizationService authorizationService, NotificationService notificationService) {
        this.vendorDao = vendorDao;
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
    }

    /**
     * search vendor by id
     * @param id
     * @return
     * @throws NotFoundException
     */
    public Vendor getVendorById(BigInteger id) throws NotFoundException {
        if ((id == null) || (id.equals(BigInteger.ZERO))) {
            throw new NumberFormatException("Wrong id input");
        }
        return vendorDao.getVendorById(id);
    }

    /**
     * create new vendor
     * @param vendorRequest
     * @return
     * @throws IOException
     * @throws MessagingException
     * @throws EmptyDataBaseException
     */
    public boolean create(Vendor vendorRequest) throws IOException, MessagingException, EmptyDataBaseException {
        Vendor vendor = new Vendor(vendorRequest.getId(),
                vendorRequest.getPhoneNumber(),
                vendorRequest.getEmail(),
                vendorRequest.getPassword(),
                vendorRequest.getRole(),
                vendorRequest.getFirstName(),
                vendorRequest.getLastName(),
                vendorRequest.getIndividual());
        return vendorDao.save(vendor);
    }

    /**
     * update vendor
     * @param updater
     * @param vendor
     * @return
     * @throws NotFoundException
     * @throws EmptyDataBaseException
     */
    public Vendor update(UserDetailsImpl updater, Vendor vendor) throws NotFoundException, EmptyDataBaseException {
        if (!updater.getId().equals(vendor.getId())) {
            throw new PermissionDeniedDataAccessException("Can not change this user password", new IllegalAccessError());
        }
        Vendor vendorfromDB = vendorDao.getVendorById(updater.getId());
        vendorfromDB.setLastName(vendor.getLastName());
        vendorfromDB.setFirstName(vendor.getFirstName());
        User user = authorizationService.updateUser(updater, vendor);
        vendorfromDB.setEmail(user.getEmail());
        vendorfromDB.setPhoneNumber(user.getPhoneNumber());
        vendorDao.update(vendorfromDB);
        return vendorfromDB;
    }

}
