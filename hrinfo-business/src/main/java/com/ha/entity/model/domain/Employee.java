/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.entity.model.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Buddhini
 */
public class Employee implements Serializable {

    private Long employeeDid;
    private String employeeNo;
    private String epfNo;
    private String companyEpfNo;
    private Long companyDid;
    private String designation;
    private String nicNo;
    private String firstName;
    private String middleName;
    private String lastName;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String homePhoneNo;
    private String mobileNo;
    private String gender;
    private Date dateOfBirth;
    private Integer age;
    private String email;
    private String maritalStatus;
    private Date dateJoined;
    private Date dueConfirmationDate;
    private Date confirmationDate;
    private String drivingLicenseNo;
    private String passportNo;
    private ContractType contractType;
    private String resignedDate;
    private String otherContractType;
    private BigDecimal basicSalary;

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public Date getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(Date confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getDrivingLicenseNo() {
        return drivingLicenseNo;
    }

    public void setDrivingLicenseNo(String drivingLicenseNo) {
        this.drivingLicenseNo = drivingLicenseNo;
    }

    public Date getDueConfirmationDate() {
        return dueConfirmationDate;
    }

    public void setDueConfirmationDate(Date dueConfirmationDate) {
        this.dueConfirmationDate = dueConfirmationDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getEmployeeDid() {
        return employeeDid;
    }

    public void setEmployeeDid(Long employeeDid) {
        this.employeeDid = employeeDid;
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
    
    public String getCompanyEpfNo() {
		return companyEpfNo;
	}

	public void setCompanyEpfNo(String companyEpfNo) {
		this.companyEpfNo = companyEpfNo;
	}
	
    public Long getCompanyDid() {
		return companyDid;
	}

	public void setCompanyDid(Long companyDid) {
		this.companyDid = companyDid;
	}

	public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
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

    public String getHomePhoneNo() {
        return homePhoneNo;
    }

    public void setHomePhoneNo(String homePhoneNo) {
        this.homePhoneNo = homePhoneNo;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getNicNo() {
        return nicNo;
    }

    public void setNicNo(String nicNo) {
        this.nicNo = nicNo;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getResignedDate() {
        return resignedDate;
    }

    public void setResignedDate(String resignedDate) {
        this.resignedDate = resignedDate;
    }

    public String getOtherContractType() {
        return otherContractType;
    }

    public void setOtherContractType(String otherContractType) {
        this.otherContractType = otherContractType;
    }

	public BigDecimal getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(BigDecimal basicSalary) {
		this.basicSalary = basicSalary;
	}

}
