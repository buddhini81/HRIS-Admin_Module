package com.ha.entity.model.domain;

import java.io.Serializable;

public class CompositeAttributeValue implements Serializable {

	private Long compositeAttributeValueDid;
	private CompositeAddedAttribute compositeAddedAttribute;
	private Long attributeDistributionDid;
	private ValueType valueType;
	private String attributeValue;
	
	public Long getCompositeAttributeValueDid() {
		return compositeAttributeValueDid;
	}
	public void setCompositeAttributeValueDid(Long compositeAttributeValueDid) {
		this.compositeAttributeValueDid = compositeAttributeValueDid;
	}	
	public CompositeAddedAttribute getCompositeAddedAttribute() {
		return compositeAddedAttribute;
	}
	public void setCompositeAddedAttribute(
			CompositeAddedAttribute compositeAddedAttribute) {
		this.compositeAddedAttribute = compositeAddedAttribute;
	}
	public Long getAttributeDistributionDid() {
		return attributeDistributionDid;
	}
	public void setAttributeDistributionDid(Long attributeDistributionDid) {
		this.attributeDistributionDid = attributeDistributionDid;
	}
	public ValueType getValueType() {
		return valueType;
	}
	public void setValueType(ValueType valueType) {
		this.valueType = valueType;
	}
	public String getAttributeValue() {
		return attributeValue;
	}
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	
}
