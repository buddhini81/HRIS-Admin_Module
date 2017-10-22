/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.services;

import com.ha.business.GlobalServiceBeanLocal;
import com.ha.business.ServiceLocator;
import com.ha.business.ServiceLocatorException;
import com.ha.entity.model.custom.AllowanceTypeDTO;
import com.ha.entity.model.custom.CompanyDTO;
import com.ha.entity.model.custom.CompanyPolicyDTO;
import com.ha.entity.model.custom.CompanyPropertyTypeDTO;
import com.ha.entity.model.custom.ContractTypeDTO;
import com.ha.entity.model.custom.DepartmentDTO;
import com.ha.entity.model.domain.Company;
import com.ha.exceptions.BusinessException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Buddhini
 */
public class GlobalDelegate {
    private transient GlobalServiceBeanLocal localSession;

    public GlobalDelegate() {
         try {

            localSession = (GlobalServiceBeanLocal) ServiceLocator.getInstance().getEjbLocalHome("GlobalServiceBean/local");


        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        }
    }

    public CompanyDTO getCompanyByDid(Long companyDid) throws BusinessException {
        try {
            return localSession.getCompanyByDid(companyDid);
        } catch (Exception e){
           throw new BusinessException();
        }
    }

    public List<CompanyDTO> getParentAndChildCompanies(Long parentCompanyDid) throws BusinessException {
        try {
            return localSession.getParentAndChildCompanies(parentCompanyDid);
        } catch (Exception e){
           throw new BusinessException();
        }
    }
    
    public List<CompanyDTO> getChildCompanies(Long parentCompanyDid) throws BusinessException {
    	try {
            return localSession.getChildCompanies(parentCompanyDid);
        } catch (Exception e){
           throw new BusinessException();
        }
    }
    
    public List<CompanyDTO> findAllCompanies() throws BusinessException {
    	try {
            return localSession.findAllCompanies();
        } catch (Exception e){
           throw new BusinessException();
        }
    }
    
    public List<DepartmentDTO> getDepartmentsByCompany(Long companyDid) throws BusinessException {
        try {
            return localSession.getDepartmentsByCompany(companyDid);
        } catch (Exception e){
           throw new BusinessException();
        }
    }
    
    public List<DepartmentDTO> findAllDepartments() throws BusinessException {
    	try {
            return localSession.findAllDepartments();
        } catch (Exception e){
           throw new BusinessException();
        }
    }


    public List<ContractTypeDTO> findAllContractTypes() throws BusinessException {

        try {
            return localSession.findAllContractTypes();
        } catch (Exception e){
           throw new BusinessException();
        }
    }

    public String getNextIdNumber(Long companyDid, Long identifierTypeDid) throws BusinessException {
        try {
            return localSession.getNextIdNumber(companyDid, identifierTypeDid);
        }catch (Exception e){
           throw new BusinessException();
        }
    }
    
    public List<AllowanceTypeDTO> findAllAllowanceTypes() throws BusinessException {
    	try {
            return localSession.findAllAllowanceTypes();
        }catch (Exception e){
           throw new BusinessException();
        }
    }
    
    public Map<Long,CompanyPolicyDTO> getCompanyPoliciesByCompanyDid(Long companyDid) throws BusinessException {
    	try {
            return localSession.getCompanyPoliciesByCompanyDid(companyDid);
        }catch (Exception e){
           throw new BusinessException();
        }
    }
    
    public List<CompanyPropertyTypeDTO> findAllPropertyTypes() throws BusinessException {
    	try {
            return localSession.findAllPropertyTypes();
        }catch (Exception e){
           throw new BusinessException();
        }
    }
}
