package com.ha.entity.model.custom;

import java.io.Serializable;

public class UserDTO implements Serializable {

	private Long userDid;
	private String userName;
	private String password;
	private String confirmPassword;
	private String oldPassword;
	private Long companyDid;
	private Long userRoleDid;
	
	
	public Long getUserDid() {
		return userDid;
	}
	public void setUserDid(Long userDid) {
		this.userDid = userDid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public Long getCompanyDid() {
		return companyDid;
	}
	public void setCompanyDid(Long companyDid) {
		this.companyDid = companyDid;
	}
	public Long getUserRoleDid() {
		return userRoleDid;
	}
	public void setUserRoleDid(Long userRoleDid) {
		this.userRoleDid = userRoleDid;
	}
	
}
