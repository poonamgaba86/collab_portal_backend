package com.config;

import java.util.Properties;

import javax.sql.DataSource;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.DaoImpl.UserDaoImpl;
import com.dao.UserDAO;
import com.model.BlogComment;
import com.model.BlogPost;
import com.model.Friend;
import com.model.Job;
import com.model.ProfilePicture;
import com.model.User;

//import com.DaoImpl.UserDaoImpl;
//import com.dao.UserDAO;




@Configuration
@ComponentScan(basePackages = "com.*")
@EnableTransactionManagement
@EnableWebMvc
public class AppConfig
{
	@Bean(name = "dataSource")
	public DataSource getDataSource() 
	{
	   // BasicDataSource dataSource = new BasicDataSource();
		DriverManagerDataSource dataSource = new DriverManagerDataSource(); // modified 
	    dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	    dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:orcl1");
	
	    dataSource.setUsername("pg1");
	    dataSource.setPassword("password1");
	    Properties connectionProperties = new Properties();
		
		connectionProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		connectionProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		connectionProperties.setProperty("hibernate.show_sql", "true");
	    return dataSource;

	    
	    //---------------------------------------
	    
	    
			/*
			dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
			dataSource.setUrl("jdbc:oracle:thin:@localhost:1523:orcl2");
			
			dataSource.setUsername("hr");
			dataSource.setPassword("hr");

			Properties connectionProperties = new Properties();
			connectionProperties.setProperty("hibernate.hbm2ddl.auto", "update");
			connectionProperties.setProperty("hibernate.show_sql", "true");
			connectionProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
			connectionProperties.setProperty("hibernate.format_sql", "true");
			//connectionProperties.setProperty("hibernate.default_schema", "COLB_DB");
			dataSource.setConnectionProperties(connectionProperties);
			System.out.println("Datasource");
			return dataSource;
			
			*/
		}
		
//	private Properties getHibernateProperties() {
//		Properties properties = new Properties();
//		properties.put("hibernate.show_sql", "true");
//		properties.put("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect");
//		/*Modified by KZN*/
//		properties.put("hibernate.hbm2ddl.auto", "create");		
//		/**/
//		System.out.println("Hibernate Properties");
//		return properties;
//
//	}  
	    

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource)
	{
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
	
		
//		sessionBuilder.addProperties(getHibernateProperties()); // new  added
	//	sessionBuilder.scanPackages("com.model");     
		
		sessionBuilder.addAnnotatedClass(User.class);
		sessionBuilder.addAnnotatedClass(Friend.class);
		sessionBuilder.addAnnotatedClass(ProfilePicture.class);
		sessionBuilder.addAnnotatedClass(Job.class);
		sessionBuilder.addAnnotatedClass(BlogPost.class);
		sessionBuilder.addAnnotatedClass(BlogComment.class);
		//sessionBuilder.addAnnotatedClass(Error.class);
	//	sessionBuilder.setProperty("hibernate.show_sql", "true");
//		sessionBuilder.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		//sessionBuilder.setProperty("hibernate.hbm2ddl.auto", "update");
//		
		//---------------
		
		
		
		
		return sessionBuilder.buildSessionFactory();
	}
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
	        SessionFactory sessionFactory) {
	    HibernateTransactionManager transactionManager = new HibernateTransactionManager(
	            sessionFactory);	 
	    return transactionManager;
	}
	
	
	//----------------------------------
	
	
	/*
	 * New Line i hv added 
	 */
	
	@Autowired
	@Bean(name = "userDao")
	public UserDAO getUserDetailsDAO(SessionFactory sessionFactory) {
		
		return new UserDaoImpl(sessionFactory);
		
	}
	
	@Bean(name="multipartResolver")
	public CommonsMultipartResolver getCommonsMultipartResolver()
	{
		
		CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(20971520);
		multipartResolver.setMaxInMemorySize(1048576);
		return multipartResolver;
		
	}
	

	
	

}