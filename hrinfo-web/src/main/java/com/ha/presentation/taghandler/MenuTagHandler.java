package com.ha.presentation.taghandler;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.ha.entity.model.custom.UserMenuDTO;

public class MenuTagHandler extends BodyTagSupport {

    public int doStartTag() throws JspException {
    	try {
	    	List<UserMenuDTO> menus = (List<UserMenuDTO>)pageContext.getSession().getAttribute("userMenus");
	    	
	    	if(menus != null && menus.size() > 0) {
	    		String st = renderTag(menus);
	    		JspWriter out = pageContext.getOut();
	    		out.print(st);
	    	}
    	} catch (java.io.IOException e) {
    	    throw new JspException(e);
    	}
    	
        return SKIP_BODY;
    }
    
	public String renderTag(List<UserMenuDTO> menusDTOs) {	
        StringBuffer buf = new StringBuffer();
        
        if (menusDTOs == null) return "";
        
        for (UserMenuDTO menuDTO : menusDTOs) {
        	
        	buf.append("<td align=\"").append("center").append("\"").append(" />");
        	
        	buf.append("<a");
        	buf.append(" href=\"javaScript:showPage1('").append(menuDTO.getActionUrl()).append("')\"");
        	buf.append(" title=\"").append(menuDTO.getToolTipText()).append("\"").append(">");
        	buf.append("<img src=\"").append(menuDTO.getImageUrl()).append("\" border=\"0\"").append(" />");
        	buf.append("</a>");
        	buf.append("</td>");  
                
        }
        return buf.toString();
    }
}
