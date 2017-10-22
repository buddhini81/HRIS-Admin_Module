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
public class Student implements Serializable {
    // unique student id

    private int studentId;
    // first name of the student
    private String firstName;
    // last name of the student
    private String lastName;
    // address of the student
    private String address;
    // set of courses that the student is related/registered for
    //private Set courses = new HashSet();

    public Student() {
    }

    /**
     * Creates a new instance of Student.
     * @param firstName first name.
     * @param lastName last name.
     * @param address address.
     */
    public Student(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    public Set getCourses() {
//        return courses;
//    }
//    public void setCourses(Set courses) {
//        this.courses = courses;
//    }
    public String clear() {
        firstName = "";
        lastName = "";
        address = "";
        return "clear";
    }
}
