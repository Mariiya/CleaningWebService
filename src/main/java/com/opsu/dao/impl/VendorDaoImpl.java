package com.opsu.dao.impl;

import com.opsu.dao.VendorDao;
import com.opsu.dao.mapper.VendorMapper;
import com.opsu.models.User;
import com.opsu.models.Vendor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class VendorDaoImpl implements VendorDao {

    private static final Logger LOG = Logger.getLogger(VendorDaoImpl.class);
    //Утилита от Спринга для работы с БД
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public VendorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Vendor findVendorByLastName(String lastName) {
        return jdbcTemplate.queryForObject(GET_VENDOR_BY_LAST_NAME, new VendorMapper(),lastName, lastName);
    }

    public void save(Vendor vendor) { try {
        jdbcTemplate.update(CREATE_VENDOR, vendor.getLastName(), vendor.getFirstName(), vendor.getIndividual());
    } catch (DataAccessException e) {
        LOG.error(e.getMessage(), e);
    }
        //save
    }

    public void update(Vendor vendor) {
        //save
    }


}
