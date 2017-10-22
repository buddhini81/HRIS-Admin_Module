package com.ha.entity.model.custom;

import java.io.Serializable;

public class EmployeeSkillDTO implements Serializable {
	
	private Long employeeDid;
	private Long skillDid;
	private Long skillTypeDid;
	private String skillType;
	private String description;
	
	
	public Long getEmployeeDid() {
		return employeeDid;
	}
	public void setEmployeeDid(Long employeeDid) {
		this.employeeDid = employeeDid;
	}
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
	public String getSkillType() {
		return skillType;
	}
	public void setSkillType(String skillType) {
		this.skillType = skillType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void reset() {
		this.skillDid = null;
		this.skillTypeDid = -1L;
		this.skillType = null;
		this.description = null;
	}
}
