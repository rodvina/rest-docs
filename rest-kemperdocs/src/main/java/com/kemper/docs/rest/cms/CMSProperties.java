package com.kemper.docs.rest.cms;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CMSProperties {

	@Value("${cms.requestUsername}")
	private String requestUsername;
	
	@Value("${cms.requestPassword}")
	private String requestPassword;
	
	@Value("${cms.requestOrgname}")
	private String requestOrgname;
	
	@Value("${cms.requestAppname}")
	private String requestAppname;
	
	@Value("${cms.requestSpname}")
	private String requestSpname;
	
	@Value("${cms.cmis.repositoryid}")
	private String cmisRepositoryid;
	
	@Value("${cms.cmis.url}") 
	private String cmisUrl;
	
	@Value("${cms.searchEntryPoint}")
	private String searchDest;
	
	@Value("${cms.loadEntryPoint}")
	private String loadDest;
	
	@Value("${cms.retrieveEntryPoint}")
	private String retrieveDest;
	
	@Value("${cms.coatcheckUrl}")
	private String coatCheckUrl;
		
	public String getRequestUsername() {
		return requestUsername;
	}
	public void setRequestUsername(String requestUsername) {
		this.requestUsername = requestUsername;
	}
	public String getRequestPassword() {
		return requestPassword;
	}
	public void setRequestPassword(String requestPassword) {
		this.requestPassword = requestPassword;
	}
	public String getRequestOrgname() {
		return requestOrgname;
	}
	public void setRequestOrgname(String requestOrgname) {
		this.requestOrgname = requestOrgname;
	}
	public String getRequestAppname() {
		return requestAppname;
	}
	public void setRequestAppname(String requestAppname) {
		this.requestAppname = requestAppname;
	}
	public String getRequestSpname() {
		return requestSpname;
	}
	public void setRequestSpname(String requestSpname) {
		this.requestSpname = requestSpname;
	}
	public String getCmisRepositoryid() {
		return cmisRepositoryid;
	}
	public void setCmisRepositoryid(String cmisRepositoryid) {
		this.cmisRepositoryid = cmisRepositoryid;
	}
	public String getCmisUrl() {
		return cmisUrl;
	}
	public void setCmisUrl(String cmisUrl) {
		this.cmisUrl = cmisUrl;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	public String getSearchDest() {
		return searchDest;
	}
	public void setSearchDest(String searchDest) {
		this.searchDest = searchDest;
	}
	public String getLoadDest() {
		return loadDest;
	}
	public void setLoadDest(String loadDest) {
		this.loadDest = loadDest;
	}
	public String getRetrieveDest() {
		return retrieveDest;
	}
	public void setRetrieveDest(String retrieveDest) {
		this.retrieveDest = retrieveDest;
	}
	public String getCoatCheckUrl() {
		return coatCheckUrl;
	}
	public void setCoatCheckUrl(String coatCheckUrl) {
		this.coatCheckUrl = coatCheckUrl;
	}
	
}
