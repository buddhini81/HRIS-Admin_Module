/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.services;

import java.util.List;

import com.ha.business.ServiceLocator;
import com.ha.business.ServiceLocatorException;
import com.ha.business.UserServiceBeanLocal;
import com.ha.entity.model.custom.UserMenuDTO;
import com.ha.entity.model.custom.UserProfileDTO;
import com.ha.entity.model.domain.UserLogin;
import com.ha.exceptions.BusinessException;

/**
 *
 * @author Buddhini
 */
public class UserDelegate {
    private transient UserServiceBeanLocal localSession;
    public UserDelegate() {
         try {
            localSession = (UserServiceBeanLocal) ServiceLocator.getInstance().getEjbLocalHome("UserServiceBean/local");
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        }
    }

    public UserProfileDTO getUserProfile(String userName, String password) throws BusinessException {
          try {
        	  UserProfileDTO userProfileDTO = (UserProfileDTO)localSession.getUserProfile(userName,password);
        	  return userProfileDTO;
        } catch (Exception e){
        	e.printStackTrace();
        	throw new BusinessException();
        }
    }
    
    public List<Long> getUserFunctionDids(Long userRoleDid) throws BusinessException {
       try {
       	  return localSession.getUserFunctionDids(userRoleDid);
       } catch (Exception e){
       	e.printStackTrace();
       	throw new BusinessException();
       }
    }
    
    public List<UserMenuDTO> getUserMenus(Long userRoleDid) throws BusinessException {
    	 try {
          	  return localSession.getUserMenus(userRoleDid);
          } catch (Exception e){
          	e.printStackTrace();
          	throw new BusinessException();
          }
    }
    
    public UserLogin getUserByRoleAndName(Long userRoleDid, String userName) throws BusinessException {
    	try {
            return localSession.getUserByRoleAndName(userRoleDid, userName);
        } catch (Exception e){
           throw new BusinessException();
        }
    }

}
