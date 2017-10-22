/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.presentation.employee;


import com.ha.entity.model.custom.CommentDTO;
import com.ha.entity.model.custom.CompanyPropertyAssignmentDTO;
import com.ha.entity.model.custom.CompanyPropertyTypeDTO;
import com.ha.entity.model.custom.EmployeeDTO;
import com.ha.entity.model.custom.EmployeeQualificationDTO;
import com.ha.entity.model.custom.EmployeeSearchDTO;
import com.ha.entity.model.custom.EmployeeSkillDTO;
import com.ha.exceptions.BusinessException;
import com.ha.presentation.util.PresentationConstants;
import com.ha.services.EmployeeDelegate;
import com.ha.services.GlobalDelegate;
import com.ha.util.Constants;
import com.ha.util.Util;
import java.util.ArrayList;
import java.util.List;

import javassist.bytecode.Descriptor.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.LocalDate;
import org.joda.time.Years;

/**
 *
 * @author Buddhini
 */
public class EmployeeAction extends EmployeeBaseAction  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Log log = LogFactory.getLog(EmployeeAction.class);
		
	private String comments;
				
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public String execute() {
		return "success";
    }

	public String saveEmployee() {
		String forward = "success";
		validateEmployeeData();
		try {
			if (!hasFieldErrors()) {
				EmployeeDelegate employeeDelegate = new EmployeeDelegate();
				populateComments();
				
				if(employeeDTO.getDateOfBirth() != null) {
					calcEmpAge();
				}
				
				employeeDelegate.saveEmployeeDetails(employeeDTO);
				sessionMap.put("currentEmpDid", employeeDTO.getEmployeeDid());
				
				addActionMessage("Employee record was successfully saved. You may add another employee below. Please use " + this.employeeDTO.getEmployeeNo() + " to search for the employee you just created.");
				employeeDTO.reset();
				pageStatus = PresentationConstants.PAGE_STATUS_SUCCESS;
				forward = "success";
			}else {
				populatePageData();
				pageStatus = PresentationConstants.PAGE_STATUS_HAS_VALIDATION_ERRORS;
				forward = "error";
			}
		} catch (BusinessException be) {
			log.error(be);
        	be.printStackTrace();
        	pageStatus = PresentationConstants.PAGE_STATUS_INTERNAL_ERROR;
			return "error";
		} catch (Exception e) {
			log.error(e);
        	e.printStackTrace();
        	pageStatus = PresentationConstants.PAGE_STATUS_INTERNAL_ERROR;
			return "error";
		}
		return forward;
	}
	
	public String viewEmployee() {
		String forward = "";
		List<Long> functionDids = (List<Long>)sessionMap.get("userFunctions");
		try {
			populatePageData();
			Long employeeDid = null;
			
			if(request.getParameter("employeeDid") != null) {
				employeeDid = Long.valueOf(request.getParameter("employeeDid"));
			} else if(sessionMap.get("currentEmpDid") != null) {
				employeeDid = Long.valueOf(sessionMap.get("currentEmpDid").toString());
			}

			EmployeeDelegate employeeDelegate = new EmployeeDelegate();
			EmployeeSearchDTO searchDTO = new EmployeeSearchDTO();
			searchDTO.setSearchByDid(employeeDid);
			searchDTO.setSearchContext(Constants.EMPLOYEE_SEARCH_CONTEXT_VIEW);
			
			EmployeeDTO employeeViewDTO = employeeDelegate.getEmployeeDetails(searchDTO);	
			if(employeeViewDTO != null) {
				setEmployeeViewDTO(employeeViewDTO);
			}
			
			forward = functionDids.contains(PresentationConstants.FUNC_DID_ADD_NEW_EMPLOYEE) ? "success" : "basic";
			
			return forward;
		} catch (BusinessException be) {
			log.error(be);
        	be.printStackTrace();
			forward = functionDids.contains(PresentationConstants.FUNC_DID_ADD_NEW_EMPLOYEE) ? "error" : "basic_error";
			return forward;
		} catch (Exception e) {
			log.error(e);
        	e.printStackTrace();
        	forward = functionDids.contains(PresentationConstants.FUNC_DID_ADD_NEW_EMPLOYEE) ? "error" : "basic_error";
        	return forward;
		}
		
	}
	
	public String editEmployee() {
		try {
			Long employeeDid = Long.valueOf(request.getParameter("employeeDid"));
			
			EmployeeDelegate employeeDelegate = new EmployeeDelegate();
			EmployeeSearchDTO searchDTO = new EmployeeSearchDTO();
			searchDTO.setSearchByDid(employeeDid);
			searchDTO.setSearchContext(Constants.EMPLOYEE_SEARCH_CONTEXT_EDIT);
			
			EmployeeDTO employeeDTO = employeeDelegate.getEmployeeDetails(searchDTO);	
			if(employeeDTO != null && Util.isNotEmpty(employeeDTO.getSpouseGender())) {
				employeeDTO.setSpouseGender(employeeDTO.getSpouseGender().substring(0, 1));
			}	
			setEmployeeDTO(employeeDTO);
			populatePageData();
		} catch (BusinessException be) {
			log.error(be);
        	be.printStackTrace();
			return "error";
		} catch (Exception e) {
			log.error(e);
        	e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	public String loadEmployeeQualificationPage() {
		try{
			EmployeeDelegate employeeDelegate = new EmployeeDelegate();
			Long employeeDid = Long.valueOf(request.getParameter("did"));
			qualificationDTO.setEmployeeDid(employeeDid);
			
			if(qualificationTypeList.size() == 0) {
				setQualificationTypeList();
			}
			
			List<EmployeeQualificationDTO> qualifications = employeeDelegate.getEmployeeQualifications(qualificationDTO.getEmployeeDid());
			sessionMap.put("empQualificationResults", qualifications);
			return "success";
		} catch (BusinessException be) {
			log.error(be);
        	be.printStackTrace();
			return "error";
		} catch (Exception e) {
			log.error(e);
        	e.printStackTrace();
			return "error";
		}
	}
	
	public String saveEmployeeQualification() {
		String forward = "success";
		validateQualificationData();
		try{	
			if (!hasFieldErrors()) {
				EmployeeDelegate employeeDelegate = new EmployeeDelegate();
				employeeDelegate.saveEmployeeQualification(qualificationDTO);
				List<EmployeeQualificationDTO> qualifications = employeeDelegate.getEmployeeQualifications(qualificationDTO.getEmployeeDid());
				sessionMap.put("empQualificationResults", qualifications);
				qualificationDTO.reset();
				forward = "success";
			} else {
				forward = "error";
			}
			
			if(qualificationTypeList.size() == 0) {
				setQualificationTypeList();
			}
			return forward;
		} catch (BusinessException be) {
			log.error(be);
        	be.printStackTrace();
			return "error";
		} catch (Exception e) {
			log.error(e);
        	e.printStackTrace();
			return "error";
		}
	}
	
	public String editEmployeeQualification() {
		try{
			if(qualificationTypeList.size() == 0) {
				setQualificationTypeList();
			}
			Long qualificationDid = Long.valueOf(request.getParameter("did"));
			EmployeeDelegate employeeDelegate = new EmployeeDelegate();
			EmployeeQualificationDTO empQualificationDTO = employeeDelegate.getEmpQualificationByDid(qualificationDid);
			
			if(empQualificationDTO != null) {
				setQualificationDTO(empQualificationDTO);
			}
			return "success";
		} catch (BusinessException be) {
			log.error(be);
        	be.printStackTrace();
			return "error";
		} catch (Exception e) {
			log.error(e);
        	e.printStackTrace();
			return "error";
		}
	}
	
	public String deleteEmployeeQualification() {
		try{
			if(qualificationTypeList.size() == 0) {
				setQualificationTypeList();
			}
			Long qualificationDid = Long.valueOf(request.getParameter("qualDid"));
			EmployeeDelegate employeeDelegate = new EmployeeDelegate();
			employeeDelegate.deleteEmployeeQualification(qualificationDid);
			return loadEmployeeQualificationPage();
		} catch (BusinessException be) {
			log.error(be);
        	be.printStackTrace();
			return "error";
		} catch (Exception e) {
			log.error(e);
        	e.printStackTrace();
			return "error";
		}
	}
	
	////
	
	public String loadEmployeeSkillPage() {
		try{
			EmployeeDelegate employeeDelegate = new EmployeeDelegate();
			Long employeeDid = Long.valueOf(request.getParameter("did"));
			skillDTO.setEmployeeDid(employeeDid);
			
			if(skillTypeTypeList.size() == 0) {
				setSkillTypeTypeList();
			}
			
			List<EmployeeSkillDTO> qualifications = employeeDelegate.getEmployeeSkills(skillDTO.getEmployeeDid());
			sessionMap.put("empSkillResults", qualifications);
			return "success";
		} catch (BusinessException be) {
			log.error(be);
        	be.printStackTrace();
			return "error";
		} catch (Exception e) {
			log.error(e);
        	e.printStackTrace();
			return "error";
		}
	}
	
	public String saveEmployeeSkill() {
		String forward = "success";
		validateSkillData();
		try{
			if (!hasFieldErrors()) {
			EmployeeDelegate employeeDelegate = new EmployeeDelegate();
			employeeDelegate.saveEmployeeSkill(skillDTO);
			List<EmployeeSkillDTO> qualifications = employeeDelegate.getEmployeeSkills(skillDTO.getEmployeeDid());
			sessionMap.put("empSkillResults", qualifications);
			skillDTO.reset();
			forward = "success";
			} else {
				forward= "error";
			}
			
			if(skillTypeTypeList.size() == 0) {
				setSkillTypeTypeList();
			}
	
			return forward;
		} catch (BusinessException be) {
			log.error(be);
        	be.printStackTrace();
			return "error";
		} catch (Exception e) {
			log.error(e);
        	e.printStackTrace();
			return "error";
		}
	}
	
	public String editEmployeeSkill() {
		try{
			if(skillTypeTypeList.size() == 0) {
				setSkillTypeTypeList();
			}
			Long skillDid = Long.valueOf(request.getParameter("did"));
			EmployeeDelegate employeeDelegate = new EmployeeDelegate();
			EmployeeSkillDTO empSkillDTO = employeeDelegate.getEmpSkillByDid(skillDid);
			
			if(empSkillDTO != null) {
				setSkillDTO(empSkillDTO);
			}
			return "success";
		} catch (BusinessException be) {
			log.error(be);
        	be.printStackTrace();
			return "error";
		} catch (Exception e) {
			log.error(e);
        	e.printStackTrace();
			return "error";
		}
	}
	
	public String deleteEmployeeSkill() {
		try{
			if(skillTypeTypeList.size() == 0) {
				setSkillTypeTypeList();
			}
			
			Long skillDid = Long.valueOf(request.getParameter("skillDid"));
			EmployeeDelegate employeeDelegate = new EmployeeDelegate();
			employeeDelegate.deleteEmployeeSkill(skillDid);
			return loadEmployeeSkillPage();
		} catch (BusinessException be) {
			log.error(be);
        	be.printStackTrace();
			return "error";
		} catch (Exception e) {
			log.error(e);
        	e.printStackTrace();
			return "error";
		}
	}
	
	
	public String loadPropertyAssignmentPage() {
		try{
			propertyAssignmentDTO.reset();
			Long employeeDid = null;
			if(propertyAssignmentDTO.getEmployeeDid() == null) {
				employeeDid = Long.valueOf(request.getParameter("did"));
				propertyAssignmentDTO.setEmployeeDid(employeeDid);
			} else{
				employeeDid = propertyAssignmentDTO.getEmployeeDid();
			}
			
			EmployeeDelegate employeeDelegate = new EmployeeDelegate();
			List<CompanyPropertyAssignmentDTO> propertyAssignment = employeeDelegate.getCompanyPropertyAssignment(employeeDid);
			sessionMap.put("empPropAssignResults", propertyAssignment);
			
			if(propertyTypeList.size() == 0) {
				if(sessionMap.get("propertyTypeData") != null) {
					setPropertyTypeList((List<CompanyPropertyTypeDTO>)sessionMap.get("propertyTypeData"));
				} else {
					GlobalDelegate globalDelegate = new GlobalDelegate();
					List<CompanyPropertyTypeDTO> propertyTypes = globalDelegate.findAllPropertyTypes();
					if(propertyTypes != null && propertyTypes.size() > 0) {
						setPropertyTypeList(propertyTypes);
						sessionMap.put("propertyTypeData", propertyTypes);
					}
				}
			}
			
			return "success";
		} catch (BusinessException be) {
			log.error(be);
        	be.printStackTrace();
			return "error";
		} catch (Exception e) {
			log.error(e);
        	e.printStackTrace();
			return "error";
		}
	}
	
	public String savePropertyAssignment() {
		String forward = "success";
		validatePropertyAssignmentData();
		try{
			if (!hasFieldErrors()) {
				EmployeeDelegate employeeDelegate = new EmployeeDelegate();
				employeeDelegate.savePropertyAssignment(propertyAssignmentDTO);
				List<CompanyPropertyAssignmentDTO> propertyAssignment = employeeDelegate.getCompanyPropertyAssignment(propertyAssignmentDTO.getEmployeeDid());
				sessionMap.put("empPropAssignResults", propertyAssignment);
				propertyAssignmentDTO.reset();
				forward = "success";
			} else {
				forward = "error";
			}
			
			if(propertyTypeList.size() == 0) {
				setPropertyTypeList((List<CompanyPropertyTypeDTO>)sessionMap.get("propertyTypeData"));
			}
						
			return forward;
		} catch (BusinessException be) {
			log.error(be);
        	be.printStackTrace();
			return "error";
		} catch (Exception e) {
			log.error(e);
        	e.printStackTrace();
			return "error";
		}
	}
	
	public String editPropertyAssignment() {
		try{
			if(propertyTypeList.size() == 0) {
				setPropertyTypeList((List<CompanyPropertyTypeDTO>)sessionMap.get("propertyTypeData"));
			}
			Long propertyAssignDid = Long.valueOf(request.getParameter("did"));
			EmployeeDelegate employeeDelegate = new EmployeeDelegate();
			CompanyPropertyAssignmentDTO  propAssignment = employeeDelegate.getPropertyAssignmentByDid(propertyAssignDid);
			
			if(propAssignment != null) {
				setPropertyAssignmentDTO(propAssignment);
			}
			return "success";
		} catch (BusinessException be) {
			log.error(be);
        	be.printStackTrace();
			return "error";
		} catch (Exception e) {
			log.error(e);
        	e.printStackTrace();
			return "error";
		}
	}
	
	public String deletePropertyAssignment() {
		try{
			if(propertyTypeList.size() == 0) {
				setPropertyTypeList((List<CompanyPropertyTypeDTO>)sessionMap.get("propertyTypeData"));
			}
			Long assignmentDid = Long.valueOf(request.getParameter("assignmentDid"));
			EmployeeDelegate employeeDelegate = new EmployeeDelegate();
			employeeDelegate.deletePropertyAssignment(assignmentDid);
			return loadPropertyAssignmentPage();
		} catch (BusinessException be) {
			log.error(be);
        	be.printStackTrace();
			return "error";
		} catch (Exception e) {
			log.error(e);
        	e.printStackTrace();
			return "error";
		}
	}
	
	public String returnCompanyProperty() {		
		try{
			if(propertyTypeList.size() == 0) {
				setPropertyTypeList((List<CompanyPropertyTypeDTO>)sessionMap.get("propertyTypeData"));
			}
			Long propertyAssignDid = Long.valueOf(request.getParameter("did"));
			EmployeeDelegate employeeDelegate = new EmployeeDelegate();
			CompanyPropertyAssignmentDTO  propAssignment = employeeDelegate.getPropertyAssignmentByDid(propertyAssignDid);
			
			propertyAssignmentDTO.setPropertyAssignmentDid(propAssignment.getPropertyAssignmentDid());
			propertyAssignmentDTO.setEmployeeDid(propAssignment.getEmployeeDid());
			propertyAssignmentDTO.setPropertyTypeDid(propAssignment.getPropertyTypeDid());
			propertyAssignmentDTO.setAssignedDate(propAssignment.getAssignedDate());
			propertyAssignmentDTO.setComment(propAssignment.getComment());
			propertyAssignmentDTO.setDisableReturnDate(false);
			
			return "success";
		} catch (BusinessException be) {
			log.error(be);
        	be.printStackTrace();
			return "error";
		} catch (Exception e) {
			log.error(e);
        	e.printStackTrace();
			return "error";
		}
	}
	
	public String loadResignationAndClearence() {
		try {
			Long employeeDid = Long.valueOf(request.getParameter("did"));
			resignationDTO.setEmployeeDid(employeeDid);
			EmployeeDelegate employeeDelegate = new EmployeeDelegate();
			List<CompanyPropertyAssignmentDTO> companyPropertyAssignmentDTO = employeeDelegate.getCompanyPropertyAssignment(employeeDid);
			if(companyPropertyAssignmentDTO != null && companyPropertyAssignmentDTO.size() > 0) {
				List<CompanyPropertyTypeDTO> propList = new ArrayList<CompanyPropertyTypeDTO>();
				 for (int i = 0; i < companyPropertyAssignmentDTO.size(); i++) {
					 CompanyPropertyAssignmentDTO prop =  companyPropertyAssignmentDTO.get(i);
					 CompanyPropertyTypeDTO propType = new CompanyPropertyTypeDTO();
					 propType.setPropertyName(prop.getPropertyName());
					 if(prop.getReturnedDate() != null) {
						 propType.setReturned(true);
						 propType.setStrReturnValue("Yes");
					 } else {
						 propType.setReturned(false);
						 propType.setStrReturnValue("No");
					 }
					 propList.add(propType);
				}
				resignationDTO.setCompanyPropertyTypeDTO(propList);
			}
			return "success";
		} catch (BusinessException be) {
			log.error(be);
        	be.printStackTrace();
			return "error";
		} catch (Exception e) {
			log.error(e);
        	e.printStackTrace();
			return "error";
		}
	}
	
	public String resignationAndClearence() {
		try{
			EmployeeDelegate employeeDelegate = new EmployeeDelegate();
			employeeDelegate.saveEmployeeClearence(resignationDTO);
			return "success";
		} catch (BusinessException be) {
			log.error(be);
        	be.printStackTrace();
			return "error";
		} catch (Exception e) {
			log.error(e);
        	e.printStackTrace();
			return "error";
		}
	}
	
	
	public String resetPage() {
		try {
			 employeeDTO.reset();
			 populatePageData();
			 return "success";
		 } catch (BusinessException be) {
	     	log.error(be);
	     	be.printStackTrace();
	        return "error";
	     }
    }
	

	
	private void populateComments() {
		if(Util.isNotEmpty(comments)) {
			List<CommentDTO> commentList = new ArrayList<CommentDTO>();
			
			String[] commentArray = comments.split(",");
			for(String oneComment : commentArray) {
				CommentDTO comment = new CommentDTO();
				comment.setComment(oneComment.split(":")[1]);
				commentList.add(comment);
			}
				
			employeeDTO.setEmpComments(commentList);
		}
	}
    
    private void validateEmployeeData() {
    	
    	if(Util.isEmpty(employeeDTO.getFirstName())) {
    		addFieldError("employeeDTO.firstName", "First Name is required");
    	}
    	
    	if(Util.isEmpty(employeeDTO.getGender())) {
    		addFieldError("employeeDTO.gender", "Gender is required");
    	}
    	
    	if(employeeDTO.getCompanyDid() != null && employeeDTO.getCompanyDid().equals(PresentationConstants.LIST_DEFAULT_VALUE)) {
    		addFieldError("employeeDTO.companyDid", "Company is required");
    	}
    	
    	if(employeeDTO.getDepartmentDid() != null && employeeDTO.getDepartmentDid().equals(PresentationConstants.LIST_DEFAULT_VALUE)) {
    		addFieldError("employeeDTO.departmentDid", "Department is required");
    	}
    	
    	if(Util.isEmpty(employeeDTO.getEmployeeNo())) {
    		addFieldError("employeeDTO.employeeNo", "Employee Number is required");
    	}
    	
    	String uiValidationBypass = System.getProperty(Constants.UI_VALIDATION_BYPASS);
    	
    	if(!uiValidationBypass.equalsIgnoreCase("YES")) {
    		
    		if(Util.isEmpty(employeeDTO.getEpfNo())) {
        		addFieldError("employeeDTO.epfNo", "EPF Number is required");
        	}
        	
        	if(Util.isEmpty(employeeDTO.getNicNo())) {
        		addFieldError("employeeDTO.nicNo", "NIC is required");
        	}
        	
        	if(Util.isEmpty(employeeDTO.getLastName())) {
        		addFieldError("employeeDTO.lastName", "Last Name is required");
        	}
        	
        	if(Util.isEmpty(employeeDTO.getDateOfBirth())) {
        		addFieldError("employeeDTO.dateOfBirth", "Date of Birth is required");
        	}
        	

        	
        	if(Util.isEmpty(employeeDTO.getDateJoined())) {
        		addFieldError("employeeDTO.dateJoined", "Date Joined is required");
        	}
        	
        
        	
        	if(Util.isEmpty(employeeDTO.getDesignation())) {
        		addFieldError("employeeDTO.designation", "Designation is required");
        	}
        	
        	if(Util.isEmpty(employeeDTO.getDueConfDate())) {
        		addFieldError("employeeDTO.dueConfDate", "Due Confirmation Date is required");
        	}
        	
        	if(employeeDTO.getContractTypeDid() != null && employeeDTO.getContractTypeDid().equals(PresentationConstants.LIST_DEFAULT_VALUE)) {
        		addFieldError("employeeDTO.contractTypeDid", "Contract Type is required");
        	}
        	
        	if(Util.isEmpty(employeeDTO.getMaritalStatus())) {
        		addFieldError("employeeDTO.maritalStatus", "Marital Status is required");
        	} else {
        		if(employeeDTO.getMaritalStatus().equalsIgnoreCase("M")) {
        			if(Util.isEmpty(employeeDTO.getSpouseName())) {
            			addFieldError("employeeDTO.spouseName", "Spouse Name is required");
            		}
            		if(Util.isEmpty(employeeDTO.getSpouseGender())) {
            			addFieldError("employeeDTO.spouseGender", "Spouse Gender is required");
            		}
            		if(Util.isEmpty(employeeDTO.getSpouseDateOfBirth())) {
            			addFieldError("employeeDTO.spouseDateOfBirth", "Spouse Date of Birth is required");
            		}
        		}
        	}
    	}
    	
    	

    		
    	//Field validations for formatting
    	
    	if (Util.isNotEmpty(employeeDTO.getNicNo())) {
    		if (!Util.isValidNIC(this.employeeDTO.getNicNo())) {
    			addFieldError("employeeDTO.nicNo.invalid", "NIC number is invalid");
            }
        }
    	
    	if (Util.isNotEmpty(employeeDTO.getEpfNo())) {
            if (!Util.isNumeric(employeeDTO.getEpfNo())) {
            	addFieldError("employeeDTO.epfNo.invalid", "EPF number is invalid");
            }
        }
    	
    	/*if (Util.isNotEmpty(employeeDTO.getCompanyEPFNumber())) {
            if (!Util.isNumeric(employeeDTO.getCompanyEPFNumber())) {
            	addFieldError("employeeDTO.companyEPFNumber.invalid", "Company EPF number is invalid");
            }
        }*/
    	
    	if (Util.isNotEmpty(employeeDTO.getHomePhoneNo())) {
             if (!Util.isNumeric(employeeDTO.getHomePhoneNo())) {
            	 addFieldError("employeeDTO.homePhoneNo.invalid1", "Home Phone Number is invalid");
             }

             if (employeeDTO.getHomePhoneNo().length() != 10) {
            	 addFieldError("employeeDTO.homePhoneNo.invalid2", "Home Phone Number should have 10 digits");
             }
         }
    	
    	if (Util.isNotEmpty(employeeDTO.getMobileNo())) {
            if (!Util.isNumeric(employeeDTO.getMobileNo())) {
           	 addFieldError("employeeDTO.mobileNo.invalid1", "Mobile Number is invalid");
            }

            if (employeeDTO.getMobileNo().length() != 10) {
            	addFieldError("employeeDTO.mobileNo.invalid2", "Mobile Number should have 10 digits");
            }
        }
    	
    	if (Util.isNotEmpty(employeeDTO.getEmail())) {
            if (!Util.isValidEmail(employeeDTO.getEmail())) {
            	addFieldError("employeeDTO.email", "Email address is invalid");
            }
        }
    	
    	if (Util.isNotEmpty(employeeDTO.getDateOfBirth())) {
            if (!Util.isValidDate(employeeDTO.getDateOfBirth(), "dd-MM-yyyy")) {
            	addFieldError("employeeDTO.dateOfBirth.invalid", "The preffered date format is dd-MM-yyyy");
            }
        }
    	
    	if (Util.isNotEmpty(employeeDTO.getDateJoined())) {
            if (!Util.isValidDate(employeeDTO.getDateJoined(), "dd-MM-yyyy")) {
            	addFieldError("employeeDTO.dateJoined.invalid", "The preffered date format is dd-MM-yyyy");
            }
        }
    	
    	if (Util.isNotEmpty(employeeDTO.getDueConfDate())) {
            if (!Util.isValidDate(employeeDTO.getDueConfDate(), "dd-MM-yyyy")) {
            	addFieldError("employeeDTO.dueConfDate.invalid", "The preffered date format is dd-MM-yyyy");
            }
        }
    	
    	if (Util.isNotEmpty(employeeDTO.getConfDate())) {
            if (!Util.isValidDate(employeeDTO.getConfDate(), "dd-MM-yyyy")) {
            	addFieldError("employeeDTO.confDate.invalid", "The preffered date format is dd-MM-yyyy");
            }
        }
    	
    	if (Util.isNotEmpty(employeeDTO.getSpouseDateOfBirth())) {
            if (!Util.isValidDate(employeeDTO.getSpouseDateOfBirth(), "dd-MM-yyyy")) {
            	addFieldError("employeeDTO.spouseDateOfBirth.invalid", "The preffered date format is dd-MM-yyyy");
            }
        }
    	
    }
    
    
    private void validateQualificationData() {
    	if(qualificationDTO.getQualificationTypeDid().equals(PresentationConstants.LIST_DEFAULT_VALUE)) {
    		addFieldError("qualificationDTO.qualificationTypeDid", "Qualification Type is required");		
    	}
    	
    	if(Util.isEmpty(qualificationDTO.getDescription())) {
    		addFieldError("qualificationDTO.description", "Description is required");		
    	}
    	
    	if (Util.isNotEmpty(qualificationDTO.getYear())) {
            if (!Util.isNumeric(qualificationDTO.getYear())) {
            	addFieldError("qualificationDTO.year.invalid", "Year is invalid");
            }
        }
    }
    
    private void validateSkillData() {
    	if(skillDTO.getSkillTypeDid().equals(PresentationConstants.LIST_DEFAULT_VALUE)) {
    		addFieldError("skillDTO.skillTypeDid", "Skill Type is required");		
    	}
    	
    	if(Util.isEmpty(skillDTO.getDescription())) {
    		addFieldError("skillDTO.description", "Description is required");		
    	}
    }
    
    private void validatePropertyAssignmentData() {
    	if(propertyAssignmentDTO.getPropertyTypeDid().equals(PresentationConstants.LIST_DEFAULT_VALUE)) {
    		addFieldError("propertyAssignmentDTO.propertyTypeDid", "Property Type is required");		
    	}
    	
    	if(Util.isEmpty(propertyAssignmentDTO.getAssignedDate())) {
    		addFieldError("propertyAssignmentDTO.assignedDate", "Assigned Date is required");		
    	}
    }
    

    private void calcEmpAge() {
    	if(Util.isEmpty(employeeDTO.getAge())) {
	    	String[] dobComponents = Util.formatDate(employeeDTO.getDateOfBirth()).split("-");
	    	int date = Integer.parseInt(dobComponents[0]);
	    	int month = Integer.parseInt(dobComponents[1]);
	    	int year = Integer.parseInt(dobComponents[2]);
	    	LocalDate birthdate = new LocalDate(year, month, date);
	    	LocalDate now = new LocalDate();
	    	Years age = Years.yearsBetween(birthdate, now);
	    	employeeDTO.setAge(age.getYears());
    	}
    }
    

   
}
