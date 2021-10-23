package com.opsu.services;

import com.opsu.dao.VendorDao;
import com.opsu.models.User;
import com.opsu.models.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class VendorService {  private final VendorDao vendorDao;

    @Autowired
    public VendorService (VendorDao vendorDao) {
        this.vendorDao = vendorDao;
    }

    public void getVendorById(BigInteger id) {

    }

    public void create(Vendor vendor) {

    }

    public void update(Vendor vendor) {

    }

}
