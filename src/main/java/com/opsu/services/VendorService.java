package com.opsu.services;

import com.opsu.dao.VendorDao;
import com.opsu.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class VendorService {  private final VendorDao vendorDao;

    @Autowired
    public VendorService (VendorDao vendorDao) {
        this.vendorDao = vendorDao;
    }


    public void create(VendorDao vendorDao) {

    }
    public void update(VendorDao vendorDao) {

    }


}
