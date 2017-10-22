package com.ha.entity.dao.hibernate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import com.ha.entity.model.custom.AllowanceTypeDTO;
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
import com.ha.exceptions.BusinessException;
import com.ha.util.Constants;
import com.ha.util.Util;

public class HibernateEmployeeDAO extends HibernateCommonDAO implements IEmployeeDAO {
	
	public EmployeeDTO getEmployeeDetails(EmployeeSearchDTO searchDTO) throws PersistenceException {
		Session session = getSession();
		
			try {
	            Map<String, Object> paramMap = new HashMap<String, Object>();

	            StringBuffer sql = new StringBuffer();

	            sql.append("SELECT emp.employeeDid AS employeeDid, ");
	            sql.append("emp.employeeNumber AS employeeNo, ");
	            sql.append("emp.EPFNumber AS epfNo, ");
	            sql.append("emp.companyEPFNumber AS companyEPFNumber, ");
	            sql.append("emp.NICNumber AS nicNo, ");
	            sql.append("emp.firstName AS firstName, ");
	            sql.append("emp.middleName AS middleName, ");
	            sql.append("emp.lastName AS lastName, ");
	            sql.append("emp.addressline1 AS addressLine1, ");
	            sql.append("emp.addressline2 AS addressLine2, ");
	            sql.append("emp.addressline3 AS addressLine3, ");
	            sql.append("emp.homePhoneNumber AS homePhoneNo, ");
	            sql.append("emp.mobileNumber AS mobileNo, ");
	            if(searchDTO.getSearchContext() == Constants.EMPLOYEE_SEARCH_CONTEXT_VIEW) {
	            	sql.append("IF(emp.gender = 'M', 'Male', 'Female') AS gender, ");
	            }
	            if(searchDTO.getSearchContext() == Constants.EMPLOYEE_SEARCH_CONTEXT_EDIT) {
	            	sql.append("emp.gender AS gender, ");
	            }
	            sql.append("emp.dob AS dateOfBirth, ");
	            sql.append("emp.age AS age, ");
	            sql.append("emp.email AS email, ");
	            if(searchDTO.getSearchContext() == Constants.EMPLOYEE_SEARCH_CONTEXT_VIEW) {
	            	sql.append("CASE emp.maritalStatus ");
	            	sql.append("WHEN 'M' THEN 'Yes' ");
	            	sql.append("WHEN 'S' THEN 'No' ");
	            	sql.append("ELSE ' ' END AS maritalStatus, ");		
	            	
	            	//sql.append("IF(emp.maritalStatus = 'M', 'Yes', 'No') AS maritalStatus, ");
	            }
	            if(searchDTO.getSearchContext() == Constants.EMPLOYEE_SEARCH_CONTEXT_EDIT) {
	            	sql.append("emp.maritalStatus AS maritalStatus, ");
	            }
	            sql.append("emp.dateJoined AS dateJoined, ");
	            sql.append("emp.confirmationDate AS confDate, ");
	            sql.append("emp.drvLicenceNumber AS drivingLicenseNo, ");
	            sql.append("emp.passportNumber AS passportNo, ");
	            sql.append("emp.dueConfirmationDate AS dueConfDate, ");
	            sql.append("emp.basicsalary AS basicSalary, ");
	            sql.append("emp.designation AS designation, ");
	            sql.append("da.deptAssignmentDid AS departmentAssignmentDid, ");
	            sql.append("dept.departmentDid AS departmentDid, ");
	            sql.append("dept.departmentCode AS departmentName, ");
	            sql.append("com.companyDid AS companyDid, ");
	            sql.append("com.companyName AS companyName, ");
	            sql.append("ct.contractTypeDid AS contractTypeDid, ");
	            sql.append("ct.name AS contractType ");
	            sql.append("FROM employee emp ");
	            sql.append("LEFT OUTER JOIN contracttype ct ON emp.contracttypedid = ct.contractTypeDid,");
	            sql.append("company com,department dept,departmentassignment da ");
	            sql.append("WHERE emp.employeeDid = da.employeeDid ");
	            sql.append("AND da.departmentDid = dept.departmentDid ");
	            sql.append("AND dept.companyDid = com.companyDid ");
	           
	            if(searchDTO.getSearchByDid() != null) {
	                sql.append("AND emp.employeeDid = :did ");
	                paramMap.put("did", searchDTO.getSearchByDid());
	            }

	            SQLQuery query = session.createSQLQuery(sql.toString());

	            for (String key : paramMap.keySet()) {
	                query.setParameter(key, paramMap.get(key));
	            }

	            query.addScalar("employeeDid", Hibernate.LONG);
	            query.addScalar("employeeNo", Hibernate.STRING);
	            query.addScalar("epfNo", Hibernate.STRING);
	            query.addScalar("companyEPFNumber", Hibernate.STRING);
	            query.addScalar("nicNo", Hibernate.STRING);
	            query.addScalar("firstName", Hibernate.STRING);
	            query.addScalar("middleName", Hibernate.STRING);
	            query.addScalar("lastName", Hibernate.STRING);
	            query.addScalar("addressLine1", Hibernate.STRING);
	            query.addScalar("addressLine2", Hibernate.STRING);
	            query.addScalar("addressLine3", Hibernate.STRING);
	            query.addScalar("homePhoneNo", Hibernate.STRING);
	            query.addScalar("mobileNo", Hibernate.STRING);
	            query.addScalar("gender", Hibernate.STRING);
	            query.addScalar("dateOfBirth", Hibernate.DATE);
	            query.addScalar("age", Hibernate.INTEGER);
	            query.addScalar("email", Hibernate.STRING);
	            query.addScalar("maritalStatus", Hibernate.STRING);
	            query.addScalar("dateJoined", Hibernate.DATE);
	            query.addScalar("confDate", Hibernate.DATE);
	            query.addScalar("drivingLicenseNo", Hibernate.STRING);
	            query.addScalar("passportNo", Hibernate.STRING);
	            query.addScalar("dueConfDate", Hibernate.DATE);
	            query.addScalar("basicSalary", Hibernate.BIG_DECIMAL);
	            query.addScalar("designation", Hibernate.STRING);
	            query.addScalar("departmentAssignmentDid",Hibernate.LONG);
	            query.addScalar("departmentDid", Hibernate.LONG);
	            query.addScalar("departmentName", Hibernate.STRING);
	            query.addScalar("companyDid", Hibernate.LONG);
	            query.addScalar("companyName", Hibernate.STRING);
	            query.addScalar("contractTypeDid", Hibernate.LONG);
	            query.addScalar("contractType", Hibernate.STRING);

	            query.setResultTransformer(Transformers.aliasToBean(EmployeeDTO.class));

	            return (query.list() != null && query.list().size() > 0) ? (EmployeeDTO)query.list().get(0) : null;
		} catch (HibernateException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
	}
	
	public List<SingleAddedAttribute> getEmployeeSingleAddedAttributes(Long employeeDid) throws PersistenceException {
  	  Session session = getSession();

        try {
            StringBuffer hql = new StringBuffer();

            hql.append("FROM SingleAddedAttribute sa WHERE sa.attributeGroupDid = 100 AND sa.did = :employeeDid AND sa.tObject = 'EMPLOYEE'");

            Query query = session.createQuery(hql.toString());

            query.setParameter("employeeDid", employeeDid);

            List results = query.list();
            return results;
        } catch (HibernateException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
  }
     
  public List<CompositeAttributeValue> getEmployeeCompositeAddedAttributes(Long employeeDid) throws PersistenceException {
  	  Session session = getSession();

        try {
            StringBuffer hql = new StringBuffer();

            hql.append("SELECT cav ");
            hql.append("FROM CompositeAttributeValue cav ");
            hql.append("JOIN FETCH cav.compositeAddedAttribute ");
            hql.append("WHERE cav.compositeAddedAttribute.attributeGroupDid = 100 AND cav.compositeAddedAttribute.did = :employeeDid AND cav.compositeAddedAttribute.tObject = 'EMPLOYEE'");

            Query query = session.createQuery(hql.toString());

            query.setParameter("employeeDid", employeeDid);

            List results = query.list();
            return results;
        } catch (HibernateException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
  }


public List<EmployeeAllowanceDTO> getEmployeeAllowances(Long employeeDid) throws PersistenceException {
	Session session = getSession();
	
	try {

        StringBuffer sql = new StringBuffer();

        sql.append("SELECT empallow.employeeallowancedid AS employeeAllowanceDid, ");
        sql.append("empallow.allowancetypedid AS allowanceTypeDid, ");
        sql.append("allow.allowancetype AS allowanceType, ");
        sql.append("empallow.amount AS amount ");
        sql.append("FROM employeeallowance empallow,allowancetype allow ");
        sql.append("WHERE empallow.allowancetypedid = allow.allowancetypedid ");
        sql.append("AND empallow.employeedid = :employeeDid ");
       

        SQLQuery query = session.createSQLQuery(sql.toString());

        query.setParameter("employeeDid", employeeDid);

        query.addScalar("employeeAllowanceDid", Hibernate.LONG);
        query.addScalar("allowanceTypeDid", Hibernate.LONG);
        query.addScalar("allowanceType", Hibernate.STRING);
        query.addScalar("amount", Hibernate.BIG_DECIMAL);
        
        query.setResultTransformer(Transformers.aliasToBean(EmployeeAllowanceDTO.class));

        return query.list();
	} catch (HibernateException e) {
		throw new PersistenceException(e);
	} finally {
        session.close();
    }
}


public List<EmployeeSearchResultsDTO> searchEmployee(EmployeeSearchDTO searchDTO) throws PersistenceException {
	Session session = getSession();
    try {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Map<String, List> paramListMap = new HashMap<String, List>();

        StringBuffer sql = new StringBuffer();

        sql.append("SELECT emp.employeeDid AS employeeDid, ");
        sql.append("emp.employeeNumber AS empNo, ");
        sql.append("emp.EPFNumber AS epfNo, ");
        sql.append("emp.NICNumber AS nicNo, ");
        sql.append("emp.firstName AS firstName, ");
        sql.append("emp.middleName AS middleName, ");
        sql.append("emp.lastName AS lastName, ");
        sql.append("emp.addressline1 AS addressLine1, ");
        sql.append("emp.addressline2 AS addressLine2, ");
        sql.append("emp.addressline3 AS addressLine3, ");
        sql.append("emp.homePhoneNumber AS homePhone, ");
        sql.append("emp.mobileNumber AS mobNo, ");
        sql.append("IF(emp.gender = 'M', 'Male', 'Female') AS gender, ");
        sql.append("emp.dob AS dob, ");
        sql.append("emp.email AS email, ");
        sql.append("IF(emp.maritalStatus = 'M', 'Yes', 'No') AS maritalStatus, ");
        sql.append("emp.dateJoined AS dateJoined, ");
        sql.append("emp.confirmationDate AS confDate, ");
        sql.append("emp.drvLicenceNumber AS drvLicenseNo, ");
        sql.append("emp.passportNumber AS passportNo, ");
        sql.append("emp.dueConfirmationDate AS dueConfDate, ");
        sql.append("emp.resignedDate AS resigDate, ");
        sql.append("emp.designation AS designation, ");
        sql.append("dept.departmentCode AS department, ");
        sql.append("com.companyName AS companyName, ");
        sql.append("ct.name AS contractType ");
        sql.append("FROM employee emp ");
        sql.append("LEFT OUTER JOIN contracttype ct ON emp.contracttypedid = ct.contractTypeDid, ");
        sql.append("company com,department dept,departmentassignment da ");
        sql.append("WHERE emp.employeeDid = da.employeeDid ");
        sql.append("AND da.departmentDid = dept.departmentDid ");
        sql.append("AND dept.companyDid = com.companyDid ");
        sql.append("AND emp.companyDid IN (:companyDidList) ");
        
        paramListMap.put("companyDidList", searchDTO.getCompanyDidList());

        if(Util.isNotEmpty(searchDTO.getFirstName())) {
            sql.append("AND emp.firstName LIKE :firstName ");
            paramMap.put("firstName", Util.wrapInWildcards(searchDTO.getFirstName()));
        }

        if(Util.isNotEmpty(searchDTO.getLastName())) {
            sql.append("AND emp.lastName LIKE :lastName ");
            paramMap.put("lastName", Util.wrapInWildcards(searchDTO.getLastName()));
        }

        if(Util.isNotEmpty(searchDTO.getNICNo())) {
            sql.append("AND emp.NICNumber = :nicNo ");
            paramMap.put("nicNo", searchDTO.getNICNo());
        }

        if(Util.isNotEmpty(searchDTO.getEPFNo())) {
            sql.append("AND emp.EPFNumber = :epfNo ");
            paramMap.put("epfNo", searchDTO.getEPFNo());
        }

        if(Util.isNotEmpty(searchDTO.getDesignation())) {
            sql.append("AND emp.designation LIKE :designation ");
            paramMap.put("designation", Util.wrapInWildcards(searchDTO.getDesignation()));
        }

        if(searchDTO.getCompanyDid() != null && searchDTO.getCompanyDid() != -1L) {
            sql.append("AND emp.companyDid = :company ");
            paramMap.put("company", searchDTO.getCompanyDid());
        }

        if(searchDTO.getDepartmentDid() != null && searchDTO.getDepartmentDid() != -1L) {
            sql.append("AND dept.departmentDid = :department ");
            paramMap.put("department", searchDTO.getDepartmentDid()); 
        }

        if(searchDTO.getContractTypeDid() != null && searchDTO.getContractTypeDid() != -1) {
            sql.append("AND emp.contracttypedid = :contractTypeDid ");
            paramMap.put("contractTypeDid", searchDTO.getContractTypeDid());
        }

        if(Util.isNotEmpty(searchDTO.getDateOfBirthFrom()) && Util.isNotEmpty(searchDTO.getDateOfBirthTo())) {
            sql.append("AND emp.DOB BETWEEN :dobFromDate AND :dobToDate ");
            paramMap.put("dobFromDate", Util.floor(searchDTO.getDateOfBirthTo()));
            paramMap.put("dobToDate", Util.roof(searchDTO.getDateOfBirthTo()));
        } else if(Util.isNotEmpty(searchDTO.getDateOfBirthFrom())) {
            sql.append("AND emp.DOB = :dobFromDate ");
            paramMap.put("dobFromDate", searchDTO.getDateOfBirthFrom());                
        }
        
        sql.append("ORDER BY emp.EPFNumber DESC ");
        
       /* if(Util.isNotEmpty(searchDTO.getDateJoinedFrom()) && Util.isNotEmpty(searchDTO.getDateJoinedTo())) {
            sql.append("AND emp.dateJoined BETWEEN :dateJoinedFrom AND :dateJoinedTo ");
            paramMap.put("dateJoinedFrom", Util.floor(searchDTO.getDateJoinedFrom()));
            paramMap.put("dateJoinedTo", Util.roof(searchDTO.getDateJoinedTo()));
        } else if(Util.isNotEmpty(searchDTO.getDateJoinedFrom())) {
            sql.append("AND emp.dateJoined = :dateJoinedFrom ");
            paramMap.put("dateJoinedFrom", searchDTO.getDateJoinedFrom());
        }*/

       /* if(Util.isNotEmpty(searchDTO.getDueConfirmDateFrom()) && Util.isNotEmpty(searchDTO.getDueConfirmDateTo())) {
            sql.append("AND emp.dueConfirmationDate BETWEEN :dueConfDateFrom AND :dueConfDateTo ");
            paramMap.put("dueConfDateFrom", Util.floor(searchDTO.getDueConfirmDateFrom()));
            paramMap.put("dueConfDateTo", Util.roof(searchDTO.getDueConfirmDateTo()));
        } else if(Util.isNotEmpty(searchDTO.getDueConfirmDateFrom())) {
            sql.append("AND emp.dueConfirmationDate = :dueConfDateFrom ");
            paramMap.put("dueConfDateFrom", searchDTO.getDueConfirmDateFrom());
        }*/

       /* if(Util.isNotEmpty(searchDTO.getConfirmDateFrom()) && Util.isNotEmpty(searchDTO.getConfirmDateTo())) {
            sql.append("AND emp.confirmationDate BETWEEN :confDateFrom AND :confDateTo ");
            paramMap.put("confDateFrom", Util.floor(searchDTO.getConfirmDateFrom()));
            paramMap.put("confDateTo", Util.roof(searchDTO.getConfirmDateTo()));
        } else if(Util.isNotEmpty(searchDTO.getConfirmDateFrom())) {
            sql.append("AND emp.confirmationDate = :confDateFrom ");
            paramMap.put("confDateFrom", searchDTO.getConfirmDateFrom());
        }*/

        SQLQuery query = session.createSQLQuery(sql.toString());

        for (String key : paramMap.keySet()) {
            query.setParameter(key, paramMap.get(key));
        }
        
        for (String key : paramListMap.keySet()) {
            query.setParameterList(key,paramListMap.get(key));
        }

        query.addScalar("employeeDid", Hibernate.LONG);
        query.addScalar("empNo", Hibernate.STRING);
        query.addScalar("epfNo", Hibernate.STRING);
        query.addScalar("nicNo", Hibernate.STRING);
        query.addScalar("firstName", Hibernate.STRING);
        query.addScalar("middleName", Hibernate.STRING);
        query.addScalar("lastName", Hibernate.STRING);
        query.addScalar("addressLine1", Hibernate.STRING);
        query.addScalar("addressLine2", Hibernate.STRING);
        query.addScalar("addressLine3", Hibernate.STRING);
        query.addScalar("homePhone", Hibernate.STRING);
        query.addScalar("mobNo", Hibernate.STRING);
        query.addScalar("gender", Hibernate.STRING);
        query.addScalar("dob", Hibernate.DATE);
        query.addScalar("email", Hibernate.STRING);
        query.addScalar("maritalStatus", Hibernate.STRING);
        query.addScalar("dateJoined", Hibernate.DATE);
        query.addScalar("confDate", Hibernate.DATE);
        query.addScalar("drvLicenseNo", Hibernate.STRING);
        query.addScalar("passportNo", Hibernate.STRING);
        query.addScalar("dueConfDate", Hibernate.DATE);
        query.addScalar("resigDate", Hibernate.DATE);
        query.addScalar("designation", Hibernate.STRING);
        query.addScalar("department", Hibernate.STRING);
        query.addScalar("companyName", Hibernate.STRING);
        query.addScalar("contractType", Hibernate.STRING);

        query.setResultTransformer(Transformers.aliasToBean(EmployeeSearchResultsDTO.class));

        return query.list();

    } catch (HibernateException e) {
        throw new PersistenceException(e);
    } finally {
        session.close();
    }
}


public void deleteEmployeeSingleAddedAttributes(Long did, String tobject) throws PersistenceException {
	Session session = getSession();
	try {	
		StringBuffer sql = new StringBuffer();
		
		sql.append("DELETE FROM singleaddedattribute ");
		sql.append("WHERE attributegroupdid = 100 ");
		sql.append("AND did = :did ");
		sql.append("AND tobject = :tobject ");
		
		SQLQuery query = session.createSQLQuery(sql.toString());
		
		query.setParameter("did", did);
		query.setParameter("tobject", tobject);
		
		session.beginTransaction();
		query.executeUpdate();
		session.getTransaction().commit();
		
	}  catch (HibernateException e) {
		session.getTransaction().rollback();
        throw new PersistenceException(e);
    } finally {
        session.close();
    }
}
  
public void deleteEmployeeCompositeAddedAttributes(Long did, String tobject) throws PersistenceException {
	Session session = getSession();
	try {	
		StringBuffer sql = new StringBuffer();
		
		sql.append("CALL deleteCompositeAddedAttributes(:did,:tobject)");
		
		
		SQLQuery query = session.createSQLQuery(sql.toString());
		
		query.setParameter("did", did);
		query.setParameter("tobject", tobject);
		
		session.beginTransaction();
		query.executeUpdate();
		session.getTransaction().commit();
		
	}  catch (HibernateException e) {
		session.getTransaction().rollback();
        throw new PersistenceException(e);
    } finally {
        session.close();
    }
}


public List<HistoryDTO> getEmployeeHistory(Long employeeDid) throws PersistenceException {
	Session session = getSession();	
	try {

        StringBuffer sql = new StringBuffer();

        sql.append("SELECT history.historydid AS did, ");
        sql.append("history.note AS description, ");
        sql.append("history.historydate AS date, ");
        sql.append("history.employeeDid AS employeeDid ");
        sql.append("FROM employee emp, employeehistory history ");
        sql.append("WHERE emp.employeeDid = history.employeeDid ");
        sql.append("AND emp.employeeDid = :employeeDid ");

        SQLQuery query = session.createSQLQuery(sql.toString());

        query.setParameter("employeeDid", employeeDid);

        query.addScalar("did", Hibernate.LONG);
        query.addScalar("description", Hibernate.STRING);
        query.addScalar("date", Hibernate.DATE);
        query.addScalar("employeeDid", Hibernate.LONG);
        
        
        query.setResultTransformer(Transformers.aliasToBean(HistoryDTO.class));

        return query.list();
	} catch (HibernateException e) {
		throw new PersistenceException(e);
	} finally {
        session.close();
    }
}


public boolean isExistingEPFNumber(Long companyDid, Integer epfNumber) throws PersistenceException {
	Session session = getSession();	
	try {

        StringBuffer sql = new StringBuffer();

        sql.append("SELECT EPFNumber ");
        sql.append("FROM employee ");
        sql.append("WHERE companyDid = :companyDid ");
        sql.append("AND EPFNumber = :epfNumber ");

        SQLQuery query = session.createSQLQuery(sql.toString());

        query.setParameter("companyDid", companyDid);
        query.setParameter("epfNumber", epfNumber);

        if (query.list() != null && query.list().size() > 0) {
            return true;
        } else {
            return false;
        }
        
	} catch (HibernateException e) {
		throw new PersistenceException(e);
	} finally {
        session.close();
    }
}

public boolean isExistingNICNumber(String nic) throws PersistenceException {
	Session session = getSession();	
	try {

        StringBuffer sql = new StringBuffer();

        sql.append("SELECT NICNumber ");
        sql.append("FROM employee ");
        sql.append("WHERE NICNumber = :nicNumber ");

        SQLQuery query = session.createSQLQuery(sql.toString());

        query.setParameter("nicNumber", nic);

        if (query.list() != null && query.list().size() > 0) {
            return true;
        } else {
            return false;
        }
        
	} catch (HibernateException e) {
		throw new PersistenceException(e);
	} finally {
        session.close();
    }
}

public List<ConfirmationAlertDTO> getConfirmationAlertData(Long userCompanyDid, boolean isSingleCompany, boolean isParent) throws PersistenceException {
	
	Session session = getSession();
    try {

        StringBuffer sql = new StringBuffer();
        StringBuffer prepareSql = new StringBuffer();
        prepareSql.append("CALL hrdb.findToBeConfirmedEmployees(:userCompanyDid,:isSingleCompany,:isParent)");

        SQLQuery prepareQuery = session.createSQLQuery(prepareSql.toString());

        prepareQuery.setParameter("userCompanyDid", userCompanyDid);
        prepareQuery.setParameter("isSingleCompany", isSingleCompany ? 1 : 0);
        prepareQuery.setParameter("isParent", isParent ? 1 : 0);

        int flag = prepareQuery.executeUpdate();

        sql.append("SELECT r.epfNo AS epfNo, ");
        sql.append("r.companyId AS companyId, ");
        sql.append("r.dueConfDate AS dueConfDate ");
        sql.append("FROM tobeconformed r ");
        
        SQLQuery query = session.createSQLQuery(sql.toString());
        
        query.addScalar("epfNo", Hibernate.INTEGER);
        query.addScalar("companyId", Hibernate.STRING);
        query.addScalar("dueConfDate", Hibernate.DATE);
 
        query.setResultTransformer(Transformers.aliasToBean(ConfirmationAlertDTO.class));
        
        return query.list();
        
    } catch (HibernateException e) {
        throw new PersistenceException(e);
    } finally {
        session.close();
    }
}

public List<EmployeeQualificationDTO> getEmployeeQualifications(Long employeeDid) throws PersistenceException {
	
	Session session = getSession();
    try {

    	 StringBuffer sql = new StringBuffer();

    	 sql.append("SELECT q.qualificationDid AS qualificationDid, ");
    	 sql.append("qt.description AS qualificationType, ");
    	 sql.append("q.description AS description, ");
    	 sql.append("q.yearObtained AS year, ");
    	 sql.append("q.comment AS comment, "); 
    	 sql.append("q.employeeDid AS employeeDid "); 
    	 sql.append("FROM employeequalification q, qualificationtype qt ");
    	 sql.append("WHERE qt.qualificationTypeDid = q.qualificationTypeDid ");
    	 sql.append("AND q.employeeDid = :employeeDid ");
    	 
         SQLQuery query = session.createSQLQuery(sql.toString());

         query.setParameter("employeeDid", employeeDid);

         query.addScalar("qualificationDid", Hibernate.LONG);
         query.addScalar("qualificationType", Hibernate.STRING);
         query.addScalar("description", Hibernate.STRING);
         query.addScalar("year", Hibernate.STRING);
         query.addScalar("comment", Hibernate.STRING);
         query.addScalar("employeeDid", Hibernate.LONG);
         
         
         query.setResultTransformer(Transformers.aliasToBean(EmployeeQualificationDTO.class));

         return query.list();
        
    } catch (HibernateException e) {
        throw new PersistenceException(e);
    } finally {
        session.close();
    }
}

public EmployeeQualificationDTO getEmpQualificationByDid(Long qualificationDid) throws PersistenceException {
	Session session = getSession();
    try {

    	 StringBuffer sql = new StringBuffer();

    	 sql.append("SELECT q.qualificationDid AS qualificationDid, ");
    	 sql.append("q.qualificationTypeDid AS qualificationTypeDid, ");
    	 sql.append("q.description AS description, ");
    	 sql.append("q.yearObtained AS year, ");
    	 sql.append("q.comment AS comment, "); 
    	 sql.append("q.employeeDid AS employeeDid "); 
    	 sql.append("FROM employeequalification q ");
    	 sql.append("WHERE q.qualificationDid = :qualificationDid ");
    	 
         SQLQuery query = session.createSQLQuery(sql.toString());

         query.setParameter("qualificationDid", qualificationDid);

         query.addScalar("qualificationDid", Hibernate.LONG);
         query.addScalar("qualificationTypeDid", Hibernate.LONG);
         query.addScalar("description", Hibernate.STRING);
         query.addScalar("year", Hibernate.STRING);
         query.addScalar("comment", Hibernate.STRING);
         query.addScalar("employeeDid", Hibernate.LONG);
         
         
         query.setResultTransformer(Transformers.aliasToBean(EmployeeQualificationDTO.class));

         List results = query.list();
         
         return results != null && results.size() > 0 ? (EmployeeQualificationDTO)results.get(0) : null;
        
    } catch (HibernateException e) {
        throw new PersistenceException(e);
    } finally {
        session.close();
    }
}

public List<EmployeeSkillDTO> getEmployeeSkills(Long employeeDid) throws PersistenceException {
	
	Session session = getSession();
    try {

    	 StringBuffer sql = new StringBuffer();

    	 sql.append("SELECT s.skillDid AS skillDid, ");
    	 sql.append("st.description AS skillType, ");
    	 sql.append("s.description AS description, ");
    	 sql.append("s.employeeDid AS employeeDid "); 
    	 sql.append("FROM employeeskill s, skilltype st ");
    	 sql.append("WHERE s.skillTypeDid = st.skillTypeDid ");
    	 sql.append("AND s.employeeDid = :employeeDid ");
    	 
         SQLQuery query = session.createSQLQuery(sql.toString());

         query.setParameter("employeeDid", employeeDid);

         query.addScalar("skillDid", Hibernate.LONG);
         query.addScalar("skillType", Hibernate.STRING);
         query.addScalar("description", Hibernate.STRING);
         query.addScalar("employeeDid", Hibernate.LONG);
         
         
         query.setResultTransformer(Transformers.aliasToBean(EmployeeSkillDTO.class));

         return query.list();
        
    } catch (HibernateException e) {
        throw new PersistenceException(e);
    } finally {
        session.close();
    }
}

public EmployeeSkillDTO getEmpSkillByDid(Long skillDid) throws PersistenceException {
	Session session = getSession();
    try {

    	 StringBuffer sql = new StringBuffer();

    	 sql.append("SELECT s.skillDid AS skillDid, ");
    	 sql.append("s.skillTypeDid AS skillTypeDid, ");
    	 sql.append("s.description AS description, ");
    	 sql.append("s.employeeDid AS employeeDid "); 
    	 sql.append("FROM employeeskill s ");
    	 sql.append("WHERE s.skillDid = :skillDid ");
    	 
         SQLQuery query = session.createSQLQuery(sql.toString());

         query.setParameter("skillDid", skillDid);

         query.addScalar("skillDid", Hibernate.LONG);
         query.addScalar("skillTypeDid", Hibernate.LONG);
         query.addScalar("description", Hibernate.STRING);
         query.addScalar("employeeDid", Hibernate.LONG);
         
         
         query.setResultTransformer(Transformers.aliasToBean(EmployeeSkillDTO.class));

         List results = query.list();
         
         return results != null && results.size() > 0 ? (EmployeeSkillDTO)results.get(0) : null;
        
    } catch (HibernateException e) {
        throw new PersistenceException(e);
    } finally {
        session.close();
    }
}

public List<CompanyPropertyAssignmentDTO> getCompanyPropertyAssignment(Long employeeDid) throws PersistenceException {
	Session session = getSession();
    try {

    	 StringBuffer sql = new StringBuffer();

    	 sql.append("SELECT cp.propertyName AS propertyName, ");
    	 sql.append("pa.propertyAssignmentDid AS propertyAssignmentDid, ");
    	 sql.append("pa.propertyTypeDid AS propertyTypeDid, ");
    	 sql.append("pa.employeeDid AS employeeDid, ");
    	 sql.append("pa.assignedDate AS assignedDate, ");
    	 sql.append("pa.returnedDate AS returnedDate, ");
    	 sql.append("pa.comment AS comment "); 
    	 sql.append("FROM companypropertytype cp, employeepropertyassignment pa ");
    	 sql.append("WHERE cp.propertyTypeDid = pa.propertyTypeDid ");
    	 sql.append("AND pa.employeeDid = :employeeDid ");
    	 
         SQLQuery query = session.createSQLQuery(sql.toString());

         query.setParameter("employeeDid", employeeDid);

         query.addScalar("propertyAssignmentDid", Hibernate.LONG);
         query.addScalar("propertyTypeDid", Hibernate.LONG);
         query.addScalar("employeeDid", Hibernate.LONG);
         query.addScalar("propertyName", Hibernate.STRING);
         query.addScalar("assignedDate", Hibernate.DATE);
         query.addScalar("returnedDate", Hibernate.DATE);
         query.addScalar("comment", Hibernate.STRING);
         
         
         query.setResultTransformer(Transformers.aliasToBean(CompanyPropertyAssignmentDTO.class));

         return query.list();
        
    } catch (HibernateException e) {
        throw new PersistenceException(e);
    } finally {
        session.close();
    }
}


public CompanyPropertyAssignmentDTO getPropertyAssignmentByDid(Long propertyAssignmentDid) throws PersistenceException {
	Session session = getSession();
    try {

    	 StringBuffer sql = new StringBuffer();

    	 sql.append("SELECT pa.propertyAssignmentDid AS propertyAssignmentDid, ");
    	 sql.append("pa.propertyTypeDid AS propertyTypeDid, ");
    	 sql.append("pa.employeeDid AS employeeDid, ");
    	 sql.append("pa.assignedDate AS assignedDate, "); 
    	 sql.append("pa.returnedDate AS returnedDate, ");
    	 sql.append("pa.comment AS comment ");
    	 sql.append("FROM employeepropertyassignment pa ");
    	 sql.append("WHERE pa.propertyAssignmentDid = :propertyAssignmentDid ");
    	 
         SQLQuery query = session.createSQLQuery(sql.toString());

         query.setParameter("propertyAssignmentDid", propertyAssignmentDid);

         query.addScalar("propertyAssignmentDid", Hibernate.LONG);
         query.addScalar("propertyTypeDid", Hibernate.LONG);
         query.addScalar("employeeDid", Hibernate.LONG);
         query.addScalar("assignedDate", Hibernate.DATE);
         query.addScalar("returnedDate", Hibernate.DATE);
         query.addScalar("comment", Hibernate.STRING);
         
         
         query.setResultTransformer(Transformers.aliasToBean(CompanyPropertyAssignmentDTO.class));

         List results = query.list();
         
         return results != null && results.size() > 0 ? (CompanyPropertyAssignmentDTO)results.get(0) : null;
        
    } catch (HibernateException e) {
        throw new PersistenceException(e);
    } finally {
        session.close();
    }
}

public void updateCompanyPropertyReturns(Long propertyAssignmentDid, Date returnDate) throws PersistenceException {
	Session session = getSession();
	try {
		StringBuffer hql = new StringBuffer();
		hql.append("UPDATE EmployeePropertyAssignment propAssignment ");
		hql.append("SET propAssignment.returnedDate = :returnDate ");
		hql.append("WHERE propAssignment.propertyAssignmentDid = :propertyAssignmentDid ");
		
		Query query = session.createQuery(hql.toString());
        query.setParameter("propertyAssignmentDid", propertyAssignmentDid);
        query.setParameter("returnDate", returnDate);
        
        query.executeUpdate();
        
	} catch (HibernateException e) {
        throw new PersistenceException(e);
    } finally {
        session.close();
    }
}
  
}
