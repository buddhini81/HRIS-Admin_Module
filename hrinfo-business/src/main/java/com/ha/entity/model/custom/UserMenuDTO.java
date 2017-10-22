package com.ha.entity.model.custom;

import java.io.Serializable;

public class UserMenuDTO implements Serializable {
	
	private Long menuDid;
	private Long userRoleDid;
	private String actionUrl;
	private String imageUrl;
	private String toolTipText;
	
	public Long getMenuDid() {
		return menuDid;
	}
	public void setMenuDid(Long menuDid) {
		this.menuDid = menuDid;
	}
	public Long getUserRoleDid() {
		return userRoleDid;
	}
	public void setUserRoleDid(Long userRoleDid) {
		this.userRoleDid = userRoleDid;
	}
	public String getActionUrl() {
		return actionUrl;
	}
	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getToolTipText() {
		return toolTipText;
	}
	public void setToolTipText(String toolTipText) {
		this.toolTipText = toolTipText;
	}
	
}
