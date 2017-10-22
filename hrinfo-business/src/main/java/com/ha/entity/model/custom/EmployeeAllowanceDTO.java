package com.ha.entity.model.custom;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class EmployeeAllowanceDTO {

	private Long employeeAllowanceDid;
	private Long allowanceTypeDid;
	private String allowanceType;
	private BigDecimal amount;
	
	
	public Long getEmployeeAllowanceDid() {
		return employeeAllowanceDid;
	}
	public void setEmployeeAllowanceDid(Long employeeAllowanceDid) {
		this.employeeAllowanceDid = employeeAllowanceDid;
	}
	public Long getAllowanceTypeDid() {
		return allowanceTypeDid;
	}
	public void setAllowanceTypeDid(Long allowanceTypeDid) {
		this.allowanceTypeDid = allowanceTypeDid;
	}
	
	public String getAllowanceType() {
		return allowanceType;
	}
	public void setAllowanceType(String allowanceType) {
		this.allowanceType = allowanceType;
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
