<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ha.presentation.util.PresentationConstants"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page buffer = "16kb" %>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../js/jquery.loadmask.js"></script>
<link href="../css/jquery.loadmask.css" rel="stylesheet" type="text/css" />
<!-- TemplateBeginEditable name="doctitle" -->
<title>Login</title>
<!-- TemplateEndEditable -->
<!-- TemplateBeginEditable name="head" --><!-- TemplateEndEditable -->
<style type="text/css">
<!--
.style4 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 14px;
	font-weight: bold;
	color: #000000;
}
.style5 {
	color: #FF8000;
	font-size: 18px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-weight: bold;
}
.style8 {font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; font-weight: bold; color: #4AB915; }
.style10 {font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; font-weight: bold; color: white; background-color: #FF8000;}
.style11 {color: #FF8000}
.styleError {color: red;font-family:Verdana, Arial, Helvetica, sans-serif;font-size: xx-small;font-weight: bold;}
-->
</style>
<script language="javascript">


</script>
</head>

<body>
<s:form action="/login/login.action" method="post">
<s:hidden name="loginStatus" id="login_status"/>
<table width="876" height="1082" border="0" align="center" bgcolor="#C0C0C0">
  <tr>
    <td width="870" height="1078" valign="top">
      <p>&nbsp;</p>
      <table width="804" height="962" border="0" align="center" bgcolor="#FFFFFF">
          <tr>
              <td width="798" height="300">&nbsp;&nbsp;</td>
          </tr>
          <tr>
            <td width="798" height="300" valign="baseline" align="center">
            <div id="formDiv">
              <table width="400" border="0" bgcolor="CCCCCC">
                  <tr bgcolor="666666" style="font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; font-weight: bold; color: white;">
                      <td colspan="2" align="center">Login</td>
                  </tr>

                  <tr style="font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; font-weight: bold; color: CCCCCC;">
                      <td align="left">User Name</td>
                      <td align="left">
                          <s:textfield name="userName" maxlength="20"/>
                          <s:if test="fieldErrors != null && fieldErrors.get('userName') != null && fieldErrors.get('userName').size() > 0">
							<div class="styleError"><img src="../images/alert.jpg" width="12"
								height="12" border="0" />&nbsp <s:property
								value="fieldErrors.get('userName').get(0)" />
							</div>
						 </s:if>
                      </td>
                  </tr>
                  <tr style="font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; font-weight: bold; color: CCCCCC;">
                      <td align="left">Password</td>
                      <td align="left">
                          <s:password name="password" maxlength="20"/>
                          <s:if test="fieldErrors != null && fieldErrors.get('password') != null && fieldErrors.get('password').size() > 0">
							<div class="styleError"><img src="../images/alert.jpg" width="12"
								height="12" border="0" />&nbsp <s:property
								value="fieldErrors.get('password').get(0)" />
							</div>
						 </s:if>
                      </td>
                  </tr>
                  <tr>
                      <td colspan="2" align="center"><s:submit method="login" id="log" key="hrinfo.login.button.label" cssStyle="background-color: #666666;font-family: Verdana, Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;color: white;"/></td>
                  </tr>
                  <tr>
                      <td colspan="2" class="styleError" align="center" >                                         
                          <s:property value="message"/>
                      </td>
                  </tr>
              </table>
             </div>
          </td>
        </tr>
        <tr>
            <td width="798" height="300">&nbsp;&nbsp;</td>
        </tr>
      </table>
    <p>&nbsp;</p></td>
  </tr>
</table>
</s:form>
</body>
</html>
