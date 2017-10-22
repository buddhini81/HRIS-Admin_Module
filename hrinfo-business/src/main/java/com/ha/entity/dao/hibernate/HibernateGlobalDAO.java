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
import com.ha.entity.model.domain.Company;
import com.ha.entity.model.domain.CompositeAttributeValue;
import com.ha.entity.model.domain.ContractType;
import com.ha.entity.model.domain.Department;
import com.ha.entity.model.domain.SingleAddedAttribute;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Buddhini
 */
public class HibernateGlobalDAO extends HibernateCommonDAO implements IGlobalDAO {

    public CompanyDTO getCompanyByDid(Long companyDid) throws PersistenceException {
        Session session = getSession();
        try {
        	 StringBuffer sql = new StringBuffer();

             sql.append("(SELECT c.companyDid AS companyDid, ");
             sql.append("c.companyId AS companyId, ");
             sql.append("c.companyName AS companyName, ");
             sql.append("c.companyRegNumber AS regNumber, ");
             sql.append("c.VATNumber AS vatNo, ");
             sql.append("c.isparent AS isParent, ");
             sql.append("c.parentCompanyDid AS parentCompanyDid, ");
             sql.append("c.issinglecompany AS isSingleCompany, ");
             sql.append("c.epfnumber AS companyEPFNo ");
             sql.append("FROM company c WHERE c.companyDid = :companyDid) ");

             SQLQuery query = session.createSQLQuery(sql.toString());


             query.setParameter("companyDid", companyDid);

             query.addScalar("companyDid", Hibernate.LONG);
             query.addScalar("companyId", Hibernate.STRING);
             query.addScalar("companyName", Hibernate.STRING);
             query.addScalar("regNumber", Hibernate.STRING);
             query.addScalar("vatNo", Hibernate.STRING);
             query.addScalar("isParent", Hibernate.BOOLEAN);
             query.addScalar("parentCompanyDid", Hibernate.LONG);
             query.addScalar("isSingleCompany", Hibernate.BOOLEAN);
             query.addScalar("companyEPFNo", Hibernate.STRING);

             query.setResultTransformer(Transformers.aliasToBean(CompanyDTO.class));
             
             List results = query.list();
            return (results != null && results.size() > 0) ? (CompanyDTO) results.get(0) : null;
        } catch (HibernateException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }

    public List<CompanyDTO> getParentAndChildCompanies(Long parentCompanyDid) throws PersistenceException {
         Session session = getSession();
        try {
            StringBuffer sql = new StringBuffer();

            sql.append("(SELECT c1.companyDid AS companyDid, ");
            sql.append("c1.companyId AS companyId, ");
            sql.append("c1.companyName AS companyName, ");
            sql.append("c1.companyRegNumber AS regNumber, ");
            sql.append("c1.VATNumber AS vatNo, ");
            sql.append("c1.parentCompanyDid AS parentCompanyDid ");
            sql.append("FROM company c1 WHERE c1.companyDid = :parentCompanyDid) ");
            sql.append("UNION ALL ");
            sql.append("(SELECT c2.companyDid AS companyDid, ");
            sql.append("c2.companyId AS companyId, ");
            sql.append("c2.companyName AS companyName, ");
            sql.append("c2.companyRegNumber AS regNumber, ");
            sql.append("c2.VATNumber AS vatNo, ");
            sql.append("c2.parentCompanyDid AS parentCompanyDid ");
            sql.append("FROM company c2 WHERE c2.parentCompanyDid = :parentCompanyDid)");

            SQLQuery query = session.createSQLQuery(sql.toString());


            query.setParameter("parentCompanyDid", parentCompanyDid);

            query.addScalar("companyDid", Hibernate.LONG);
            query.addScalar("companyId", Hibernate.STRING);
            query.addScalar("companyName", Hibernate.STRING);
            query.addScalar("regNumber", Hibernate.STRING);
            query.addScalar("vatNo", Hibernate.STRING);
            query.addScalar("parentCompanyDid", Hibernate.LONG);

            query.setResultTransformer(Transformers.aliasToBean(CompanyDTO.class));
            
            List results = query.list();
            return results;
        } catch (HibernateException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }
    
    public List<CompanyDTO> getChildCompanies(Long parentCompanyDid) throws PersistenceException {
    	Session session = getSession();
        try {
            StringBuffer sql = new StringBuffer();
            
            sql.append("(SELECT c.companyDid AS companyDid, ");
            sql.append("c.companyId AS companyId, ");
            sql.append("c.companyName AS companyName, ");
            sql.append("c.companyRegNumber AS regNumber, ");
            sql.append("c.VATNumber AS vatNo, ");
            sql.append("c.parentCompanyDid AS parentCompanyDid ");
            sql.append("FROM company c WHERE c.parentCompanyDid = :parentCompanyDid)");

            SQLQuery query = session.createSQLQuery(sql.toString());


            query.setParameter("parentCompanyDid", parentCompanyDid);

            query.addScalar("companyDid", Hibernate.LONG);
            query.addScalar("companyId", Hibernate.STRING);
            query.addScalar("companyName", Hibernate.STRING);
            query.addScalar("regNumber", Hibernate.STRING);
            query.addScalar("vatNo", Hibernate.STRING);
            query.addScalar("parentCompanyDid", Hibernate.LONG);

            query.setResultTransformer(Transformers.aliasToBean(CompanyDTO.class));
            
            List results = query.list();
            return results;
        } catch (HibernateException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }
    
    public List<CompanyDTO> findAllCompanies() throws PersistenceException {
    	Session session = getSession();
        try {
            StringBuffer sql = new StringBuffer();
            
            sql.append("(SELECT c.companyDid AS companyDid, ");
            sql.append("c.companyId AS companyId, ");
            sql.append("c.companyName AS companyName, ");
            sql.append("c.companyRegNumber AS regNumber, ");
            sql.append("c.VATNumber AS vatNo, ");
            sql.append("c.parentCompanyDid AS parentCompanyDid ");
            sql.append("FROM company c )");

            SQLQuery query = session.createSQLQuery(sql.toString());

            query.addScalar("companyDid", Hibernate.LONG);
            query.addScalar("companyId", Hibernate.STRING);
            query.addScalar("companyName", Hibernate.STRING);
            query.addScalar("regNumber", Hibernate.STRING);
            query.addScalar("vatNo", Hibernate.STRING);
            query.addScalar("parentCompanyDid", Hibernate.LONG);

            query.setResultTransformer(Transformers.aliasToBean(CompanyDTO.class));
            
            List results = query.list();
            return results;
        } catch (HibernateException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }

    public List<Department> getDepartmentsByCompany(Long companyDid) throws PersistenceException {
         Session session = getSession();

        try {
            StringBuffer hql = new StringBuffer();

            hql.append("SELECT d FROM Department d JOIN FETCH d.company WHERE d.company.companyDid = :companyDid ");

            Query query = session.createQuery(hql.toString());

            query.setParameter("companyDid", companyDid);

            List results = query.list();
            return results;
        } catch (HibernateException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }
    
    public List<DepartmentDTO> findAllDepartments() throws PersistenceException {
    	 Session session = getSession();

         try {
        	 
             StringBuffer sql = new StringBuffer();

             sql.append("SELECT d.departmentDid AS departmentDid, ");
             sql.append("d.companyDid AS companyDid, ");
             sql.append("d.departmentCode AS departmentCode, ");
             sql.append("d.numberOfEmployees AS numberOfEmployees ");
             sql.append("FROM department d");

             SQLQuery query = session.createSQLQuery(sql.toString());

             query.addScalar("departmentDid", Hibernate.LONG);
             query.addScalar("companyDid", Hibernate.LONG);
             query.addScalar("departmentCode", Hibernate.STRING);
             query.addScalar("numberOfEmployees", Hibernate.LONG);

             query.setResultTransformer(Transformers.aliasToBean(DepartmentDTO.class));
             
             List results = query.list();
             return results;
         } catch (HibernateException e) {
             throw new PersistenceException(e);
         } finally {
             session.close();
         }
    }

   public List<ContractType> findAllContractTypes() throws PersistenceException {
        Session session = getSession();
        try {
            StringBuffer hql = new StringBuffer();

            hql.append("FROM ContractType ct ");

            Query query = session.createQuery(hql.toString());

            List results = query.list();
            return results;
        } catch (HibernateException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }
    public String generateIdentifier(Long companyDid, Long identifierTypeDid) throws PersistenceException {

        Session session = getSession();
        try {

            StringBuffer sql = new StringBuffer();
            StringBuffer prepareSql = new StringBuffer();
            prepareSql.append("CALL hrdb.generateIdentifier(:companyDid,:identifierTypeDid)");

            SQLQuery prepareQuery = session.createSQLQuery(prepareSql.toString());

            prepareQuery.setParameter("companyDid", companyDid);
            prepareQuery.setParameter("identifierTypeDid", identifierTypeDid);

            int flag = prepareQuery.executeUpdate();

            sql.append("SELECT i.nextid AS nextId ");
            sql.append("FROM identifier i ");
            sql.append("WHERE i.companydid = :companyDid AND i.idtypedid = :identifierTypeDid");
            
            SQLQuery query = session.createSQLQuery(sql.toString());
            
            query.setParameter("companyDid", companyDid);
            query.setParameter("identifierTypeDid", identifierTypeDid);
            
            List results = query.list();
            
            return (results != null && results.size() > 0) ? (String)results.get(0) : null;

        } catch (HibernateException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }
    
    public List<SingleAddedAttribute> getSingleAddedAttributes(Long did, String tObject) throws PersistenceException {
    	  Session session = getSession();

          try {
              StringBuffer hql = new StringBuffer();

              hql.append("FROM SingleAddedAttribute sa WHERE sa.did = :did AND sa.tObject = :tObject");

              Query query = session.createQuery(hql.toString());

              query.setParameter("did", did);
              query.setParameter("tObject", tObject);

              List results = query.list();
              return results;
          } catch (HibernateException e) {
              throw new PersistenceException(e);
          } finally {
              session.close();
          }
    }
       
    public List<CompositeAttributeValue> getCompositeAddedAttributes(Long did, String tObject) throws PersistenceException {
    	  Session session = getSession();

          try {
              StringBuffer hql = new StringBuffer();

              hql.append("SELECT cav ");
              hql.append("FROM CompositeAttributeValue cav ");
              hql.append("JOIN FETCH cav.compositeAddedAttribute ");
              hql.append("WHERE cav.compositeAddedAttribute.did = :did AND cav.compositeAddedAttribute.tObject = :tObject");


              Query query = session.createQuery(hql.toString());

              query.setParameter("did", did);
              query.setParameter("tObject", tObject);

              List results = query.list();
              return results;
          } catch (HibernateException e) {
              throw new PersistenceException(e);
          } finally {
              session.close();
          }
    }

	public List<AllowanceTypeDTO> findAllAllowanceTypes() throws PersistenceException {
		 Session session = getSession();
	        try {
	            StringBuffer sql = new StringBuffer();

	            sql.append("SELECT a.allowancetypedid AS allowanceDid, ");
	            sql.append("a.allowancetype AS allowanceType, ");
	            sql.append("a.description AS description ");
	            sql.append("FROM allowancetype a");

	            SQLQuery query = session.createSQLQuery(sql.toString());

	            query.addScalar("allowanceDid", Hibernate.LONG);
	            query.addScalar("allowanceType", Hibernate.STRING);
	            query.addScalar("description", Hibernate.STRING);
	     
	            query.setResultTransformer(Transformers.aliasToBean(AllowanceTypeDTO.class));
	            
	            return query.list();

	        } catch (HibernateException e) {
	            throw new PersistenceException(e);
	        } finally {
	            session.close();
	        }
	}


	public List<CompanyPolicyDTO> getCompanyPoliciesByCompanyDid(Long companyDid) throws PersistenceException {
		 	Session session = getSession();
	        try {
	            StringBuffer sql = new StringBuffer();

	            sql.append("SELECT pa.policydid AS policyDid, ");
	            sql.append("pa.companydid AS companyDid, ");
	            sql.append("cp.policyname AS policyName, ");
	            sql.append("pa.policyvalue AS policyValue ");
	            sql.append("FROM companypolicy cp, policyassignment pa ");
	            sql.append("WHERE cp.policydid = pa.policydid ");
	            sql.append("AND pa.companydid = :companyDid ");
	            sql.append("AND cp.isvalid = 1 ");
	            sql.append("AND pa.isvalid = 1 ");

	            SQLQuery query = session.createSQLQuery(sql.toString());
	            
	            query.setParameter("companyDid", companyDid);

	            query.addScalar("policyDid", Hibernate.LONG);
	            query.addScalar("companyDid", Hibernate.LONG);
	            query.addScalar("policyName", Hibernate.STRING);
	            query.addScalar("policyValue", Hibernate.STRING);
	     
	            query.setResultTransformer(Transformers.aliasToBean(CompanyPolicyDTO.class));
	            
	            return query.list();

	        } catch (HibernateException e) {
	            throw new PersistenceException(e);
	        } finally {
	            session.close();
	        }
	}
	
	public List<CompanyPropertyTypeDTO> findAllPropertyTypes() throws PersistenceException {
		Session session = getSession();
        try {
            StringBuffer sql = new StringBuffer();

            sql.append("SELECT cp.propertyTypeDid AS propertyTypeDid, ");
            sql.append("cp.propertyName AS propertyName, ");
            sql.append("cp.description AS description ");
            sql.append("FROM companypropertytype cp ");
           
            SQLQuery query = session.createSQLQuery(sql.toString());
            
            query.addScalar("propertyTypeDid", Hibernate.LONG);
            query.addScalar("propertyName", Hibernate.STRING);
            query.addScalar("description", Hibernate.STRING);
     
            query.setResultTransformer(Transformers.aliasToBean(CompanyPropertyTypeDTO.class));
            
            return query.list();

        } catch (HibernateException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
	}
	
}
