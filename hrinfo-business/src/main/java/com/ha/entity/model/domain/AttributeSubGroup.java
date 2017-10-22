package com.ha.entity.model.domain;

import java.io.Serializable;

public class AttributeSubGroup implements Serializable {
	
	private Long attributeSubGroupDid;
	private AttributeGroup attributeGroup;
	private String subgroupName;
	private String description;
	
	public Long getAttributeSubGroupDid() {
		return attributeSubGroupDid;
	}
	public void setAttributeSubGroupDid(Long attributeSubGroupDid) {
		this.attributeSubGroupDid = attributeSubGroupDid;
	}
	public AttributeGroup getAttributeGroup() {
		return attributeGroup;
	}
	public void setAttributeGroup(AttributeGroup attributeGroup) {
		this.attributeGroup = attributeGroup;
	}
	public String getSubgroupName() {
		return subgroupName;
	}
	public void setSubgroupName(String subgroupName) {
		this.subgroupName = subgroupName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
