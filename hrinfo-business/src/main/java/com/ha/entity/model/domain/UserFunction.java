package com.ha.entity.model.domain;

import java.io.Serializable;

public class UserFunction implements Serializable {
	
	private Long userFunctionDid;
	
	private Long userRoleDid;
	
	private Long functionDid;

	public Long getUserFunctionDid() {
		return userFunctionDid;
	}

	public void setUserFunctionDid(Long userFunctionDid) {
		this.userFunctionDid = userFunctionDid;
	}

	public Long getUserRoleDid() {
		return userRoleDid;
	}

	public void setUserRoleDid(Long userRoleDid) {
		this.userRoleDid = userRoleDid;
	}

	public Long getFunctionDid() {
		return functionDid;
	}

	public void setFunctionDid(Long functionDid) {
		this.functionDid = functionDid;
	}
	
}
