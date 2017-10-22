/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.entity.model.custom;

import com.ha.util.Constants.LoginErrorCodes;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Buddhini
 */
public class UserProfileDTO implements Serializable {

   private String userName;
   private String password;
   private Long userRoleDid;
   private Long companyDid;
   private String companyName;
   private Boolean isParent;

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

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserRoleDid() {
        return userRoleDid;
    }

    public void setUserRoleDid(Long userRoleDid) {
        this.userRoleDid = userRoleDid;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }

}
