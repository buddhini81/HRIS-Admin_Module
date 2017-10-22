package com.ha.entity.model.custom;

import java.io.Serializable;

public class CompanyPolicyDTO implements Serializable {

	private Long policyDid;
	private Long companyDid;
	private String policyName;
	private String policyValue;
	
	public Long getPolicyDid() {
		return policyDid;
	}
	public void setPolicyDid(Long policyDid) {
		this.policyDid = policyDid;
	}
	public Long getCompanyDid() {
		return companyDid;
	}
	public void setCompanyDid(Long companyDid) {
		this.companyDid = companyDid;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public String getPolicyValue() {
		return policyValue;
	}
	public void setPolicyValue(String policyValue) {
		this.policyValue = policyValue;
	}
	
}
