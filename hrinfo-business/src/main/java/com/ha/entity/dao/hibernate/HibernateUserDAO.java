/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.entity.dao.hibernate;

import com.ha.entity.model.custom.HistoryDTO;
import com.ha.entity.model.custom.UserMenuDTO;
import com.ha.entity.model.custom.UserProfileDTO;
import com.ha.entity.model.domain.UserFunction;
import com.ha.entity.model.domain.UserLogin;
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
public class HibernateUserDAO extends HibernateCommonDAO implements IUserDAO {

    public UserProfileDTO getUser(String userName, String password) throws PersistenceException {
        Session session = getSession();
        try {
            StringBuffer sql = new StringBuffer();
            
            sql.append("SELECT u.username AS userName, ");
            sql.append("u.password AS password , ");
            sql.append("u.userroledid AS userRoleDid, ");
            sql.append("c.companyDid AS companyDid, ");
            sql.append("c.companyName AS companyName, ");
            sql.append("c.isparent AS isParent ");
            sql.append("FROM userlogin u, company c "); 
            sql.append("WHERE u.companydid = c.companydid ");
            sql.append("AND BINARY username = :userName ");
            sql.append("AND password = :password ");

            SQLQuery query = session.createSQLQuery(sql.toString());

            query.setParameter("userName", userName);
            query.setParameter("password", password);

            query.addScalar("userName", Hibernate.STRING);
	        query.addScalar("password", Hibernate.STRING);
	        query.addScalar("userRoleDid", Hibernate.LONG);
	        query.addScalar("companyDid", Hibernate.LONG);
	        query.addScalar("companyName", Hibernate.STRING);
	        query.addScalar("isParent", Hibernate.BOOLEAN);
	        
	        query.setResultTransformer(Transformers.aliasToBean(UserProfileDTO.class));

	        List results = query.list();
            return (results != null && results.size() > 0) ? (UserProfileDTO) results.get(0) : null;
	        
        } catch (HibernateException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }


	public List<UserFunction> getUserFunctions(Long userRoleDid) throws PersistenceException {
		 Session session = getSession();
	        try {
	            StringBuffer hql = new StringBuffer();

	            hql.append("FROM UserFunction uf WHERE uf.userRoleDid = :userRoleDid ");

	            Query query = session.createQuery(hql.toString());

	            query.setParameter("userRoleDid", userRoleDid);

	            return query.list();
	        } catch (HibernateException e) {
	            throw new PersistenceException(e);
	        } finally {
	            session.close();
	        }
	}


	public List<UserMenuDTO> getUserMenus(Long userRoleDid) throws PersistenceException {
		Session session = getSession();	
		try {

	        StringBuffer sql = new StringBuffer();

	        sql.append("SELECT m.menudid AS menuDid, ");
	        sql.append("um.userroledid AS userRoleDid, ");
	        sql.append("m.actionurl AS actionUrl, ");
	        sql.append("m.imageurl AS imageUrl, ");
	        sql.append("m.tooltip AS toolTipText ");
	        sql.append("FROM menu m, usermenu um ");
	        sql.append("WHERE m.menudid = um.menudid ");
	        sql.append("AND um.userroledid = :userRoleDid ");

	        SQLQuery query = session.createSQLQuery(sql.toString());

	        query.setParameter("userRoleDid", userRoleDid);

	        query.addScalar("menuDid", Hibernate.LONG);
	        query.addScalar("userRoleDid", Hibernate.LONG);
	        query.addScalar("actionUrl", Hibernate.STRING);
	        query.addScalar("imageUrl", Hibernate.STRING);
	        query.addScalar("toolTipText", Hibernate.STRING);
	        
	        query.setResultTransformer(Transformers.aliasToBean(UserMenuDTO.class));

	        return query.list();
		} catch (HibernateException e) {
			throw new PersistenceException(e);
		} finally {
	        session.close();
	    }
	}


	public UserLogin getUserByRoleAndName(Long userRoleDid, String userName) throws PersistenceException {
		Session session = getSession();
        try {
            StringBuffer hql = new StringBuffer();

            hql.append("SELECT u FROM UserLogin u JOIN FETCH u.company WHERE u.userName = :userName AND u.userRoleDid = :userRoleDid ");

            Query query = session.createQuery(hql.toString());

            query.setParameter("userName", userName);
            query.setParameter("userRoleDid", userRoleDid);

            List results = query.list();
            return (results != null && results.size() > 0) ? (UserLogin) results.get(0) : null;
        } catch (HibernateException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
	}
    
}
