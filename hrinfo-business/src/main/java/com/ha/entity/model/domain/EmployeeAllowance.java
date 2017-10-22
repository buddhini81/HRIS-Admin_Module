package com.ha.entity.model.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class EmployeeAllowance implements Serializable {
	
	private Long employeeAllowanceDid;
	private Long employeeDid;
	private Long allowanceTypeDid;
	private BigDecimal amount;
	
	public Long getEmployeeAllowanceDid() {
		return employeeAllowanceDid;
	}
	public void setEmployeeAllowanceDid(Long employeeAllowanceDid) {
		this.employeeAllowanceDid = employeeAllowanceDid;
	}
	public Long getEmployeeDid() {
		return employeeDid;
	}
	public void setEmployeeDid(Long employeeDid) {
		this.employeeDid = employeeDid;
	}
	public Long getAllowanceTypeDid() {
		return allowanceTypeDid;
	}
	public void setAllowanceTypeDid(Long allowanceTypeDid) {
		this.allowanceTypeDid = allowanceTypeDid;
	}
	public BigDecimal getAmount() {
		amount.setScale(2);
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		amount.setScale(2);
		this.amount = amount;
	}
}
