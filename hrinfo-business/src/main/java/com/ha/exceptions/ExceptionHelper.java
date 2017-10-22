/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ha.exceptions;

import com.ha.entity.dao.hibernate.HibernateUtil;
import javax.ejb.EJBContext;
import javax.persistence.PersistenceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;

/**
 *
 * @author Buddhini
 */
public class ExceptionHelper {

    private static Log log = LogFactory.getLog(ExceptionHelper.class);

    public static void handleError(String message, EJBContext ctx, Exception e) throws BusinessException {
        log.info("In handleError method " + e);
        if (ctx != null) {
            log.info("In ctx " + e);
            HibernateUtil.getSessionFactory().getCurrentSession().close();
            try {
                log.info("In try " + e);
                if (!ctx.getRollbackOnly()) {
                    log.info("In if " + e);
                    ctx.setRollbackOnly();
                    log.info("after roleback " + e);
                }
            } catch (IllegalStateException e1) {
                log.info("In catch " + e);
                //Ignore... probably the rollback was could not be performed because there was no transaction context
            }
        }

        if (e != null) {
            if (e instanceof PersistenceException) {
                e.printStackTrace();
                throw new BusinessException("Unexpected DB Error", e);
            } else if (e instanceof HibernateException) {
                e.printStackTrace();
                throw new BusinessException("Hibernate Error", e);
            } else {
                e.printStackTrace();
                throw new BusinessException("Unknown Error", e);
            }

        }
    }
}
