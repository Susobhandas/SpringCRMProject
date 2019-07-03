package com.luv2code.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.luv2code.springsecurity.demo")
@EnableTransactionManagement
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig implements WebMvcConfigurer{

	@Autowired
	private Environment env;
	
	Logger myLogger=Logger.getLogger(getClass().getName());
	
	
	@Bean
	public DataSource securityDataSource() {
		ComboPooledDataSource securityDataSource=new ComboPooledDataSource();
		
		//setting up datasource jdbc properties
		
		myLogger.info("Driver Class====> "+env.getProperty("jdbc.driver"));
		
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		
		myLogger.info("TNS Connection url====> "+env.getProperty("jdbc.url"));
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		
		myLogger.info("UserName====> "+env.getProperty("jdbc.user"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		
		
		myLogger.info("Password====> "+env.getProperty("jdbc.password"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));
		
		//setting up connection pool property
		securityDataSource.setInitialPoolSize(getCalculatedValue("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getCalculatedValue("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getCalculatedValue("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getCalculatedValue("connection.pool.maxIdleTime"));
		
		return securityDataSource;
	}
	
	
	private Integer getCalculatedValue(String entry) {
		
		Integer value=Integer.parseInt(env.getProperty(entry));
		
		
		
		return value;
		
	}
	
	public Properties getHibernateProperties() {
		
		Properties props=new Properties();
		
		props.setProperty("hibernate.dialect",env.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.show_sql",env.getProperty("hibernate.show_sql"));
		return props;
		
		
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		
		LocalSessionFactoryBean sessionFactory=new LocalSessionFactoryBean();
		
		sessionFactory.setDataSource(securityDataSource());
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		sessionFactory.setHibernateProperties(getHibernateProperties());
		
		return sessionFactory;
		
	}
	
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager=new HibernateTransactionManager();
		
		transactionManager.setSessionFactory(sessionFactory);
		
		return transactionManager;
		
	}
	
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		
		return viewResolver;
	}


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.
		addResourceHandler("/resources/**")
		.addResourceLocations("/resources/")
		.setCachePeriod(3600)
		.resourceChain(true)
		.addResolver(new PathResourceResolver());
		
	}
	
	
	
}
