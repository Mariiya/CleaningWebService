package com.opsu.dao.impl;

import com.opsu.dao.VendorDao;
import com.opsu.dao.mapper.VendorMapper;
import com.opsu.models.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class VendorDaoImpl implements VendorDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public VendorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Vendor findByUsernameOrEmail(String lastName) {
        return jdbcTemplate.queryForObject(GET_VENDOR_BY_LAST_NAME, new VendorMapper(),lastName, lastName);
    }

    public void save(Vendor vendor) {
        //save
    }

    @Override
    public Boolean isIndividual(String individual) {
        Integer res = jdbcTemplate.queryForObject(IS_VENDOR_INDIVIDUAL, Integer.class);
        if (res != null) {
            return res != 0;
        }
        return true;
    }
}
