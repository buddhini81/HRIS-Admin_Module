package com.ha.entity.model.custom;

import java.io.Serializable;
import java.util.Date;

public class ConfirmationAlertDTO implements Serializable {

	private String companyId;
	private Integer epfNo;
	private Date dueConfDate;
	
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public Integer getEpfNo() {
		return epfNo;
	}
	public void setEpfNo(Integer epfNo) {
		this.epfNo = epfNo;
	}
	public Date getDueConfDate() {
		return dueConfDate;
	}
	public void setDueConfDate(Date dueConfDate) {
		this.dueConfDate = dueConfDate;
	}
}
