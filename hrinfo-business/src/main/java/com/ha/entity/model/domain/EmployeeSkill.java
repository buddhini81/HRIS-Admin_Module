package com.ha.entity.model.domain;

import java.io.Serializable;

public class EmployeeSkill implements Serializable {
	
	private Long skillDid;
	private Long skillTypeDid;
	private String description;
	private Long employeeDid;
	
	public Long getSkillDid() {
		return skillDid;
	}
	public void setSkillDid(Long skillDid) {
		this.skillDid = skillDid;
	}
	public Long getSkillTypeDid() {
		return skillTypeDid;
	}
	public void setSkillTypeDid(Long skillTypeDid) {
		this.skillTypeDid = skillTypeDid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getEmployeeDid() {
		return employeeDid;
	}
	public void setEmployeeDid(Long employeeDid) {
		this.employeeDid = employeeDid;
	}
}
