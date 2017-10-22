package com.ha.entity.model.custom;

import java.util.Date;

public class HistoryDTO {
	
	private Long did;
	private Long employeeDid;
	private String description;
	private Date date;
	
	public Long getDid() {
		return did;
	}
	public void setDid(Long did) {
		this.did = did;
	}
	public Long getEmployeeDid() {
		return employeeDid;
	}
	public void setEmployeeDid(Long employeeDid) {
		this.employeeDid = employeeDid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
