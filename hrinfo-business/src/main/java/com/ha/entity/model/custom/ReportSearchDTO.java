/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.entity.model.custom;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Buddhini
 */
public class ReportSearchDTO implements Serializable {

    private String firstName;
    private String lastName;
    private Date dobFrom;
    private Date dobTo;
    private String gender;
    private String employeeNo;
    private String epfNo;
    private String nicNo;
    private String designation;
    private String companyName;
    private String departmentCode;
    private Date dateJoinedFrom;
    private Date dateJoinedTo;
    private Date confirmDateFrom;
    private Date confirmDateTo;
    private Date dueConfirmDateFrom;
    private Date dueConfirmDateTo;
    private Long contractTypeDid;
    private String sortBy;
    private String sortOrder;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getConfirmDateFrom() {
        return confirmDateFrom;
    }

    public void setConfirmDateFrom(Date confirmDateFrom) {
        this.confirmDateFrom = confirmDateFrom;
    }

    public Date getConfirmDateTo() {
        return confirmDateTo;
    }

    public void setConfirmDateTo(Date confirmDateTo) {
        this.confirmDateTo = confirmDateTo;
    }

    

    public Long getContractTypeDid() {
        return contractTypeDid;
    }

    public void setContractTypeDid(Long contractTypeDid) {
        this.contractTypeDid = contractTypeDid;
    }

    public Date getDateJoinedFrom() {
        return dateJoinedFrom;
    }

    public void setDateJoinedFrom(Date dateJoinedFrom) {
        this.dateJoinedFrom = dateJoinedFrom;
    }

    public Date getDateJoinedTo() {
        return dateJoinedTo;
    }

    public void setDateJoinedTo(Date dateJoinedTo) {
        this.dateJoinedTo = dateJoinedTo;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getDobFrom() {
        return dobFrom;
    }

    public void setDobFrom(Date dobFrom) {
        this.dobFrom = dobFrom;
    }

    public Date getDobTo() {
        return dobTo;
    }

    public void setDobTo(Date dobTo) {
        this.dobTo = dobTo;
    }

    public Date getDueConfirmDateFrom() {
        return dueConfirmDateFrom;
    }

    public void setDueConfirmDateFrom(Date dueConfirmDateFrom) {
        this.dueConfirmDateFrom = dueConfirmDateFrom;
    }

    public Date getDueConfirmDateTo() {
        return dueConfirmDateTo;
    }

    public void setDueConfirmDateTo(Date dueConfirmDateTo) {
        this.dueConfirmDateTo = dueConfirmDateTo;
    }
    

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getEpfNo() {
        return epfNo;
    }

    public void setEpfNo(String epfNo) {
        this.epfNo = epfNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNicNo() {
        return nicNo;
    }

    public void setNicNo(String nicNo) {
        this.nicNo = nicNo;
    }

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
    
}
