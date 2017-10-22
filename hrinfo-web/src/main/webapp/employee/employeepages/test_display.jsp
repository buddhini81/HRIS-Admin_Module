
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page buffer = "16kb" %>
<%@page import="com.ha.entity.model.custom.EmployeeSearchResultsDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Test</title>
<link rel="stylesheet" href="../css/displaytag.css" type="text/css" media="all" />

</head>

<body>
<%
List<EmployeeSearchResultsDTO> empList = new ArrayList<EmployeeSearchResultsDTO>();
EmployeeSearchResultsDTO row1 = new EmployeeSearchResultsDTO();
row1.setEmployeeDid(1L);
row1.setFirstName("asdas");
empList.add(row1);
request.getSession().setAttribute("empSearchResults",empList);
%>
<table>
	<tr>
	<td>

			<display:table name="sessionScope.empSearchResults" pagesize="10" htmlId="dispTable" requestURI="" sort="list">
				
				<display:column title="Test1" property="employeeDid" style="width:50%;"/>  
				<display:column title="Test2" property="firstName"/>	 
					 				   
			</display:table>

	</td>
	</tr>
</table>
</body>
</html>
