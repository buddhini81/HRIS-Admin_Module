package com.ha.entity.model.custom;

import java.io.Serializable;

public class EmployeeEmergencyContactDTO implements Serializable {
	

	private String contactName;
	private String relationship;
	private String contactNumber;
	

	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
}
