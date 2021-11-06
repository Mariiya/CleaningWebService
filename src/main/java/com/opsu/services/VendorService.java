package com.opsu.services;

import com.opsu.dao.VendorDao;


import com.opsu.models.Vendor;
import com.opsu.secutity.services.UserDetailsImpl;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.math.BigInteger;

@Service
public class VendorService {  private final VendorDao vendorDao;
    private final AuthorizationService authorizationService ;
    private NotificationService notificationService;

    @Autowired
    public VendorService (VendorDao vendorDao,AuthorizationService authorizationService,NotificationService notificationService) {
        this.vendorDao = vendorDao;
        this.authorizationService = authorizationService;
        this.notificationService=notificationService;
    }

    public Vendor getVendorById(BigInteger id) throws NotFoundException {
        if ((id==null)||(id.equals(BigInteger.ZERO))){
            throw new NumberFormatException("Wrong id input");
        }
        return vendorDao.getVendorById(id);


    }


        public void create(Vendor vendorRequest) throws IOException, MessagingException {
            Vendor vendor = new Vendor(vendorRequest.getId(),
                    vendorRequest.getPhoneNumber(),
                    vendorRequest.getEmail(),
                    vendorRequest.getPassword(),
                    vendorRequest.getRole(),
                    vendorRequest.getFirstName(),
                    vendorRequest.getLastName(),
                    vendorRequest.getIndividual());
            vendorDao.save(vendor);
            notificationService.sendRegistrationNotification(vendorRequest);
        }

    public Vendor  findVendorByLastName (String lastname) throws Exception {
        return vendorDao.findVendorByLastName (lastname);
    }


    public boolean update(UserDetailsImpl updater, Vendor vendor) throws NotFoundException {
        if (!updater.getId().equals(vendor.getId())) {
            throw new PermissionDeniedDataAccessException("Can not change this user password", new IllegalAccessError());
        }
        Vendor vendorfromDB = vendorDao.getVendorById(updater.getId());
        vendorfromDB.setLastName(vendor.getLastName());
        vendorfromDB.setFirstName(vendor.getFirstName());
        authorizationService.updateUser(updater,vendorfromDB);
        vendorDao.save(vendorfromDB);
        return true;
    }

}
