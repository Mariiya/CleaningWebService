package com.opsu.dao.impl;

import com.opsu.dao.VendorDao;

import com.opsu.dao.mapper.VendorMapper;
import com.opsu.exceptions.EmptyDataBaseException;
import com.opsu.models.Vendor;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;


@Repository
public class VendorDaoImpl implements VendorDao {

    private static final Logger LOG = Logger.getLogger(VendorDaoImpl.class);
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public VendorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Vendor getVendorById(BigInteger id) throws NotFoundException {
        try {
            return jdbcTemplate.queryForObject(GET_VENDOR_BY_ID, new VendorMapper(), id);
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            throw new NotFoundException("Vendor not found  " + id);
        }

    }

    public boolean update(Vendor vendor) throws EmptyDataBaseException {
        try {
            jdbcTemplate.update(UPDATE_VENDOR, vendor.getFirstName(), vendor.getLastName(), vendor.getId());
            jdbcTemplate.update("commit");
        } catch (DataAccessException e) {
            throw new EmptyDataBaseException("Error during Vendor saving");
        }
        return true;
    }

    public boolean save(Vendor vendor) throws EmptyDataBaseException {
        try {
            jdbcTemplate.update(CREATE_VENDOR, vendor.getFirstName(), vendor.getLastName(), vendor.getIndividual(), vendor.getId());
        } catch (DataAccessException e) {
            System.out.println(e);
            LOG.error(e.getMessage(),e);
            throw new EmptyDataBaseException("Error during Vendor saving");
        }
        return true;
    }

}
