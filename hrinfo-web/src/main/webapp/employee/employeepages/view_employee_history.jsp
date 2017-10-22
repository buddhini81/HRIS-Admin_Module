<%@page import="com.ha.entity.model.custom.HistoryDTO"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page buffer = "16kb" %>
<%@page import="com.ha.presentation.util.PresentationConstants"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://hrapp.customtag" prefix="ac" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>View Employee History</title>
<link rel="stylesheet" href="../css/displaytag.css" type="text/css" media="all" />
<link href="../css/jquery.loadmask.css" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../js/jquery.loadmask.js"></script>
<script language="javascript" type="text/javascript" src="../js/datetimepicker.js"></script>
<script src="../js/commonscripts/common.js" type="text/javascript"></script>
<script src="../js/employeescripts/employee.js" type="text/javascript"></script>
<link href="../css/commonstyles/common.css" rel="stylesheet" type="text/css" media="screen" />

</head>

<body>
<table>
	<tr>
	<td>
		<div id="disTbl" style="width:737px;overflow: scroll;">
			<display:table name="sessionScope.empHistory" pagesize="10" htmlId="dispTable" requestURI="showEmployeeHistory.action" sort="list" id="rec" decorator="decorators.HistoryResultsDecorator">
				<%
	                HistoryDTO row = (HistoryDTO) rec;
	                Long hisDid = row != null ? row.getDid(): null ;
	                Long did = row != null ? row.getEmployeeDid(): null ;
	            %>
				<display:column property="date" titleKey="hrinfo.employee.history.date"/>	  
				<display:column property="description" titleKey="hrinfo.employee.history.information"/>	 
				<ac:accesscontrol functionDid="<%=PresentationConstants.FUNC_DID_ADD_EMPLOYEE_HISTORY%>">
		            <display:column>
		              <%
		              	String url = "/hrapp/employee/deleteEmployeeHistory.action?did="+did+"&hisDid="+hisDid;
		              %>
		              <a href="javaScript:deleteRow('<%=url%>')" title="Delete"><img src="../images/delete.jpg"  border="0" /></a>
		            </display:column>
		        </ac:accesscontrol>  		 				   
			</display:table>
		</div>
	</td>
	</tr>
</table>
</body>
</html>
