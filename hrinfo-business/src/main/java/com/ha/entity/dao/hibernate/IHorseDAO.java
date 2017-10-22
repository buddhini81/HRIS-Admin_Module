/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.entity.dao.hibernate;

import com.ha.entity.model.custom.UserProfileDTO;
import com.ha.entity.model.domain.UserLogin;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author Buddhini
 */
public interface IHorseDAO {
    public void saveAnyObject(Object object) throws PersistenceException;
    public UserLogin getUser(String userName,String password) throws PersistenceException;
    public UserProfileDTO getUserProfile(Long userLoginDid) throws PersistenceException;
    public List findPendingRegistrations() throws PersistenceException;
}
