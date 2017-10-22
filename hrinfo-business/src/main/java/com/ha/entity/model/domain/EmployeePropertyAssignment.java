package com.ha.entity.model.domain;

import java.io.Serializable;
import java.util.Date;

public class EmployeePropertyAssignment implements Serializable {

	private Long propertyAssignmentDid;
	private Long propertyTypeDid;
	private Long employeeDid;
	private Date assignedDate;
	private Date returnedDate;
	private String comment;
	
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
	
}
