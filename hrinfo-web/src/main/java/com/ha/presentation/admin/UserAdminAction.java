package com.ha.presentation.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.ha.entity.model.custom.CompanyDTO;
import com.ha.entity.model.custom.UserDTO;
import com.ha.entity.model.custom.UserProfileDTO;
import com.ha.exceptions.BusinessException;

import com.ha.presentation.util.PasswordService;
import com.ha.presentation.util.PresentationConstants;
import com.ha.presentation.util.SelectBoxDataHolder;
import com.ha.services.AdminDelegate;

import com.ha.services.UserDelegate;
import com.ha.util.Util;
import com.opensymphony.xwork2.ActionSupport;

public class UserAdminAction extends ActionSupport implements SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Log log = LogFactory.getLog(UserAdminAction.class);

	private Map<String, Object> sessionMap;
	protected Integer pageStatus = 0;
	protected String activeTab;
	
	private UserDTO userDTO = new UserDTO();
	private UserDTO changeUserDTO = new UserDTO();
	protected List<SelectBoxDataHolder> companyList = new ArrayList<SelectBoxDataHolder>();
	protected List<SelectBoxDataHolder> userRoles = new ArrayList<SelectBoxDataHolder>();
	protected List<SelectBoxDataHolder> userList = new ArrayList<SelectBoxDataHolder>();
	
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;		
	}
	
	public Integer getPageStatus() {
		return pageStatus;
	}
	
	public String getActiveTab() {
		return activeTab;
	}

	public void setActiveTab(String activeTab) {
		this.activeTab = activeTab;
	}

	public void setPageStatus(Integer pageStatus) {
		this.pageStatus = pageStatus;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	
	public UserDTO getChangeUserDTO() {
		return changeUserDTO;
	}

	public void setChangeUserDTO(UserDTO changeUserDTO) {
		this.changeUserDTO = changeUserDTO;
	}

	public List<SelectBoxDataHolder> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<CompanyDTO> companyList) {
		if (companyList.size() > 1) {
			this.companyList.add(new SelectBoxDataHolder(PresentationConstants.LIST_DEFAULT_VALUE, "---Select---"));
		}

		for (CompanyDTO dto : companyList) {
			this.companyList.add(new SelectBoxDataHolder(dto.getCompanyDid(),dto.getCompanyName()));
		}
	}

	public List<SelectBoxDataHolder> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles() {
		this.userRoles.add(new SelectBoxDataHolder(PresentationConstants.LIST_DEFAULT_VALUE,"---Select---"));
		UserProfileDTO userProfileDTO = (UserProfileDTO) sessionMap.get("userProfile");
		
		if(userProfileDTO.getUserRoleDid().equals(PresentationConstants.USER_ROLE_DID_ADMIN)) {
			this.userRoles.add(new SelectBoxDataHolder(PresentationConstants.USER_ROLE_DID_ADMIN,"Admin"));
			this.userRoles.add(new SelectBoxDataHolder(PresentationConstants.USER_ROLE_DID_NON_ADMIN,"Common"));
		} else {
			this.userRoles.add(new SelectBoxDataHolder(PresentationConstants.USER_ROLE_DID_NON_ADMIN,"Common"));
		}
	}
	
	public List<SelectBoxDataHolder> getUserList() {
		return userList;
	}

	public void setUserList(List<UserDTO> userList) {
		this.userList.add(new SelectBoxDataHolder(PresentationConstants.LIST_DEFAULT_VALUE,"---Select---"));
		for (UserDTO dto : userList) {
			this.userList.add(new SelectBoxDataHolder(dto.getUserDid(),dto.getUserName()));
		}
	}

	public String loadPage() {
		 try {     
		     populateData();
			 	
			 return "success";
	        } catch (BusinessException be) {
				log.error(be);
	        	be.printStackTrace();
				return "error";
			}
	}
	
	public String saveUser() {
		String forward = "success";
		 try {     
		     
		     validateNewUser() ;
			 
			 if (!hasFieldErrors()) {
				 userDTO.setPassword(PasswordService.getInstance().encrypt(userDTO.getPassword()));
				 AdminDelegate adminDelegate = new AdminDelegate();
				 adminDelegate.saveUser(userDTO);
				 
				 pageStatus = PresentationConstants.PAGE_STATUS_SUCCESS;
				 forward = "success"; 
			 } else {
				 pageStatus = PresentationConstants.PAGE_STATUS_HAS_VALIDATION_ERRORS;
				 forward = "error"; 
			 }
			 populateData();
			 return forward;
	        } catch (BusinessException be) {
				log.error(be);
	        	be.printStackTrace();
	        	pageStatus = PresentationConstants.PAGE_STATUS_INTERNAL_ERROR;
				return "error";
			}
	}
	
	public String changePassword() {
		String forward = "";
		List<Long> functionDids = (List<Long>)sessionMap.get("userFunctions");
		
		 try {     
		     
		     validateChange();
		     validateOldPassword();
			 
			 if (!hasFieldErrors()) {
				 changeUserDTO.setPassword(PasswordService.getInstance().encrypt(changeUserDTO.getPassword()));
				 AdminDelegate adminDelegate = new AdminDelegate();
				 adminDelegate.saveUser(changeUserDTO);
				 
				 pageStatus = PresentationConstants.PAGE_STATUS_SUCCESS;
				 forward = functionDids.contains(PresentationConstants.FUNC_DID_ADD_NEW_USER) ? "success" : "basic";
			 } else {
				 pageStatus = PresentationConstants.PAGE_STATUS_HAS_VALIDATION_ERRORS;
				 forward = functionDids.contains(PresentationConstants.FUNC_DID_ADD_NEW_USER) ? "error" : "basic_error";
			 }
			 populateData();		 
			 return forward;
	        } catch (BusinessException be) {
				log.error(be);
	        	be.printStackTrace();
	        	pageStatus = PresentationConstants.PAGE_STATUS_INTERNAL_ERROR;
	        	forward = functionDids.contains(PresentationConstants.FUNC_DID_ADD_NEW_USER) ? "error" : "basic_error";
				return forward;
			}
	}

	private void populateData() throws BusinessException {
		 if (companyList.size() == 0) {
			 List<CompanyDTO> companyData = (List<CompanyDTO>)sessionMap.get("companyData");
			 setCompanyList(companyData);
	     }
		 
		 if (userRoles.size() == 0) {
	        setUserRoles();	            
	     }
		 
		 setUserList(new ArrayList());
	}

    public void validateNewUser() {
        if(userDTO.getCompanyDid() != null && userDTO.getCompanyDid().equals(PresentationConstants.LIST_DEFAULT_VALUE)) {
        	addFieldError("userDTO.company", "Company is required");
        }

        if(userDTO.getUserRoleDid() != null && userDTO.getUserRoleDid().equals(PresentationConstants.LIST_DEFAULT_VALUE)) {
        	addFieldError("userDTO.role", "Role is required");
        }
        
        if(Util.isEmpty(userDTO.getUserName())) {
        	addFieldError("userDTO.uName", "User Name is required");
        }
        
        if(Util.isEmpty(userDTO.getPassword())) {
        	addFieldError("userDTO.password", "Password is required");
        } else {
        	if(Util.isEmpty(userDTO.getConfirmPassword())) {
        		addFieldError("userDTO.confirmPassword", "Confirm Password is required");
        	}
        	if(!userDTO.getPassword().equalsIgnoreCase(userDTO.getConfirmPassword())) {
        		addFieldError("userDTO.passwordMismatch", "Confirm Password does not match the Password");
        	}
        }

    }
    
    public void validateChange() {
        if(changeUserDTO.getCompanyDid() != null && changeUserDTO.getCompanyDid().equals(PresentationConstants.LIST_DEFAULT_VALUE)) {
        	addFieldError("changeUserDTO.company", "Company is required");
        }

        if(changeUserDTO.getUserRoleDid() != null && changeUserDTO.getUserRoleDid().equals(PresentationConstants.LIST_DEFAULT_VALUE)) {
        	addFieldError("changeUserDTO.role", "Role is required");
        }
        
        if(changeUserDTO.getUserDid() != null && changeUserDTO.getUserDid().equals(PresentationConstants.LIST_DEFAULT_VALUE)) {
        	addFieldError("changeUserDTO.user", "User is required");
        }
        
        if(Util.isEmpty(changeUserDTO.getOldPassword())) {
        	addFieldError("changeUserDTO.oldPassword", "Old Password is required");
        }
        
        if(Util.isEmpty(changeUserDTO.getPassword())) {
        	addFieldError("changeUserDTO.password", "New Password is required");
        } else {
        	if(Util.isEmpty(changeUserDTO.getConfirmPassword())) {
        		addFieldError("changeUserDTO.confirmPassword", "Confirm Password is required");
        	}
        	if(!changeUserDTO.getPassword().equalsIgnoreCase(changeUserDTO.getConfirmPassword())) {
        		addFieldError("changeUserDTO.passwordMismatch", "Confirm Password does not match the Password");
        	}
        }


    }
    
    private void validateOldPassword() throws BusinessException {
    	 UserDelegate userDelegate = new UserDelegate();
    	 if(Util.isNotEmpty(changeUserDTO.getUserName()) && Util.isNotEmpty(changeUserDTO.getOldPassword())) {
	    	 boolean exists = userDelegate.getUserProfile(changeUserDTO.getUserName(), PasswordService.getInstance().encrypt(changeUserDTO.getOldPassword())) != null ? true : false;
	    	 
	    	 if(!exists) {
	    		 addFieldError("changeUserDTO.invalid.oldPassword", "Old password is invalid");
	    	 }
    	 }
    }
}
