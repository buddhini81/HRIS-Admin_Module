<%@page import="com.ha.presentation.util.PresentationConstants"%>
<%@page import="com.ha.entity.model.custom.EmployeeSearchResultsDTO"%>
<%@page import="com.ha.entity.model.custom.SearchCols"%>
<%@page import="com.ha.entity.model.custom.TestDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page buffer = "16kb" %>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://hrapp.customtag" prefix="ac" %>



<s:form action="searchEmployee.action" method="post">
        
            <fieldset id="employeeSearchForm">
                <legend class="all_text_style_orange">Search Criteria</legend>
            
             
             <table width="750" border="0" align="center">
            
              <tr>
                  <td class="all_text_style_green"><s:text name="hrinfo.employee.search.firstname"/></td>
                  <td><s:textfield name="employeeSearchDTO.firstName" cssClass="common_style_black"/></td>
                  <td class="all_text_style_green"><s:text name="hrinfo.employee.search.lastname"/></td>
                  <td><s:textfield name="employeeSearchDTO.lastName" cssClass="common_style_black"/></td>
              </tr>
              <tr>
                  <td class="all_text_style_green"><s:text name="hrinfo.employee.search.nicnumber"/></td>
                  <td><s:textfield name="employeeSearchDTO.NICNo" cssClass="common_style_black"/></td>
                  <td class="all_text_style_green"><s:text name="hrinfo.employee.search.epfnumber"/></td>
                  <td><s:textfield name="employeeSearchDTO.EPFNo" cssClass="common_style_black"/></td>
              </tr>
              <tr>
                  <td class="all_text_style_green"><s:text name="hrinfo.employee.search.company"/></td>
                  <td>
                  	<div id="searchbycompanies">
	                  	<s:select name="employeeSearchDTO.companyDid"
						list="searchCompanyList" listKey="value" listValue="label"
						cssClass="selectStyleRequired"
						onchange="loadSearchByDepartments(this.value);" /><br />
					</div>
						
                  </td>
                  <td class="all_text_style_green"><s:text name="hrinfo.employee.search.department"/></td>
                  <td>
                      	<div id="searchbydepartments">
							<s:select
							name="employeeSearchDTO.departmentDid" list="searchDepartmentList"
							listKey="value" listValue="label" cssClass="selectStyleRequired" /><br />
						</div>
                  </td>
              </tr>
              <tr>
                <td class="all_text_style_green"><s:text name="hrinfo.employee.search.designation"/></td>
                <td><s:textfield name="employeeSearchDTO.designation" cssClass="common_style_black"/></td>
                <td class="all_text_style_green"><s:text name="hrinfo.employee.search.typeofcontract"/></td>
                <td>
                	<s:select name="employeeSearchDTO.contractTypeDid"
                        list="searchContractTypeList"
       					listKey="value"
       					listValue="label"
       					cssClass="select_style_black2"
                      />
                </td>
              </tr>   
            </table>
            </fieldset>
            <table width="132" border="0" align="center">
                <tr>
                  <td width="44" height="43">
                      <s:submit type="image" method="searchEmployee" src="../images/GEN Report (small).png" title="Search"/> 
                  </td>
                  <td width="24">&nbsp;</td>
                  <td width="50">
                      <s:a id="btnClear_search"
						   value="javascript:showPage('/hrapp/employee/resetSearchPage.action');"
					       onmouseover="showToolTip(this,'Clear');">
					       <img src="../images/Cancel (small).png" border="0" />
				      </s:a>
                  </td>
                </tr>
            </table>
           <fieldset id="employeeSearchResults">
            	<legend class="all_text_style_orange">Search Results</legend>
            <table width="750" border="0" align="center">
            <tr>
                <td>
                	<div id="disTbl" style="width:737px;overflow: scroll;">
	                    <display:table name="sessionScope.empSearchResults" pagesize="50" htmlId="dispTable" requestURI="searchEmployee.action" sort="list" id="rec" decorator="decorators.EmployeeSearchResultsDecorator">
	                    	<%
	                    		EmployeeSearchResultsDTO row = (EmployeeSearchResultsDTO) rec;
	                    	
	                    		
	                    		Long empDid = row != null ? row.getEmployeeDid() : null ;
	                    	%>
	                   		<display:column property="epfNo" titleKey="hrinfo.employee.searchresults.epfnumber" style="white-space: nowrap;"/>
	                   		<display:column property="firstName" titleKey="hrinfo.employee.searchresults.firstname" style="white-space: nowrap;"/>
	                   		<display:column property="lastName" titleKey="hrinfo.employee.searchresults.lastname" style="white-space: nowrap;"/>
	                   		<display:column property="nicNo" titleKey="hrinfo.employee.searchresults.nicnumber" style="white-space: nowrap;"/>
	                   		<display:column property="gender" titleKey="hrinfo.employee.searchresults.gender" style="white-space: nowrap;"/>
	                   		<display:column property="dob" titleKey="hrinfo.employee.searchresults.dateofbirth" style="white-space: nowrap;"/>
	                   		<display:column property="contractType" titleKey="hrinfo.employee.searchresults.typeofcontract" style="white-space: nowrap;"/>
	                   		<display:column property="companyName" titleKey="hrinfo.employee.searchresults.company" style="white-space: nowrap;"/>
	                   		<display:column property="department" titleKey="hrinfo.employee.searchresults.department" style="white-space: nowrap;"/>
	                   		<display:column property="designation" titleKey="hrinfo.employee.searchresults.designation" style="white-space: nowrap;"/>
	                   		<ac:accesscontrol functionDid="<%=PresentationConstants.FUNC_DID_VIEW_EMPLOYEE%>">
		                   		<display:column>                  		
		                   			<a href="javaScript:goToPage('/hrapp/employee/viewEmployeeData.action','employeeDid','<%=empDid%>')" title="View Employee Information"><img src="../images/view.jpg"  border="0" /></a>
		                   		</display:column>
		                   	</ac:accesscontrol>
		                   	<ac:accesscontrol functionDid="<%=PresentationConstants.FUNC_DID_EDIT_EMPLOYEE%>">
		                   		<display:column>
		                   			<a href="javaScript:goToPage('/hrapp/employee/editEmployeeData.action','employeeDid','<%=empDid%>')" title="Edit Employee Information"><img src="../images/edit.gif"  border="0" /></a>
		                   		</display:column>
		                   	</ac:accesscontrol>
		                   	<ac:accesscontrol functionDid="<%=PresentationConstants.FUNC_DID_VIEW_EMPLOYEE_HISTORY%>">
		                   		<display:column>
		                   			<%
		                   				String url = "/hrapp/employee/showEmployeeHistory.action?did="+empDid;
		                   			%>
		                   			<a href="javaScript:popup_large('<%=url%>')" title="View Employee History"><img src="../images/history.jpg"  border="0" /></a>
		                   		</display:column>
		                   	</ac:accesscontrol>
		                   	<ac:accesscontrol functionDid="<%=PresentationConstants.FUNC_DID_ADD_EMPLOYEE_HISTORY%>">
		                   		<display:column>
		                   			<%
		                   			String url = "/hrapp/employee/loadEmployeeHistoryPage.action?did="+empDid;
		                   			%>
		                   			<a href="javaScript:popup_small('<%=url%>')" title="Add Employee History"><img src="../images/add_history.jpg"  border="0" /></a>
		                   		</display:column>
		                   	</ac:accesscontrol>
		                   	<ac:accesscontrol functionDid="<%=PresentationConstants.FUNC_DID_ADD_EMPLOYEE_QUALIFICATIONS%>">
		                   		<display:column>
		                   			<%
		                   			String url = "/hrapp/employee/loadEmployeeQualificationPage.action?did="+empDid;
		                   			%>
		                   			<a href="javaScript:popup_large('<%=url%>')" title="Add Qualifications"><img src="../images/qualifications.jpg"  border="0" /></a>
		                   		</display:column>
		                   	</ac:accesscontrol>
		                   	<ac:accesscontrol functionDid="<%=PresentationConstants.FUNC_DID_ADD_EMPLOYEE_SKILLS%>">
		                   		<display:column>
		                   			<%
		                   			String url = "/hrapp/employee/loadEmployeeSkillPage.action?did="+empDid;
		                   			%>
		                   			<a href="javaScript:popup_large('<%=url%>')" title="Add Skills"><img src="../images/skills.jpg"  border="0" /></a>
		                   		</display:column>
		                   	</ac:accesscontrol>
		                   	<ac:accesscontrol functionDid="<%=PresentationConstants.FUNC_DID_ADD_EMPLOYEE_PROPERTY_ASSIGNMENT%>">
		                   		<display:column>
		                   			<%
		                   			String url = "/hrapp/employee/loadPropertyAssignmentPage.action?did="+empDid;
		                   			%>
		                   			<a href="javaScript:popup_large('<%=url%>')" title="Company Property Assignment"><img src="../images/comp_property.jpg"  border="0" /></a>
		                   		</display:column>
		                   	</ac:accesscontrol>
		                   	<ac:accesscontrol functionDid="<%=PresentationConstants.FUNC_DID_ADD_EMPLOYEE_RESIGNATION_AND_CLEARENCE%>">
		                   		<display:column>
		                   			<%
		                   			String url = "/hrapp/employee/loadResignationAndClearence.action?did="+empDid;
		                   			%>
		                   			<a href="javaScript:popup_large('<%=url%>')" title="Resignation & Clearence"><img src="../images/resign.jpg"  border="0" /></a>
		                   		</display:column>
		                   	</ac:accesscontrol>
	                    </display:table>
	                </div>
                </td>
            </tr>
            </table>
           </fieldset>
         
</s:form>

