/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.business;

import java.util.List;

import com.ha.entity.model.custom.UserMenuDTO;
import com.ha.entity.model.custom.UserProfileDTO;
import com.ha.entity.model.domain.UserLogin;
import com.ha.exceptions.BusinessException;
import javax.ejb.Local;
import javax.persistence.PersistenceException;


/**
 *
 * @author Buddhini
 */
@Local
public interface UserServiceBeanLocal {
    public UserProfileDTO getUserProfile(String userName, String password) throws BusinessException;
    public List<Long> getUserFunctionDids(Long userRoleDid) throws BusinessException;
    public List<UserMenuDTO> getUserMenus(Long userRoleDid) throws BusinessException;
    public UserLogin getUserByRoleAndName(Long userRoleDid, String userName) throws BusinessException;
}
