/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ha.business;

import java.net.URL;

import javax.ejb.EJBHome;
import javax.ejb.EJBLocalHome;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.sql.DataSource;


import com.ha.business.ServiceLocatorException;

/**
 *
 * @author Buddhini
 */
public class ServiceLocator {

    private static ServiceLocator serviceLocator;
    private static InitialContext context;

    protected ServiceLocator() throws ServiceLocatorException {
        try {
            context = new InitialContext();
        } catch (Exception e) {
            throw new ServiceLocatorException(e.getMessage());
        }
    }

//    public EJBHome getEjbHome(String ejbName, Class ejbClass) throws
//            ServiceLocatorException {
//        try {
//            Object object = context.lookup(ejbName);
//            EJBHome ejbHome = null;
//            ejbHome = (EJBHome) PortableRemoteObject.narrow(object, ejbClass);
//            if (ejbHome == null) {
//                throw new ServiceLocatorException("Could not get home for " + ejbName);
//            }
//            return ejbHome;
//        } catch (NamingException ne) {
//            throw new ServiceLocatorException(ne.getMessage());
//        }
//    }

    public Object getEjbLocalHome(String ejbName) throws
            ServiceLocatorException {
        try {
            Context localContext = new InitialContext();
            Object object = localContext.lookup(ejbName);

            return object;
        } catch (NamingException ne) {
            throw new ServiceLocatorException(ne.getMessage());
        }
    }

    public static synchronized ServiceLocator getInstance() throws
            ServiceLocatorException {
        if (serviceLocator == null) {
            serviceLocator = new ServiceLocator();
        }
        return serviceLocator;
    }
}
