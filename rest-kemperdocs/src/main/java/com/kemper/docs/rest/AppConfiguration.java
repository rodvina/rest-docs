package com.kemper.docs.rest;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.kemper.docs.rest.cms.CMSProperties;
import com.kemper.docs.rest.jms.JMSProperties;
import com.ksg.cms.client.CMSClient;
import com.ksg.cms.client.util.ClientUtils;

@Configuration
@PropertySource("classpath:application-${spring.profiles.active}.properties")
public class AppConfiguration {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppConfiguration.class);
	
	@Autowired
	CMSProperties cmsProperties;
	
	@Autowired
	JMSProperties jmsProperties;
	
	@Bean
	public CMSClient getCMSClient() {
		LOGGER.info("cmisurl: " + cmsProperties);

//		return ClientUtils.getClient(
//				cmsProperties.getCmisUrl(), 
//				cmsProperties.getRequestUsername(), 
//				cmsProperties.getRequestPassword(), 
//				cmsProperties.getCmisRepositoryid()
//				);

//JMS IMPL		
		return ClientUtils.getClient(
				this.connectionFactory(), 
				cmsProperties.getCoatCheckUrl(), 
				cmsProperties.getLoadDest(), 
				cmsProperties.getSearchDest(), 
				cmsProperties.getRetrieveDest());
	}
	
	@Bean
	public ConnectionFactory connectionFactory() {
		// TODO Auto-generated method stub
		progress.message.jclient.ConnectionFactory cf = null;
		try {
			cf = new progress.message.jclient.ConnectionFactory();
			cf.setConnectionURLs(jmsProperties.getConnectionUrl());
			cf.setDefaultUser(jmsProperties.getUserid());
			cf.setDefaultPassword(jmsProperties.getPassword());
		} catch (JMSException e) {

			LOGGER.error("Error=FailedToCreateConnectionFactory", e);
		}

		return cf;
	}

	
	/**
	 * This is still needed in addition to @PropertySource to allow @Value to work
	 * @return
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
	    return new PropertySourcesPlaceholderConfigurer();
	}
}
