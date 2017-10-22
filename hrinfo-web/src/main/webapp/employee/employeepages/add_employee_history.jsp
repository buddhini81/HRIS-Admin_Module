<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page buffer = "16kb" %>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Add Employee History</title>
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
<s:form action="saveEmployeeHistory.action" method="post">
<s:hidden name="historyDTO.employeeDid"/>
	        <table width="250" border="0" align="left">
	        	<tr>
					<td width="125" class="all_text_style_green"><s:text name="hrinfo.employee.history.date" /></td>
					<td width="125">
						<sx:datetimepicker name="historyDTO.date" displayFormat="dd-MM-yyyy" cssClass="datebox_style_black_required" /><br/>
						<s:if test="fieldErrors.get('historyDTO.date') != null && fieldErrors.get('historyDTO.date').size() > 0">
							<div class="styleError">
								<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
								<s:property value="fieldErrors.get('historyDTO.date').get(0)" />
							</div>
					    </s:if>
					</td>
				</tr>
				<tr>
					<td width="125" class="all_text_style_green"><s:text name="hrinfo.employee.history.information" /></td>
					<td width="125">
						<s:textarea name="historyDTO.description"  cssClass="common_style_black"/><br/>
						<s:if test="fieldErrors.get('historyDTO.description') != null && fieldErrors.get('historyDTO.description').size() > 0">
							<div class="styleError">
								<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
								<s:property value="fieldErrors.get('historyDTO.description').get(0)" />
							</div>
					    </s:if>
					</td>
				</tr>
				<tr>
					<td width="83.33"></td>
					<td width="83.33"><s:submit method="saveEmployeeHistory" key="hrinfo.users.save"></s:submit></td>
					<td width="83.33"></td>
				</tr>
				<tr>
                      <td colspan="2" class="styleError" align="center" >                                         
                          <s:property value="message"/>
                      </td>
                </tr>
			</table>
</s:form>
</body>
</html>
