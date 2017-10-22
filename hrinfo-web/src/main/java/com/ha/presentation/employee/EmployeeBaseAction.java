/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.presentation.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.ha.entity.model.custom.AllowanceTypeDTO;
import com.ha.entity.model.custom.CompanyDTO;
import com.ha.entity.model.custom.CompanyPropertyAssignmentDTO;
import com.ha.entity.model.custom.CompanyPropertyTypeDTO;
import com.ha.entity.model.custom.ContractTypeDTO;
import com.ha.entity.model.custom.DepartmentDTO;
import com.ha.entity.model.custom.EmployeeDTO;
import com.ha.entity.model.custom.EmployeeQualificationDTO;
import com.ha.entity.model.custom.EmployeeSearchDTO;
import com.ha.entity.model.custom.EmployeeSkillDTO;
import com.ha.entity.model.custom.HistoryDTO;
import com.ha.entity.model.custom.ResignationDTO;
import com.ha.exceptions.BusinessException;
import com.ha.presentation.util.PresentationConstants;
import com.ha.presentation.util.SelectBoxDataHolder;
import com.ha.services.GlobalDelegate;
import com.ha.util.Constants;
import com.ha.util.Util;
import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author Buddhini
 */
public class EmployeeBaseAction extends ActionSupport implements SessionAware, ServletRequestAware {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Log log = LogFactory.getLog(EmployeeBaseAction.class);
	
	protected GlobalDelegate globalDelegate;
	
	protected HttpServletRequest request;
	protected Map<String, Object> sessionMap;
	protected String activeTab;
	protected Integer pageStatus = 0;
	protected String message;
	
	protected EmployeeDTO employeeDTO = new EmployeeDTO();
	protected EmployeeDTO employeeViewDTO = new EmployeeDTO();
	protected EmployeeSearchDTO employeeSearchDTO = new EmployeeSearchDTO();
	protected HistoryDTO historyDTO = new HistoryDTO();
	protected EmployeeQualificationDTO qualificationDTO = new EmployeeQualificationDTO();
	protected EmployeeSkillDTO skillDTO = new EmployeeSkillDTO();
	protected CompanyPropertyAssignmentDTO propertyAssignmentDTO = new CompanyPropertyAssignmentDTO();
	protected ResignationDTO resignationDTO = new ResignationDTO();

	
	protected List<SelectBoxDataHolder> genderList = new ArrayList<SelectBoxDataHolder>();
	protected List<SelectBoxDataHolder> maritalStatusList = new ArrayList<SelectBoxDataHolder>();
	protected List<SelectBoxDataHolder> companyList = new ArrayList<SelectBoxDataHolder>();
	protected List<SelectBoxDataHolder> departmentList = new ArrayList<SelectBoxDataHolder>();
	protected List<SelectBoxDataHolder> contractTypeList = new ArrayList<SelectBoxDataHolder>();
	protected List<SelectBoxDataHolder> allowanceList = new ArrayList<SelectBoxDataHolder>();
	protected List<SelectBoxDataHolder> qualificationTypeList = new ArrayList<SelectBoxDataHolder>();
	protected List<SelectBoxDataHolder> skillTypeTypeList = new ArrayList<SelectBoxDataHolder>();
	protected List<SelectBoxDataHolder> propertyTypeList = new ArrayList<SelectBoxDataHolder>();
	
	protected List<SelectBoxDataHolder> searchCompanyList = new ArrayList<SelectBoxDataHolder>();
	protected List<SelectBoxDataHolder> searchDepartmentList = new ArrayList<SelectBoxDataHolder>();
	protected List<SelectBoxDataHolder> searchContractTypeList = new ArrayList<SelectBoxDataHolder>();
	
	
	public EmployeeBaseAction() {
		globalDelegate = new GlobalDelegate();
	}
	
	public String loadPage() {
	     try {
	            sessionMap.remove("empSearchResults");
	    	 	
	    	 	populatePageData();
	            return "success";
	        } catch (BusinessException be) {
	        	log.error(be);
	        	be.printStackTrace();
	            return "error";
	        }
	}
	
	protected void populatePageData() throws BusinessException {
		
        if(genderList.size() == 0) {
        	setGenderList();
        }
            
        if(maritalStatusList.size() == 0) {
        	setMaritalStatusList();
        }
        
        CompanyDTO parentCompany = null;
                
        parentCompany = (CompanyDTO)sessionMap.get("parentCompany");

        
        if(parentCompany != null) {
        
	        if(employeeDTO.getParentCompanyDid() == null) {
	        	employeeDTO.setParentCompanyDid(parentCompany.getCompanyDid());
	        }
	        
	        if(Util.isEmpty(employeeDTO.getParentCompanyName())) {
	        	employeeDTO.setParentCompanyName(parentCompany.getCompanyName());
	        }
        }
            

        
        if (companyList.size() == 0) {
        	List<CompanyDTO> companyData = (List<CompanyDTO>)sessionMap.get("companyData");
        	setCompanyList(companyData);

	        //Generate Employee Id
	        if(companyList.size() == 1) {
	        	String nextEmployeeId = globalDelegate.getNextIdNumber(companyData.get(0).getCompanyDid(), Constants.IDENTIFIER_TYPE_EMPLOYEE_NUMBER);
	        	employeeDTO.setEmployeeNo(nextEmployeeId);
	        }
            
        }
                
        if (companyList.size() > 1 && (employeeDTO.getCompanyDid() != null && employeeDTO.getCompanyDid() > 0)) {
        	if (departmentList.size() == 0) {
        		Map<Long, List<DepartmentDTO>> compDeptMap = (Map<Long, List<DepartmentDTO>>) sessionMap.get("departmentData");
        		setDepartmentList(compDeptMap.get(employeeDTO.getCompanyDid()));       
        	}
        } else {
        		setDepartmentList(new ArrayList());
        }
        
        if(contractTypeList.size() == 0) {
        	setContractTypeList((List<ContractTypeDTO>)sessionMap.get("contractTypeData")); 
    	}
        
        if(allowanceList.size() == 0) {
        	setAllowanceList((List<AllowanceTypeDTO>)sessionMap.get("allowanceTypeData"));
        }
        
        
        if (searchCompanyList.size() == 0) {
        	   setSearchCompanyList((List<CompanyDTO>)sessionMap.get("companyData"));
        }

        
        if (searchCompanyList.size() > 1 && (employeeSearchDTO.getCompanyDid() != null && employeeSearchDTO.getCompanyDid() > 0)) {
        	if (searchDepartmentList.size() == 0) {
        		Map<Long, List<DepartmentDTO>> compDeptMap = (Map<Long, List<DepartmentDTO>>) sessionMap.get("departmentData");
        		setSearchDepartmentList(compDeptMap.get(employeeSearchDTO.getCompanyDid()));   
            }
        } else {
        	setSearchDepartmentList(new ArrayList());
        }
        
        if(searchContractTypeList.size() == 0) {
        	setSearchContractTypeList((List<ContractTypeDTO>)sessionMap.get("contractTypeData")); 
        }

    }
			
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getServletRequest() {
		return request;
	}
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;		
	}
	
	public String getActiveTab() {
		return activeTab;
	}

	public void setActiveTab(String activeTab) {
		this.activeTab = activeTab;
	}
	
	public Integer getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(Integer pageStatus) {
		this.pageStatus = pageStatus;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public EmployeeDTO getEmployeeDTO() {
        return employeeDTO;
    }

    public void setEmployeeDTO(EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }
    
	public EmployeeDTO getEmployeeViewDTO() {
		return employeeViewDTO;
	}

	public void setEmployeeViewDTO(EmployeeDTO employeeViewDTO) {
		this.employeeViewDTO = employeeViewDTO;
	}
	
	public EmployeeSearchDTO getEmployeeSearchDTO() {
		return employeeSearchDTO;
	}

	public void setEmployeeSearchDTO(EmployeeSearchDTO employeeSearchDTO) {
		this.employeeSearchDTO = employeeSearchDTO;
	}
	
	public HistoryDTO getHistoryDTO() {
		return historyDTO;
	}

	public void setHistoryDTO(HistoryDTO historyDTO) {
		this.historyDTO = historyDTO;
	}
	
    public EmployeeQualificationDTO getQualificationDTO() {
		return qualificationDTO;
	}

	public void setQualificationDTO(EmployeeQualificationDTO qualificationDTO) {
		this.qualificationDTO = qualificationDTO;
	}

	public EmployeeSkillDTO getSkillDTO() {
		return skillDTO;
	}

	public void setSkillDTO(EmployeeSkillDTO skillDTO) {
		this.skillDTO = skillDTO;
	}
	
	public CompanyPropertyAssignmentDTO getPropertyAssignmentDTO() {
		return propertyAssignmentDTO;
	}

	public void setPropertyAssignmentDTO(
			CompanyPropertyAssignmentDTO propertyAssignmentDTO) {
		this.propertyAssignmentDTO = propertyAssignmentDTO;
	}
	
	public ResignationDTO getResignationDTO() {
		return resignationDTO;
	}

	public void setResignationDTO(ResignationDTO resignationDTO) {
		this.resignationDTO = resignationDTO;
	}

	public void setGenderList() {
		this.genderList.add(new SelectBoxDataHolder("M","Male"));
		this.genderList.add(new SelectBoxDataHolder("F","Female"));
	}
	
	public List<SelectBoxDataHolder> getMaritalStatusList() {
		return maritalStatusList;
	}

	public void setMaritalStatusList() {
		this.maritalStatusList.add(new SelectBoxDataHolder("M","Yes"));
		this.maritalStatusList.add(new SelectBoxDataHolder("S","No"));
	}

	public List<SelectBoxDataHolder> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<CompanyDTO> companyList) {
		this.companyList.add(new SelectBoxDataHolder(PresentationConstants.LIST_DEFAULT_VALUE, "---Select---"));
		for (CompanyDTO dto : companyList) {
			this.companyList.add(new SelectBoxDataHolder(dto.getCompanyDid(),dto.getCompanyName()));
		}
	}

	public List<SelectBoxDataHolder> getDepartmentList() {
		return departmentList;
	}

	public List<SelectBoxDataHolder> getGenderList() {
		return genderList;
	}

	public void setDepartmentList(List<DepartmentDTO> departmentList) {
		this.departmentList.add(new SelectBoxDataHolder(PresentationConstants.LIST_DEFAULT_VALUE,"---Select---"));
        for(DepartmentDTO dto : departmentList) {
            this.departmentList.add(new SelectBoxDataHolder(dto.getDepartmentDid(),dto.getDepartmentCode()));
        }
	}

	public List<SelectBoxDataHolder> getContractTypeList() {
		return contractTypeList;
	}

	public void setContractTypeList(List<ContractTypeDTO> contractTypeList) {
		this.contractTypeList.add(new SelectBoxDataHolder(PresentationConstants.LIST_DEFAULT_VALUE,"---Select---"));
        for(ContractTypeDTO dto : contractTypeList) {
            this.contractTypeList.add(new SelectBoxDataHolder(dto.getContractTypeDid(),dto.getName()));
        }
	}
	
    public List<SelectBoxDataHolder> getAllowanceList() {
		return allowanceList;
	}

	public void setAllowanceList(List<AllowanceTypeDTO> allowanceList) {
		this.allowanceList.add(new SelectBoxDataHolder(PresentationConstants.LIST_DEFAULT_VALUE,"---Select---"));
		for(AllowanceTypeDTO dto : allowanceList) {
            this.allowanceList.add(new SelectBoxDataHolder(dto.getAllowanceDid(),dto.getAllowanceType()));
        }
	}
	
	public List<SelectBoxDataHolder> getQualificationTypeList() {
		return qualificationTypeList;
	}

	public void setQualificationTypeList() {
		this.qualificationTypeList.add(new SelectBoxDataHolder(PresentationConstants.LIST_DEFAULT_VALUE,"---Select---"));
		this.qualificationTypeList.add(new SelectBoxDataHolder(PresentationConstants.QUALIFICATION_TYPE_DID_ACADEMIC,"Academic"));
		this.qualificationTypeList.add(new SelectBoxDataHolder(PresentationConstants.QUALIFICATION_TYPE_DID_PROFESSIONAL,"Professional"));
	}

	public List<SelectBoxDataHolder> getSkillTypeTypeList() {
		return skillTypeTypeList;
	}

	public void setSkillTypeTypeList() {
		this.skillTypeTypeList.add(new SelectBoxDataHolder(PresentationConstants.LIST_DEFAULT_VALUE,"---Select---"));
		this.skillTypeTypeList.add(new SelectBoxDataHolder(PresentationConstants.SKILL_TYPE_DID_MANAGEMENT,"Management"));
		this.skillTypeTypeList.add(new SelectBoxDataHolder(PresentationConstants.SKILL_TYPE_DID_COMPUTER,"Computer"));
		this.skillTypeTypeList.add(new SelectBoxDataHolder(PresentationConstants.SKILL_TYPE_DID_OTHER,"Other"));
	}
	
	public List<SelectBoxDataHolder> getPropertyTypeList() {
		return propertyTypeList;
	}

	public void setPropertyTypeList(List<CompanyPropertyTypeDTO> propertyTypeList) {
		this.propertyTypeList.add(new SelectBoxDataHolder(PresentationConstants.LIST_DEFAULT_VALUE,"---Select---"));
        for(CompanyPropertyTypeDTO dto : propertyTypeList) {
            this.propertyTypeList.add(new SelectBoxDataHolder(dto.getPropertyTypeDid(),dto.getPropertyName()));
        }
	}

	public List<SelectBoxDataHolder> getSearchCompanyList() {
		return searchCompanyList;
	}

	public void setSearchCompanyList(List<CompanyDTO> searchCompanyList) {
		this.searchCompanyList.add(new SelectBoxDataHolder(PresentationConstants.LIST_DEFAULT_VALUE, "---Select---"));
		for (CompanyDTO dto : searchCompanyList) {
			this.searchCompanyList.add(new SelectBoxDataHolder(dto.getCompanyDid(),dto.getCompanyName()));
		}
	}

	public List<SelectBoxDataHolder> getSearchDepartmentList() {
		return searchDepartmentList;
	}

	public void setSearchDepartmentList(List<DepartmentDTO> searchDepartmentList) {
		this.searchDepartmentList.add(new SelectBoxDataHolder(PresentationConstants.LIST_DEFAULT_VALUE,"---Select---"));
        for(DepartmentDTO dto : searchDepartmentList) {
            this.searchDepartmentList.add(new SelectBoxDataHolder(dto.getDepartmentDid(),dto.getDepartmentCode()));
        }
	}

	public List<SelectBoxDataHolder> getSearchContractTypeList() {
		return searchContractTypeList;
	}

	public void setSearchContractTypeList(List<ContractTypeDTO> searchContractTypeList) {
		this.searchContractTypeList.add(new SelectBoxDataHolder(PresentationConstants.LIST_DEFAULT_VALUE,"---Select---"));
        for(ContractTypeDTO dto : searchContractTypeList) {
            this.searchContractTypeList.add(new SelectBoxDataHolder(dto.getContractTypeDid(),dto.getName()));
        }
	}
}
