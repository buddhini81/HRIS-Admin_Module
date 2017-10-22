<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page buffer = "16kb" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://hrapp.customtag" prefix="ac" %>
<%@page import="com.ha.entity.model.custom.EmployeeSkillDTO"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Add Skills</title>
<link rel="stylesheet" href="../css/displaytag.css" type="text/css" media="all" />
<link href="../css/jquery.loadmask.css" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../js/jquery.loadmask.js"></script>
<script language="javascript" type="text/javascript" src="../js/datetimepicker.js"></script>
<script src="../js/commonscripts/common.js" type="text/javascript"></script>
<script src="../js/employeescripts/employee.js" type="text/javascript"></script>
<link href="../css/commonstyles/common.css" rel="stylesheet" type="text/css" media="screen" />

</head>
<sx:head />
<body>

<s:form action="saveEmployeeSkill.action" method="post">
<s:hidden name="skillDTO.skillDid"/>
<s:hidden name="skillDTO.employeeDid"/>
<fieldset id="SkillForm">
	<legend class="all_text_style_orange">Add Skill</legend>
	        <table width="250" border="0" align="left">
	        	<tr>
					<td width="125" class="all_text_style_green"><s:text name="hrinfo.employee.skill.type" /></td>
					<td width="125">
						<s:select name="skillDTO.skillTypeDid"
							list="skillTypeTypeList" listKey="value" listValue="label"
							cssClass="selectStyleRequired"/><br/>
						<s:if
							test="fieldErrors.get('skillDTO.skillTypeDid') != null && fieldErrors.get('skillDTO.skillTypeDid').size() > 0">
							<div class="styleError">
								<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
								<s:property value="fieldErrors.get('skillDTO.skillTypeDid').get(0)" />
							</div>
						</s:if>
					</td>
				</tr>
				<tr>
					<td width="125" class="all_text_style_green"><s:text name="hrinfo.employee.skill.description" /></td>
					<td width="125">
						<s:textarea name="skillDTO.description"  cssClass="common_style_black"/><br/>
						<s:if
							test="fieldErrors.get('skillDTO.description') != null && fieldErrors.get('skillDTO.description').size() > 0">
							<div class="styleError">
								<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
								<s:property value="fieldErrors.get('skillDTO.description').get(0)" />
							</div>
						</s:if>
					</td>
				</tr>
				<tr>
					<td width="83.33"></td>
					<td width="83.33"><s:submit method="saveEmployeeSkill" key="hrinfo.users.save"></s:submit></td>
					<td width="83.33"></td>
				</tr>
			</table>
</fieldset>
<fieldset id="employeeSkills">
            	<legend class="all_text_style_orange">All Skills</legend>
            <table width="250" border="0" align="left">
            <tr>
                <td>
                	<div id="disTbl" style="width:250;overflow: scroll;">
	                    <display:table name="sessionScope.empSkillResults" pagesize="50" htmlId="dispTable" requestURI="" sort="list" id="rec" decorator="">
	                    	<%
	                    		EmployeeSkillDTO row = (EmployeeSkillDTO) rec;
	                    	
	                    		
	                    		Long skillDid = row != null ? row.getSkillDid() : null ;
	                    		Long empDid = row != null ? row.getEmployeeDid() : null ;
	                    	%>
	                   		<display:column property="skillType" titleKey="hrinfo.employee.skill.searchresults.type" style="white-space: nowrap;"/>
	                   		<display:column property="description" titleKey="hrinfo.employee.skill.searchresults.description" style="white-space: nowrap;"/>
	                   		
	                   		<display:column>
		                   		<a href="javaScript:goToPage('/hrapp/employee/editEmployeeSkill.action','did','<%=skillDid%>')" title="Edit"><img src="../images/edit.gif"  border="0" /></a>
		                   	</display:column>
		                   	<display:column>
		                   		<%
		              				String url = "/hrapp/employee/deleteEmployeeSkill.action?did="+empDid+"&skillDid="+skillDid;
		              			%>
		              			<a href="javaScript:deleteRow('<%=url%>')" title="Delete"><img src="../images/delete.jpg"  border="0" /></a>
		                   	</display:column>
	                    </display:table>
	                </div>
                </td>
            </tr>
            </table>
           </fieldset>
         
</s:form>
</body>
</html>