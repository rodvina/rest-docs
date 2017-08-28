package com.kemper.docs.rest;

import javax.jms.ConnectionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.ksg.cms.client.CMSClient;
import com.ksg.cms.client.util.ClientUtils;

@Configuration
@PropertySource("classpath:application-${spring.profiles.active}.properties")
public class AppConfiguration {

	@Bean
	public CMSClient getCMSClient() {
		return ClientUtils.getClient(
				this.getConnectionFactory(), 
				"http://appsdev.unitrininc.com", 
				"com.usg.cms.load.mdp", 
				"com.usg.cms.search.mdp", 
				"com.usg.cms.retrieve.mdp");
	}

	private ConnectionFactory getConnectionFactory() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * This is still needed in addition to @PropertySource to allow @Value to work
	 * @return
	 */
	@Bean
	public PropertySourcesPlaceholderConfigurer properties() {
	    return new PropertySourcesPlaceholderConfigurer();
	}
}
