<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page buffer = "16kb" %>
<%@ page import="com.ha.entity.model.custom.UserProfileDTO" %>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<s:form action="changePassword.action" method="post">
<s:hidden name="pageStatus" id="pageStatus" />
	<s:hidden name="changeUserDTO.userName" id="user_name" />
	        <table width="300" border="0" align="left">
	        	<tr>
					<td width="150" class="all_text_style_green"><s:text name="hrinfo.users.companyname" /></td>
					<td width="150">
					<div id="companies">
						<s:select name="changeUserDTO.companyDid"
							list="companyList" listKey="value" listValue="label"
							cssClass="selectStyleRequired" onchange="loadUsers2(this.value);"/><br/>
						<s:if
							test="fieldErrors.get('changeUserDTO.company') != null && fieldErrors.get('changeUserDTO.company').size() > 0">
							<div class="styleError">
								<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
								<s:property value="fieldErrors.get('changeUserDTO.company').get(0)" />
							</div>
						</s:if>	
					</div>
					</td>
				</tr>
				<tr>
					<td width="150" class="all_text_style_green"><s:text name="hrinfo.users.userrole" /></td>
					<td width="150">
					<div id="roles">
						<s:select name="changeUserDTO.userRoleDid"
							list="userRoles" listKey="value" listValue="label"
							cssClass="selectStyleRequired" onchange="loadUsers(this.value);" /><br/>
						<s:if
							test="fieldErrors.get('changeUserDTO.role') != null && fieldErrors.get('changeUserDTO.role').size() > 0">
							<div class="styleError">
								<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
								<s:property value="fieldErrors.get('changeUserDTO.role').get(0)" />
							</div>
						</s:if>	
					</div>
					</td>
				</tr>
				<tr>
					<td width="150" class="all_text_style_green"><s:text name="hrinfo.users.user" /></td>
					<td width="150">
					<div id="users">
							<s:select name="changeUserDTO.userDid" id="userDid"
							list="userList" listKey="value" listValue="label"
							cssClass="selectStyleRequired" onchange="setUserName();" /><br/>
							
							<s:if
								test="fieldErrors.get('changeUserDTO.user') != null && fieldErrors.get('changeUserDTO.user').size() > 0">
								<div class="styleError">
									<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
									<s:property value="fieldErrors.get('changeUserDTO.user').get(0)" />
								</div>
							</s:if>	
							
					</div>
					</td>
				</tr>
				<tr>
					<td width="150" class="all_text_style_green"><s:text name="hrinfo.users.oldpassword" /></td>
					<td width="150">
						<s:password name="changeUserDTO.oldPassword" cssClass="common_style_black_required" maxlength="20" id="oldPassword"/><br />
						<s:if
								test="fieldErrors.get('changeUserDTO.oldPassword') != null && fieldErrors.get('changeUserDTO.oldPassword').size() > 0">
								<div class="styleError">
									<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
									<s:property value="fieldErrors.get('changeUserDTO.oldPassword').get(0)" />
								</div>
						</s:if>	
						<s:if
								test="fieldErrors.get('changeUserDTO.invalid.oldPassword') != null && fieldErrors.get('changeUserDTO.invalid.oldPassword').size() > 0">
								<div class="styleError">
									<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
									<s:property value="fieldErrors.get('changeUserDTO.invalid.oldPassword').get(0)" />
								</div>
						</s:if>	
				</tr>
				<tr>
					<td width="150" class="all_text_style_green"><s:text name="hrinfo.users.newpassword" /></td>
					<td width="150"><s:password name="changeUserDTO.password" cssClass="common_style_black_required" maxlength="20" /><br />
					<s:if
								test="fieldErrors.get('changeUserDTO.password') != null && fieldErrors.get('changeUserDTO.password').size() > 0">
								<div class="styleError">
									<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
									<s:property value="fieldErrors.get('changeUserDTO.password').get(0)" />
								</div>
					</s:if>	
				</tr>
				<tr>
					<td width="150" class="all_text_style_green"><s:text name="hrinfo.users.confirmpassword" /></td>
					<td width="150"><s:password name="changeUserDTO.confirmPassword" cssClass="common_style_black_required" maxlength="20" /><br />
					<s:if
								test="fieldErrors.get('changeUserDTO.confirmPassword') != null && fieldErrors.get('changeUserDTO.confirmPassword').size() > 0">
								<div class="styleError">
									<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
									<s:property value="fieldErrors.get('changeUserDTO.confirmPassword').get(0)" />
								</div>
					</s:if>	
					<s:if
								test="fieldErrors.get('changeUserDTO.passwordMismatch') != null && fieldErrors.get('changeUserDTO.passwordMismatch').size() > 0">
								<div class="styleError">
									<img src="../images/alert.jpg" width="12" height="12" border="0" />&nbsp; 
									<s:property value="fieldErrors.get('changeUserDTO.passwordMismatch').get(0)" />
								</div>
					</s:if>	
				</tr>
				<tr>
					<td width="150" class="all_text_style_green"><s:submit method="changePassword" key="hrinfo.users.save"></s:submit></td>
					<td width="150"></td>
				</tr>
			</table>
</s:form>


