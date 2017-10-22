package com.ha.entity.model.domain;

import java.io.Serializable;
import java.util.Date;

public class EmployeeHistory implements Serializable {
	
	private Long historyDid;
	private Long employeeDid;
	private String note;
	private Date historyDate;
	
	public Long getHistoryDid() {
		return historyDid;
	}
	public void setHistoryDid(Long historyDid) {
		this.historyDid = historyDid;
	}
	public Long getEmployeeDid() {
		return employeeDid;
	}
	public void setEmployeeDid(Long employeeDid) {
		this.employeeDid = employeeDid;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getHistoryDate() {
		return historyDate;
	}
	public void setHistoryDate(Date historyDate) {
		this.historyDate = historyDate;
	}
	
	
}
