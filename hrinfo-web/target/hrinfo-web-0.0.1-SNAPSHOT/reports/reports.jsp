<%@page import="com.ha.entity.model.custom.SearchCols"%>
<%@page import="com.ha.entity.model.custom.TestDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page buffer = "16kb" %>
<%@ page import="com.ha.entity.model.custom.UserProfileDTO" %>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://hrapp.customtag" prefix="ac" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Generate Reports</title>
<link rel="stylesheet" href="../css/displaytag.css" type="text/css" media="all" />
<script src="../js/AC_RunActiveContent.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.js"></script>
<script src="../js/commonscripts/common.js" type="text/javascript"></script>
<script src="../js/reportscripts/report.js" type="text/javascript"></script>
<link href="../css/commonstyles/common.css" rel="stylesheet" type="text/css" media="screen" />
<sx:head />
<s:head />

<script language="javascript">

var secondsBeforeExpire = ${pageContext.session.maxInactiveInterval};
var timeToDecide = 15; // Give client 15 seconds to choose.
setTimeout(function() {
    alert('Your session is about to expire in ' + timeToDecide + ' seconds!')
}, (secondsBeforeExpire - timeToDecide) * 1000);


    $(document).ready(function() {
        if(document.getElementById('dobRange_check').checked == true)
        {
            document.getElementById("dobRange_div").style.visibility = "visible";
            document.getElementById("dobRange_div").style.display = "block";
        }
        if(document.getElementById('djRange_check').checked == true)
        {
            document.getElementById("djRange_div").style.visibility = "visible";
            document.getElementById("djRange_div").style.display = "block";
        }
        if(document.getElementById('dueConfDateRange_check').checked == true)
        {
            document.getElementById("dueConfDateRange_div").style.visibility = "visible";
            document.getElementById("dueConfDateRange_div").style.display = "block";
        }
       
    });
</script>
</head>
<body>
<s:include value="../confirmation_popup.jsp" />
<div id="ajxDspId">
    <s:form action="searchReport.action" method="post">
    <s:hidden name="searchDobRangeCheckedValue" id="dobRange_chkedValue"/>
    <s:hidden name="searchJoinDateRangeCheckedValue" id="jdRange_chkedValue"/>
    <s:hidden name="searchConfDateRangeCheckedValue" id="confdRange_chkedValue"/>
    <s:hidden name="searchDueconfDateRangeCheckedValue" id="dueConfdRange_chkedValue"/>
<table width="876" border="0" align="center" bgcolor="#C0C0C0">
  <tr>
    <td width="870" valign="top">
      <table width="824" border="0" align="center" bgcolor="#C0C0C0">
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
          <td width="816" height="28" class="page_title_text_style"><s:text name="hrinfo.report.title" /></td>
        </tr>
      </table>
      <table width="824" border="0" align="center" bgcolor="#FFFFFF">
        <tr>
          <td valign="top">           
            <fieldset id="reportSearchForm">
            	<legend class="all_text_style_orange">Report Columns</legend>
            <table width="750" border="0" align="center" bgcolor="#FFFFFF">
              <tr>
                <td valign="baseline">
					<s:optiontransferselect
					     cssClass="select_style_black"
					     name="boxOneSelected"
					     leftTitle="All Columns"
					     rightTitle="Selected Columns"
					     list="boxOneData"
					     listKey="fieldName"
					     listValue="fieldLabel"
					     multiple="true"
					     doubleList="boxTwoData"
					     doubleName="boxTwoSelected"
					     doubleListKey="fieldName"
					     doubleListValue="fieldLabel"
					     doubleCssClass="select_style_black"
					     buttonCssClass="button_style"
					     allowUpDownOnLeft="false"
					     allowUpDownOnRight="false"
					     template="optiontransferselect.ftl"
					     templateDir="struts2_custom_templates"
					     addAllToLeftLabel="<-- Add All"
					     addAllToRightLabel="Add All -->"
					     addToLeftLabel="<-- Add"
					     addToRightLabel="Add -->"
					     selectAllLabel="Select All"	
					     doubleId="box2"	
					     id="box1"
					     addToRightOnclick="addItemsToBox()"	
					     addToLeftOnclick="addItemsToBox()"	
					     addAllToRightOnclick="addItemsToBox()"
					     addAllToLeftOnclick="addItemsToBox()"
					 />
				
                </td>                              
              </tr>
            </table>
            </fieldset>
                        
            <fieldset id="reportSearchForm">
                <legend class="all_text_style_orange">Report Search Criteria</legend>
            
             
             <table width="750" border="0" align="center">
            
              <tr>

                  <td class="all_text_style_green"><s:text name="hrinfo.report.search.firstname"/></td>
                  <td><s:textfield name="firstName" cssClass="common_style_black"/></td>
                  <td class="all_text_style_green"><s:text name="hrinfo.report.search.lastname"/></td>
                  <td><s:textfield name="lastName" cssClass="common_style_black"/></td>
                  <td class="all_text_style_green"><s:text name="hrinfo.report.search.gender"/></td>
                  <td>
                      <s:select name="gender" 
						list="genderData"
       					listKey="value"
       					listValue="label"
       					cssClass="select_style_black2"
						/>
                            
                  </td>
              </tr>
              <tr>
          
                  <td class="all_text_style_green"><s:text name="hrinfo.report.search.epfnumber"/></td>
                  <td><s:textfield name="epfNumber" cssClass="common_style_black"/></td>
                  <td class="all_text_style_green"><s:text name="hrinfo.report.search.nicnumber"/></td>
                  <td><s:textfield name="nicNumber" cssClass="common_style_black"/></td>
                  <td class="all_text_style_green"><s:text name="hrinfo.report.search.typeofcontract"/></td>
                  <td>
                      <s:select name="contractTypeDid"
                        list="contractTypeData"
       					listKey="value"
       					listValue="label"
       					cssClass="select_style_black2"
                      />
                            
                  </td>           
              </tr>
              <tr>
                  <td class="all_text_style_green"><s:text name="hrinfo.report.search.company"/></td>
                  <td><s:textfield name="company" cssClass="common_style_black"/></td>
                  <td class="all_text_style_green"><s:text name="hrinfo.report.search.department"/></td>
                  <td><s:textfield name="department" cssClass="common_style_black"/></td>
                  <td class="all_text_style_green"><s:text name="hrinfo.report.search.designation"/></td>
                  <td><s:textfield name="designation" cssClass="common_style_black"/></td>
              </tr>
              <tr>
              	  <td class="all_text_style_green">
		          	<s:text name="hrinfo.report.search.daterange"/><br/><br/>
		            <s:text name="hrinfo.report.search.dateofbirth"/>
		          </td>
		          <td>
		          	<input type="checkbox" value="dobRange" id="dobRange_check" onClick ="searchRange(this.value)" 
		            <s:if test="searchDobRangeCheckedValue.equalsIgnoreCase('yes') ==  true">checked</s:if>/><br/><br/>
		                <sx:datetimepicker name="dateOfBirthFrom" displayFormat="dd-MM-yyyy" cssClass="datebox_style_black" cssStyle="width:15em;"/>
						<div id="dobRange_div" style="visibility:hidden;display:none">
		                <sx:datetimepicker name="dateOfBirthTo" displayFormat="dd-MM-yyyy" id="dateOfBirthTo" cssClass="datebox_style_black"/>                        
		                </div>
		          </td>
                  
                  <td class="all_text_style_green">
                      <s:text name="hrinfo.report.search.daterange"/><br/><br/>
                      <s:text name="hrinfo.report.search.datejoined"/>
                  </td>
                  <td>
                      <input type="checkbox" value="djRange" id="djRange_check" onClick ="searchRange(this.value)" <s:if test="SearchJoinDateRangeCheckedValue.equalsIgnoreCase('yes') == true">checked</s:if>/><br/><br/>
                      <sx:datetimepicker name="joinDateFrom" displayFormat="dd-MM-yyyy" cssClass="datebox_style_black"/>
                       
                      <div id="djRange_div" style="visibility:hidden;display:none">
                      	  <sx:datetimepicker name="joinDateTo" displayFormat="dd-MM-yyyy" id="joinDateTo" cssClass="datebox_style_black"/>                          
                      </div>
                  </td>
                  <td class="all_text_style_green">
                     <s:text name="hrinfo.report.search.daterange"/>  <br/><br/>
                     <s:text name="hrinfo.report.search.dueconfirmationdate"/> 
                 </td>
                 <td>
                    
                     <input type="checkbox" value="dueConfDateRange" id="dueConfDateRange_check" onClick ="searchRange(this.value)" <s:if test="SearchDueconfDateRangeCheckedValue.equalsIgnoreCase('yes') == true">checked</s:if>/><br/><br/>
                     <sx:datetimepicker name="dueConfDateFrom" displayFormat="dd-MM-yyyy" cssClass="datebox_style_black"/>
                                          
                     <div id="dueConfDateRange_div" style="visibility:hidden;display:none">
                     	  <sx:datetimepicker name="dueConfDateTo" displayFormat="dd-MM-yyyy" id="dueConfDateTo" cssClass="datebox_style_black"/>                          
                     </div>
                 </td>       
              </tr>          
            </table>
            </fieldset>
            <fieldset id="reportSorting">
                <legend class="all_text_style_orange">Sorting Criteria</legend>
            
             
             <table width="750" border="0" align="center">
            
              <tr>

                  <td class="all_text_style_green"><s:text name="hrinfo.report.search.sortby"/></td>
                  <td class="all_text_style_green" colspan="5"><s:radio name="sortBy" list="sortByData" listKey="value" listValue="label" /></td>
                
              </tr>
              <tr>

                  <td class="all_text_style_green"><s:text name="hrinfo.report.search.sortingorder"/></td>
                  <td class="all_text_style_green" colspan="5"><s:radio name="sortOrder" list="sortingOrderData" listKey="value" listValue="label" /></td>
              </tr>
            </table>
            </fieldset>
            <table width="132" border="0" align="center">
                <tr>
                  <td width="44" height="43">
                      <s:submit type="image" method="searchReport" src="../images/GEN Report (small).png" title="Generate Report" value="hasj"/> 
                  </td>
                  <td width="24">&nbsp;</td>
                  <td width="50">
                  	<s:a id="btnClear"
						 value="javascript:showPage('/hrapp/reports/resetPage.action');"
						 onmouseover="showToolTip(this,'Clear');">
						 <img src="../images/Cancel (small).png" border="0" />
					</s:a>
                  </td>
                </tr>
            </table>
           <fieldset id="reportSearchResults">
            	<legend class="all_text_style_orange">Search Results</legend>
            <table width="750" border="0" align="center">
            <tr>
                <td>
                	<div id="disTbl" style="width:737px;overflow: scroll;">
	                    <display:table name="sessionScope.results" pagesize="50" export="true" htmlId="dispTable" requestURI="searchReport.action" sort="list" decorator="decorators.ReportResultsDecorator">
	
	                        <display:setProperty name="export.csv" value="false" />
	                        <display:setProperty name="export.xml" value="false" />
	                        <display:setProperty name="export.rtf" value="false"/>
	                        <display:setProperty name="export.pdf.filename" value="EmpReport.pdf"/>
	                        <display:setProperty name="export.excel.filename" value="EmpReport.xls"/>
	                        <display:setProperty name="export.csv.filename" value="false"/>
	                        
	
	                        <%
	                                    //String pName = (thisbean.getPageNumberParamName() != null && thisbean.getPageNumberParamName() != "") ? thisbean.getPageNumberParamName() : "";
	                                    //String pVal = (thisbean.getPageNumber() != 0) ? String.valueOf(thisbean.getPageNumber()) : "";
	
	                                    //String url = "Student.do?pageNumParamName=" + pName + "&pageNumParamValue=" + pVal;
	                        %>
	                        
						  <%
						  	List<SearchCols> selectedColList = request.getAttribute("selectedColList") != null ? (List<SearchCols>)request.getAttribute("selectedColList") : null;
						  	if(selectedColList != null && selectedColList.size() >0 ) {
						  	for (SearchCols col : selectedColList) {
						  		if(col != null) {%>
	                        	<display:column property="<%=col.getFieldName()%>" title="<%=col.getFieldLabel()%>" style="white-space: nowrap;"/>
	                      <%}}
	                      }%>
	
	
	                    </display:table>
	                </div>
                </td>
            </tr>
            </table>
           </fieldset>
         </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</s:form>
</div>
</body>
</html>
