/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.entity.dao.hibernate;

import com.ha.entity.model.custom.ReportResultsDTO;
import com.ha.entity.model.custom.ReportSearchDTO;
import com.ha.entity.model.domain.ContractType;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import com.ha.entity.model.custom.SearchCols;
import com.ha.util.Constants;
import com.ha.util.Util;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author Buddhini
 */
public class HibernateReportDAO extends HibernateCommonDAO implements IReportDAO {
    public List<SearchCols> findAllReportFields() throws PersistenceException {
        Session session = getSession();
        try {
            StringBuffer sql = new StringBuffer();

            sql.append("SELECT rf.fieldName AS fieldName, ");
            sql.append("rf.fieldLabel AS fieldLabel ");
            sql.append("FROM reportfield rf");

            SQLQuery query = session.createSQLQuery(sql.toString());

            query.addScalar("fieldName", Hibernate.STRING);
            query.addScalar("fieldLabel", Hibernate.STRING);

            query.setResultTransformer(Transformers.aliasToBean(SearchCols.class));

            return query.list();

        } catch (HibernateException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }

    public List<ReportResultsDTO> searchReport(ReportSearchDTO searchDTO) throws PersistenceException {
        Session session = getSession();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();

            StringBuffer sql = new StringBuffer();

            sql.append("SELECT emp.basicsalary AS basicSalary, ");
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
            sql.append("CASE emp.maritalStatus ");
        	sql.append("WHEN 'M' THEN 'Yes' ");
        	sql.append("WHEN 'S' THEN 'No' ");
        	sql.append("ELSE ' ' END AS maritalStatus, ");
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
            sql.append("FROM employee emp,company com,department dept,departmentassignment da,contracttype ct ");
            sql.append("WHERE emp.employeeDid = da.employeeDid ");
            sql.append("AND da.departmentDid = dept.departmentDid ");
            sql.append("AND dept.companyDid = com.companyDid ");
            sql.append("AND emp.contracttypedid = ct.contractTypeDid ");

            if(Util.isNotEmpty(searchDTO.getFirstName())) {
                sql.append("AND emp.firstName LIKE :firstName ");
                paramMap.put("firstName", Util.wrapInWildcards(searchDTO.getFirstName()));
            }

            if(Util.isNotEmpty(searchDTO.getLastName())) {
                sql.append("AND emp.lastName LIKE :lastName ");
                paramMap.put("lastName", Util.wrapInWildcards(searchDTO.getLastName()));
            }

            if(Util.isNotEmpty(searchDTO.getGender())) {
                sql.append("AND emp.gender = :gender ");
                paramMap.put("gender", searchDTO.getGender());
            }

            if(Util.isNotEmpty(searchDTO.getEmployeeNo())) {
                sql.append("AND emp.employeeNumber = :empNo ");
                paramMap.put("empNo", searchDTO.getEmployeeNo());
            }

            if(Util.isNotEmpty(searchDTO.getNicNo())) {
                sql.append("AND emp.NICNumber = :nicNo ");
                paramMap.put("nicNo", searchDTO.getNicNo());
            }

            if(Util.isNotEmpty(searchDTO.getEpfNo())) {
                sql.append("AND emp.EPFNumber = :epfNo ");
                paramMap.put("epfNo", searchDTO.getEpfNo());
            }

            if(Util.isNotEmpty(searchDTO.getDesignation())) {
                sql.append("AND emp.designation LIKE :designation ");
                paramMap.put("designation", Util.wrapInWildcards(searchDTO.getDesignation()));
            }

            if(Util.isNotEmpty(searchDTO.getCompanyName())) {
                sql.append("AND com.companyName LIKE :companyName ");
                paramMap.put("companyName", Util.wrapInWildcards(searchDTO.getCompanyName()));
            }

            if(Util.isNotEmpty(searchDTO.getDepartmentCode())) {
                sql.append("AND dept.departmentCode LIKE :deptCode ");
                paramMap.put("deptCode", Util.wrapInWildcards(searchDTO.getDepartmentCode()));
            }

            if(searchDTO.getContractTypeDid() != null) {
                sql.append("AND emp.contracttypedid = :contractTypeDid ");
                paramMap.put("contractTypeDid", searchDTO.getContractTypeDid());
            }

            if(Util.isNotEmpty(searchDTO.getDobFrom()) && Util.isNotEmpty(searchDTO.getDobTo())) {
                sql.append("AND emp.DOB BETWEEN :dobFromDate AND :dobToDate ");
                paramMap.put("dobFromDate", Util.floor(searchDTO.getDobFrom()));
                paramMap.put("dobToDate", Util.roof(searchDTO.getDobTo()));
            } else if(Util.isNotEmpty(searchDTO.getDobFrom())) {
                sql.append("AND emp.DOB = :dobFromDate ");
                paramMap.put("dobFromDate", searchDTO.getDobFrom());                
            }
            
            if(Util.isNotEmpty(searchDTO.getDateJoinedFrom()) && Util.isNotEmpty(searchDTO.getDateJoinedTo())) {
                sql.append("AND emp.dateJoined BETWEEN :dateJoinedFrom AND :dateJoinedTo ");
                paramMap.put("dateJoinedFrom", Util.floor(searchDTO.getDateJoinedFrom()));
                paramMap.put("dateJoinedTo", Util.roof(searchDTO.getDateJoinedTo()));
            } else if(Util.isNotEmpty(searchDTO.getDateJoinedFrom())) {
                sql.append("AND emp.dateJoined = :dateJoinedFrom ");
                paramMap.put("dateJoinedFrom", searchDTO.getDateJoinedFrom());
            }

            if(Util.isNotEmpty(searchDTO.getDueConfirmDateFrom()) && Util.isNotEmpty(searchDTO.getDueConfirmDateTo())) {
                sql.append("AND emp.dueConfirmationDate BETWEEN :dueConfDateFrom AND :dueConfDateTo ");
                paramMap.put("dueConfDateFrom", Util.floor(searchDTO.getDueConfirmDateFrom()));
                paramMap.put("dueConfDateTo", Util.roof(searchDTO.getDueConfirmDateTo()));
            } else if(Util.isNotEmpty(searchDTO.getDueConfirmDateFrom())) {
                sql.append("AND emp.dueConfirmationDate = :dueConfDateFrom ");
                paramMap.put("dueConfDateFrom", searchDTO.getDueConfirmDateFrom());
            }

            if(Util.isNotEmpty(searchDTO.getConfirmDateFrom()) && Util.isNotEmpty(searchDTO.getConfirmDateTo())) {
                sql.append("AND emp.confirmationDate BETWEEN :confDateFrom AND :confDateTo ");
                paramMap.put("confDateFrom", Util.floor(searchDTO.getConfirmDateFrom()));
                paramMap.put("confDateTo", Util.roof(searchDTO.getConfirmDateTo()));
            } else if(Util.isNotEmpty(searchDTO.getConfirmDateFrom())) {
                sql.append("AND emp.confirmationDate = :confDateFrom ");
                paramMap.put("confDateFrom", searchDTO.getConfirmDateFrom());
            }
            
            if(Util.isNotEmpty(searchDTO.getSortBy())) {
            	int sortBy = Integer.parseInt(searchDTO.getSortBy());
            	sql.append("ORDER BY ");
            	switch(sortBy) {
	   			 case 1 : 
	   				sql.append("com.companyName ").append(searchDTO.getSortOrder());
	   				break;
	   			 case 2 : 
	   				sql.append("dept.departmentCode ").append(searchDTO.getSortOrder());
	   				break;
	   			 case 3 : 
	   				sql.append("emp.dateJoined ").append(searchDTO.getSortOrder());
	   				break;
	   			 case 4 : 
		   			sql.append("emp.age ").append(searchDTO.getSortOrder());
		   			break;
	   			 default:
	   				sql.append("emp.EPFNumber ").append(searchDTO.getSortOrder());
	   				break;
            	}
            }

            SQLQuery query = session.createSQLQuery(sql.toString());

            for (String key : paramMap.keySet()) {
                query.setParameter(key, paramMap.get(key));
            }

            query.addScalar("basicSalary", Hibernate.BIG_DECIMAL);
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

            query.setResultTransformer(Transformers.aliasToBean(ReportResultsDTO.class));

            return query.list();

        } catch (HibernateException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }
}
