/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.business;

import com.ha.entity.dao.hibernate.HibernateDAOFactory;
import com.ha.entity.dao.hibernate.ICommonDAO;
import com.ha.entity.dao.hibernate.IEmployeeDAO;
import com.ha.entity.dao.hibernate.IGlobalDAO;
import com.ha.entity.model.custom.CommentDTO;
import com.ha.entity.model.custom.CompanyPropertyAssignmentDTO;
import com.ha.entity.model.custom.ConfirmationAlertDTO;
import com.ha.entity.model.custom.EmployeeAllowanceDTO;
import com.ha.entity.model.custom.EmployeeChildDTO;
import com.ha.entity.model.custom.EmployeeDTO;
import com.ha.entity.model.custom.EmployeeEmergencyContactDTO;
import com.ha.entity.model.custom.EmployeeQualificationDTO;
import com.ha.entity.model.custom.EmployeeSearchDTO;
import com.ha.entity.model.custom.EmployeeSearchResultsDTO;
import com.ha.entity.model.custom.EmployeeSkillDTO;
import com.ha.entity.model.custom.HistoryDTO;
import com.ha.entity.model.custom.ResignationDTO;
import com.ha.entity.model.domain.AttributeGroup;
import com.ha.entity.model.domain.CompositeAddedAttribute;
import com.ha.entity.model.domain.CompositeAttributeValue;
import com.ha.entity.model.domain.ContractType;
import com.ha.entity.model.domain.Department;
import com.ha.entity.model.domain.DepartmentAssignment;
import com.ha.entity.model.domain.Employee;
import com.ha.entity.model.domain.EmployeeAllowance;
import com.ha.entity.model.domain.EmployeeClearence;
import com.ha.entity.model.domain.EmployeeHistory;
import com.ha.entity.model.domain.EmployeePropertyAssignment;
import com.ha.entity.model.domain.EmployeeQualification;
import com.ha.entity.model.domain.EmployeeSkill;
import com.ha.entity.model.domain.SingleAddedAttribute;
import com.ha.exceptions.BusinessException;
import com.ha.exceptions.ExceptionHelper;
import com.ha.util.Constants;
import com.ha.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import org.jboss.ejb3.annotation.RemoteBinding;
import org.jboss.ejb3.annotation.LocalBinding;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author Buddhini
 */
@Stateless
@Local(EmployeeServicBeaneLocal.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@LocalBinding(jndiBinding = "EmployeeServiceBean/local")
public class EmployeeServiceBean implements EmployeeServicBeaneLocal {

	private Log log = LogFactory.getLog(ExceptionHelper.class);
	@Resource
	private SessionContext context;
	private ICommonDAO commonDAO = HibernateDAOFactory.getInstance().getCommonDAO();
	private IEmployeeDAO employeeDAO = HibernateDAOFactory.getInstance().getEmployeeDAO();

	public void saveEmployeeDetails(EmployeeDTO employeeDTO)
			throws BusinessException {
		try {
			Employee employee = new Employee();
			
			if (employeeDTO.getEmployeeDid() != null) {
				employee.setEmployeeDid(employeeDTO.getEmployeeDid());
			}
			if (Util.isNotEmpty(employeeDTO.getFirstName())) {
				employee.setFirstName(employeeDTO.getFirstName());
			}
			if (Util.isNotEmpty(employeeDTO.getMiddleName())) {
				employee.setMiddleName(employeeDTO.getMiddleName());
			}
			if (Util.isNotEmpty(employeeDTO.getLastName())) {
				employee.setLastName(employeeDTO.getLastName());
			}
			if (Util.isNotEmpty(employeeDTO.getDateOfBirth())) {
				employee.setDateOfBirth(employeeDTO.getDateOfBirth());
			}
			if (Util.isNotEmpty(employeeDTO.getAge())) {
				employee.setAge(employeeDTO.getAge());
			}
			if (Util.isNotEmpty(employeeDTO.getGender())) {
				employee.setGender(employeeDTO.getGender());
			}
			if (Util.isNotEmpty(employeeDTO.getNicNo())) {
				employee.setNicNo(employeeDTO.getNicNo());
			}
			if (Util.isNotEmpty(employeeDTO.getPassportNo())) {
				employee.setPassportNo(employeeDTO.getPassportNo());
			}
			if (Util.isNotEmpty(employeeDTO.getDrivingLicenseNo())) {
				employee.setDrivingLicenseNo(employeeDTO.getDrivingLicenseNo());
			}
			if (Util.isNotEmpty(employeeDTO.getEmployeeNo())) {
				employee.setEmployeeNo(employeeDTO.getEmployeeNo());
			}
			if (employeeDTO.getContractTypeDid() != null && employeeDTO.getContractTypeDid() != -1L) {
				ContractType contractType = new ContractType();
				contractType.setContractTypeDid(employeeDTO
						.getContractTypeDid());
				employee.setContractType(contractType);
			}
			if (Util.isNotEmpty(employeeDTO.getCompanyEPFNumber())) {
				employee.setCompanyEpfNo(employeeDTO.getCompanyEPFNumber());
			}
			if (Util.isNotEmpty(employeeDTO.getEpfNo())) {
				employee.setEpfNo(employeeDTO.getEpfNo());
			}
			if (Util.isNotEmpty(employeeDTO.getCompanyDid())) {
				employee.setCompanyDid(employeeDTO.getCompanyDid());
			}
			if (Util.isNotEmpty(employeeDTO.getDesignation())) {
				employee.setDesignation(employeeDTO.getDesignation());
			}
			if (Util.isNotEmpty(employeeDTO.getDateJoined())) {
				employee.setDateJoined(employeeDTO.getDateJoined());
			}
			if (Util.isNotEmpty(employeeDTO.getDueConfDate())) {
				employee.setDueConfirmationDate(employeeDTO.getDueConfDate());
			}
			if (Util.isNotEmpty(employeeDTO.getConfDate())) {
				employee.setConfirmationDate(employeeDTO.getConfDate());
			}
			if (Util.isNotEmpty(employeeDTO.getAddressLine1())) {
				employee.setAddressLine1(employeeDTO.getAddressLine1());
			}
			if (Util.isNotEmpty(employeeDTO.getAddressLine2())) {
				employee.setAddressLine2(employeeDTO.getAddressLine2());
			}
			if (Util.isNotEmpty(employeeDTO.getAddressLine3())) {
				employee.setAddressLine3(employeeDTO.getAddressLine3());
			}
			if (Util.isNotEmpty(employeeDTO.getHomePhoneNo())) {
				employee.setHomePhoneNo(employeeDTO.getHomePhoneNo());
			}
			if (Util.isNotEmpty(employeeDTO.getMobileNo())) {
				employee.setMobileNo(employeeDTO.getMobileNo());
			}
			if (Util.isNotEmpty(employeeDTO.getEmail())) {
				employee.setEmail(employeeDTO.getEmail());
			}
			if (Util.isNotEmpty(employeeDTO.getMaritalStatus())) {
				employee.setMaritalStatus(employeeDTO.getMaritalStatus());
			}
			if(Util.isNotEmpty(employeeDTO.getBasicSalary())) {
				employee.setBasicSalary(employeeDTO.getBasicSalary());
			}

			commonDAO.saveAnyObject(employee);
			employeeDTO.setEmployeeDid(employee.getEmployeeDid());
			
			
			DepartmentAssignment departmentAssignment = new DepartmentAssignment();
			
			if(employeeDTO.getDepartmentAssignmentDid() != null) {departmentAssignment.setDeptAssignmentDid(employeeDTO.getDepartmentAssignmentDid());}
			
			departmentAssignment.setEmployee(employee);

			if (employeeDTO.getDepartmentDid() != null && employeeDTO.getDepartmentDid() != -1L) {
				Department department = new Department();
				department.setDepartmentDid(employeeDTO.getDepartmentDid());
				departmentAssignment.setDepartment(department);
			}

			commonDAO.saveAnyObject(departmentAssignment);
			
			// Save Allowances
			if(employeeDTO.getAllowances() != null && employeeDTO.getAllowances().size() > 0) {
				List<EmployeeAllowance> allowances = new ArrayList<EmployeeAllowance>();
				
				for(EmployeeAllowanceDTO a : employeeDTO.getAllowances()) {
					EmployeeAllowance allowance = new EmployeeAllowance();
					if(a.getEmployeeAllowanceDid() != null && a.getEmployeeAllowanceDid() > 0) {allowance.setEmployeeAllowanceDid(a.getEmployeeAllowanceDid());}
					allowance.setEmployeeDid(employee.getEmployeeDid());
					allowance.setAllowanceTypeDid(a.getAllowanceTypeDid());
					allowance.setAmount(a.getAmount());
					
					allowances.add(allowance);
				}
				
				commonDAO.saveAnyObjects(allowances.toArray());
			}

			// Save Added Atributes
			saveEmployeeAddedAttributes(employeeDTO, employee.getEmployeeDid());
			
			//Save Comments
			if(employeeDTO.getEmpComments() != null && employeeDTO.getEmpComments().size() > 0) {
				List<EmployeeHistory> historyList = new ArrayList<EmployeeHistory>();
				
				for(CommentDTO comment : employeeDTO.getEmpComments()) {
					EmployeeHistory history = new EmployeeHistory();
					history.setEmployeeDid(employee.getEmployeeDid());
					history.setNote(comment.getComment());
					history.setHistoryDate(new Date());
					
					historyList.add(history);
				}
				
				commonDAO.saveAnyObjects(historyList.toArray());
			}

		} catch (Exception e) {
			log.info("Before handleError ", e);
			ExceptionHelper.handleError(
					"Error in EmployeeServiceBean -> saveEmployeeDetails",
					context, e);
		}

	}

	private void saveEmployeeAddedAttributes(EmployeeDTO employeeDTO, Long employeeDid) {
		//Delete existing single added attributes for the employee
		employeeDAO.deleteEmployeeSingleAddedAttributes(employeeDid, Constants.TOBJECT_EMPLOYEE);
		
		List<SingleAddedAttribute> singleAddedAttributes = new ArrayList<SingleAddedAttribute>();
		if (Util.isNotEmpty(employeeDTO.getSpouseName())) {
			SingleAddedAttribute singleAttr1 = new SingleAddedAttribute();
			singleAttr1.setAttributeGroupDid(Constants.ATTRIBUTE_GROUP_DID_EMPLOYEE);
			singleAttr1.setAttributeSubGroupDid(Constants.ATTRIBUTE_SUBGROUP_DID_EMPLOYEE_SPOUSE_INFO);
			singleAttr1.setAttributeDistributionDid(Constants.ATTRIBUTE_DISTRIBUTION_DID_EMPLOYEE_SPOUSE_NAME);
			singleAttr1.setDid(employeeDid);
			singleAttr1.settObject(Constants.TOBJECT_EMPLOYEE);
			singleAttr1.setAttribValue(employeeDTO.getSpouseName());

			singleAddedAttributes.add(singleAttr1);
		}
		if (Util.isNotEmpty(employeeDTO.getSpouseGender())) {
			SingleAddedAttribute singleAttr2 = new SingleAddedAttribute();
			singleAttr2.setAttributeGroupDid(Constants.ATTRIBUTE_GROUP_DID_EMPLOYEE);
			singleAttr2.setAttributeSubGroupDid(Constants.ATTRIBUTE_SUBGROUP_DID_EMPLOYEE_SPOUSE_INFO);
			singleAttr2.setAttributeDistributionDid(Constants.ATTRIBUTE_DISTRIBUTION_DID_EMPLOYEE_SPOUSE_GENDER);
			singleAttr2.setDid(employeeDid);
			singleAttr2.settObject(Constants.TOBJECT_EMPLOYEE);
			singleAttr2.setAttribValue(employeeDTO.getSpouseGender().equalsIgnoreCase("M") ? "Male" : "Female");

			singleAddedAttributes.add(singleAttr2);
		}
		if (Util.isNotEmpty(employeeDTO.getSpouseDateOfBirth())) {
			SingleAddedAttribute singleAttr3 = new SingleAddedAttribute();
			singleAttr3.setAttributeGroupDid(Constants.ATTRIBUTE_GROUP_DID_EMPLOYEE);
			singleAttr3.setAttributeSubGroupDid(Constants.ATTRIBUTE_SUBGROUP_DID_EMPLOYEE_SPOUSE_INFO);
			singleAttr3.setAttributeDistributionDid(Constants.ATTRIBUTE_DISTRIBUTION_DID_EMPLOYEE_SPOUSE_DOB);
			singleAttr3.setDid(employeeDid);
			singleAttr3.settObject(Constants.TOBJECT_EMPLOYEE);
			singleAttr3.setAttribValue(Util.formatDate(employeeDTO.getSpouseDateOfBirth()));

			singleAddedAttributes.add(singleAttr3);
		}

		commonDAO.saveAnyObjects(singleAddedAttributes.toArray());
		
		//Delete existing composite added attributes for the employee
		employeeDAO.deleteEmployeeCompositeAddedAttributes(employeeDid, Constants.TOBJECT_EMPLOYEE);

		if (employeeDTO.getChildren() != null && employeeDTO.getChildren().size() > 0) {

			for (EmployeeChildDTO dto : employeeDTO.getChildren()) {
				CompositeAddedAttribute compAttribute = new CompositeAddedAttribute();
				compAttribute.setAttributeGroupDid(Constants.ATTRIBUTE_GROUP_DID_EMPLOYEE);
				compAttribute.setAttributeSubGroupDid(Constants.ATTRIBUTE_SUBGROUP_DID_EMPLOYEE_CHILDREN_INFO);
				compAttribute.setDid(employeeDid);
				compAttribute.settObject(Constants.TOBJECT_EMPLOYEE);

				commonDAO.saveAnyObject(compAttribute);

				List<CompositeAttributeValue> compositeAttrVal = new ArrayList<CompositeAttributeValue>();
				CompositeAttributeValue val1 = new CompositeAttributeValue();
				val1.setCompositeAddedAttribute(compAttribute);
				val1.setAttributeDistributionDid(Constants.ATTRIBUTE_DISTRIBUTION_DID_EMPLOYEE_CHILD_NAME);
				val1.setAttributeValue(dto.getChildName());
				compositeAttrVal.add(val1);

				CompositeAttributeValue val2 = new CompositeAttributeValue();
				val2.setCompositeAddedAttribute(compAttribute);
				val2.setAttributeDistributionDid(Constants.ATTRIBUTE_DISTRIBUTION_DID_EMPLOYEE_CHILD_GENDER);
				val2.setAttributeValue(dto.getChildGender());
				compositeAttrVal.add(val2);

				CompositeAttributeValue val3 = new CompositeAttributeValue();
				val3.setCompositeAddedAttribute(compAttribute);
				val3.setAttributeDistributionDid(Constants.ATTRIBUTE_DISTRIBUTION_DID_EMPLOYEE_CHILD_DOB);
				val3.setAttributeValue(dto.getChildDateOfBirth());
				compositeAttrVal.add(val3);

				commonDAO.saveAnyObjects(compositeAttrVal.toArray());
			}
		}

		if (employeeDTO.getEmergencyContacts() != null && employeeDTO.getEmergencyContacts().size() > 0) {

			for (EmployeeEmergencyContactDTO dto : employeeDTO.getEmergencyContacts()) {
				CompositeAddedAttribute compAttribute = new CompositeAddedAttribute();
				compAttribute.setAttributeGroupDid(Constants.ATTRIBUTE_GROUP_DID_EMPLOYEE);
				compAttribute.setAttributeSubGroupDid(Constants.ATTRIBUTE_SUBGROUP_DID_EMPLOYEE_EMERGENCY_CONTACT_INFO);
				compAttribute.setDid(employeeDid);
				compAttribute.settObject(Constants.TOBJECT_EMPLOYEE);

				commonDAO.saveAnyObject(compAttribute);

				List<CompositeAttributeValue> compositeAttrVal = new ArrayList<CompositeAttributeValue>();
				CompositeAttributeValue val1 = new CompositeAttributeValue();
				val1.setCompositeAddedAttribute(compAttribute);
				val1.setAttributeDistributionDid(Constants.ATTRIBUTE_DISTRIBUTION_DID_EMPLOYEE_ECI_CONTACTNAME);
				val1.setAttributeValue(dto.getContactName());
				compositeAttrVal.add(val1);

				CompositeAttributeValue val2 = new CompositeAttributeValue();
				val2.setCompositeAddedAttribute(compAttribute);
				val2.setAttributeDistributionDid(Constants.ATTRIBUTE_DISTRIBUTION_DID_EMPLOYEE_ECI_CONTACTNUMBER);
				val2.setAttributeValue(dto.getContactNumber());
				compositeAttrVal.add(val2);

				CompositeAttributeValue val3 = new CompositeAttributeValue();
				val3.setCompositeAddedAttribute(compAttribute);
				val3.setAttributeDistributionDid(Constants.ATTRIBUTE_DISTRIBUTION_DID_EMPLOYEE_ECI_RELATIONSHIP);
				val3.setAttributeValue(dto.getRelationship());
				compositeAttrVal.add(val3);

				commonDAO.saveAnyObjects(compositeAttrVal.toArray());
			}
		}
	}
	
	private void populateAddedAttributes(EmployeeDTO employeeDTO) throws BusinessException {
		try{
			List<SingleAddedAttribute> singleAddedAttributes = employeeDAO.getEmployeeSingleAddedAttributes(employeeDTO.getEmployeeDid());
			if(singleAddedAttributes != null && singleAddedAttributes.size() > 0) {
				for(SingleAddedAttribute attrib : singleAddedAttributes) {
					if(attrib.getAttributeSubGroupDid().equals(Constants.ATTRIBUTE_SUBGROUP_DID_EMPLOYEE_SPOUSE_INFO)) {
						switch(attrib.getAttributeDistributionDid().intValue()) {
						 case 1000 : 
							 employeeDTO.setSpouseName(attrib.getAttribValue());
							 break;
						 case 1001 : 
							 employeeDTO.setSpouseGender(attrib.getAttribValue());
							 break;
						 case 1002 : 
							 employeeDTO.setSpouseDateOfBirth(Util.parseStringToDate(attrib.getAttribValue()));
							 break;
						 default:
							 break;
						}
					}
				}
			}
			
			List<CompositeAttributeValue> compositeAddedAttribValues = employeeDAO.getEmployeeCompositeAddedAttributes(employeeDTO.getEmployeeDid());
			if(compositeAddedAttribValues != null && compositeAddedAttribValues.size() > 0) {
				
				List<EmployeeChildDTO> childDTOs = new ArrayList<EmployeeChildDTO>();
				List<EmployeeEmergencyContactDTO> contactDTOs = new ArrayList<EmployeeEmergencyContactDTO>();
				
				Map<Long,List<CompositeAttributeValue>> uniqueCompAddedAttribMap = new HashMap<Long,List<CompositeAttributeValue>>();
				
				for(CompositeAttributeValue attribVal : compositeAddedAttribValues) {
					
					Long key = attribVal.getCompositeAddedAttribute().getCompositeAddedAttributeDid();
					
					if(uniqueCompAddedAttribMap.containsKey(key)) {
						List<CompositeAttributeValue> list = uniqueCompAddedAttribMap.get(key);
						list.add(attribVal);
						uniqueCompAddedAttribMap.put(key, list);
					}else{
						List<CompositeAttributeValue> newList = new ArrayList<CompositeAttributeValue>();
						newList.add(attribVal);
						uniqueCompAddedAttribMap.put(key, newList);
					}					
				}
				
				
				for(Iterator<Long> i = uniqueCompAddedAttribMap.keySet().iterator();i.hasNext();) {
					List<CompositeAttributeValue> valueList = uniqueCompAddedAttribMap.get(i.next());
					switch(valueList.get(0).getCompositeAddedAttribute().getAttributeSubGroupDid().intValue()) {
					case 201:
						childDTOs.add(populateEmployeeChildInfo(valueList));
						break;
					case 202:
						contactDTOs.add(populateEmergencyContactInfo(valueList));
						break;
					default:
						break;
					}
				}
				
				if(childDTOs.size() > 0) {
				    employeeDTO.setChildren(childDTOs);
				}
				if(contactDTOs.size() > 0) {
					employeeDTO.setEmergencyContacts(contactDTOs);
				}
			}
		}catch(Exception e) {
			log.info("Before handleError ", e);
			ExceptionHelper.handleError("Error in EmployeeServiceBean -> populateAddedAttributes",context, e);
		}
	}
		
	private EmployeeChildDTO populateEmployeeChildInfo(List<CompositeAttributeValue> attribValList) {
		EmployeeChildDTO childDTO = new EmployeeChildDTO();
		for(CompositeAttributeValue val : attribValList) {
			switch(val.getAttributeDistributionDid().intValue()) {
			 case 2000 : 
				 childDTO.setChildName(val.getAttributeValue());
				 break;
			 case 2001 : 
				 childDTO.setChildGender(val.getAttributeValue());
				 break;
			 case 2002 : 
				 childDTO.setChildDateOfBirth(val.getAttributeValue());
				 break;
			 default:
				 break;
			}
		}
		return childDTO;
	}
	
	private EmployeeEmergencyContactDTO populateEmergencyContactInfo(List<CompositeAttributeValue> attribValList) {
		EmployeeEmergencyContactDTO contactDTO = new EmployeeEmergencyContactDTO();
		for(CompositeAttributeValue val : attribValList) {
			switch(val.getAttributeDistributionDid().intValue()) {
			 case 3000 : 
				 contactDTO.setContactName(val.getAttributeValue());
				 break;
			 case 3001 : 
				 contactDTO.setContactNumber(val.getAttributeValue());
				 break;
			 case 3002 : 
				 contactDTO.setRelationship(val.getAttributeValue());
				 break;
			 default:
				 break;
			}
		}
		return contactDTO;
	}
	
	 @TransactionAttribute(TransactionAttributeType.SUPPORTS)
	 public EmployeeDTO getEmployeeDetails(EmployeeSearchDTO searchDTO) throws BusinessException {
		 EmployeeDTO employeeDTO = employeeDAO.getEmployeeDetails(searchDTO);
		 if(employeeDTO != null) {
			 List<EmployeeAllowanceDTO> empAllow = employeeDAO.getEmployeeAllowances(employeeDTO.getEmployeeDid());
			 if(empAllow != null && empAllow.size() > 0) {
				 employeeDTO.setAllowances(empAllow);
			 }
			 populateAddedAttributes(employeeDTO);
		 }
		 return employeeDTO;
	 }
	 
	 @TransactionAttribute(TransactionAttributeType.SUPPORTS)
	 public List<EmployeeSearchResultsDTO> searchEmployee(EmployeeSearchDTO searchDTO) throws BusinessException {
		 try {
			 return employeeDAO.searchEmployee(searchDTO);
		 } catch(Exception e) {
				log.info("Before handleError ", e);
				ExceptionHelper.handleError("Error in EmployeeServiceBean -> searchEmployee",context, e);
				return null;
		 }
	 }
	 
	 @TransactionAttribute(TransactionAttributeType.SUPPORTS)
	 public List<HistoryDTO> getEmployeeHistory(Long employeeDid) throws BusinessException {
		 try {
			 return employeeDAO.getEmployeeHistory(employeeDid);
		 } catch(Exception e) {
				log.info("Before handleError ", e);
				ExceptionHelper.handleError("Error in EmployeeServiceBean -> getEmployeeHistory",context, e);
				return null;
		 }
	 }


	public void saveEmployeeHistory(HistoryDTO historyDTO) throws BusinessException {
		try {
			EmployeeHistory employeeHistory = new EmployeeHistory();
			employeeHistory.setEmployeeDid(historyDTO.getEmployeeDid());
			employeeHistory.setHistoryDate(historyDTO.getDate());
			employeeHistory.setNote(historyDTO.getDescription());
			
			commonDAO.saveAnyObject(employeeHistory);
		}catch (Exception e) {
			log.info("Before handleError ", e);
			ExceptionHelper.handleError(
					"Error in EmployeeServiceBean -> saveEmployeeHistory",
					context, e);
		}	
	}
	
	public void deleteEmployeeHistory(Long did) throws BusinessException {
		try {
			EmployeeHistory employeeHistory = new EmployeeHistory();
			employeeHistory.setHistoryDid(did);
				
			commonDAO.deleteAnyObject(employeeHistory);
		}catch (Exception e) {
			log.info("Before handleError ", e);
			ExceptionHelper.handleError(
					"Error in EmployeeServiceBean -> deleteEmployeeHistory",
					context, e);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public boolean isExistingEPFNumber(Long companyDid, Integer epfNumber) throws BusinessException {
		try {			
			return employeeDAO.isExistingEPFNumber(companyDid,epfNumber);
		}catch (Exception e) {
			log.info("Before handleError ", e);
			ExceptionHelper.handleError("Error in EmployeeServiceBean -> isExistingEPFNumber",context, e);
			return true;
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public boolean isExistingNICNumber(String nic) throws BusinessException {
		try {			
			return employeeDAO.isExistingNICNumber(nic);
		}catch (Exception e) {
			log.info("Before handleError ", e);
			ExceptionHelper.handleError("Error in EmployeeServiceBean -> isExistingNICNumber",context, e);
			return true;
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<ConfirmationAlertDTO> getConfirmationAlertData(Long userCompanyDid, boolean isSingleCompany, boolean isParent) throws BusinessException {
		try {			
			return employeeDAO.getConfirmationAlertData(userCompanyDid,isSingleCompany,isParent);
		}catch (Exception e) {
			log.info("Before handleError ", e);
			ExceptionHelper.handleError("Error in EmployeeServiceBean -> getConfirmationAlertData",context, e);
			return null;
		}
	}
	
	public void saveEmployeeQualification(EmployeeQualificationDTO qualificationDTO) throws BusinessException {
		try {
			EmployeeQualification employeeQualification = new EmployeeQualification();
			if(qualificationDTO.getQualificationDid() != null) {
				employeeQualification.setQualificationDid(qualificationDTO.getQualificationDid());
			}
			employeeQualification.setEmployeeDid(qualificationDTO.getEmployeeDid());
			employeeQualification.setQualificationTypeDid(qualificationDTO.getQualificationTypeDid());
			employeeQualification.setDescription(qualificationDTO.getDescription());
			if(Util.isNotEmpty(qualificationDTO.getYear())) {
				employeeQualification.setYearObtained(Integer.parseInt(qualificationDTO.getYear()));
			}
			employeeQualification.setComment(qualificationDTO.getComment());
			
			commonDAO.saveAnyObject(employeeQualification);
		}catch (Exception e) {
			log.info("Before handleError ", e);
			ExceptionHelper.handleError(
					"Error in EmployeeServiceBean -> saveEmployeeQualification",
					context, e);
		}	
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<EmployeeQualificationDTO> getEmployeeQualifications(Long employeeDid) throws BusinessException {
		try {			
			return employeeDAO.getEmployeeQualifications(employeeDid);
		}catch (Exception e) {
			log.info("Before handleError ", e);
			ExceptionHelper.handleError("Error in EmployeeServiceBean -> getEmployeeQualifications",context, e);
			return null;
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public EmployeeQualificationDTO getEmpQualificationByDid(Long qualificationDid) throws BusinessException {
		try {			
			return employeeDAO.getEmpQualificationByDid(qualificationDid);
		} catch (Exception e) {
			log.info("Before handleError ", e);
			ExceptionHelper.handleError("Error in EmployeeServiceBean -> getEmpQualificationByDid",context, e);
			return null;
		}
	}
	
	
	public void deleteEmployeeQualification(Long did) throws BusinessException {
		try {
			EmployeeQualification employeeQualification = new EmployeeQualification();
			employeeQualification.setQualificationDid(did);
				
			commonDAO.deleteAnyObject(employeeQualification);
		}catch (Exception e) {
			log.info("Before handleError ", e);
			ExceptionHelper.handleError(
					"Error in EmployeeServiceBean -> deleteEmployeeQualification",
					context, e);
		}
	}
	
	//
	
	public void saveEmployeeSkill(EmployeeSkillDTO skillDTO) throws BusinessException {
		try {
			EmployeeSkill employeeSkill = new EmployeeSkill();
			if(skillDTO.getSkillDid() != null) {
				employeeSkill.setSkillDid(skillDTO.getSkillDid());
			}
			employeeSkill.setEmployeeDid(skillDTO.getEmployeeDid());
			employeeSkill.setSkillTypeDid(skillDTO.getSkillTypeDid());
			employeeSkill.setDescription(skillDTO.getDescription());
			
			commonDAO.saveAnyObject(employeeSkill);
		}catch (Exception e) {
			log.info("Before handleError ", e);
			ExceptionHelper.handleError(
					"Error in EmployeeServiceBean -> saveEmployeeSkill",
					context, e);
		}	
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<EmployeeSkillDTO> getEmployeeSkills(Long employeeDid) throws BusinessException {
		try {			
			return employeeDAO.getEmployeeSkills(employeeDid);
		}catch (Exception e) {
			log.info("Before handleError ", e);
			ExceptionHelper.handleError("Error in EmployeeServiceBean -> getEmployeeSkills",context, e);
			return null;
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public EmployeeSkillDTO getEmpSkillByDid(Long qualificationDid) throws BusinessException {
		try {			
			return employeeDAO.getEmpSkillByDid(qualificationDid);
		} catch (Exception e) {
			log.info("Before handleError ", e);
			ExceptionHelper.handleError("Error in EmployeeServiceBean -> getEmpSkillByDid",context, e);
			return null;
		}
	}
	
	
	public void deleteEmployeeSkill(Long did) throws BusinessException {
		try {
			EmployeeSkill employeeSkill = new EmployeeSkill();
			employeeSkill.setSkillDid(did);
				
			commonDAO.deleteAnyObject(employeeSkill);
		}catch (Exception e) {
			log.info("Before handleError ", e);
			ExceptionHelper.handleError(
					"Error in EmployeeServiceBean -> deleteEmployeeSkill",
					context, e);
		}
	}
	
	public void savePropertyAssignment(CompanyPropertyAssignmentDTO propertyAssignmentDTO) throws BusinessException {
		try {
			EmployeePropertyAssignment employeePropertyAssignment = new EmployeePropertyAssignment();
			if(propertyAssignmentDTO.getPropertyAssignmentDid() != null) {
				employeePropertyAssignment.setPropertyAssignmentDid(propertyAssignmentDTO.getPropertyAssignmentDid());
			}
			employeePropertyAssignment.setEmployeeDid(propertyAssignmentDTO.getEmployeeDid());
			employeePropertyAssignment.setPropertyTypeDid(propertyAssignmentDTO.getPropertyTypeDid());
			employeePropertyAssignment.setAssignedDate(propertyAssignmentDTO.getAssignedDate());
			employeePropertyAssignment.setReturnedDate(propertyAssignmentDTO.getReturnedDate());
			employeePropertyAssignment.setComment(propertyAssignmentDTO.getComment());
			
			commonDAO.saveAnyObject(employeePropertyAssignment);
		} catch (Exception e) {
			log.info("Before handleError ", e);
			ExceptionHelper.handleError(
					"Error in EmployeeServiceBean -> savePropertyAssignment",
					context, e);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<CompanyPropertyAssignmentDTO> getCompanyPropertyAssignment(Long employeeDid) throws BusinessException {
		try {			
			return employeeDAO.getCompanyPropertyAssignment(employeeDid);
		}catch (Exception e) {
			log.info("Before handleError ", e);
			ExceptionHelper.handleError("Error in EmployeeServiceBean -> getCompanyPropertyAssignment",context, e);
			return null;
		}
	}
	
	public CompanyPropertyAssignmentDTO getPropertyAssignmentByDid(Long propertyAssignmentDid) throws BusinessException {
		try {			
			return employeeDAO.getPropertyAssignmentByDid(propertyAssignmentDid);
		}catch (Exception e) {
			log.info("Before handleError ", e);
			ExceptionHelper.handleError("Error in EmployeeServiceBean -> getPropertyAssignmentByDid",context, e);
			return null;
		}
	}
	
	public void deletePropertyAssignment(Long did) throws BusinessException {
		try {
			EmployeePropertyAssignment propertyAssignment = new EmployeePropertyAssignment();
			propertyAssignment.setPropertyAssignmentDid(did);
				
			commonDAO.deleteAnyObject(propertyAssignment);
		}catch (Exception e) {
			log.info("Before handleError ", e);
			ExceptionHelper.handleError(
					"Error in EmployeeServiceBean -> deletePropertyAssignment",
					context, e);
		}
	}
	
	public void updateCompanyPropertyReturns(Long propertyAssignmentDid, Date returnDate) throws BusinessException {
		try {
			employeeDAO.updateCompanyPropertyReturns(propertyAssignmentDid, returnDate);
		}catch (Exception e) {
			log.info("Before handleError ", e);
			ExceptionHelper.handleError(
					"Error in EmployeeServiceBean -> updateCompanyPropertyReturns",
					context, e);
		}
	}
	
	public void saveEmployeeClearence(ResignationDTO resignationDTO) throws BusinessException {
		try {
			EmployeeClearence employeeClearence = new EmployeeClearence();
			employeeClearence.setEmployeeDid(resignationDTO.getEmployeeDid());
			employeeClearence.setbCardHandedOver(resignationDTO.getIsBcardHandedOver());
			employeeClearence.setCompanyPropertyHandedOver(resignationDTO.getIsCompanyPropHandedOver());
			employeeClearence.setFinancialClearenceDone(resignationDTO.getIsFinancialClearenceDone());
			employeeClearence.setGrativityPaid(resignationDTO.getIsGrativityPaid());
			employeeClearence.setResignationAcceptanceIssued(resignationDTO.getIsResignationAcceptanceIssued());
			employeeClearence.setServiceLetterIssued(resignationDTO.getIsServiceLetterIssued());
			employeeClearence.setWorkHandedOver(resignationDTO.getIsWorkHandedOver());
			
			commonDAO.saveAnyObject(employeeClearence);
		} catch (Exception e) {
			log.info("Before handleError ", e);
			ExceptionHelper.handleError(
					"Error in EmployeeServiceBean -> updateCompanyPropertyReturns",
					context, e);
		}
	}
}
