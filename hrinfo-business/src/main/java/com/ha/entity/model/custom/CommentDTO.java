package com.ha.entity.model.custom;

import java.io.Serializable;

public class CommentDTO implements Serializable {

	private Long commentTypeDid;
	private String comment;
	
	public Long getCommentTypeDid() {
		return commentTypeDid;
	}
	public void setCommentTypeDid(Long commentTypeDid) {
		this.commentTypeDid = commentTypeDid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
