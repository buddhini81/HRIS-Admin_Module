package com.ha.entity.model.custom;

import java.io.Serializable;
import java.util.Date;

public class CompanyPropertyAssignmentDTO implements Serializable {

	private Long propertyAssignmentDid;
	private Long propertyTypeDid;
	private String propertyName;
	private Long employeeDid;
	private Date assignedDate;
	private Date returnedDate;
	private String comment;
	private Boolean disableReturnDate = true;
	
	public Long getPropertyAssignmentDid() {
		return propertyAssignmentDid;
	}
	public void setPropertyAssignmentDid(Long propertyAssignmentDid) {
		this.propertyAssignmentDid = propertyAssignmentDid;
	}
	public Long getPropertyTypeDid() {
		return propertyTypeDid;
	}
	public void setPropertyTypeDid(Long propertyTypeDid) {
		this.propertyTypeDid = propertyTypeDid;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public Long getEmployeeDid() {
		return employeeDid;
	}
	public void setEmployeeDid(Long employeeDid) {
		this.employeeDid = employeeDid;
	}
	public Date getAssignedDate() {
		return assignedDate;
	}
	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}
	public Date getReturnedDate() {
		return returnedDate;
	}
	public void setReturnedDate(Date returnedDate) {
		this.returnedDate = returnedDate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Boolean getDisableReturnDate() {
		return disableReturnDate;
	}
	public void setDisableReturnDate(Boolean disableReturnDate) {
		this.disableReturnDate = disableReturnDate;
	}
	
	public void reset() {
		this.propertyAssignmentDid = null;
		this.propertyTypeDid = -1L;
		this.propertyName = null;
		this.assignedDate = null;
		this.returnedDate = null;
		this.comment = null;
	}
	
}
