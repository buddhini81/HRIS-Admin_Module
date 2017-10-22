/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.entity.dao.hibernate;

import java.util.List;

import com.ha.entity.model.custom.UserMenuDTO;
import com.ha.entity.model.custom.UserProfileDTO;
import com.ha.entity.model.domain.UserLogin;
import com.ha.entity.model.domain.UserFunction;
import javax.persistence.PersistenceException;

/**
 *
 * @author Buddhini
 */
public interface IUserDAO {
    public UserProfileDTO getUser(String userName,String password) throws PersistenceException;
    public List<UserFunction> getUserFunctions(Long userRoleDid) throws PersistenceException;
    public List<UserMenuDTO> getUserMenus(Long userRoleDid) throws PersistenceException;
    public UserLogin getUserByRoleAndName(Long userRoleDid, String userName) throws PersistenceException;
}
