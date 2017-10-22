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
public class SingleAddedAttribute implements Serializable {

    private Long singleAddedAttributeDid;
    private Long attributeGroupDid;
    private Long attributeSubGroupDid;
    private Long attributeDistributionDid;
    private ValueType valueType;
    private Long did;
    private String tObject;
    private String attribValue;

    

    public Long getSingleAddedAttributeDid() {
		return singleAddedAttributeDid;
	}

	public void setSingleAddedAttributeDid(Long singleAddedAttributeDid) {
		this.singleAddedAttributeDid = singleAddedAttributeDid;
	}

	public Long getAttributeGroupDid() {
		return attributeGroupDid;
	}

	public void setAttributeGroupDid(Long attributeGroupDid) {
		this.attributeGroupDid = attributeGroupDid;
	}

	public Long getAttributeSubGroupDid() {
		return attributeSubGroupDid;
	}

	public void setAttributeSubGroupDid(Long attributeSubGroupDid) {
		this.attributeSubGroupDid = attributeSubGroupDid;
	}
	
	public Long getAttributeDistributionDid() {
		return attributeDistributionDid;
	}

	public void setAttributeDistributionDid(Long attributeDistributionDid) {
		this.attributeDistributionDid = attributeDistributionDid;
	}

	public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public String gettObject() {
        return tObject;
    }

    public void settObject(String tObject) {
        this.tObject = tObject;
    }

    public String getAttribValue() {
        return attribValue;
    }

    public void setAttribValue(String attribValue) {
        this.attribValue = attribValue;
    }   

    public ValueType getValueType() {
        return valueType;
    }

    public void setValueType(ValueType valueType) {
        this.valueType = valueType;
    }
   
}
