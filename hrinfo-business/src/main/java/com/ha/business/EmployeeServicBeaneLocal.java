/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.business;

import java.util.Date;
import java.util.List;

import com.ha.entity.model.custom.CompanyPropertyAssignmentDTO;
import com.ha.entity.model.custom.ConfirmationAlertDTO;
import com.ha.entity.model.custom.EmployeeDTO;
import com.ha.entity.model.custom.EmployeeQualificationDTO;
import com.ha.entity.model.custom.EmployeeSearchDTO;
import com.ha.entity.model.custom.EmployeeSearchResultsDTO;
import com.ha.entity.model.custom.EmployeeSkillDTO;
import com.ha.entity.model.custom.HistoryDTO;
import com.ha.entity.model.custom.ResignationDTO;
import com.ha.exceptions.BusinessException;
import javax.ejb.Local;
import javax.persistence.PersistenceException;

/**
 *
 * @author Buddhini
 */
@Local
public interface EmployeeServicBeaneLocal {
    public void saveEmployeeDetails(EmployeeDTO employeeDTO) throws BusinessException;
    public EmployeeDTO getEmployeeDetails(EmployeeSearchDTO searchDTO) throws BusinessException;
    public List<EmployeeSearchResultsDTO> searchEmployee(EmployeeSearchDTO searchDTO) throws BusinessException;
    public List<HistoryDTO> getEmployeeHistory(Long employeeDid) throws BusinessException;
    public void saveEmployeeHistory(HistoryDTO historyDTO) throws BusinessException;
    public void deleteEmployeeHistory(Long did) throws BusinessException;
    public boolean isExistingEPFNumber(Long companyDid, Integer epfNumber) throws BusinessException;
    public boolean isExistingNICNumber(String nic) throws BusinessException;
    public List<ConfirmationAlertDTO> getConfirmationAlertData(Long userCompanyDid, boolean isSingleCompany, boolean isParent) throws BusinessException;
    public List<EmployeeQualificationDTO> getEmployeeQualifications(Long employeeDid) throws BusinessException;
    public void saveEmployeeQualification(EmployeeQualificationDTO qualificationDTO) throws BusinessException;
    public EmployeeQualificationDTO getEmpQualificationByDid(Long qualificationDid) throws BusinessException;
    public void deleteEmployeeQualification(Long did) throws BusinessException;
    //
    public void saveEmployeeSkill(EmployeeSkillDTO skillDTO) throws BusinessException;
    public EmployeeSkillDTO getEmpSkillByDid(Long skillDid) throws BusinessException;
    public List<EmployeeSkillDTO> getEmployeeSkills(Long employeeDid) throws BusinessException;
    public void deleteEmployeeSkill(Long did) throws BusinessException;
    
    public void savePropertyAssignment(CompanyPropertyAssignmentDTO propertyAssignmentDTO) throws BusinessException;
    public CompanyPropertyAssignmentDTO getPropertyAssignmentByDid(Long propertyAssignmentDid) throws BusinessException;
    public List<CompanyPropertyAssignmentDTO> getCompanyPropertyAssignment(Long employeeDid) throws BusinessException;
    public void deletePropertyAssignment(Long did) throws BusinessException;
    public void updateCompanyPropertyReturns(Long propertyAssignmentDid, Date returnDate) throws BusinessException;
    public void saveEmployeeClearence(ResignationDTO resignationDTO) throws BusinessException;
}
