<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page buffer="16kb"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<s:form action="saveUser.action" method="post">
<s:hidden name="pageStatus" id="pageStatus" />
	        <table width="300" border="0" align="left">
	        	<tr>
					<td width="150" class="all_text_style_green"><s:text name="hrinfo.users.companyname" /></td>
					<td width="150">
						<s:select name="userDTO.companyDid"
							list="companyList" listKey="value" listValue="label"
							cssClass="selectStyleRequired" /><br/>
						<s:if
							test="fieldErrors.get('userDTO.company') != null && fieldErrors.get('userDTO.company').size() > 0">
							<div class="styleError">
								<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
								<s:property value="fieldErrors.get('userDTO.company').get(0)" />
							</div>
						</s:if>	
					</td>
				</tr>
				<tr>
					<td width="150" class="all_text_style_green"><s:text name="hrinfo.users.userrole" /></td>
					<td width="150">
						<s:select name="userDTO.userRoleDid" id="userRoleDid"
							list="userRoles" listKey="value" listValue="label"
							cssClass="selectStyleRequired" /><br/>
					    <s:if
							test="fieldErrors.get('userDTO.role') != null && fieldErrors.get('userDTO.role').size() > 0">
							<div class="styleError">
								<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
								<s:property value="fieldErrors.get('userDTO.role').get(0)" />
							</div>
						</s:if>	
					</td>
				</tr>
				<tr>
					<td width="150" class="all_text_style_green"><s:text name="hrinfo.users.username" /></td>
					<td width="150"><s:textfield name="userDTO.userName" cssClass="common_style_black_required" maxlength="20" onblur="checkUserName(this.value);" id="userName"/><br />
					<s:if
							test="fieldErrors.get('userDTO.uName') != null && fieldErrors.get('userDTO.uName').size() > 0">
							<div class="styleError">
								<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
								<s:property value="fieldErrors.get('userDTO.uName').get(0)" />
							</div>
					</s:if>	
				</tr>
				<tr>
					<td width="150" class="all_text_style_green"><s:text name="hrinfo.users.password" /></td>
					<td width="150"><s:password name="userDTO.password" cssClass="common_style_black_required" maxlength="20" /><br />
					<s:if
							test="fieldErrors.get('userDTO.password') != null && fieldErrors.get('userDTO.password').size() > 0">
							<div class="styleError">
								<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
								<s:property value="fieldErrors.get('userDTO.password').get(0)" />
							</div>
					</s:if>	
				</tr>
				<tr>
					<td width="150" class="all_text_style_green"><s:text name="hrinfo.users.confirmpassword" /></td>
					<td width="150"><s:password name="userDTO.confirmPassword" cssClass="common_style_black_required" maxlength="20" /><br />
					<s:if
							test="fieldErrors.get('userDTO.confirmPassword') != null && fieldErrors.get('userDTO.confirmPassword').size() > 0">
							<div class="styleError">
								<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
								<s:property value="fieldErrors.get('userDTO.confirmPassword').get(0)" />
							</div>
					</s:if>
					<s:if
							test="fieldErrors.get('userDTO.passwordMismatch') != null && fieldErrors.get('userDTO.passwordMismatch').size() > 0">
							<div class="styleError">
								<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
								<s:property value="fieldErrors.get('userDTO.passwordMismatch').get(0)" />
							</div>
					</s:if>		
				</tr>
				<tr>
					<td width="150" class="all_text_style_green"><s:submit method="saveUser" key="hrinfo.users.save"></s:submit></td>
					<td width="150"></td>
				</tr>
			</table>
</s:form>

