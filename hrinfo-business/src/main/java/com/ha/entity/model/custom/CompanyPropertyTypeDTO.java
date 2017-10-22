package com.ha.entity.model.custom;

import java.io.Serializable;

public class CompanyPropertyTypeDTO implements Serializable {

	private Long propertyTypeDid;
	private String propertyName;
	private String description;
	private Boolean returned;
	private String strReturnValue;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getReturned() {
		return returned;
	}
	public void setReturned(Boolean returned) {
		this.returned = returned;
	}
	public String getStrReturnValue() {
		return strReturnValue;
	}
	public void setStrReturnValue(String strReturnValue) {
		this.strReturnValue = strReturnValue;
	}
}
