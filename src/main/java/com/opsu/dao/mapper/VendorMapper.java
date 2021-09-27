package com.opsu.dao.mapper;
import com.opsu.models.Vendor;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

    public class VendorMapper implements RowMapper<Vendor> {
        public Vendor mapRow(ResultSet resultSet, int i) throws SQLException {
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            Boolean individual = resultSet.getBoolean("individual");
            return new Vendor( firstName, lastName, individual);
        }
    }


