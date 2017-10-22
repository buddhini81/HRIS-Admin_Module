/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.entity.model.custom;

import java.io.Serializable;

/**
 *
 * @author Buddhini
 */
public class CompanyDTO implements Serializable {
    private Long companyDid;
    private String companyId;
    private String companyName;
    private String regNumber;
    private String vatNo;
    private Boolean isParent;
    private Boolean isSingleCompany;
    private Long parentCompanyDid;
    private String companyEPFNo;

    public Long getCompanyDid() {
        return companyDid;
    }

    public void setCompanyDid(Long companyDid) {
        this.companyDid = companyDid;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getParentCompanyDid() {
        return parentCompanyDid;
    }

    public void setParentCompanyDid(Long parentCompanyDid) {
        this.parentCompanyDid = parentCompanyDid;
    }
    
    public Boolean getIsSingleCompany() {
		return isSingleCompany;
	}

	public void setIsSingleCompany(Boolean isSingleCompany) {
		this.isSingleCompany = isSingleCompany;
	}

	public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }

    public String getVatNo() {
        return vatNo;
    }

    public void setVatNo(String vatNo) {
        this.vatNo = vatNo;
    }

	public String getCompanyEPFNo() {
		return companyEPFNo;
	}

	public void setCompanyEPFNo(String companyEPFNo) {
		this.companyEPFNo = companyEPFNo;
	}
}
