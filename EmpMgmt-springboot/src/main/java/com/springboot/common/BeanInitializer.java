package com.springboot.common;

import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


@Configuration
@PropertySource("classpath:application-dev.properties")
public class BeanInitializer {
	@Autowired
	Environment env;
	
	@SuppressWarnings("deprecation")
	@Bean("postgresqlDataSource")
	public DataSource getPostgresqlDataSource() {
		String prefix="dao.datasource.postgresql.";
		PGSimpleDataSource dataSource=new PGSimpleDataSource();
		dataSource.setServerName(env.getProperty(prefix+"servername"));
		dataSource.setPortNumber(Integer.parseInt(env.getProperty(prefix +"port")));
		dataSource.setDatabaseName(env.getProperty(prefix+"dbname"));
		dataSource.setUser(env.getProperty(prefix+"user"));
		dataSource.setPassword(env.getProperty(prefix+"password"));
		return dataSource;
		
	}

}