package com.ha.entity.dao.hibernate;

import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import com.ha.entity.model.custom.CompanyPropertyAssignmentDTO;
import com.ha.entity.model.custom.ConfirmationAlertDTO;
import com.ha.entity.model.custom.EmployeeAllowanceDTO;
import com.ha.entity.model.custom.EmployeeDTO;
import com.ha.entity.model.custom.EmployeeQualificationDTO;
import com.ha.entity.model.custom.EmployeeSearchDTO;
import com.ha.entity.model.custom.EmployeeSearchResultsDTO;
import com.ha.entity.model.custom.EmployeeSkillDTO;
import com.ha.entity.model.custom.HistoryDTO;
import com.ha.entity.model.domain.CompositeAttributeValue;
import com.ha.entity.model.domain.SingleAddedAttribute;

public interface IEmployeeDAO {
	
	public EmployeeDTO getEmployeeDetails(EmployeeSearchDTO searchDTO) throws PersistenceException;
	public List<SingleAddedAttribute> getEmployeeSingleAddedAttributes(Long employeeDid) throws PersistenceException;
    public List<CompositeAttributeValue> getEmployeeCompositeAddedAttributes(Long employeeDid) throws PersistenceException;
    public List<EmployeeAllowanceDTO> getEmployeeAllowances(Long employeeDid) throws PersistenceException;
    public List<EmployeeSearchResultsDTO> searchEmployee(EmployeeSearchDTO searchDTO) throws PersistenceException;
    public void deleteEmployeeSingleAddedAttributes(Long did, String tobject) throws PersistenceException;
    public void deleteEmployeeCompositeAddedAttributes(Long did, String tobject) throws PersistenceException;
    public List<HistoryDTO> getEmployeeHistory(Long employeeDid) throws PersistenceException;
    public boolean isExistingEPFNumber(Long companyDid, Integer epfNumber) throws PersistenceException;
    public boolean isExistingNICNumber(String nic) throws PersistenceException;
    public List<ConfirmationAlertDTO> getConfirmationAlertData(Long userCompanyDid, boolean isSingleCompany, boolean isParent) throws PersistenceException;
    public List<EmployeeQualificationDTO> getEmployeeQualifications(Long employeeDid) throws PersistenceException;
    public EmployeeQualificationDTO getEmpQualificationByDid(Long qualificationDid) throws PersistenceException;
    public List<EmployeeSkillDTO> getEmployeeSkills(Long employeeDid) throws PersistenceException;
    public EmployeeSkillDTO getEmpSkillByDid(Long skillDid) throws PersistenceException;
    
    public List<CompanyPropertyAssignmentDTO> getCompanyPropertyAssignment(Long employeeDid) throws PersistenceException;
    public CompanyPropertyAssignmentDTO getPropertyAssignmentByDid(Long propertyAssignmentDid) throws PersistenceException;
    public void updateCompanyPropertyReturns(Long propertyAssignmentDid, Date returnDate) throws PersistenceException;
}
