<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page buffer="16kb"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<s:hidden name="employeeViewDTO.employeeDid" id="employeeDid" />
<fieldset id="generalInfo">
	<legend class="all_text_style_orange">General Information</legend> 
	<table width="750" border="0" align="center">
		<tr>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.firstname" /></td>
			<td width="187.5" class="common_style_black"><s:property value="employeeViewDTO.firstName" /></td>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.gender" /></td>
			<td width="187.5" class="common_style_black"> <s:property value="employeeViewDTO.gender"/></td>
		</tr>
		<tr>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.middlename" /></td>
			<td width="187.5" class="common_style_black"><s:property value="employeeViewDTO.middleName"/></td>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.nicnumber" /></td>
			<td width="187.5" class="common_style_black"><s:property value="employeeViewDTO.nicNo" /></td>
		</tr>
		<tr>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.lastname" /></td>
			<td width="187.5" class="common_style_black"><s:property value="employeeViewDTO.lastName"/></td>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.passportnumber" /></td>
			<td width="187.5" class="common_style_black"><s:property value="employeeViewDTO.passportNo"/></td>
		</tr>
		<tr>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.dob" /></td>
			<td width="187.5" class="common_style_black"><s:date name="employeeViewDTO.dateOfBirth" format="dd-MM-yyyy" /></td>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.licenceno" /></td>
			<td width="187.5" class="common_style_black"><s:property value="employeeViewDTO.drivingLicenseNo" /></td>
		</tr>
	</table>
	</fieldset>
	<br />
	<fieldset id="empDetails"><legend class="all_text_style_orange">&nbsp; Employment Details </legend> 
	<table width="750" border="0" align="center">
		<tr>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.company" /></td>
			<td width="187.5" class="common_style_black"><s:property value="employeeViewDTO.companyName" /><br /></td>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.companyepfnumber" /></td>
			<td width="187.5" class="common_style_black"><s:property value="employeeViewDTO.companyEPFNumber" /></td>
		</tr>
		<tr>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.department" /></td>
			<td width="187.5" class="common_style_black"><s:property value="employeeViewDTO.departmentName" /></td>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.epfnumber" /></td>
			<td width="187.5" class="common_style_black"><s:property value="employeeViewDTO.epfNo" /></td>
		</tr>
		<tr>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.designation" /></td>
			<td width="187.5" class="common_style_black"><s:property value="employeeViewDTO.designation" /></td>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.datejoined" /></td>
			<td width="187.5" class="common_style_black"><s:date name="employeeViewDTO.dateJoined" format="dd-MM-yyyy" /></td>
		</tr>
		<tr>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.empnumber" /></td>
			<td width="187.5" class="common_style_black"><s:property value="employeeViewDTO.employeeNo" /></td>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.dueconfdate" /></td>
			<td width="187.5" class="common_style_black"><s:date name="employeeViewDTO.dueConfDate" format="dd-MM-yyyy" /></td>
		</tr>
		<tr>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.contracttype" /></td>
			<td width="187.5" class="common_style_black"><s:property value="employeeViewDTO.contractType" /></td>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.confirmationdate" /></td>
			<td width="187.5" class="common_style_black"><s:date name="employeeViewDTO.confDate" format="dd-MM-yyyy" /></td>

		</tr>
		<tr>
			<td width="187.5" class="all_text_style_green"></td>
			<td width="187.5" class="all_text_style_green"></td>
			<td width="187.5" class="all_text_style_green"></td>
			<td width="187.5" class="common_style_black"><s:property value="employeeViewDTO.otherContractType"/></td>
		</tr>
	</table>
	</fieldset>
	<br/>
	<fieldset id="compensationInfo"><legend class="all_text_style_orange">&nbsp; Compensation &amp; Benefits </legend> 
	<table width="750" border="0" align="center">
		<tr>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.basicsalary" /></td>
			<td width="187.5" class="common_style_black"><s:property value="employeeViewDTO.basicSalary" />
			</td>
			<td width="187.5" class="all_text_style_green"></td>
			<td width="187.5"></td>
		</tr>
		
		<tr>
			<td colspan="4">
			<fieldset id="allowanceInfo"><legend class="all_text_style_orange">&nbsp; Allowances </legend>
			<table id="allowanceTable" width="550" align="center">
				<tr>
							<th width="275" class="all_text_style_green" align="left">
								<s:text name="hrinfo.employeemaster.addemployee.allowancestype" />
							</th>
							<th width="275" class="all_text_style_green" align="left">
								<s:text name="hrinfo.employeemaster.addemployee.allowanceamount" />
							</th>
			    </tr>
				<s:if
					test="employeeViewDTO.getAllowances() != null && employeeViewDTO.getAllowances().size() > 0">
					<s:iterator value="employeeViewDTO.allowances" status="stat" var="allow">
						<tr>
							<td width="275" class="common_style_black"><s:property value="%{#allow.allowanceType}" /></td>
							<td width="275" class="common_style_black"><s:property value="%{#allow.amount}" /></td>
						</tr>
					</s:iterator>
				</s:if>
			</table>
			</fieldset>
			</td>
		</tr>
	</table>

	</fieldset>
	<br/>
	<fieldset id="contactInfo"><legend class="all_text_style_orange">&nbsp; Contact Information </legend> 
	<table width="750" border="0" align="center">
		<tr>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.addressline1" /></td>
			<td width="187.5" class="common_style_black"><s:property value="employeeViewDTO.addressLine1"/></td>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.homeno" /></td>
			<td width="187.5" class="common_style_black"><s:property value="employeeViewDTO.homePhoneNo"/></td>
		</tr>
		<tr>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.addressline2" /></td>
			<td width="187.5" class="common_style_black"><s:property value="employeeViewDTO.addressLine2" /></td>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.mobileno" /></td>
			<td width="187.5" class="common_style_black"><s:property value="employeeViewDTO.mobileNo"/></td>
		</tr>
		<tr>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.addressline3" /></td>
			<td width="187.5" class="common_style_black"><s:property value="employeeViewDTO.addressLine3"/></td>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.email" /></td>
			<td width="187.5" class="common_style_black"><s:property value="employeeViewDTO.email" /></td>
		</tr>
	</table>
	</fieldset>
	<br />
	<fieldset id="personalInfo"><legend class="all_text_style_orange">&nbsp; Personal Information </legend> 
	<table width="750" border="0" align="center">
		<tr>
			<td width="187.5" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.married" /></td>
			<td width="187.5" class="common_style_black"><s:property value="employeeViewDTO.maritalStatus" /></td>
			<td width="187.5" class="all_text_style_green"></td>
			<td width="187.5"></td>
		</tr>
		<tr>
			<td colspan="4">
			<fieldset id="spouseInfo"><legend class="all_text_style_orange">&nbsp; Spouse </legend>
			<table width="550" border="0" align="center">
				<tr>
					<td width="183" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.spousename" /></td>
					<td width="183" class="common_style_black"><s:property value="employeeViewDTO.spouseName" /></td>
					<td width="184"></td>
				</tr>
				<tr>
					<td width="183" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.spousegender" /></td>
					<td width="183" class="common_style_black"><s:property value="employeeViewDTO.spouseGender" /></td>
					<td width="184"></td>
				</tr>
				<tr>
					<td width="183" class="all_text_style_green"><s:text name="hrinfo.employeemaster.addemployee.spousedob" /></td>
					<td width="183" class="common_style_black"><s:date name="employeeViewDTO.spouseDateOfBirth" format="dd-MM-yyyy" /></td>
					<td width="184"></td>
				</tr>
			</table>
			</fieldset>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<fieldset id="childrenInfo"><legend class="all_text_style_orange">&nbsp; Children </legend>
			
			<table id="childrenTable" width="550" align="center">
				<tr>
							<th width="137" class="all_text_style_green" align="left">
								<s:text name="hrinfo.employeemaster.addemployee.childname" />
							</th>
							<th width="137" class="all_text_style_green" align="left">
								<s:text name="hrinfo.employeemaster.addemployee.childgender" />
							</th>
							<th width="137" class="all_text_style_green" align="left">
								<s:text name="hrinfo.employeemaster.addemployee.childdob" />
							</th>
			    </tr>
				<s:if
					test="employeeViewDTO.getChildren() != null && employeeViewDTO.getChildren().size() > 0">
					<s:iterator value="employeeViewDTO.children" status="stat" var="child">
						<tr>
							<td width="137" class="common_style_black">
								<s:property value="%{#child.childName}"/>
							</td>
							<td width="137" class="common_style_black">
								<s:property value="%{#child.childGender}"/>
							</td>
							<td width="137" class="common_style_black">
								<s:property value="%{#child.childDateOfBirth}"/>
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
			<fieldset id="emergencyContactInfo"><legend class="all_text_style_orange">&nbsp; In Case of Emergency
			(ICE) </legend>
			<table id="contactTable" width="550" align="center">
				<tr>
							<th width="137" class="all_text_style_green" align="left">
								<s:text name="hrinfo.employeemaster.addemployee.contactname" />
							</th>
							<th width="137" class="all_text_style_green" align="left">
								<s:text name="hrinfo.employeemaster.addemployee.contactnumber" />
							</th>
							<th width="137" class="all_text_style_green" align="left">
								<s:text name="hrinfo.employeemaster.addemployee.relationship" />
							</th>
			    </tr>
				<s:if
					test="employeeViewDTO.getEmergencyContacts() != null && employeeViewDTO.getEmergencyContacts().size() > 0">
					<s:iterator value="employeeViewDTO.emergencyContacts" status="stat"
						var="contact">
						<tr>
							<td width="137" class="common_style_black">
								<s:property value="%{#contact.contactName}"/>
							</td>
							<td width="137" class="common_style_black">
								<s:property value="%{#contact.contactNumber}"/>
							</td>
							<td width="137" class="common_style_black">
								<s:property value="%{#contact.relationship}"/>
							</td>
						</tr>
					</s:iterator>
				</s:if>
			</table>
			</fieldset>
			</td>
		</tr>
	</table>
	</fieldset>
	



