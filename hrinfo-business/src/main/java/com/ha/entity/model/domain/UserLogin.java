/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ha.entity.model.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Buddhini
 */
public class UserLogin implements Serializable {
    private Long userLoginDid;
    private String userName;
    private String password;
    private Long userRoleDid;
    private Date LastSucessfulLoginDate;
    private Company company;

    public Date getLastSucessfulLoginDate() {
        return LastSucessfulLoginDate;
    }

    public void setLastSucessfulLoginDate(Date LastSucessfulLoginDate) {
        this.LastSucessfulLoginDate = LastSucessfulLoginDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserLoginDid() {
        return userLoginDid;
    }

    public void setUserLoginDid(Long userLoginDid) {
        this.userLoginDid = userLoginDid;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
