package com.ha.entity.model.domain;

import java.io.Serializable;

public class EmployeeQualification implements Serializable {

	private Long qualificationDid;
	private Long qualificationTypeDid;
	private String description;
	private Integer yearObtained;
	private String comment;
	private Long employeeDid;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getYearObtained() {
		return yearObtained;
	}
	public void setYearObtained(Integer yearObtained) {
		this.yearObtained = yearObtained;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Long getEmployeeDid() {
		return employeeDid;
	}
	public void setEmployeeDid(Long employeeDid) {
		this.employeeDid = employeeDid;
	}

	
}
