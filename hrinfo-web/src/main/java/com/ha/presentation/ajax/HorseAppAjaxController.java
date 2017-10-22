/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ha.presentation.ajax;

import com.ha.entity.model.custom.CompanyDTO;
import com.ha.entity.model.custom.DepartmentDTO;
import com.ha.entity.model.custom.MemberDTO;
import com.ha.entity.model.custom.SearchCols;
import com.ha.entity.model.custom.UserDTO;
import com.ha.entity.model.custom.UserProfileDTO;
import com.ha.exceptions.BusinessException;
import com.ha.presentation.util.PasswordService;
import com.ha.services.AdminDelegate;
import com.ha.services.EmployeeDelegate;
import com.ha.services.GlobalDelegate;
import com.ha.services.HorseDelegate;
import com.ha.services.UserDelegate;
import com.ha.util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import org.json.JSONArray;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ha.util.Constants;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Buddhini
 */
public class HorseAppAjaxController {

    private static final String REPORTS_FORM = "reportForm";
    private static final String EMPLOYEE_FORM = "employeeForm";
    private static final String FIELD_NAME = "fieldName";
    private static final String FIELD_LABEL = "fieldLabel";
    private static final String FIELD_DID = "fieldDID";
    private static final String JSON_ARRAY_BOX_ONE = "boxOneJsonArray";
    private static final String JSON_ARRAY_BOX_TWO = "boxTwoJsonArray";
    private static final String JSON_ARRAY_DEPARTMENTS = "departmentJsonArray";
    private static final String JSON_OBJECT_EMPNUMBER = "employeeNumber";
    private static final String JSON_OBJECT_COMPANYEPFNO = "companyEpfNumber";
    private static final String JSON_ARRAY_USERS = "usersJsonArray";
    private static final String EPFNO_EXIST_FLAG = "epfNoExistFlag";
    private static final String USER_NAME_EXIST_FLAG = "userNameExistFlag";
    private static final String NICNO_EXIST_FLAG = "nicNoExistFlag";
    
    private Log log = LogFactory.getLog(HorseAppAjaxController.class);
    
    public JSONObject loadDepartments(HttpServletRequest request) {
        JSONObject mainObject = new JSONObject();
        JSONArray departmentJsonArray = new JSONArray();
        JSONObject departmentJson = null;
        List<DepartmentDTO> departments = null;
        try {

            GlobalDelegate globalDelegate = new GlobalDelegate();

            Long companyDid = Util.isNotEmpty(request.getParameter("companyDid")) ? new Long(request.getParameter("companyDid")) : null;
            Long departmentDid = Util.isNotEmpty(request.getParameter("departmentDid")) ? new Long(request.getParameter("departmentDid")) : null;

            if(companyDid !=  null && !companyDid.equals(-1L)) {
                departments = globalDelegate.getDepartmentsByCompany(companyDid);
            }
            
            JSONObject defaultValue = new JSONObject();
            defaultValue.put(FIELD_DID,-1);
            defaultValue.put(FIELD_LABEL,"---Select---");
            departmentJsonArray.put(defaultValue);

            if(departments != null && departments.size() > 0) {
               for (DepartmentDTO dto : departments) {
                    departmentJson = new JSONObject();
                    departmentJson.put(FIELD_DID, dto.getDepartmentDid());
                    departmentJson.put(FIELD_LABEL, dto.getDepartmentCode());
                    departmentJsonArray.put(departmentJson);
               }
            }

            mainObject.put(JSON_ARRAY_DEPARTMENTS, departmentJsonArray);
        } catch (Exception e) {
            log.error("Error in HorseAppAjaxController -> loadDepartments" + e);
        }
        return mainObject;
    }

    public JSONObject loadCompanySpecificData(HttpServletRequest request) {
        JSONObject mainObject = new JSONObject();
        JSONArray departmentJsonArray = new JSONArray();
        JSONObject departmentJson = null;
        List<DepartmentDTO> departments = null;
        CompanyDTO companyDTO = null;
        try {

            GlobalDelegate globalDelegate = new GlobalDelegate();

            Long companyDid = Util.isNotEmpty(request.getParameter("companyDid")) ? new Long(request.getParameter("companyDid")) : null;
            Long departmentDid = Util.isNotEmpty(request.getParameter("departmentDid")) ? new Long(request.getParameter("departmentDid")) : null;
            Long employeeDid = Util.isNotEmpty(request.getParameter("employeeDid")) ? new Long(request.getParameter("employeeDid")) : null;
            String loadEvent = Util.isNotEmpty(request.getParameter("loadEvent")) ? request.getParameter("loadEvent") : null;

            if(employeeDid == null && (Util.isNotEmpty(loadEvent) && loadEvent.equalsIgnoreCase("onChange"))) {
                String nextEmployeeId = globalDelegate.getNextIdNumber(companyDid, Constants.IDENTIFIER_TYPE_EMPLOYEE_NUMBER);
                if (Util.isNotEmpty(nextEmployeeId)) {
                    mainObject.put(JSON_OBJECT_EMPNUMBER, nextEmployeeId);
                }
            }

            if(companyDid !=  null && !companyDid.equals(-1L)) {
                departments = globalDelegate.getDepartmentsByCompany(companyDid);
                companyDTO = globalDelegate.getCompanyByDid(companyDid);
                if(Util.isNotEmpty(companyDTO.getCompanyEPFNo())) {
                	mainObject.put(JSON_OBJECT_COMPANYEPFNO, companyDTO.getCompanyEPFNo());
                }
            }
            
            JSONObject defaultValue = new JSONObject();
            defaultValue.put(FIELD_DID,-1);
            defaultValue.put(FIELD_LABEL,"---Select---");
            departmentJsonArray.put(defaultValue);

            if(departments != null && departments.size() > 0) {
               for (DepartmentDTO dto : departments) {
                    departmentJson = new JSONObject();
                    departmentJson.put(FIELD_DID, dto.getDepartmentDid());
                    departmentJson.put(FIELD_LABEL, dto.getDepartmentCode());
                    departmentJsonArray.put(departmentJson);
               }
            }

            mainObject.put(JSON_ARRAY_DEPARTMENTS, departmentJsonArray);
        } catch (Exception e) {
            log.error("Error in HorseAppAjaxController -> loadCompanySpecificData" + e);
        }
        return mainObject;
    }
    
    public JSONObject loadUsers(HttpServletRequest request) {
        JSONObject mainObject = new JSONObject();
        JSONArray usersJsonArray = new JSONArray();
        JSONObject userJson = null;
        List<UserDTO> users = null;
        try {

            AdminDelegate adminDelegate = new AdminDelegate();
        	
            Long companyDid = Util.isNotEmpty(request.getParameter("companyDid")) ? new Long(request.getParameter("companyDid")) : null;
            Long roleDid = Util.isNotEmpty(request.getParameter("roleDid")) ? new Long(request.getParameter("roleDid")) : null;
           

            if((companyDid !=  null && !companyDid.equals(-1L)) && (roleDid !=  null && !roleDid.equals(-1L)) ) {
            	users = adminDelegate.getUserListByCompanyAndRole(companyDid, roleDid);
            }
            
            JSONObject defaultValue = new JSONObject();
            defaultValue.put(FIELD_DID,-1);
            defaultValue.put(FIELD_LABEL,"---Select---");
            usersJsonArray.put(defaultValue);

            if(users != null && users.size() > 0) {
               for (UserDTO dto : users) {
            	   	userJson = new JSONObject();
            	   	userJson.put(FIELD_DID, dto.getUserDid());
            	   	userJson.put(FIELD_LABEL, dto.getUserName());
            	   	usersJsonArray.put(userJson);
               }
            }

//            if(departmentDid != null) {
//                mainObject.put(JSON_OBJECT_SELECTED_DEPARTMENT, departmentDid);
//            }

            mainObject.put(JSON_ARRAY_USERS, usersJsonArray);
        } catch (Exception e) {
            log.error("Error in HorseAppAjaxController -> loadUsers" + e);
        }
        return mainObject;
    }


    public JSONObject checkEPFNumber(HttpServletRequest request) {
    	JSONObject mainObject = new JSONObject();
        try {

            EmployeeDelegate employeeDelegate = new EmployeeDelegate();
            
            Long companyDid = Util.isNotEmpty(request.getParameter("companyDid")) ? new Long(request.getParameter("companyDid")) : null;
            Integer epfNumber = Util.isNotEmpty(request.getParameter("number")) ? new Integer(request.getParameter("number")) : null;
        	
            boolean flag = employeeDelegate.isExistingEPFNumber(companyDid, epfNumber);
           
            if(flag) {
            	mainObject.put(EPFNO_EXIST_FLAG, "TRUE");
            } else {
            	mainObject.put(EPFNO_EXIST_FLAG, "FALSE");
            }
            
        } catch (Exception e) {
            log.error("Error in HorseAppAjaxController -> checkEPFNumber" + e);
        }
        return mainObject;
    }
    
    public JSONObject checkUserName(HttpServletRequest request) {
    	JSONObject mainObject = new JSONObject();
        try {

            UserDelegate userDelegate = new UserDelegate();
            
            Long roleDid = new Long(request.getParameter("roleDid"));
            String userName = request.getParameter("userName");
        	
            boolean exists = userDelegate.getUserByRoleAndName(roleDid,userName) != null ? true : false;
           
            if(exists) {
            	mainObject.put(USER_NAME_EXIST_FLAG, "TRUE");
            } else {
            	mainObject.put(USER_NAME_EXIST_FLAG, "FALSE");
            }
            
        } catch (Exception e) {
            log.error("Error in HorseAppAjaxController -> checkUserName" + e);
        }
        return mainObject;
    }
    
    public JSONObject checkNICNumber(HttpServletRequest request) {
    	JSONObject mainObject = new JSONObject();
        try {

            EmployeeDelegate employeeDelegate = new EmployeeDelegate();
            
            String nicNo = Util.isNotEmpty(request.getParameter("number")) ? request.getParameter("number") : null;
        	
            boolean flag = employeeDelegate.isExistingNICNumber(nicNo);
           
            if(flag) {
            	mainObject.put(NICNO_EXIST_FLAG, "TRUE");
            } else {
            	mainObject.put(NICNO_EXIST_FLAG, "FALSE");
            }
            
        } catch (Exception e) {
            log.error("Error in HorseAppAjaxController -> checkNICNumber" + e);
        }
        return mainObject;
    }
    
    public JSONObject testJQDataTable(HttpServletRequest request) {
    	 JSONObject mainObject = new JSONObject();
         JSONArray testJsonArray = new JSONArray();
         JSONArray data = new JSONArray();
         try {           
        	 JSONArray row1 = new JSONArray();
             row1.put(new JSONObject().put("col1","Row 1 Data 1"));
             row1.put(new JSONObject().put("col2","Row 1 Data 2"));
             data.put(row1);
             
             JSONArray row2 = new JSONArray();
             row2.put(new JSONObject().put("col1","Row 2 Data 1"));
             row2.put(new JSONObject().put("col2","Row 2 Data 2"));
             data.put(row2);
             
             JSONArray row3 = new JSONArray();
             row3.put(new JSONObject().put("col1","Row 3 Data 1"));
             row3.put(new JSONObject().put("col2","Row 3 Data 2"));
             data.put(row3);
             
             mainObject.put("aaData", data);
         } catch (Exception e) {
             log.error("Error in HorseAppAjaxController -> testJQDataTable" + e);
         }
         return mainObject;
    }
}
