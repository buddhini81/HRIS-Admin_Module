package com.ha.entity.model.custom;

import java.io.Serializable;

public class AllowanceTypeDTO implements Serializable {
	
	private Long allowanceDid;
	private String allowanceType;
	private String description;
	
	public Long getAllowanceDid() {
		return allowanceDid;
	}
	public void setAllowanceDid(Long allowanceDid) {
		this.allowanceDid = allowanceDid;
	}
	public String getAllowanceType() {
		return allowanceType;
	}
	public void setAllowanceType(String allowanceType) {
		this.allowanceType = allowanceType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
