package com.kemper.docs.rest.model;

public abstract class DocumentResult extends BaseModel {

	private static final long serialVersionUID = 1L;

	private String contentId;

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	
	
	
}
