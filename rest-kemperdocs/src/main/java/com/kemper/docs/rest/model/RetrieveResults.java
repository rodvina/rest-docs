package com.kemper.docs.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class RetrieveResults extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	@JsonInclude(Include.NON_NULL)
	private byte[] document;
	
	private String contentId;
	
	public RetrieveResults() {}

	public byte[] getDocument() {
		return document == null ?  null : document.clone();
	}

	public void setDocument(byte[] document) {
		this.document = document == null ? null : document.clone();
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	

}
