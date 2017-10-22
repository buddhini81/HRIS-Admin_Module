/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.entity.dao.hibernate;

import javax.persistence.PersistenceException;

/**
 *
 * @author Buddhini
 */
public interface ICommonDAO {

    public void saveAnyObject(Object object) throws PersistenceException;
    public void saveAnyObjects(Object[] objs) throws PersistenceException;
    public void deleteAnyObject(Object object) throws PersistenceException;

}
