package com.kemper.docs.rest.model;

import java.io.Serializable;

public class Document implements Serializable {

	private static final long serialVersionUID = 1L;

	private String urlRetrieve;

	public String getUrlRetrieve() {
		return urlRetrieve;
	}

	public void setUrlRetrieve(String urlRetrieve) {
		this.urlRetrieve = urlRetrieve;
	}
	
}
