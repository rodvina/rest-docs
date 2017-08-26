package com.kemper.docs.rest.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class DecResult extends DocumentResult {

	private static final long serialVersionUID = 1L;

	private String policyno;
	private String name;
	private String transactionType;
	private String lob;
	
	@ApiModelProperty(example="11/25/2017")
	@JsonFormat(pattern="MM/dd/yyyy")
	private LocalDate effectiveDate;
	
	@ApiModelProperty(example="08/25/2017")
	@JsonFormat(pattern="MM/dd/yyyy")
	private LocalDate processedDate;
	
	private String producerCode;
	private BigDecimal premium;
	private String city;
	private String state;
	private String zip;
	
	public String getPolicyno() {
		return policyno;
	}
	public void setPolicyno(String policyno) {
		this.policyno = policyno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getLob() {
		return lob;
	}
	public void setLob(String lob) {
		this.lob = lob;
	}
	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public LocalDate getProcessedDate() {
		return processedDate;
	}
	public void setProcessedDate(LocalDate processedDate) {
		this.processedDate = processedDate;
	}
	public String getProducerCode() {
		return producerCode;
	}
	public void setProducerCode(String producerCode) {
		this.producerCode = producerCode;
	}
	public BigDecimal getPremium() {
		return premium;
	}
	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	
}
