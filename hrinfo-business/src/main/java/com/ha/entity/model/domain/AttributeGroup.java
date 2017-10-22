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
public class AttributeGroup implements Serializable {

    private Long attributeGroupDid;
    private String name;
    private String description;

    public Long getAttributeGroupDid() {
        return attributeGroupDid;
    }

    public void setAttributeGroupDid(Long attributeGroupDid) {
        this.attributeGroupDid = attributeGroupDid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
