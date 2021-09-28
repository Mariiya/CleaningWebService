package com.opsu.dao.mapper;
import com.opsu.models.Vendor;
import org.springframework.jdbc.core.RowMapper;


import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

    public class VendorMapper implements RowMapper<Vendor> {
        public Vendor mapRow(ResultSet resultSet, int i) throws SQLException {
            BigInteger vendorId =BigInteger.valueOf(resultSet.getLong("vendorId"));
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            Boolean individual = resultSet.getBoolean("individual"); //здесь упадёт, так как java не сможет преобразовать 0 или 1 в false или true соответственно
            return new Vendor( vendorId, firstName, lastName, individual);
        }
    }


