package com.ha.presentation.taghandler;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class AccessRightsTagHandler extends BodyTagSupport {
	
	private Long functionDid;

	@SuppressWarnings("deprecation")
	public int doStartTag() {                   
		List<Long> userFunctions = (List<Long>)pageContext.getSession().getAttribute("userFunctions");
			
		if(userFunctions.contains(functionDid)) {
			return EVAL_BODY_TAG;
		}
		return SKIP_BODY;
	}


	public Long getFunctionDid() {
		return functionDid;
	}

	public void setFunctionDid(Long functionDid) {
		this.functionDid = functionDid;
	} 
}

