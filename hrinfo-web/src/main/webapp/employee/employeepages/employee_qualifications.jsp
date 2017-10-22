<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page buffer = "16kb" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://hrapp.customtag" prefix="ac" %>
<%@page import="com.ha.entity.model.custom.EmployeeQualificationDTO"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Add Qualifications</title>
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

<s:form action="saveEmployeeQualification.action" method="post">
<s:hidden name="qualificationDTO.qualificationDid"/>
<s:hidden name="qualificationDTO.employeeDid"/>
<fieldset id="QualificationForm">
	<legend class="all_text_style_orange">Add Qualification</legend>
	        <table width="450" border="0" align="left">
	        	<tr>
					<td width="225" class="all_text_style_green"><s:text name="hrinfo.employee.qualification.type" /></td>
					<td width="225">
						<s:select name="qualificationDTO.qualificationTypeDid"
							list="qualificationTypeList" listKey="value" listValue="label"
							cssClass="selectStyleRequired"/><br/>
						<s:if
							test="fieldErrors.get('qualificationDTO.qualificationTypeDid') != null && fieldErrors.get('qualificationDTO.qualificationTypeDid').size() > 0">
							<div class="styleError">
								<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
								<s:property value="fieldErrors.get('qualificationDTO.qualificationTypeDid').get(0)" />
							</div>
						</s:if>
					</td>
				</tr>
				<tr>
					<td width="225" class="all_text_style_green"><s:text name="hrinfo.employee.qualification.description" /></td>
					<td width="225">
						<s:textarea name="qualificationDTO.description"  cssClass="common_style_black"/><br/>
						<s:if
							test="fieldErrors.get('qualificationDTO.description') != null && fieldErrors.get('qualificationDTO.description').size() > 0">
							<div class="styleError">
								<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
								<s:property value="fieldErrors.get('qualificationDTO.description').get(0)" />
							</div>
						</s:if>
					</td>
				</tr>
				<tr>
					<td width="225" class="all_text_style_green"><s:text name="hrinfo.employee.qualification.year" /></td>
					<td width="225">
						<s:textfield name="qualificationDTO.year"  cssClass="common_style_black" maxlength="4"/><br/>
						<s:if
							test="fieldErrors.get('qualificationDTO.year.invalid') != null && fieldErrors.get('qualificationDTO.year.invalid').size() > 0">
							<div class="styleError">
								<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
								<s:property value="fieldErrors.get('qualificationDTO.year.invalid').get(0)" />
							</div>
						</s:if>
					</td>
				</tr>
				<tr>
					<td width="225" class="all_text_style_green"><s:text name="hrinfo.employee.qualification.comment" /></td>
					<td width="225">
						<s:textarea name="qualificationDTO.comment"  cssClass="common_style_black"/><br/>
					</td>
				</tr>
				<tr>
					<td width="150"></td>
					<td width="150"><s:submit method="saveEmployeeQualification" key="hrinfo.users.save"></s:submit></td>
					<td width="150"></td>
				</tr>
			</table>
</fieldset>
<fieldset id="employeeQualifications">
            	<legend class="all_text_style_orange">All Qualifications</legend>
            <table width="450" border="0" align="left">
            <tr>
                <td>
                	<div id="disTbl" style="width:450;overflow: scroll;">
	                    <display:table name="sessionScope.empQualificationResults" pagesize="50" htmlId="dispTable" requestURI="" sort="list" id="rec" decorator="">
	                    	<%
	                    		EmployeeQualificationDTO row = (EmployeeQualificationDTO) rec;
	                    	
	                    		
	                    		Long qualDid = row != null ? row.getQualificationDid() : null ;
	                    		Long empDid = row != null ? row.getEmployeeDid() : null ;
	                    	%>
	                   		<display:column property="qualificationType" titleKey="hrinfo.employee.qualification.searchresults.type" style="white-space: nowrap;"/>
	                   		<display:column property="description" titleKey="hrinfo.employee.qualification.searchresults.description" style="white-space: nowrap;"/>
	                   		<display:column property="year" titleKey="hrinfo.employee.qualification.searchresults.year" style="white-space: nowrap;"/>
	                   		<display:column>
		                   		<a href="javaScript:goToPage('/hrapp/employee/editEmployeeQualification.action','did','<%=qualDid%>')" title="Edit"><img src="../images/edit.gif"  border="0" /></a>
		                   	</display:column>
		                   	<display:column>
		                   		<%
		              				String url = "/hrapp/employee/deleteEmployeeQualification.action?did="+empDid+"&qualDid="+qualDid;
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
