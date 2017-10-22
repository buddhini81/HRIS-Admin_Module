/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.business;

import com.ha.entity.dao.hibernate.HibernateDAOFactory;
import com.ha.entity.dao.hibernate.IGlobalDAO;
import com.ha.entity.model.custom.AllowanceTypeDTO;
import com.ha.entity.model.custom.CompanyDTO;
import com.ha.entity.model.custom.CompanyPolicyDTO;
import com.ha.entity.model.custom.CompanyPropertyTypeDTO;
import com.ha.entity.model.custom.ContractTypeDTO;
import com.ha.entity.model.custom.DepartmentDTO;
import com.ha.entity.model.domain.Company;
import com.ha.entity.model.domain.ContractType;
import com.ha.entity.model.domain.Department;
import com.ha.exceptions.BusinessException;
import com.ha.exceptions.ExceptionHelper;
import java.util.ArrayList;
import java.util.HashMap;
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
import javax.persistence.PersistenceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.ejb3.annotation.LocalBinding;

/**
 *
 * @author Buddhini
 */
@Stateless
@Local(GlobalServiceBeanLocal.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@LocalBinding(jndiBinding = "GlobalServiceBean/local")
public class GlobalServiceBean implements GlobalServiceBeanLocal {

    private Log log = LogFactory.getLog(ExceptionHelper.class);
    private IGlobalDAO globalDAO = HibernateDAOFactory.getInstance().getGlobalDAO();
    @Resource
    private SessionContext context;

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public CompanyDTO getCompanyByDid(Long companyDid) throws BusinessException {
        try {
            return globalDAO.getCompanyByDid(companyDid);
        } catch (Exception e) {
            ExceptionHelper.handleError("Error in GlobalServiceBean -> getCompanyByDid", context, e);
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<CompanyDTO> getParentAndChildCompanies(Long parentCompanyDid) throws BusinessException {
        try {
            return globalDAO.getParentAndChildCompanies(parentCompanyDid);
        } catch (Exception e) {
            ExceptionHelper.handleError("Error in GlobalServiceBean -> getParentAndChildCompanies", context, e);
            return null;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<CompanyDTO> getChildCompanies(Long parentCompanyDid) throws BusinessException {
    	try {
            return globalDAO.getChildCompanies(parentCompanyDid);
        } catch (Exception e) {
            ExceptionHelper.handleError("Error in GlobalServiceBean -> getChildCompanies", context, e);
            return null;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<CompanyDTO> findAllCompanies() throws BusinessException {
    	try {
            return globalDAO.findAllCompanies();
        } catch (Exception e) {
            ExceptionHelper.handleError("Error in GlobalServiceBean -> findAllCompanies", context, e);
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<DepartmentDTO> getDepartmentsByCompany(Long companyDid) throws BusinessException {
        List<DepartmentDTO> departmentDTOs = null;
        try {
            List<Department> departments = globalDAO.getDepartmentsByCompany(companyDid);
            if(departments != null && departments.size() > 0) {
                departmentDTOs = new ArrayList<DepartmentDTO>();
                for(Department d : departments) {
                    DepartmentDTO dto = new DepartmentDTO();
                    dto.setCompanyDid(d.getCompany().getCompanyDid());
                    dto.setDepartmentCode(d.getDepartmentCode());
                    dto.setDepartmentDid(d.getDepartmentDid());
                    dto.setNumberOfEmployees(d.getNumberOfEmployees());

                    departmentDTOs.add(dto);
                }
            }
           return departmentDTOs;
        } catch (Exception e) {
            ExceptionHelper.handleError("Error in GlobalServiceBean -> getDepartmentsByCompany", context, e);
            return null;
        }
    }

     @TransactionAttribute(TransactionAttributeType.SUPPORTS)
      public List<ContractTypeDTO> findAllContractTypes() throws BusinessException {
        List<ContractTypeDTO> contractTypes = new ArrayList<ContractTypeDTO>();
        try {
            List<ContractType> cTypes = globalDAO.findAllContractTypes();
            if (cTypes != null && cTypes.size() > 0) {
                for (ContractType c : cTypes) {
                    ContractTypeDTO dto = new ContractTypeDTO();
                    dto.setContractTypeDid(c.getContractTypeDid());
                    dto.setName(c.getName());
                    dto.setDescription(c.getDescription());

                    contractTypes.add(dto);
                }
            }

            return contractTypes;
        } catch (Exception e) {
            ExceptionHelper.handleError("Error in GlobalServiceBean -> findAllContractTypes", context, e);
            return null;
        }
    }

    public String getNextIdNumber(Long companyDid, Long identifierTypeDid) throws BusinessException {
        try {
            return globalDAO.generateIdentifier(companyDid, identifierTypeDid);
        } catch (Exception e) {
            ExceptionHelper.handleError("Error in GlobalServiceBean -> getNextIdNumber", context, e);
            return null;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<AllowanceTypeDTO> findAllAllowanceTypes() throws BusinessException {
    	try {
    		return globalDAO.findAllAllowanceTypes();
    	} catch (Exception e) {
            ExceptionHelper.handleError("Error in GlobalServiceBean -> findAllAllowanceTypes", context, e);
            return null;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Map<Long,CompanyPolicyDTO> getCompanyPoliciesByCompanyDid(Long companyDid) throws BusinessException {
    	Map<Long,CompanyPolicyDTO> policyMap = null;
    	try {
    		  List<CompanyPolicyDTO> policyList = globalDAO.getCompanyPoliciesByCompanyDid(companyDid);
    		  if(policyList != null && policyList.size() > 0) {
    			  policyMap = new HashMap<Long,CompanyPolicyDTO>();
    			 for(CompanyPolicyDTO policy : policyList) {
    				 policyMap.put(policy.getPolicyDid(), policy);
    			 }
    		  }
    		  return policyMap;
    	} catch (Exception e) {
            ExceptionHelper.handleError("Error in GlobalServiceBean -> getCompanyPoliciesByCompanyDid", context, e);
            return null;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<DepartmentDTO> findAllDepartments() throws BusinessException {
    	try {
    		return globalDAO.findAllDepartments();
    	} catch (Exception e) {
            ExceptionHelper.handleError("Error in GlobalServiceBean -> findAllDepartments", context, e);
            return null;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<CompanyPropertyTypeDTO> findAllPropertyTypes() throws BusinessException {
    	try {
    		return globalDAO.findAllPropertyTypes();
    	} catch (Exception e) {
            ExceptionHelper.handleError("Error in GlobalServiceBean -> findAllPropertyTypes", context, e);
            return null;
        }
    }
}
