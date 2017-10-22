/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.entity.dao.hibernate;

import com.ha.entity.model.custom.AllowanceTypeDTO;
import com.ha.entity.model.custom.CompanyDTO;
import com.ha.entity.model.custom.CompanyPolicyDTO;
import com.ha.entity.model.custom.CompanyPropertyTypeDTO;
import com.ha.entity.model.custom.DepartmentDTO;

import javax.persistence.PersistenceException;
import com.ha.entity.model.domain.Company;
import com.ha.entity.model.domain.CompositeAttributeValue;
import com.ha.entity.model.domain.ContractType;
import com.ha.entity.model.domain.Department;
import com.ha.entity.model.domain.SingleAddedAttribute;

import java.util.List;

/**
 *
 * @author Buddhini
 */
public interface IGlobalDAO {

    public CompanyDTO getCompanyByDid(Long companyDid) throws PersistenceException;
    public List<CompanyDTO> getParentAndChildCompanies(Long parentCompanyDid) throws PersistenceException;
    public List<CompanyDTO> getChildCompanies(Long parentCompanyDid) throws PersistenceException;
    public List<CompanyDTO> findAllCompanies() throws PersistenceException;
    public List<Department> getDepartmentsByCompany(Long companyDid) throws PersistenceException;
    public List<ContractType> findAllContractTypes() throws PersistenceException;
    public String generateIdentifier(Long companyDid, Long identifierTypeDid) throws PersistenceException;
    public List<SingleAddedAttribute> getSingleAddedAttributes(Long did, String tObject) throws PersistenceException;
    public List<CompositeAttributeValue> getCompositeAddedAttributes(Long did, String tObject) throws PersistenceException;
    public List<AllowanceTypeDTO> findAllAllowanceTypes() throws PersistenceException;
    public List<CompanyPolicyDTO> getCompanyPoliciesByCompanyDid(Long companyDid) throws PersistenceException;
    public List<DepartmentDTO> findAllDepartments() throws PersistenceException;
    public List<CompanyPropertyTypeDTO> findAllPropertyTypes() throws PersistenceException;
}
