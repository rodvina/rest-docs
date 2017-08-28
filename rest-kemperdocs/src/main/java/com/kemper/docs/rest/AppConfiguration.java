package com.kemper.docs.rest;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.kemper.docs.rest.cms.CMSProperties;
import com.ksg.cms.client.CMSClient;
import com.ksg.cms.client.util.ClientUtils;

@Configuration
@PropertySource("classpath:application-${spring.profiles.active}.properties")
public class AppConfiguration {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppConfiguration.class);
	
	@Autowired
	CMSProperties cmsProperties;
	
	@Value("${cms.requestUsername}")
	private String requestUsername;
	
	@Value("${cms.requestPassword}")
	private String requestPassword;
	
	@Value("${cms.cmis.repositoryid}")
	private String requestSpname;
	
	@Value("${cms.cmis.repositoryid}")
	private String cmisRepositoryid;
	
	@Value("${cms.cmis.url}") 
	private String cmisUrl;
	
	@Bean
	public CMSClient getCMSClient() {
		LOGGER.info("cmisurl: " + cmisUrl);
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
				"http://appsdev.unitrininc.com", 
				"com.usg.cms.load.mdp", 
				"com.usg.cms.search.mdp", 
				"com.usg.cms.retrieve.mdp");
	}
	
	@Bean
	public ConnectionFactory connectionFactory() {
		// TODO Auto-generated method stub
		progress.message.jclient.ConnectionFactory cf = null;
		try {
			cf = new progress.message.jclient.ConnectionFactory();
			cf.setConnectionURLs("tcp://kahobtesbb93.kah.unitrininc.com:2916");
			cf.setDefaultUser("USGClient");
			cf.setDefaultPassword("USGClient");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
