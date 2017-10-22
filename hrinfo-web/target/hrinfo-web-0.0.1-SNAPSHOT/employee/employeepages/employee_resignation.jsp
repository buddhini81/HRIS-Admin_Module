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
<title>Resignation & Clearence</title>
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

<s:form action="resignationAndClearence.action" method="post">
<s:hidden name="resignationDTO.employeeDid"/>
<fieldset id="QualificationForm">
	<legend class="all_text_style_orange">Resignation</legend>
	        <table width="450" border="0" align="left">
	        	<tr>
					<td width="225" class="all_text_style_green"><s:text name="hrinfo.employee.resignation.acceptance" /></td>
					<td width="225">
						<s:checkbox name="resignationDTO.isResignationAcceptanceIssued" fieldValue="true"/>
					</td>
				</tr>
			</table>
</fieldset>
<fieldset id="QualificationForm">
	<legend class="all_text_style_orange">Clearence</legend>
	        <table width="450" border="0" align="left">
	        	<tr>
					<td width="225" class="all_text_style_green"><s:text name="hrinfo.employee.resignation.workhandedover" /></td>
					<td width="225">
						<s:checkbox name="resignationDTO.isWorkHandedOver" fieldValue="true"/>
					</td>
				</tr>
				<tr>
					<td width="225" class="all_text_style_green"><s:text name="hrinfo.employee.resignation.compprophandedover" /></td>
					<td width="225">
						<s:checkbox name="resignationDTO.isCompanyPropHandedOver" fieldValue="true"/><br/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<fieldset id="propertyAssigned">
					<legend class="all_text_style_orange">Company Property Assigned</legend>
						<table id="allowanceTable" width="450" align="center">
							<s:if
								test="resignationDTO.getArrCompanyProperty() != null && resignationDTO.getArrCompanyProperty().size() > 0">
								<tr>
										<th width="225"><s:text name="hrinfo.employee.resignation.propertyname" /></th>
										<th width="225"><s:text name="hrinfo.employee.resignation.returned" /></th>
								</tr>
								<s:iterator value="resignationDTO.arrCompanyProperty" status="stat" var="prop1">
									<tr>
										<td width="225"><s:textfield value="%{#prop1.propertyName}"
											name="resignationDTO.companyPropertyTypeDTO[%{#stat.count - 1}].propertyName"
											readonly="true" cssStyle="border:0;color: #FF8000" /></td>
										<td width="225"><s:textfield
											value="%{#prop1.strReturnValue}"
											name="resignationDTO.companyPropertyTypeDTO[%{#stat.count - 1}].strReturnValue"
											readonly="true" cssStyle="border:0;color: #FF8000" /></td>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<tr>
										<td colspan="2"><s:text name="hrinfo.common.label.none" /></td>
								</tr>
							</s:else>
						</table>
					</fieldset>
					</td>
				</tr>
				<tr>
					<td width="225" class="all_text_style_green"><s:text name="hrinfo.employee.resignation.financialclearencedone" /></td>
					<td width="225">
						<s:checkbox name="resignationDTO.isFinancialClearenceDone" fieldValue="true"/><br/>
					</td>
				</tr>
				<tr>
					<td width="225" class="all_text_style_green"><s:text name="hrinfo.employee.resignation.serviceletter" /></td>
					<td width="225">
						<s:checkbox name="resignationDTO.isServiceLetterIssued" fieldValue="true"/><br/>
					</td>
				</tr>
				<tr>
					<td width="225" class="all_text_style_green"><s:text name="hrinfo.employee.resignation.bcardhandedover" /></td>
					<td width="225">
						<s:checkbox name="resignationDTO.isBcardHandedOver" fieldValue="true"/><br/>
					</td>
				</tr>
				<tr>
					<td width="225" class="all_text_style_green"><s:text name="hrinfo.employee.resignation.grativitypaid" /></td>
					<td width="225">
						<s:checkbox name="resignationDTO.isGrativityPaid" fieldValue="true"/><br/>
					</td>
				</tr>
				<tr>
					<td colspan="3"><s:submit method="resignationAndClearence" key="hrinfo.users.save"></s:submit></td>
				</tr>
			</table>
</fieldset>
         
</s:form>
</body>
</html>
