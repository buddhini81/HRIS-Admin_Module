<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page buffer = "16kb" %>
<%@ page import="com.ha.entity.model.custom.UserProfileDTO" %>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://hrapp.customtag" prefix="ac" %>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Employee Information</title>
<link rel="stylesheet" href="../css/displaytag.css" type="text/css" media="all" />
<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
<script src="../js/commonscripts/common.js" type="text/javascript"></script>
<script src="../js/searchscripts/search.js" type="text/javascript"></script>
<link href="../css/commonstyles/common.css" rel="stylesheet" type="text/css" media="screen" />
<sx:head />
<style type="text/CSS">  

.dojoTabPaneWrapper {
 border : 2px solid silver;
 _zoom: 1; /* force IE6 layout mode so top border doesnt disappear */
 display: block;
 clear: both;
 overflow: hidden;
}

.dojoTab {
 position : relative;
 float : left;
 padding-left : 9px;
 border-bottom : 1px solid silver;
 background :  url(../images/tab_image.jpg) no-repeat left top;
 cursor: pointer;
 white-space: nowrap;
 z-index: 3;
 font-weight: bold;
 font-size:11px;
 font-family:Verdana, Arial, Helvetica, sans-serif;
 width: 125px;
}

.dojoTab div {
 display : block;
 padding : 4px 15px 4px 6px;
 background :  url(../images/tab_image.jpg) no-repeat right top;
 color : #333;
 font-size : 90%;
}

.dojoTab.current {
 padding-bottom : 0px;
 padding-left : 0px;
 margin-left: 0px;
 border-bottom : 0;
 background-position : 0 -150px;
 background : url(../images/tab_image.jpg);
}

.dojoTab.current div {
 padding-bottom : 5px;
 margin-bottom : -1px;
 border-bottom : 0;
 background-position : 100% -150px;
 background : url(../images/tab_image.jpg);
}


</style>   

<script language="javascript">

var secondsBeforeExpire = ${pageContext.session.maxInactiveInterval};
var timeToDecide = 15; // Give client 15 seconds to choose.
setTimeout(function() {
    alert('Your session is about to expire in ' + timeToDecide + ' seconds!')
}, (secondsBeforeExpire - timeToDecide) * 1000);

    $(document).ready(function() {  
    	searchTabOnload();
    	dojo.addOnLoad(dojoWidgetReady);	
    });
    
    
function searchTabOnload() {
	if($('#searchbycompanies select').val() == -1) {
        $('#searchbydepartments').attr("disabled","disabled")
    } else {
    	var companyDid = $('#searchbycompanies select').val();
    	$('#searchbydepartments').removeAttr("disabled");
    }
}

function dojoTabSelector() {
	var activeTab = '<s:property value="activeTab" escape="false"/>'; 
  	var tabContainer = dojo.widget.byId("mytabp1");
	
	if(activeTab != null && activeTab != '' && activeTab == 'view') {
		tabContainer.selectTab("view");
	}else if(activeTab != null && activeTab != '' && activeTab == 'search') {
		tabContainer.selectTab("search");
	}
}

function dojoWidgetReady() {
	dojoTabSelector();
}

</script>

</head>
<body>
<div id="ajxDspId">
<table width="876" border="0" align="center" bgcolor="#C0C0C0">
  <tr>
    <td width="870" valign="top">
      <table width="824" height="75" border="0" cellpadding="0" cellspacing="0" align="center" bgcolor="#C0C0C0">
        <tr>
          <td width="206" height="70" align="right" bgcolor="#C0C0C0">
             
          </td>
		  <td width="206" height="70" align="right" bgcolor="#C0C0C0">
             
          </td>
          <td width="206" height="70" align="right" bgcolor="#C0C0C0">
             
          </td>
          <td width="206" height="70" align="right" bgcolor="#C0C0C0">
           <s:if test="%{#session.userProfile != null}">
          	 <img src="../images/user.jpg"  border="0" ></img>&nbsp;<s:property value="%{#session.userProfile.companyName}"/>&#58;<s:property value="%{#session.userProfile.userName}"/><br/>
             <a href="javascript:showPage1('/hrapp/login/logout.action')" title="Logout">Logout</a>
           </s:if>
          </td>
        </tr>
        <tr>
            <td width="824" colspan="4" height="5">&nbsp;</td>
        </tr>
      </table>
      <table width="824" border="0" align="center" bgcolor="#C0C0C0">
        <tr>
			<ac:menu/>
        </tr>
      </table>
      <table width="824" border="0" align="center" bgcolor="#FFFFFF">
        <tr>
          <td width="816" height="28">&nbsp;</td>
        </tr>
      </table>
      <table width="824" border="0" align="center" bgcolor="#FFFFFF">
        <tr>
          <td width="817" valign="top">
          <div id="tabDiv">
           	<sx:tabbedpanel id="mytabp1">
           	  <sx:div id="search" label="Search" theme="ajax" labelposition="top">
			  	<s:include value="employee_search.jsp" />
			  </sx:div>	
			  <sx:div id="view" label="View" theme="ajax" labelposition="top">
			  	<s:include value="view_employee_details.jsp" />
			  </sx:div>			
			 </sx:tabbedpanel>		
			 </div>
          </td>
        </tr>
      </table>

    </td>
  </tr>
</table>
</div>
</body>

</html>
