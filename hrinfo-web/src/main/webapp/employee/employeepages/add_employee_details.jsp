<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page buffer="16kb"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<s:form action="/employee/saveEmployeeData.action" method="post">
	<s:hidden name="pageStatus" id="pageStatus" />
	<s:hidden name="comments" id="comments" />
	<s:hidden name="employeeDTO.parentCompanyDid" id="parentCompanyDid" />
	<s:hidden name="employeeDTO.employeeDid" id="employeeDid" />
	<s:hidden name="employeeDTO.departmentAssignmentDid" id="departmentAssignmentDid" />
	<s:hidden name="employeeDTO.age" id="age" />
	
	<p class="heading"><img src="../images/add.gif" width="12" height="12" border="0" />&nbsp;General Information</p>
	<div class="content">
	<table width="750" border="0" align="center">
		<tr>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.firstname" /></td>
			<td width="187.5"><s:textfield name="employeeDTO.firstName" cssClass="common_style_black_required" maxlength="20" /><br />
				<s:if
					test="fieldErrors.get('employeeDTO.firstName') != null && fieldErrors.get('employeeDTO.firstName').size() > 0">
					<div class="styleError">
						<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
						<s:property value="fieldErrors.get('employeeDTO.firstName').get(0)" />
					</div>
				</s:if>
			</td>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.gender" /></td>
			<td width="187.5" class="all_text_style_green"><s:radio name="employeeDTO.gender" list="genderList" listKey="value" listValue="label" cssClass="radioStyleRequired"/><br />
				<s:if
					test="fieldErrors.get('employeeDTO.gender') != null && fieldErrors.get('employeeDTO.gender').size() > 0">
					<div class="styleError">
						<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp;
						<s:property value="fieldErrors.get('employeeDTO.gender').get(0)" />
					</div>
				</s:if>
			</td>
		</tr>
		<tr>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.middlename" /></td>
			<td width="187.5"><s:textfield name="employeeDTO.middleName" cssClass="common_style_black" maxlength="20" /></td>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.nicnumber" /></td>
			<td width="187.5"><s:textfield name="employeeDTO.nicNo" cssClass="common_style_black" maxlength="10" onblur="javascript:checkNICNumber()" id="nicNo"/><br />
				<s:if
					test="fieldErrors.get('employeeDTO.nicNo') != null && fieldErrors.get('employeeDTO.nicNo').size() > 0">
					<div class="styleError">
						<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
						<s:property value="fieldErrors.get('employeeDTO.nicNo').get(0)" />
					</div>
				</s:if> 
				<s:if
					test="fieldErrors.get('employeeDTO.nicNo.invalid') != null && fieldErrors.get('employeeDTO.nicNo.invalid').size() > 0">
					<div class="styleError">
						<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
						<s:property value="fieldErrors.get('employeeDTO.nicNo.invalid').get(0)" />
					</div>
				</s:if>
			</td>
		</tr>
		<tr>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.lastname" /></td>
			<td width="187.5"><s:textfield name="employeeDTO.lastName" cssClass="common_style_black" maxlength="50" /><br />
				<s:if
					test="fieldErrors.get('employeeDTO.lastName') != null && fieldErrors.get('employeeDTO.lastName').size() > 0">
					<div class="styleError">
						<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
						<s:property value="fieldErrors.get('employeeDTO.lastName').get(0)" />
					</div>
				</s:if>
			</td>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.passportnumber" /></td>
			<td width="187.5"><s:textfield name="employeeDTO.passportNo" cssClass="common_style_black" maxlength="10" /></td>
		</tr>
		<tr>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.dob" /></td>
			<td width="187.5"><sx:datetimepicker name="employeeDTO.dateOfBirth" displayFormat="dd-MM-yyyy" cssClass="common_style_black"/><br />
				<s:if
					test="fieldErrors.get('employeeDTO.dateOfBirth') != null && fieldErrors.get('employeeDTO.dateOfBirth').size() > 0">
					<div class="styleError">
						<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
						<s:property value="fieldErrors.get('employeeDTO.dateOfBirth').get(0)" />
					</div>
				</s:if> 
				<s:if
					test="fieldErrors.get('employeeDTO.dateOfBirth.invalid') != null && fieldErrors.get('employeeDTO.dateOfBirth.invalid').size() > 0">
					<div class="styleError">
						<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
						<s:property value="fieldErrors.get('employeeDTO.dateOfBirth.invalid').get(0)" />
					</div>
				</s:if>
			</td>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.licenceno" /></td>
			<td width="187.5"><s:textfield name="employeeDTO.drivingLicenseNo" maxlength="10" cssClass="common_style_black"/></td>
		</tr>
	</table>
	</div>
	
	<p class="heading"><img src="../images/add.gif" width="12" height="12" border="0" />&nbsp;Employment Details</p>
	<div class="content">
	<table width="750" border="0" align="center">
	    <tr id="testTr" style="visibility:hidden;display:none;">
			<td class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.parentcompany" /></td>
			<td width="187.5">
				<s:textfield name="employeeDTO.parentCompanyName" cssClass="common_style_black" readonly="true"/>
			</td>
			<td width="187.5" class="all_text_style_green"></td>
			<td width="187.5">
				
			</td>
		</tr>
		
		<tr>
			<td class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.company" /></td>
			<td width="187.5">
			<div id="company">
				<s:select name="employeeDTO.companyDid"
				list="companyList" listKey="value" listValue="label"
				cssClass="selectStyleRequired"
				onchange="loadCompanySpecificData(this.value,'onChange');" /><br />
				<s:if
					test="fieldErrors.get('employeeDTO.companyDid') != null && fieldErrors.get('employeeDTO.companyDid').size() > 0">
					<div class="styleError">
						<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
						<s:property value="fieldErrors.get('employeeDTO.companyDid').get(0)" />
					</div>
				</s:if>
			</div>
			</td>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.companyepfnumber" /></td>
			<td width="187.5"><s:textfield name="employeeDTO.companyEPFNumber" cssClass="common_style_black" maxlength="10" id="companyEPFNumber" readonly="true"/><br />
				<s:if
					test="fieldErrors.get('employeeDTO.companyEPFNumber.invalid') != null && fieldErrors.get('employeeDTO.companyEPFNumber.invalid').size() > 0">
					<div class="styleError">
						<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
						<s:property value="fieldErrors.get('employeeDTO.companyEPFNumber.invalid').get(0)" />
					</div>
				</s:if>
			</td>
		</tr>
		<tr>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.department" /></td>
			<td width="187.5">
			<div id="departments">
				<s:select id="departments_list"
				name="employeeDTO.departmentDid" list="departmentList"
				listKey="value" listValue="label" cssClass="selectStyleRequired" /><br />
				<s:if
					test="fieldErrors.get('employeeDTO.departmentDid') != null && fieldErrors.get('employeeDTO.departmentDid').size() > 0">
					<div class="styleError">
						<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
						<s:property value="fieldErrors.get('employeeDTO.departmentDid').get(0)" />
					</div>
				</s:if>
			</div>
			</td>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.epfnumber" /></td>
			<td width="187.5"><s:textfield name="employeeDTO.epfNo" cssClass="common_style_black" maxlength="10" id="epfNumbeer" onblur="javascript:checkEPFNumber()" onKeyUp="numericFilter(this);"/><br />
				<s:if
					test="fieldErrors.get('employeeDTO.epfNo') != null && fieldErrors.get('employeeDTO.epfNo').size() > 0">
					<div class="styleError">
						<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
						<s:property value="fieldErrors.get('employeeDTO.epfNo').get(0)" />
					</div>
				</s:if>
				<s:if
					test="fieldErrors.get('employeeDTO.epfNo.invalid') != null && fieldErrors.get('employeeDTO.epfNo.invalid').size() > 0">
					<div class="styleError" id="err_epfNo">
						<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
						<s:property value="fieldErrors.get('employeeDTO.epfNo.invalid').get(0)" />
					</div>
				</s:if>
			</td>
		</tr>
		<tr>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.designation" /></td>
			<td width="187.5">
				<s:textfield name="employeeDTO.designation" cssClass="common_style_black" maxlength="50" />
				<br />
				<s:if
					test="fieldErrors.get('employeeDTO.designation') != null && fieldErrors.get('employeeDTO.designation').size() > 0">
					<div class="styleError">
						<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
						<s:property value="fieldErrors.get('employeeDTO.designation').get(0)" />
					</div>
				</s:if>
			</td>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.datejoined" /></td>
			<td width="187.5"><sx:datetimepicker name="employeeDTO.dateJoined" displayFormat="dd-MM-yyyy" cssClass="common_style_black"/><br />
			<s:if
				test="fieldErrors.get('employeeDTO.dateJoined') != null && fieldErrors.get('employeeDTO.dateJoined').size() > 0">
				<div class="styleError">
					<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
					<s:property value="fieldErrors.get('employeeDTO.dateJoined').get(0)" />
				</div>
			</s:if> 
			<s:if
				test="fieldErrors.get('employeeDTO.dateJoined.invalid') != null && fieldErrors.get('employeeDTO.dateJoined.invalid').size() > 0">
				<div class="styleError">
					<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
					<s:property value="fieldErrors.get('employeeDTO.dateJoined.invalid').get(0)" />
				</div>
			</s:if>
			</td>		
		</tr>
		<tr>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.empnumber" /></td>
			<td width="187.5">
				<input type="text" name="employeeDTO.employeeNo" id="empNo" readonly="readonly" class="common_style_black_required"/>
				<s:if
					test="fieldErrors.get('employeeDTO.employeeNo') != null && fieldErrors.get('employeeDTO.employeeNo').size() > 0">
					<div class="styleError">
						<img src="../images/alert.jpg" width="12 height="12" border="0" />&nbsp; 
						<s:property value="fieldErrors.get('employeeDTO.employeeNo').get(0)" />
					</div>
				</s:if> 			
			</td>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.dueconfdate" /></td>
			<td width="187.5"><sx:datetimepicker name="employeeDTO.dueConfDate" displayFormat="dd-MM-yyyy" cssClass="common_style_black"/><br />
				<s:if
					test="fieldErrors.get('employeeDTO.dueConfDate') != null && fieldErrors.get('employeeDTO.dueConfDate').size() > 0">
					<div class="styleError">
						<img src="../images/alert.jpg" width="12 height="12" border="0" />&nbsp; 
						<s:property value="fieldErrors.get('employeeDTO.dueConfDate').get(0)" />
					</div>
				</s:if> 
				<s:if
					test="fieldErrors.get('employeeDTO.dueConfDate.invalid') != null && fieldErrors.get('employeeDTO.dueConfDate.invalid').size() > 0">
					<div class="styleError">
						<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
						<s:property value="fieldErrors.get('employeeDTO.dueConfDate.invalid').get(0)" />
					</div>
				</s:if>
			</td>	
		</tr>
		<tr>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.contracttype" /></td>
			<td width="187.5">
				<div id="contracttype">
				<s:select
					name="employeeDTO.contractTypeDid" list="contractTypeList"
					listKey="value" listValue="label" cssClass="select_style_black2" 
					onchange="javascript:showOtherContrType(this.value)"/>
				<br />
				<s:if
					test="fieldErrors.get('employeeDTO.contractTypeDid') != null && fieldErrors.get('employeeDTO.contractTypeDid').size() > 0">
					<div class="styleError">
						<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
						<s:property value="fieldErrors.get('employeeDTO.contractTypeDid').get(0)" />
					</div>
				</s:if>
				</div>
			</td>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.confirmationdate" /></td>
			<td width="187.5">
			<sx:datetimepicker name="employeeDTO.confDate" cssClass="datebox_style_black" displayFormat="dd-MM-yyyy"/>
			<br />
				<s:if
					test="fieldErrors.get('employeeDTO.confDate.invalid') != null && fieldErrors.get('employeeDTO.confDate.invalid').size() > 0">
					<div class="styleError">
						<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
						<s:property value="fieldErrors.get('employeeDTO.confDate.invalid').get(0)" />
					</div>
				</s:if>
			</td>
		</tr>
		<tr>
			<td width="187.5" class="all_text_style_green"></td>
			<td width="187.5">
			<div id="otherContractType" style="visibility: hidden; display: none">
			<s:textfield name="employeeDTO.otherContractType" id="otherCtype" cssClass="common_style_black"/>
			</div>
			</td>
			<td width="187.5"></td>
			<td width="187.5"></td>
		</tr>
	</table>
	</div>
	<p class="heading"><img src="../images/add.gif" width="12" height="12" border="0" />&nbsp;Compensation &amp; Benefits</p>
	<div class="content">
	<table width="750" border="0" align="center">
		<tr>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.basicsalary" /></td>
			<td width="187.5">
				<s:textfield name="employeeDTO.basicSalary" cssClass="common_style_black" maxlength="11" id="basicSalary" onblur="javascript:validateBasicSal(this);" onfocus="javascript:clearField(this)"/>
				<br/>
			</td>
			<td width="187.5">
				<div id="div_bsal_Error" class="styleError" style="visibility: hidden;display: none;" ><img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp;<s:text name="hrinfo.employeemaster.addemployee.basicsalary.error" /></div>
			</td>
			<td width="187.5"></td>
		</tr>
		
		<tr>
			<td colspan="4">
			<fieldset id="allowanceInfo"><legend class="all_text_style_orange">&nbsp; Allowances </legend>
			<table width="550" border="0" align="center">
				<tr>
					<td width="183" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.allowancestype" /></td>
					<td width="183">
						<s:hidden name="rowId_allowance" id="rowId_allowance" />
						<s:hidden name="allowance_uniqueDid" id="allowance_uniqueDid" />
						<s:select name="allowanceType" id="allowanceType"
							list="allowanceList" listKey="value" listValue="label" cssClass="select_style_black2"/>
					</td>
					<td width="184">
					
					<div id="div_allowance_Error" style="display: none;">
						<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp;
						<s:textfield name="allowance_errorText" id="allowance_errorText" cssClass="styleError" cssStyle="border:0" readonly="true" /></div>
					
					</td>
				</tr>
				<tr>
					<td width="183" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.allowanceamount" /></td>
					<td width="183">
						<s:textfield name="amount" id="amount" cssClass="common_style_black" maxlength="8" />&nbsp;&nbsp;
						<a href="javascript:addAllowanceRow('allowanceTable');" onmouseover="showToolTip(this,'Add Allowance Information');">
							<img src="../images/add.gif" border="0" />
						</a>
					</td>
					<td width="184">
					<div id="div_allowanceamt_Error" style="display: none;">
						<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp;
						<s:textfield name="allowanceAmt_errorText" id="allowanceAmt_errorText" cssClass="styleError" cssStyle="border:0" readonly="true" /></div>
					</td>
				</tr>
			</table>
			<table id="allowanceTable" width="550" align="center">
				<s:if
					test="employeeDTO.getAllowances() != null && employeeDTO.getAllowances().size() > 0">
					<s:iterator value="employeeDTO.allowances" status="stat" var="allow">
						<tr>
							<td width="137"><s:hidden value="%{#allow.employeeAllowanceDid}"
								name="employeeDTO.allowances[%{#stat.count - 1}].employeeAllowanceDid"/></td>
							<td width="137"><s:hidden value="%{#allow.allowanceTypeDid}"
								name="employeeDTO.allowances[%{#stat.count - 1}].allowanceTypeDid"/></td>
							<td width="137"><s:textfield value="%{#allow.allowanceType}"
								name="employeeDTO.allowances[%{#stat.count - 1}].allowanceType"
								readonly="true" cssStyle="border:0;color: #FF8000" /></td>
							<td width="137"><s:textfield
								value="%{#allow.amount}"
								name="employeeDTO.allowances[%{#stat.count - 1}].amount"
								readonly="true" cssStyle="border:0;color: #FF8000" /></td>
							<td width="139">
								<s:a
									value="javascript:editAllowanceRow('allowanceTable',%{#stat.count - 1});"
									onmouseover="showToolTip(this,'Edit Allowance Information');">
									<img src="../images/edit.gif" border="0" />
								</s:a>
							</td>
						</tr>
					</s:iterator>
				</s:if>
			</table>
			</fieldset>
			</td>
		</tr>
	</table>
	</div>

	<p class="heading"><img src="../images/add.gif" width="12" height="12" border="0" />&nbsp;Contact Information</p>
	<div class="content">
	<table width="750" border="0" align="center">
		<tr>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.addressline1" /></td>
			<td width="187.5"><s:textfield name="employeeDTO.addressLine1" cssClass="common_style_black" maxlength="50" /></td>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.homeno" /></td>
			<td width="187.5"><s:textfield name="employeeDTO.homePhoneNo" cssClass="common_style_black" maxlength="10" /><br />
				<s:if
					test="fieldErrors.get('employeeDTO.homePhoneNo.invalid1') != null && fieldErrors.get('employeeDTO.homePhoneNo.invalid1').size() > 0">
					<div class="styleError">
						<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
						<s:property value="fieldErrors.get('employeeDTO.homePhoneNo.invalid1').get(0)" />
					</div>
				</s:if> 
				<s:if
					test="fieldErrors.get('employeeDTO.homePhoneNo.invalid2') != null && fieldErrors.get('employeeDTO.homePhoneNo.invalid2').size() > 0">
					<div class="styleError">
						<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
						<s:property value="fieldErrors.get('employeeDTO.homePhoneNo.invalid2').get(0)" />
					</div>
				</s:if>
			</td>
		</tr>
		<tr>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.addressline2" /></td>
			<td width="187.5"><s:textfield name="employeeDTO.addressLine2" cssClass="common_style_black" maxlength="50" /></td>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.mobileno" /></td>
			<td width="187.5"><s:textfield name="employeeDTO.mobileNo" cssClass="common_style_black" maxlength="10" /><br />
				<s:if
					test="fieldErrors.get('employeeDTO.mobileNo.invalid1') != null && fieldErrors.get('employeeDTO.mobileNo.invalid1').size() > 0">
					<div class="styleError">
						<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
						<s:property value="fieldErrors.get('employeeDTO.mobileNo.invalid1').get(0)" />
					</div>
				</s:if> 
				<s:if
					test="fieldErrors.get('employeeDTO.mobileNo.invalid2') != null && fieldErrors.get('employeeDTO.mobileNo.invalid2').size() > 0">
					<div class="styleError">
						<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
						<s:property value="fieldErrors.get('employeeDTO.mobileNo.invalid2').get(0)" />
					</div>
				</s:if>
			</td>
		</tr>
		<tr>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.addressline3" /></td>
			<td width="187.5"><s:textfield name="employeeDTO.addressLine3" cssClass="common_style_black" maxlength="50" /></td>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.email" /></td>
			<td width="187.5"><s:textfield name="employeeDTO.email" cssClass="common_style_black" maxlength="50" /><br />
				<s:if
					test="fieldErrors.get('employeeDTO.email') != null && fieldErrors.get('employeeDTO.email').size() > 0">
					<div class="styleError">
						<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
						<s:property value="fieldErrors.get('employeeDTO.email').get(0)" />
					</div>
				</s:if>
			</td>
		</tr>
	</table>
	</div>

	<p class="heading"><img src="../images/add.gif" width="12" height="12" border="0" />&nbsp;Personal Information</p>
	<div class="content">
	<table width="750" border="0" align="center">
		<tr>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.married" /></td>
			<td width="187.5" class="all_text_style_green">
				<s:radio name="employeeDTO.maritalStatus"
				list="maritalStatusList" listKey="value" listValue="label"
				onclick="activateSubPannels(this.value)" cssClass="" id="maritalStatus"/><br />
				<s:if
					test="fieldErrors.get('employeeDTO.maritalStatus') != null && fieldErrors.get('employeeDTO.maritalStatus').size() > 0">
					<div class="styleError">
					<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
					<s:property  value="fieldErrors.get('employeeDTO.maritalStatus').get(0)" /></div>
				</s:if>
			</td>
			<td width="187.5" class="all_text_style_green"></td>
			<td width="187.5"></td>
		</tr>
		<tr>
			<td colspan="4">
			<fieldset id="spouseInfo"><legend class="all_text_style_orange">&nbsp; Spouse </legend>
			<table width="550" border="0" align="center">
				<tr>
					<td width="183" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.spousename" /></td>
					<td width="183">
						<s:textfield name="employeeDTO.spouseName" cssClass="common_style_black" maxlength="50" id="spouseName"/><br/>
						<s:if
							test="fieldErrors.get('employeeDTO.spouseName') != null && fieldErrors.get('employeeDTO.spouseName').size() > 0">
							<div class="styleError" id="div_spousename_Error">
								<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
								<s:property value="fieldErrors.get('employeeDTO.spouseName').get(0)" />
							</div>
						</s:if>
					</td>
					<td width="184"></td>
				</tr>
				<tr>
					<td width="183" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.spousegender" /></td>
					<td width="183" class="all_text_style_green">
						<s:radio name="employeeDTO.spouseGender" list="genderList" listKey="value" listValue="label" id="employeeDTO.spouseGender"/><br/>
						<s:if
							test="fieldErrors.get('employeeDTO.spouseGender') != null && fieldErrors.get('employeeDTO.spouseGender').size() > 0">
							<div class="styleError" id="div_spousegender_Error">
								<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
								<s:property value="fieldErrors.get('employeeDTO.spouseGender').get(0)" />
							</div>
						</s:if>
					</td>
					<td width="184"></td>
				</tr>
				<tr>
					<td width="183" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.spousedob" /></td>
					<td width="183"><sx:datetimepicker name="employeeDTO.spouseDateOfBirth" displayFormat="dd-MM-yyyy" cssClass="datebox_style_black" id="spouseDateOfBirth"/><br />
						<s:if
							test="fieldErrors.get('employeeDTO.spouseDateOfBirth.invalid') != null && fieldErrors.get('employeeDTO.spouseDateOfBirth.invalid').size() > 0">
							<div class="styleError" id="div_spousedob_Error1">
								<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
								<s:property value="fieldErrors.get('employeeDTO.spouseDateOfBirth.invalid').get(0)" />
							</div>
						</s:if>
						<s:if
							test="fieldErrors.get('employeeDTO.spouseDateOfBirth') != null && fieldErrors.get('employeeDTO.spouseDateOfBirth').size() > 0">
							<div class="styleError" id="div_spousedob_Error2">
								<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
								<s:property value="fieldErrors.get('employeeDTO.spouseDateOfBirth').get(0)" />
							</div>
						</s:if>
					</td>
					<td width="184"></td>
				</tr>
			</table>
			</fieldset>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<fieldset id="childrenInfo"><legend class="all_text_style_orange">&nbsp; Children </legend>
			<table width="550" border="0" align="center">
				<tr>
					<td width="183" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.childname" /></td>
					<td width="183"><s:hidden name="rowId" id="rowId" /><s:textfield name="childName" maxlength="50" id="childName" cssClass="common_style_black" id="childName"/></td>
					<td width="184">
					<div id="div_childName_Error" style="display: none;">
						<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp;
						<s:textfield name="childName_errorText" id="childName_errorText" cssClass="styleError" cssStyle="border:0" readonly="true" /></div>
					</td>
				</tr>
				<tr>
					<td width="183" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.childgender" /></td>
					<td width="183" class="all_text_style_green"><s:radio name="childGender" list="genderList" listKey="value" listValue="label" id="childGender" /></td>
					<td width="184">
					<div id="div_childGender_Error" style="display: none;">
						<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp;
						<s:textfield name="childGender_errorText" id="childGender_errorText" cssClass="styleError" cssStyle="border:0" readonly="true" />
					</div>
					</td>
				</tr>
				<tr>
					<td width="183" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.childdob" /></td>
					<td width="183"><sx:datetimepicker name="childDateOfBirth" displayFormat="dd-MM-yyyy" id="childDateOfBirth" cssClass="datebox_style_black"/>&nbsp;&nbsp;
					<a href="javascript:addChildInfoRow('childrenTable');" onmouseover="showToolTip(this,'Add Child Information');" id="btnAddChild"><img src="../images/add.gif" border="0" /></a>
					</td>
					<td width="184">
					<div id="div_childDateOfBirth_Error" style="display: none;">
						<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp;
						<s:textfield name="childDateOfBirth_errorText" id="childDateOfBirth_errorText" cssClass="styleError" cssStyle="border:0" readonly="true" />
					</div>
					</td>
				</tr>
			</table>
			<table id="childrenTable" width="550" align="center" border="0">
				<s:if
					test="employeeDTO.getChildren() != null && employeeDTO.getChildren().size() > 0">
					<s:iterator value="employeeDTO.children" status="stat" var="child">
						<tr>
							
							<td width="137"><s:textfield value="%{#child.childName}"
								name="employeeDTO.children[%{#stat.count - 1}].childName"
								readonly="true" cssStyle="border:0;color: #FF8000" /></td>
							<td width="137"><s:textfield value="%{#child.childGender}"
								name="employeeDTO.children[%{#stat.count - 1}].childGender"
								readonly="true" cssStyle="border:0;color: #FF8000" /></td>
							<td width="137"><s:textfield
								value="%{#child.childDateOfBirth}"
								name="employeeDTO.children[%{#stat.count - 1}].childDateOfBirth"
								readonly="true" cssStyle="border:0;color: #FF8000" /></td>
							<td width="139">
								<s:a
									value="javascript:editChildInfoRow('childrenTable',%{#stat.count - 1});"
									onmouseover="showToolTip(this,'Edit Child Information');">
									<img src="../images/edit.gif" border="0" />
								</s:a>
							</td>
						</tr>
					</s:iterator>
				</s:if>
			</table>
			</fieldset>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<fieldset id="emergencyContactInfo"><legend class="all_text_style_orange">&nbsp; In Case of Emergency (ICE) </legend>
			<table width="550" border="0" align="center">
				<tr>
					<td width="183" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.contactname" /></td>
					<td width="183"><s:hidden name="rowId_ice" id="rowId_ice" /><s:textfield name="contactName" maxlength="50" id="contactName" cssClass="common_style_black"/></td>
					<td width="184">
						<div id="div_contactName_Error" style="display: none;"><img
							src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp;<s:textfield
							name="contactName_errorText" id="contactName_errorText"
							cssClass="styleError" cssStyle="border:0" readonly="true" />
						</div>
					</td>
				</tr>
				<tr>
					<td width="183" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.contactnumber" /></td>
					<td width="183"><s:textfield name="contactNumber" maxlength="50" id="contactNumber" cssClass="common_style_black" maxLength="10"/></td>
					<td width="184">
						<div id="div_contactNumber_Error" style="display: none;"><img
							src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp;<s:textfield
							name="contactNumber_errorText" id="contactNumber_errorText"
							cssClass="styleError" cssStyle="border:0" readonly="true" />
						</div>
					</td>
				</tr>
				<tr>
					<td width="183" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.relationship" /></td>
					<td width="183"><s:textfield name="relationship" maxlength="50" id="relationship" cssClass="common_style_black"/>&nbsp;&nbsp;
						<a
							href="javascript:addContactInfoRow('contactTable');"
							onmouseover="showToolTip(this,'Add Contact Information');"><img
							src="../images/add.gif" border="0" /></a>
					</td>
					<td width="184">
					<div id="div_relationship_Error" style="display: none;">
						<img
							src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp;<s:textfield
							name="relationship_errorText" id="relationship_errorText"
							cssClass="styleError" cssStyle="border:0" readonly="true" />
					</div>
					</td>
				</tr>
			</table>
			<table id="contactTable" width="550" align="center">
				<s:if
					test="employeeDTO.getEmergencyContacts() != null && employeeDTO.getEmergencyContacts().size() > 0">
					<s:iterator value="employeeDTO.emergencyContacts" status="stat"
						var="contact">
						<tr>
							
							<td width="137"><s:textfield value="%{#contact.contactName}"
								name="employeeDTO.emergencyContacts[%{#stat.count - 1}].contactName"
								readonly="true" cssStyle="border:0;color: #FF8000" /></td>
							<td width="137"><s:textfield
								value="%{#contact.contactNumber}"
								name="employeeDTO.emergencyContacts[%{#stat.count - 1}].contactNumber"
								readonly="true" cssStyle="border:0;color: #FF8000" /></td>
							<td width="137"><s:textfield
								value="%{#contact.relationship}"
								name="employeeDTO.emergencyContacts[%{#stat.count - 1}].relationship"
								readonly="true" cssStyle="border:0;color: #FF8000" /></td>
							<td width="139"><s:a
								value="javascript:editContactInfoRow('contactTable',%{#stat.count - 1});"
								onmouseover="showToolTip(this,'Edit Child Information');">
								<img src="../images/edit.gif" border="0" />
							</s:a></td>
						</tr>
					</s:iterator>
				</s:if>
			</table>
			</fieldset>
			</td>
		</tr>
	</table>
	</div>
	<table width="132" border="0" align="center">
		<tr>
			<td width="44" height="43"><s:submit type="image" id="saveEmp" method="saveEmployee" title="Save" src="../images/Save (small).png"/>
			</td>
			<td width="24">&nbsp;</td>
			<td width="50">
				<s:a id="btnClear"
					value="javascript:showPage('/hrapp/employee/resetPage.action');"
					onmouseover="showToolTip(this,'Clear');">
					<img src="../images/Cancel (small).png" border="0" />
				</s:a>
			</td>
		</tr>
	</table>


</s:form>


