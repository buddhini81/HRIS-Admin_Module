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
public class ValueType implements Serializable {

    private Long valueTypeDid;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getValueTypeDid() {
        return valueTypeDid;
    }

    public void setValueTypeDid(Long valueTypeDid) {
        this.valueTypeDid = valueTypeDid;
    }

    
}
