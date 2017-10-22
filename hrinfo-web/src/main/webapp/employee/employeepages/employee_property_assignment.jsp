<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page buffer = "16kb" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://hrapp.customtag" prefix="ac" %>
<%@page import="com.ha.entity.model.custom.CompanyPropertyAssignmentDTO"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Assign Company Property</title>
<link rel="stylesheet" href="../css/displaytag.css" type="text/css" media="all" />
<link href="../css/jquery.loadmask.css" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../js/jquery.loadmask.js"></script>
<script language="javascript" type="text/javascript" src="../js/datetimepicker.js"></script>
<script src="../js/commonscripts/common.js" type="text/javascript"></script>
<script src="../js/employeescripts/employee.js" type="text/javascript"></script>
<link href="../css/commonstyles/common.css" rel="stylesheet" type="text/css" media="screen" />

<script language="javascript">

function testDisable() {
	dojo.widget.byId("propertyAssignmentDTO.assignedDate").attr("disabled","disabled");
}

</script>
</head>
<sx:head />
<body>

<s:form action="savePropertyAssignment.action" method="post">
<s:hidden name="propertyAssignmentDTO.propertyAssignmentDid"/>
<s:hidden name="propertyAssignmentDTO.employeeDid"/>
<fieldset id="propertyAssignmentForm">
	<legend class="all_text_style_orange">Assign Property</legend>
	        <table width="450" border="0" align="left">
	        	<tr>
					<td width="225" class="all_text_style_green"><s:text name="hrinfo.employee.property.type" /></td>
					<td width="225">
						<s:select name="propertyAssignmentDTO.propertyTypeDid"
							list="propertyTypeList" listKey="value" listValue="label"
							cssClass="selectStyleRequired"/><br/>
						<s:if
							test="fieldErrors.get('propertyAssignmentDTO.propertyTypeDid') != null && fieldErrors.get('propertyAssignmentDTO.propertyTypeDid').size() > 0">
							<div class="styleError">
								<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
								<s:property value="fieldErrors.get('propertyAssignmentDTO.propertyTypeDid').get(0)" />
							</div>
						</s:if>
					
					</td>
				</tr>
				<tr>
					<td width="225" class="all_text_style_green"><s:text name="hrinfo.employee.property.assigneddate" /></td>
					<td width="225">
						<sx:datetimepicker  id="propertyAssignmentDTO.assignedDate" name="propertyAssignmentDTO.assignedDate" displayFormat="dd-MM-yyyy" cssClass="datebox_style_black_required" /><br/>
						<s:if
							test="fieldErrors.get('propertyAssignmentDTO.assignedDate') != null && fieldErrors.get('propertyAssignmentDTO.assignedDate').size() > 0">
							<div class="styleError">
								<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
								<s:property value="fieldErrors.get('propertyAssignmentDTO.assignedDate').get(0)" />
							</div>
						</s:if>
					</td>
				</tr>
				<tr>
					<td width="225" class="all_text_style_green"><s:text name="hrinfo.employee.property.returneddate" /></td>
					<td width="225">
					<s:if test="propertyAssignmentDTO.disableReturnDate == true">
						<sx:datetimepicker name="propertyAssignmentDTO.returnedDate" displayFormat="dd-MM-yyyy" cssClass="datebox_style_black" disabled="true"/><br/><br/>
					</s:if>
					<s:else>
						<sx:datetimepicker name="propertyAssignmentDTO.returnedDate" displayFormat="dd-MM-yyyy" cssClass="datebox_style_black" /><br/><br/>
					</s:else>
					</td>
				</tr>
				<tr>
					<td width="225" class="all_text_style_green"><s:text name="hrinfo.employee.property.comment" /></td>
					<td width="225">
						<s:textarea name="propertyAssignmentDTO.comment"  cssClass="common_style_black"/><br/>
					</td>
				</tr>
				<tr>
					<td width="150" colspan="3">
						<s:submit method="savePropertyAssignment" key="hrinfo.common.label.save"></s:submit>
					</td>
				</tr>
			</table>
</fieldset>
<fieldset id="propertyAssignments">
            	<legend class="all_text_style_orange">All Assigned</legend>
            <table width="450" border="0" align="left">
            <tr>
                <td>
                	<div id="disTbl" style="width:450;overflow: scroll;">
	                    <display:table name="sessionScope.empPropAssignResults" pagesize="50" htmlId="dispTable" requestURI="" sort="list" id="rec" decorator="">
	                    	<%
	                    	    CompanyPropertyAssignmentDTO row = (CompanyPropertyAssignmentDTO) rec;
	                    	
	                    		
	                    		Long assignmentDid = row != null ? row.getPropertyAssignmentDid() : null ;
	                    		Long empDid = row != null ? row.getEmployeeDid() : null ;
	                    	%>
	                   		<display:column property="propertyName" titleKey="hrinfo.employee.property.searchresults.name" style="white-space: nowrap;"/>
	                   		<display:column property="assignedDate" titleKey="hrinfo.employee.property.searchresults.assigneddate" style="white-space: nowrap;"/>
	                   		<display:column property="returnedDate" titleKey="hrinfo.employee.property.searchresults.returneddate" style="white-space: nowrap;"/>
	                   		<display:column>
		                   		<a href="javaScript:goToPage('/hrapp/employee/editPropertyAssignment.action','did','<%=assignmentDid%>')" title="Edit"><img src="../images/edit.gif"  border="0" /></a>
		                   	</display:column>
		                   	<display:column>
		                   		<%
		              				String url = "/hrapp/employee/deletePropertyAssignment.action?did="+empDid+"&assignmentDid="+assignmentDid;
		              			%>
		              			<a href="javaScript:deleteRow('<%=url%>')" title="Delete"><img src="../images/delete.jpg"  border="0" /></a>
		                   	</display:column>
		                   	<display:column>
		                   		<%
		              				String url = "/hrapp/employee/returnCompanyProperty.action?did="+assignmentDid;
		              			%>
		              			<a href="javascript:showPage('<%=url%>');" title="Update Return"><img src="../images/return_prop.jpg"  border="0" /></a>
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
