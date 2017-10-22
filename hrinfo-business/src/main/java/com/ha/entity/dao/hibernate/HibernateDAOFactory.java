/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ha.entity.dao.hibernate;

/**
 *
 * @author Buddhini
 */
public class HibernateDAOFactory {

    private static HibernateDAOFactory instance;

    private HibernateDAOFactory() {
    	
    }
    
    public static HibernateDAOFactory getInstance() {
        if (instance == null) {
            instance = new HibernateDAOFactory();
        }
        return instance;
    }

    public ICommonDAO getCommonDAO() {
        return new HibernateCommonDAO();
    }

    public IHorseDAO getHorseDAO() {
        return new HibernateHorseDAO();
    }

    public IReportDAO getReportDAO() {
        return new HibernateReportDAO();
    }

    public IUserDAO getUserDAO() {
        return new HibernateUserDAO();
    }

    public IGlobalDAO getGlobalDAO() {
        return new HibernateGlobalDAO();
    }
    
    public IEmployeeDAO getEmployeeDAO() {
        return new HibernateEmployeeDAO();
    }
    
    public IAdminDAO getAdminDAO() {
        return new HibernateAdminDAO();
    }
}
