package com.opsu.config;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@PropertySource({"classpath:application.properties"})
public class DataSourceConfig {

    @Autowired
    Environment environment;

    @Bean
    public DataSource dataSource() throws SQLException {
        PoolDataSource dataSource = PoolDataSourceFactory.getPoolDataSource();

        // Set the connection factory first before all other properties
        dataSource.setConnectionFactoryClassName(environment.getProperty("spring.datasource.oracleucp.connection-factory-class-name"));
        dataSource.setURL(environment.getProperty("spring.datasource.oracleucp.u-r-l"));
        dataSource.setUser(environment.getProperty("spring.datasource.oracleucp.user"));
        dataSource.setPassword(environment.getProperty("spring.datasource.oracleucp.password"));
        dataSource.setConnectionPoolName("JDBC_UCP_POOL");

        // Default is 0. Set the initial number of connections to be created
        // when UCP is started.
        dataSource.setInitialPoolSize(5);

        // Default is 0. Set the minimum number of connections
        // that is maintained by UCP at runtime.
        dataSource.setMinPoolSize(5);

        // Default is Integer.MAX_VALUE (2147483647). Set the maximum number of
        // connections allowed on the connection pool.
        dataSource.setMaxPoolSize(20);

        // Default is 30secs. Set the frequency in seconds to enforce the timeout
        // properties. Applies to inactiveConnectionTimeout(int secs),
        // AbandonedConnectionTimeout(secs)& TimeToLiveConnectionTimeout(int secs).
        // Range of valid values is 0 to Integer.MAX_VALUE. .
        dataSource.setTimeoutCheckInterval(5);

        // Default is 0. Set the maximum time, in seconds, that a
        // connection remains available in the connection pool.
        dataSource.setInactiveConnectionTimeout(10);

        // Set the JDBC connection properties after pool has been created
        Properties connProps = new Properties();
        connProps.setProperty("fixedString", "false");
        connProps.setProperty("remarksReporting", "false");
        connProps.setProperty("restrictGetTables", "false");
        connProps.setProperty("includeSynonyms", "false");
        connProps.setProperty("defaultNChar", "false");
        connProps.setProperty("AccumulateBatchResult", "false");

        // JDBC connection properties will be set on the provided
        // connection factory.
        dataSource.setConnectionProperties(connProps);

        return dataSource;
    }

}
