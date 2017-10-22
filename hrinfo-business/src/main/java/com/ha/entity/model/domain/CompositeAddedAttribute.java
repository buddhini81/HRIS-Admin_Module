package com.ha.entity.model.domain;

import java.io.Serializable;

public class CompositeAddedAttribute implements Serializable {
	
	private Long compositeAddedAttributeDid;
	private Long attributeGroupDid;
	private Long attributeSubGroupDid;
	private Long did;
	private String tObject;

	
	public Long getCompositeAddedAttributeDid() {
		return compositeAddedAttributeDid;
	}
	public void setCompositeAddedAttributeDid(Long compositeAddedAttributeDid) {
		this.compositeAddedAttributeDid = compositeAddedAttributeDid;
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
	
}
