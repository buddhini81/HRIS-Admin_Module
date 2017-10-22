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
public class DepartmentDTO implements Serializable {

    private Long departmentDid;
    private Long companyDid;
    private String departmentCode;
    private Long numberOfEmployees;

    public Long getCompanyDid() {
        return companyDid;
    }

    public void setCompanyDid(Long companyDid) {
        this.companyDid = companyDid;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public Long getDepartmentDid() {
        return departmentDid;
    }

    public void setDepartmentDid(Long departmentDid) {
        this.departmentDid = departmentDid;
    }

    public Long getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(Long numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

}
