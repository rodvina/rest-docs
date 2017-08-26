package com.kemper.docs.rest.model;

public abstract class DocumentResult extends BaseModel {

	private static final long serialVersionUID = 1L;

	private String urlRetrieve;

	public String getUrlRetrieve() {
		return urlRetrieve;
	}

	public void setUrlRetrieve(String urlRetrieve) {
		this.urlRetrieve = urlRetrieve;
	}
	
}
