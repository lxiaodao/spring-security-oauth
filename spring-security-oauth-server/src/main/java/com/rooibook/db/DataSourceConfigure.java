/**
 * 
 */
package com.rooibook.db;

import java.util.HashMap;

import javax.sql.DataSource;


import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.bitronix.PoolingDataSourceBean;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author yang
 *
 */
@Configuration
public class DataSourceConfigure {
	
	@Primary
	@ConfigurationProperties(prefix = "sso.datasource")
	public DataSourceProperties devDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Primary
    @Bean(name = "ssoDataSource")
	public DataSource userDataSource() {
		// return DataSourceBuilder.create().build();
		// .type(HikariDataSource.class)
		return devDataSourceProperties().initializeDataSourceBuilder().build();
	}

}

