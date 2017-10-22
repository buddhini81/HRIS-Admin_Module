package com.ha.entity.model.custom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeSearchDTO extends SearchDTO {

	private String firstName;
	private String lastName;
	private String EPFNo;
	private String NICNo;
	private Date dateOfBirthFrom;
	private Date dateOfBirthTo;
	private Long companyDid;
	private Long departmentDid;
	private String designation;
	private Long contractTypeDid;
	private List<Long> companyDidList = new ArrayList<Long>();
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEPFNo() {
		return EPFNo;
	}
	public void setEPFNo(String ePFNo) {
		EPFNo = ePFNo;
	}
	public String getNICNo() {
		return NICNo;
	}
	public void setNICNo(String nICNo) {
		NICNo = nICNo;
	}
	public Date getDateOfBirthFrom() {
		return dateOfBirthFrom;
	}
	public void setDateOfBirthFrom(Date dateOfBirthFrom) {
		this.dateOfBirthFrom = dateOfBirthFrom;
	}
	public Date getDateOfBirthTo() {
		return dateOfBirthTo;
	}
	public void setDateOfBirthTo(Date dateOfBirthTo) {
		this.dateOfBirthTo = dateOfBirthTo;
	}
	public Long getCompanyDid() {
		return companyDid;
	}
	public void setCompanyDid(Long companyDid) {
		this.companyDid = companyDid;
	}
	public Long getDepartmentDid() {
		return departmentDid;
	}
	public void setDepartmentDid(Long departmentDid) {
		this.departmentDid = departmentDid;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Long getContractTypeDid() {
		return contractTypeDid;
	}
	public void setContractTypeDid(Long contractTypeDid) {
		this.contractTypeDid = contractTypeDid;
	}
	public List<Long> getCompanyDidList() {
		return companyDidList;
	}
	public void setCompanyDidList(List<Long> companyDidList) {
		this.companyDidList = companyDidList;
	}
	
	public void reset() {
		this.firstName = null;
		this.lastName = null;;
		this.EPFNo = null;
		this.NICNo = null;
		this.dateOfBirthFrom = null;
		this.dateOfBirthTo = null;
		this.companyDid = -1L;
		this.departmentDid = -1L;
		this.designation = null;
		this.contractTypeDid = -1L;
		this.companyDidList.clear();
	}
}
