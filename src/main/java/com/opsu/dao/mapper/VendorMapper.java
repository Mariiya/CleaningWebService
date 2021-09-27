package com.opsu.dao.mapper;
import com.opsu.models.Vendor;
import com.opsu.models.enumeration.Role;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class VendorMapper {
    private  String firstName ;
    private  String lastName ;
    private  Boolean individual;


    public class VendorMapper implements RowMapper<Vendor> {
        public Vendor mapRow(ResultSet resultSet, int i) throws SQLException {
            String firstName = resultSet.getString("firstName"));
            String lastName = resultSet.getString("lastName");
            Boolean individual = resultSet.getBoolean("individual");
            return new Vendor( firstName, lastName, individual);
        }
    }
}

