/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.entity.model.domain;

import java.io.Serializable;

/**
 *
 * @author Buddhini
 */
public class DepartmentAssignment implements Serializable {

    private Long deptAssignmentDid;
    private Employee employee;
    private Department department;
    

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Long getDeptAssignmentDid() {
        return deptAssignmentDid;
    }

    public void setDeptAssignmentDid(Long deptAssignmentDid) {
        this.deptAssignmentDid = deptAssignmentDid;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
