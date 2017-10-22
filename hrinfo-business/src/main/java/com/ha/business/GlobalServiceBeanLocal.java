/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.business;

import com.ha.entity.model.custom.AllowanceTypeDTO;
import com.ha.entity.model.custom.CompanyDTO;
import com.ha.entity.model.custom.CompanyPolicyDTO;
import com.ha.entity.model.custom.CompanyPropertyTypeDTO;
import com.ha.entity.model.custom.ContractTypeDTO;
import com.ha.entity.model.custom.DepartmentDTO;
import com.ha.exceptions.BusinessException;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

/**
 *
 * @author Buddhini
 */
@Local
public interface GlobalServiceBeanLocal {
    public CompanyDTO getCompanyByDid(Long companyDid) throws BusinessException;
    public List<CompanyDTO> getParentAndChildCompanies(Long parentCompanyDid) throws BusinessException;
    public List<CompanyDTO> getChildCompanies(Long parentCompanyDid) throws BusinessException;
    public List<CompanyDTO> findAllCompanies() throws BusinessException;
    public List<DepartmentDTO> getDepartmentsByCompany(Long companyDid) throws BusinessException;
    public List<ContractTypeDTO> findAllContractTypes() throws BusinessException;
    public String getNextIdNumber(Long companyDid, Long identifierTypeDid) throws BusinessException;
    public List<AllowanceTypeDTO> findAllAllowanceTypes() throws BusinessException;
    public Map<Long,CompanyPolicyDTO> getCompanyPoliciesByCompanyDid(Long companyDid) throws BusinessException; 
    public List<DepartmentDTO> findAllDepartments() throws BusinessException;
    public List<CompanyPropertyTypeDTO> findAllPropertyTypes() throws BusinessException;
}
