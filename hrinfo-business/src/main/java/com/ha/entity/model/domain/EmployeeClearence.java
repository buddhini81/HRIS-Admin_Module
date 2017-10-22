package com.ha.entity.model.domain;

import java.io.Serializable;

public class EmployeeClearence implements Serializable {

	private Long employeeClearenceDid;
	private Long employeeDid;
	private Boolean resignationAcceptanceIssued;
	private Boolean workHandedOver;
	private Boolean companyPropertyHandedOver;
	private Boolean financialClearenceDone;
	private Boolean serviceLetterIssued;
	private Boolean bCardHandedOver;
	private Boolean grativityPaid;
	
	
	public Long getEmployeeClearenceDid() {
		return employeeClearenceDid;
	}
	public void setEmployeeClearenceDid(Long employeeClearenceDid) {
		this.employeeClearenceDid = employeeClearenceDid;
	}
	public Long getEmployeeDid() {
		return employeeDid;
	}
	public void setEmployeeDid(Long employeeDid) {
		this.employeeDid = employeeDid;
	}
	public Boolean getResignationAcceptanceIssued() {
		return resignationAcceptanceIssued;
	}
	public void setResignationAcceptanceIssued(Boolean resignationAcceptanceIssued) {
		this.resignationAcceptanceIssued = resignationAcceptanceIssued;
	}
	public Boolean getWorkHandedOver() {
		return workHandedOver;
	}
	public void setWorkHandedOver(Boolean workHandedOver) {
		this.workHandedOver = workHandedOver;
	}
	public Boolean getCompanyPropertyHandedOver() {
		return companyPropertyHandedOver;
	}
	public void setCompanyPropertyHandedOver(Boolean companyPropertyHandedOver) {
		this.companyPropertyHandedOver = companyPropertyHandedOver;
	}
	public Boolean getFinancialClearenceDone() {
		return financialClearenceDone;
	}
	public void setFinancialClearenceDone(Boolean financialClearenceDone) {
		this.financialClearenceDone = financialClearenceDone;
	}
	public Boolean getServiceLetterIssued() {
		return serviceLetterIssued;
	}
	public void setServiceLetterIssued(Boolean serviceLetterIssued) {
		this.serviceLetterIssued = serviceLetterIssued;
	}
	public Boolean getbCardHandedOver() {
		return bCardHandedOver;
	}
	public void setbCardHandedOver(Boolean bCardHandedOver) {
		this.bCardHandedOver = bCardHandedOver;
	}
	public Boolean getGrativityPaid() {
		return grativityPaid;
	}
	public void setGrativityPaid(Boolean grativityPaid) {
		this.grativityPaid = grativityPaid;
	}
	
}
