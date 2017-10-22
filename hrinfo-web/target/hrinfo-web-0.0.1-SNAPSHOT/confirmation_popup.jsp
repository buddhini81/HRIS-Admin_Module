<%@ page import="com.ha.entity.model.custom.ConfirmationAlertDTO" %>
<%@ page import="java.util.List" %>

<%@ taglib prefix="s" uri="/struts-tags" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" >
<style type="text/CSS">

.movable{
width:250px;
background :white transparent;
cursor:move;
z-index: 9999;
position: absolute;
}


.inner{
width:250px;
border: 2px double grey solid;
background : #C0C0C0;
position: relative;
vertical-align: middle;
}

div.close {
    background: url("../images/closebox.png") no-repeat scroll 0 0 transparent;
    bottom: 12px;
    cursor: pointer;
    float: right;
    height: 30px;
    left: 25px;
    position: relative;
    width: 30px;
	z-index: 9999;
}  

.tableHeader {
font-family: cursive;
font-weight: bold; 
color: black;
font-size: x-small;
}

.tableBody {
font-family: cursive;
font-weight: bold; 
color: red;
font-size: x-small;
}

table.fixed { table-layout:fixed; }
table.fixed td { overflow: hidden; }

</style>

<script language="javascript">
jQuery(function($) {
	$(document).ready(function() {
    	$('#movable').draggable(); 	
    });

	$("div.close").click(function() {
		$("#inner").fadeOut("normal");  
		$("#movable").fadeOut("normal");  
	});
});
</script>
</head>
<body>	
<s:if test="%{#session.dueConfData != null && #session.dueConfData.size() >0}">
	<div id="movable" class="movable">
	<div id="inner" class="inner">
	<div id="close" class="close"></div>
	<table class="fixed" border="0">
    	<col width="70px" ></col>
    	<col width="80px" ></col>
    	<col width="100px" ></col>
	    <tr>
	        <th class="tableHeader">EPF No</th>
	        <th class="tableHeader">Company</th>
	        <th class="tableHeader">Due Conf Date</th>
	    </tr>
	    <s:iterator value="%{#session.dueConfData}" var="confData" status="confDataStatus">
  			<tr><td class="tableBody"><s:property value="%{#confData.epfNo}"/></td><td class="tableBody"><s:property value="%{#confData.companyId}"/></td>
					<td class="tableBody"><s:date name="%{#confData.dueConfDate}" format="dd-MM-yyyy" /></td></tr>	
		</s:iterator>
	    
	</table>
	</div>
	</div>
</s:if>
</body>
</html>