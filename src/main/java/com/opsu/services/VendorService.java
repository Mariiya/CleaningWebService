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

    public Vendor getVendorById(BigInteger id) {
        if ((id==null)||(id.equals(BigInteger.ZERO))){
            throw new NumberFormatException("Wrong id input");
        }
        return null;


    }

    public void create(Vendor vendor) {

    }
    public Vendor  findVendorByLastName (String lastname) throws Exception {
        return vendorDao.findVendorByLastName (lastname);
    }


    public void update(Vendor vendor) {

    }

}
