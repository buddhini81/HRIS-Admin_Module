/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ha.entity.dao.hibernate;

import com.ha.entity.model.custom.UserProfileDTO;
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
public class HibernateHorseDAO extends HibernateCommonDAO implements IHorseDAO {


    public UserLogin getUser(String userName, String password) throws PersistenceException {
        Session session = getSession();
        try {
            StringBuffer hql = new StringBuffer();

            hql.append("FROM UserLogin u WHERE u.userName = :userName AND u.password = :password ");

            Query query = session.createQuery(hql.toString());

            query.setParameter("userName", userName);
            query.setParameter("password", password);

            List results = query.list();
            return (results != null && results.size() > 0) ? (UserLogin) results.get(0) : null;
        } catch (HibernateException e) {
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }

    public UserProfileDTO getUserProfile(Long userLoginDid) throws PersistenceException {
        Session session = getSession();
        try {

            StringBuffer sql = new StringBuffer();

            sql.append("SELECT m.firstname AS firstName, ");
            sql.append("m.lastname AS lastName, ");
            sql.append("m.company AS companyName, ");
            sql.append("m.phoneno AS phoneNumber, ");
            sql.append("m.mobileo AS mobileNumber, ");
            sql.append("m.faxno AS faxNumber, ");
            sql.append("m.email AS email, ");
            sql.append("ul.username AS userName, ");
            sql.append("ul.password AS password, ");
            sql.append("ul.userroledid AS userRoleDid, ");
            sql.append("ul.lastsuccessfullogindate AS lastLoginDate ");
            sql.append("FROM sitemember m, sitememberlogin ml, userlogin ul ");
            sql.append("WHERE m.sitememberdid = ml.sitememberdid ");
            sql.append("AND ml.userlogindid = ul.userlogindid ");
            sql.append("AND ul.userlogindid = :userLoginDid ");
            sql.append("AND m.approvalstatus = 2 ");

            SQLQuery query = session.createSQLQuery(sql.toString());

            query.setParameter("userLoginDid", userLoginDid);

            query.addScalar("firstName", Hibernate.STRING);
            query.addScalar("lastName", Hibernate.STRING);
            query.addScalar("companyName", Hibernate.STRING);
            query.addScalar("phoneNumber", Hibernate.STRING);
            query.addScalar("mobileNumber", Hibernate.STRING);
            query.addScalar("faxNumber", Hibernate.STRING);
            query.addScalar("email", Hibernate.STRING);
            query.addScalar("userName", Hibernate.STRING);
            query.addScalar("password", Hibernate.STRING);
            query.addScalar("userRoleDid", Hibernate.LONG);
            query.addScalar("lastLoginDate", Hibernate.TIMESTAMP);

            query.setResultTransformer(Transformers.aliasToBean(UserProfileDTO.class));

            List results = query.list();
            return (results != null && results.size() > 0) ? (UserProfileDTO) results.get(0) : null;
        } catch (HibernateException e) {
            throw new PersistenceException(e);
        } finally {
           session.close();
        }
    }

    public List findPendingRegistrations() throws PersistenceException {
        Session session = getSession();
        try {
            StringBuffer hql = new StringBuffer();

            hql.append("FROM SiteMember sm WHERE sm.approvalStatus = 1 ");

            Query query = session.createQuery(hql.toString());

            return query.list();

        } catch (HibernateException e) {
            throw new PersistenceException(e);
        } finally {
           session.close();
        }
    }
}
