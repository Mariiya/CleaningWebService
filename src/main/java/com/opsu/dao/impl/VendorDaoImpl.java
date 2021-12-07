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

/**
 * An implementation of an interface VendorDao.
 * @author group 183
 * @version 2.1
 */
@Repository
public class VendorDaoImpl implements VendorDao {

    /**Logger for creating log records*/
    private static final Logger LOG = Logger.getLogger(VendorDaoImpl.class);
    private final JdbcTemplate jdbcTemplate;

    /**required constructor*/
    @Autowired
    public VendorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    /** search vendor by id
     * @param id vendor */
    @Override
    public Vendor getVendorById(BigInteger id) throws NotFoundException {
        try {
            return jdbcTemplate.queryForObject(GET_VENDOR_BY_ID, new VendorMapper(), id);
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            throw new NotFoundException("Vendor not found  " + id);
        }

    }
    /**creating new vendor
     * @param vendor vendor*/
    public boolean update(Vendor vendor) throws EmptyDataBaseException {
        try {
            jdbcTemplate.update(UPDATE_VENDOR, vendor.getFirstName(), vendor.getLastName(), vendor.getId());
            jdbcTemplate.update("commit");
        } catch (DataAccessException e) {
            throw new EmptyDataBaseException("Error during Vendor saving");
        }
        return true;
    }
    /** updating already existing vendor
     * @param vendor vendor*/
    public boolean save(Vendor vendor) throws EmptyDataBaseException {
        try {
            jdbcTemplate.update(CREATE_VENDOR, vendor.getFirstName(), vendor.getLastName(), vendor.getIndividual(), vendor.getId());
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(),e);
            throw new EmptyDataBaseException("Error during Vendor saving");
        }
        return true;
    }

}
