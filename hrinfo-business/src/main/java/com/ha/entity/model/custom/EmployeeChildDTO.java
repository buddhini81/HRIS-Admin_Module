package com.ha.entity.model.custom;

import java.io.Serializable;

public class EmployeeChildDTO implements Serializable {
	
	private String childName;
	private String childGender;
	private String childDateOfBirth;
	
	
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public String getChildGender() {
		return childGender;
	}
	public void setChildGender(String childGender) {
		this.childGender = childGender;
	}
	public String getChildDateOfBirth() {
		return childDateOfBirth;
	}
	public void setChildDateOfBirth(String childDateOfBirth) {
		this.childDateOfBirth = childDateOfBirth;
	}
	
}
