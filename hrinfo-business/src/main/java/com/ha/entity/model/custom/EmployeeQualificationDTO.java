package com.ha.entity.model.custom;

import java.io.Serializable;

public class EmployeeQualificationDTO implements Serializable {

	private Long employeeDid;
	private Long qualificationDid;
	private Long qualificationTypeDid;
	private String qualificationType;
	private String description;
	private String year;
	private String comment;
	
	public Long getEmployeeDid() {
		return employeeDid;
	}
	public void setEmployeeDid(Long employeeDid) {
		this.employeeDid = employeeDid;
	}
	public Long getQualificationDid() {
		return qualificationDid;
	}
	public void setQualificationDid(Long qualificationDid) {
		this.qualificationDid = qualificationDid;
	}
	public Long getQualificationTypeDid() {
		return qualificationTypeDid;
	}
	public void setQualificationTypeDid(Long qualificationTypeDid) {
		this.qualificationTypeDid = qualificationTypeDid;
	}
	public String getQualificationType() {
		return qualificationType;
	}
	public void setQualificationType(String qualificationType) {
		this.qualificationType = qualificationType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	public void reset() {
		this.qualificationDid = null;
		this.qualificationTypeDid = -1L;
		this.description = null;
		this.year = null;
		this.comment = null;
	}
}
