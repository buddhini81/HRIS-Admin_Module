/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.entity.dao.hibernate;


import org.hibernate.Session;
import javax.persistence.PersistenceException;
import org.hibernate.HibernateException;
/**
 *
 * @author Buddhini
 */
public class HibernateCommonDAO implements ICommonDAO {

    protected Session getSession() throws PersistenceException {
        return HibernateUtil.getSessionFactory().openSession();
    }

    public void saveAnyObject(Object object) throws PersistenceException {
        Session session = getSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(object);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }

    public void saveAnyObjects(Object[] objs) throws PersistenceException {
        Session session = getSession();
        try {
        	session.beginTransaction();
            for (int i = 0; i < objs.length; i++) {
                Object obj = objs[i];
                session.saveOrUpdate(obj);
                if (i % 20 == 0) {
                	session.flush();
                	session.clear();
                }
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }


	public void deleteAnyObject(Object object) throws PersistenceException {
        Session session = getSession();
        try {
            session.beginTransaction();
            session.delete(object);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
	}

}
