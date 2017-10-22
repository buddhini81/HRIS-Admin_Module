/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.services;

import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import com.ha.business.EmployeeServicBeaneLocal;
import com.ha.business.ServiceLocator;
import com.ha.business.ServiceLocatorException;
import com.ha.entity.model.custom.CompanyPropertyAssignmentDTO;
import com.ha.entity.model.custom.ConfirmationAlertDTO;
import com.ha.entity.model.custom.EmployeeDTO;
import com.ha.entity.model.custom.EmployeeQualificationDTO;
import com.ha.entity.model.custom.EmployeeSearchDTO;
import com.ha.entity.model.custom.EmployeeSearchResultsDTO;
import com.ha.entity.model.custom.EmployeeSkillDTO;
import com.ha.entity.model.custom.HistoryDTO;
import com.ha.entity.model.custom.ResignationDTO;
import com.ha.entity.model.domain.UserLogin;
import com.ha.exceptions.BusinessException;
import com.ha.exceptions.ExceptionHelper;

/**
 *
 * @author Buddhini
 */
public class EmployeeDelegate {
    private transient EmployeeServicBeaneLocal localSession;

    public EmployeeDelegate() {
         try {

            localSession = (EmployeeServicBeaneLocal) ServiceLocator.getInstance().getEjbLocalHome("EmployeeServiceBean/local");


        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        }
    }

    public void saveEmployeeDetails(EmployeeDTO employeeDTO) throws BusinessException {
         try {
            localSession.saveEmployeeDetails(employeeDTO);
        } catch (Exception e){
           throw new BusinessException();
        }
    }
    
    public EmployeeDTO getEmployeeDetails(EmployeeSearchDTO searchDTO) throws BusinessException {
	try{
    	return localSession.getEmployeeDetails(searchDTO);
	 } catch (Exception e){
         throw new BusinessException();
     }
    }
    
    public List<EmployeeSearchResultsDTO> searchEmployee(EmployeeSearchDTO searchDTO) throws BusinessException {
    	try{
        	return localSession.searchEmployee(searchDTO);
    	 } catch (Exception e){
             throw new BusinessException();
         }
    }
    
    public List<HistoryDTO> getEmployeeHistory(Long employeeDid) throws BusinessException {
    	try{
        	return localSession.getEmployeeHistory(employeeDid);
    	 } catch (Exception e){
             throw new BusinessException();
         }
    }
    
    public void saveEmployeeHistory(HistoryDTO historyDTO) throws BusinessException {
    	try {
            localSession.saveEmployeeHistory(historyDTO);
        } catch (Exception e){
           throw new BusinessException();
        }
    }
    
    public void deleteEmployeeHistory(Long did) throws BusinessException {
    	try {
            localSession.deleteEmployeeHistory(did);
        } catch (Exception e){
           throw new BusinessException();
        }
    }
    
    public boolean isExistingEPFNumber(Long companyDid, Integer epfNumber) throws BusinessException {
    	try {
            return localSession.isExistingEPFNumber(companyDid, epfNumber);
        } catch (Exception e){
           throw new BusinessException();
        }
    }
    
    public boolean isExistingNICNumber(String nic) throws BusinessException {
    	try {
            return localSession.isExistingNICNumber(nic);
        } catch (Exception e){
           throw new BusinessException();
        }
    }
    
    public List<ConfirmationAlertDTO> getConfirmationAlertData(Long userCompanyDid, boolean isSingleCompany, boolean isParent) throws BusinessException {
		try {			
			return localSession.getConfirmationAlertData(userCompanyDid,isSingleCompany,isParent);
		}catch (Exception e) {
			throw new BusinessException();
		}
	}
    
    public void saveEmployeeQualification(EmployeeQualificationDTO qualificationDTO) throws BusinessException {
    	try {
            localSession.saveEmployeeQualification(qualificationDTO);
        } catch (Exception e){
           throw new BusinessException();
        }
	}
    
    public List<EmployeeQualificationDTO> getEmployeeQualifications(Long employeeDid) throws BusinessException {
    	try {			
			return localSession.getEmployeeQualifications(employeeDid);
		}catch (Exception e) {
			throw new BusinessException();
		}
    }
    
    public EmployeeQualificationDTO getEmpQualificationByDid(Long qualificationDid) throws BusinessException {
    	try {			
			return localSession.getEmpQualificationByDid(qualificationDid);
		}catch (Exception e) {
			throw new BusinessException();
		}
    }
    
    public void deleteEmployeeQualification(Long did) throws BusinessException {
    	try {
            localSession.deleteEmployeeQualification(did);
        } catch (Exception e){
           throw new BusinessException();
        }
    }
    
    //
    
    public void saveEmployeeSkill(EmployeeSkillDTO skillDTO) throws BusinessException {
    	try {
            localSession.saveEmployeeSkill(skillDTO);
        } catch (Exception e){
           throw new BusinessException();
        }
	}
    
    public List<EmployeeSkillDTO> getEmployeeSkills(Long employeeDid) throws BusinessException {
    	try {			
			return localSession.getEmployeeSkills(employeeDid);
		}catch (Exception e) {
			throw new BusinessException();
		}
    }
    
    public EmployeeSkillDTO getEmpSkillByDid(Long skillDid) throws BusinessException {
    	try {			
			return localSession.getEmpSkillByDid(skillDid);
		}catch (Exception e) {
			throw new BusinessException();
		}
    }
    
    public void deleteEmployeeSkill(Long did) throws BusinessException {
    	try {
            localSession.deleteEmployeeSkill(did);
        } catch (Exception e){
           throw new BusinessException();
        }
    }
    
    public void savePropertyAssignment(CompanyPropertyAssignmentDTO propertyAssignmentDTO) throws BusinessException {
    	try {
            localSession.savePropertyAssignment(propertyAssignmentDTO);
        } catch (Exception e){
           throw new BusinessException();
        }
    }
    
    public List<CompanyPropertyAssignmentDTO> getCompanyPropertyAssignment(Long employeeDid) throws BusinessException {
    	try {
            return localSession.getCompanyPropertyAssignment(employeeDid);
        } catch (Exception e){
           throw new BusinessException();
        }
    }
    
    public CompanyPropertyAssignmentDTO getPropertyAssignmentByDid(Long propertyAssignmentDid) throws BusinessException {
    	try {
            return localSession.getPropertyAssignmentByDid(propertyAssignmentDid);
        } catch (Exception e){
           throw new BusinessException();
        }
    }
    
    public void deletePropertyAssignment(Long did) throws BusinessException {
    	try {
            localSession.deletePropertyAssignment(did);
        } catch (Exception e){
           throw new BusinessException();
        }
    }
    
    public void updateCompanyPropertyReturns(Long propertyAssignmentDid, Date returnDate) throws BusinessException {
    	try {
            localSession.updateCompanyPropertyReturns(propertyAssignmentDid,returnDate);
        } catch (Exception e){
           throw new BusinessException();
        }
    }
    
    public void saveEmployeeClearence(ResignationDTO resignationDTO) throws BusinessException {
    	try {
            localSession.saveEmployeeClearence(resignationDTO);
        } catch (Exception e){
           throw new BusinessException();
        }
    }
}
