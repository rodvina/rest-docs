package com.kemper.docs.rest.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRequest extends BaseModel {

	private static final long serialVersionUID = 1L;

	private String documentType;
	private LocalDate fromDate;
	private LocalDate toDate;
	private List<String> producerCodeList;
	
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	
	public List<String> getProducerCodeList() {
		if (null == this.producerCodeList) {
			this.producerCodeList = new ArrayList<String>();
		}
		return this.producerCodeList;
	}
	public void setProducerCodeList(List<String> producerCodeList) {
		this.producerCodeList = producerCodeList;
	}
	public LocalDate getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDate getToDate() {
		return toDate;
	}
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
	
}
