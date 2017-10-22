package com.ha.entity.model.custom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResignationDTO implements Serializable {

	private Long employeeDid;
	private Boolean isResignationAcceptanceIssued;
	private Boolean isWorkHandedOver;
	private Boolean isCompanyPropHandedOver;
	private Boolean isFinancialClearenceDone;
	private Boolean isServiceLetterIssued;
	private Boolean isBcardHandedOver;
	private Boolean isGrativityPaid;
	private List<CompanyPropertyTypeDTO> companyPropertyTypeDTO = new ArrayList<CompanyPropertyTypeDTO>();
	
	public Long getEmployeeDid() {
		return employeeDid;
	}
	public void setEmployeeDid(Long employeeDid) {
		this.employeeDid = employeeDid;
	}
	public Boolean getIsResignationAcceptanceIssued() {
		return isResignationAcceptanceIssued;
	}
	public void setIsResignationAcceptanceIssued(
			Boolean isResignationAcceptanceIssued) {
		this.isResignationAcceptanceIssued = isResignationAcceptanceIssued;
	}
	public Boolean getIsWorkHandedOver() {
		return isWorkHandedOver;
	}
	public void setIsWorkHandedOver(Boolean isWorkHandedOver) {
		this.isWorkHandedOver = isWorkHandedOver;
	}
	public Boolean getIsCompanyPropHandedOver() {
		return isCompanyPropHandedOver;
	}
	public void setIsCompanyPropHandedOver(Boolean isCompanyPropHandedOver) {
		this.isCompanyPropHandedOver = isCompanyPropHandedOver;
	}
	public Boolean getIsFinancialClearenceDone() {
		return isFinancialClearenceDone;
	}
	public void setIsFinancialClearenceDone(Boolean isFinancialClearenceDone) {
		this.isFinancialClearenceDone = isFinancialClearenceDone;
	}
	public Boolean getIsServiceLetterIssued() {
		return isServiceLetterIssued;
	}
	public void setIsServiceLetterIssued(Boolean isServiceLetterIssued) {
		this.isServiceLetterIssued = isServiceLetterIssued;
	}
	public Boolean getIsBcardHandedOver() {
		return isBcardHandedOver;
	}
	public void setIsBcardHandedOver(Boolean isBcardHandedOver) {
		this.isBcardHandedOver = isBcardHandedOver;
	}
	public Boolean getIsGrativityPaid() {
		return isGrativityPaid;
	}
	public void setIsGrativityPaid(Boolean isGrativityPaid) {
		this.isGrativityPaid = isGrativityPaid;
	}
	public List<CompanyPropertyTypeDTO> getCompanyPropertyTypeDTO() {
		return companyPropertyTypeDTO;
	}
	public void setCompanyPropertyTypeDTO(
			List<CompanyPropertyTypeDTO> companyPropertyTypeDTO) {
		this.companyPropertyTypeDTO = companyPropertyTypeDTO;
	}
}
