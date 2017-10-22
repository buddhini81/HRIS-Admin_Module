/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.presentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ha.entity.model.custom.AllowanceTypeDTO;
import com.ha.entity.model.custom.CompanyDTO;
import com.ha.entity.model.custom.ConfirmationAlertDTO;
import com.ha.entity.model.custom.ContractTypeDTO;
import com.ha.entity.model.custom.DepartmentDTO;
import com.ha.entity.model.custom.UserMenuDTO;
import com.ha.entity.model.custom.UserProfileDTO;
import com.ha.exceptions.BusinessException;
import com.ha.helper.GlobalHelper;
import com.ha.presentation.util.PasswordService;
import com.ha.presentation.util.PresentationConstants;
import com.ha.services.EmployeeDelegate;
import com.ha.services.GlobalDelegate;
import com.ha.services.UserDelegate;
import com.ha.util.Util;
import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *
 * @author Buddhini
 */
public class LoginAction extends ActionSupport implements SessionAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Log log = LogFactory.getLog(LoginAction.class);

	private Map<String, Object> sessionMap;

	private String userName;
	private String password;
	private Integer loginStatus = 0;
	private String message;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(Integer loginStatus) {
		this.loginStatus = loginStatus;
	}
	
    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;		
	}

	
	public String login() {
    	UserDelegate userDelegate = new UserDelegate();
    	String forward = "error";
    	validateLogin();
        try {     	
			if (!hasFieldErrors()) {
				UserProfileDTO userProfileDTO = userDelegate.getUserProfile(this.userName, PasswordService.getInstance().encrypt(this.password));
				if (userProfileDTO != null) {
					
					List<Long> functionDids = userDelegate.getUserFunctionDids(userProfileDTO.getUserRoleDid());
					if(functionDids != null && functionDids.size() > 0) {sessionMap.put("userFunctions", functionDids);}
					
					List<UserMenuDTO> menuDTO = userDelegate.getUserMenus(userProfileDTO.getUserRoleDid());
					if(menuDTO != null && menuDTO.size() > 0) {sessionMap.put("userMenus", menuDTO);}
					
					sessionMap.put("userProfile", userProfileDTO);
					this.loginStatus = new Integer(PresentationConstants.LOGIN_STATUS_SUCCESS);
					
					populateGlobalData(userProfileDTO);
					
					forward = functionDids.contains(PresentationConstants.FUNC_DID_ADD_NEW_EMPLOYEE) ? "success" : "basic";
					
				} else {
					this.loginStatus = new Integer(PresentationConstants.LOGIN_STATUS_INVALID_CREDENTIALS);
					this.message = "The User Name or Password you entered is invalid. Please try again.";
					forward = "error";
				}
			} else {
				forward = "error";
			}
			
			return forward;
        } catch (BusinessException be) {
			log.error(be);
        	be.printStackTrace();
        	this.loginStatus = new Integer(PresentationConstants.LOGIN_STATUS_INTERNAL_ERROR);
        	this.message = "There was an Internal Error. Please try again later";
			return "error";
		}
    }
    
	private void populateGlobalData(UserProfileDTO userProfileDTO) throws BusinessException {
		
		CompanyDTO loggedUserCompany = null; 
		
		GlobalDelegate globalDelegate = new GlobalDelegate();
		EmployeeDelegate employeeDelegate = new EmployeeDelegate();
		
		
		if(userProfileDTO != null) {
			loggedUserCompany = globalDelegate.getCompanyByDid(userProfileDTO.getCompanyDid());
			sessionMap.put("loggedUserCompany", loggedUserCompany);
		}
		 
		CompanyDTO parentCompany = GlobalHelper.loadParentCompany(loggedUserCompany);
		
		if(parentCompany != null) {
			sessionMap.put("parentCompany", parentCompany);
		}
		
		List<CompanyDTO> companyData = GlobalHelper.loadChildCompanies(loggedUserCompany);
		
		if(companyData != null && companyData.size() > 0) {
			sessionMap.put("companyData",companyData);
		}
		
		List<DepartmentDTO> departments = globalDelegate.findAllDepartments();
		if(departments != null) {
			Map<Long, List<DepartmentDTO>> companyDeptMap = new HashMap<Long, List<DepartmentDTO>>();
			List<DepartmentDTO> depts = null;
			for(DepartmentDTO dto : departments) {
				if (companyDeptMap.containsKey(dto.getCompanyDid())) {
					depts = companyDeptMap.get(dto.getCompanyDid());
					depts.add(dto);
		        } else {
		        	depts = new ArrayList<DepartmentDTO>();   
		        	depts.add(dto);
		        }
				companyDeptMap.put(dto.getCompanyDid(), depts);
			}
			
			sessionMap.put("departmentData",companyDeptMap);
		}
		
		List<ContractTypeDTO> contractTypeData = globalDelegate.findAllContractTypes();
		if(contractTypeData != null && contractTypeData.size() > 0) {
			sessionMap.put("contractTypeData", contractTypeData);
		}
		
		List<AllowanceTypeDTO> allowanceTypeData = globalDelegate.findAllAllowanceTypes();
		sessionMap.put("allowanceTypeData", allowanceTypeData);
        
        //populate confirmation alert data
        
        List<ConfirmationAlertDTO> dueConfList = employeeDelegate.getConfirmationAlertData(loggedUserCompany.getCompanyDid(),loggedUserCompany.getIsSingleCompany(),loggedUserCompany.getIsParent());
        if(dueConfList != null && dueConfList.size() > 0) {
        	sessionMap.put("dueConfData", dueConfList); 
        }                 
	}
    
    public String logout() {
    	if (sessionMap instanceof org.apache.struts2.dispatcher.SessionMap) {
    	    try {
    	        ((org.apache.struts2.dispatcher.SessionMap) sessionMap).invalidate();
    	        ((org.apache.struts2.dispatcher.SessionMap) sessionMap).clear();
    	    } catch (IllegalStateException e) {
    	    	log.error(e);
    	        return "error";
    	    }
    	}

    	return "success";
    }
    
    public void validateLogin() {
        if(Util.isEmpty(this.userName)) {
        	addFieldError("userName", "User Name is required");
        }

        if(Util.isEmpty(this.password)) {
        	addFieldError("password", "Password is required");
        }

        this.loginStatus = 2;

    }

}
