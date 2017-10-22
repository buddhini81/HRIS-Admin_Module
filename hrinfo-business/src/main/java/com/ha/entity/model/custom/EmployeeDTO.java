/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.entity.model.custom;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Buddhini
 */
public class EmployeeDTO implements Serializable {
    private Long employeeDid;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date dateOfBirth;
    private Integer age;
    private String nicNo;
    private String passportNo;
    private String drivingLicenseNo;
    private Long parentCompanyDid;
    private String parentCompanyName;
    private Long companyDid;
    private String companyName;
    private Long departmentDid;
    private String departmentName;
    private Long departmentAssignmentDid;
    private String designation;
    private String employeeNo;
    private String epfNo;
    private String companyEPFNumber;
    private Date dateJoined;
    private Date dueConfDate;
    private Date confDate;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String homePhoneNo;
    private String mobileNo;
    private String email;
    private String gender;
    private Long contractTypeDid;
    private String contractType;
    private String otherContractType;
    private String maritalStatus;
    private String spouseName;
    private String spouseGender;
    private Date spouseDateOfBirth;
    private BigDecimal basicSalary;
    private Collection<EmployeeChildDTO> children;
    private Collection<EmployeeEmergencyContactDTO> emergencyContacts;
    private Collection<EmployeeAllowanceDTO> allowances;
    private Collection<CommentDTO> empComments;
    

    public Long getEmployeeDid() {
        return employeeDid;
    }

    public void setEmployeeDid(Long employeeDid) {
        this.employeeDid = employeeDid;
    }
    
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

    public Long getCompanyDid() {
        return companyDid;
    }

    public void setCompanyDid(Long companyDid) {
        this.companyDid = companyDid;
    }

    public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getConfDate() {
        return confDate;
    }

    public void setConfDate(Date confDate) {
        this.confDate = confDate;
    }

    public Long getContractTypeDid() {
        return contractTypeDid;
    }

    public void setContractTypeDid(Long contractTypeDid) {
        this.contractTypeDid = contractTypeDid;
    }

    public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
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

	public Long getDepartmentDid() {
        return departmentDid;
    }

    public void setDepartmentDid(Long departmentDid) {
        this.departmentDid = departmentDid;
    }

    public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public Long getDepartmentAssignmentDid() {
		return departmentAssignmentDid;
	}

	public void setDepartmentAssignmentDid(Long departmentAssignmentDid) {
		this.departmentAssignmentDid = departmentAssignmentDid;
	}

	public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDrivingLicenseNo() {
        return drivingLicenseNo;
    }

    public void setDrivingLicenseNo(String drivingLicenseNo) {
        this.drivingLicenseNo = drivingLicenseNo;
    }

    public Date getDueConfDate() {
        return dueConfDate;
    }

    public void setDueConfDate(Date dueConfDate) {
        this.dueConfDate = dueConfDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    
    public String getCompanyEPFNumber() {
		return companyEPFNumber;
	}

	public void setCompanyEPFNumber(String companyEPFNumber) {
		this.companyEPFNumber = companyEPFNumber;
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

    public String getOtherContractType() {
        return otherContractType;
    }

    public void setOtherContractType(String otherContractType) {
        this.otherContractType = otherContractType;
    }

    public Long getParentCompanyDid() {
        return parentCompanyDid;
    }

    public void setParentCompanyDid(Long parentCompanyDid) {
        this.parentCompanyDid = parentCompanyDid;
    }
    
    public String getParentCompanyName() {
		return parentCompanyName;
	}

	public void setParentCompanyName(String parentCompanyName) {
		this.parentCompanyName = parentCompanyName;
	}

	public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public String getSpouseGender() {
		return spouseGender;
	}

	public void setSpouseGender(String spouseGender) {
		this.spouseGender = spouseGender;
	}

	public Date getSpouseDateOfBirth() {
		return spouseDateOfBirth;
	}

	public void setSpouseDateOfBirth(Date spouseDateOfBirth) {
		this.spouseDateOfBirth = spouseDateOfBirth;
	}
	
	public BigDecimal getBasicSalary() {
		basicSalary.setScale(2);
		return basicSalary;
	}

	public void setBasicSalary(BigDecimal basicSalary) {
		try {
			this.basicSalary = basicSalary;
			this.basicSalary.setScale(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Collection<EmployeeChildDTO> getChildren() {
		return children;
	}

	public void setChildren(Collection<EmployeeChildDTO> children) {
		this.children = children;
	}

	public Collection<EmployeeEmergencyContactDTO> getEmergencyContacts() {
		return emergencyContacts;
	}

	public void setEmergencyContacts(
			Collection<EmployeeEmergencyContactDTO> emergencyContacts) {
		this.emergencyContacts = emergencyContacts;
	}
	
	public Collection<EmployeeAllowanceDTO> getAllowances() {
		return allowances;
	}

	public void setAllowances(Collection<EmployeeAllowanceDTO> allowances) {
		this.allowances = allowances;
	}
	
	public Collection<CommentDTO> getEmpComments() {
		return empComments;
	}

	public void setEmpComments(Collection<CommentDTO> empComments) {
		this.empComments = empComments;
	}

	public void reset() {
		this.employeeDid = null;
	    this.firstName = null;
	    this.middleName = null;
	    this.lastName = null;;
	    this.dateOfBirth = null;
	    this.nicNo = null;
	    this.passportNo = null;
	    this.drivingLicenseNo = null;
	    this.parentCompanyDid = null;
	    this.parentCompanyName = null;
	    this.companyDid = -1L;
	    this.departmentDid = -1L;
	    this.designation = null;
	    this.departmentAssignmentDid = null;
	    this.epfNo = null;
	    this.companyEPFNumber = null;
	    this.dateJoined = null;
	    this.dueConfDate = null;
	    this.confDate = null;
	    this.addressLine1 = null;
	    this.addressLine2 = null;
	    this.addressLine3 = null;
	    this.homePhoneNo = null;
	    this.mobileNo = null;
	    this.email = null;
	    this.gender = null;
	    this.contractTypeDid = -1L;
	    this.otherContractType = null;
	    this.maritalStatus = null;
	    this.spouseName = null;
	    this.spouseGender = null;
	    this.spouseDateOfBirth = null;
	    this.basicSalary = null;
	    if(this.children != null && this.children.size() > 0) {this.children.clear();}
	    if(this.emergencyContacts != null && this.emergencyContacts.size() > 0) {this.emergencyContacts.clear();}
	    if(this.allowances != null && this.allowances.size() > 0) {this.allowances.clear();}
	    if(this.empComments != null && this.empComments.size() > 0) {this.empComments.clear();}
	}

}
