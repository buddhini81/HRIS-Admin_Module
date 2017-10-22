package com.ha.entity.dao.hibernate;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import com.ha.entity.model.custom.EmployeeAllowanceDTO;
import com.ha.entity.model.custom.UserDTO;

public class HibernateAdminDAO extends HibernateCommonDAO implements IAdminDAO {

	@Override
	public List<UserDTO> getUserListByCompanyAndRole(Long companyDid, Long roleDid) throws PersistenceException {
		Session session = getSession();
		try{
			StringBuffer sql = new StringBuffer();

			sql.append("SELECT user.userlogindid AS userDid, ");
			sql.append("user.username AS userName, ");
			sql.append("user.userroledid AS userRoleDid, ");
			sql.append("user.companydid AS companyDid ");
			sql.append("FROM userlogin user ");
			sql.append("WHERE user.companydid = :companyDid ");
			sql.append("AND user.userroledid = :roleDid ");

	       
	        SQLQuery query = session.createSQLQuery(sql.toString());

	        query.setParameter("companyDid", companyDid);
	        query.setParameter("roleDid", roleDid);

	        query.addScalar("userDid", Hibernate.LONG);
	        query.addScalar("userName", Hibernate.STRING);
	        query.addScalar("userRoleDid", Hibernate.LONG);
	        query.addScalar("companyDid", Hibernate.LONG);
	        
	        query.setResultTransformer(Transformers.aliasToBean(UserDTO.class));

	        return query.list();
		} catch (HibernateException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
	}

}
