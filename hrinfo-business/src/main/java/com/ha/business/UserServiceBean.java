/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.business;

import java.util.ArrayList;
import java.util.List;

import com.ha.business.UserServiceBeanLocal;
import com.ha.entity.dao.hibernate.HibernateDAOFactory;
import com.ha.entity.dao.hibernate.IUserDAO;
import com.ha.entity.model.custom.UserMenuDTO;
import com.ha.entity.model.custom.UserProfileDTO;
import com.ha.entity.model.domain.UserFunction;
import com.ha.entity.model.domain.UserLogin;
import com.ha.exceptions.BusinessException;
import com.ha.exceptions.ExceptionHelper;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import org.jboss.ejb3.annotation.RemoteBinding;
import org.jboss.ejb3.annotation.LocalBinding;


/**
 *
 * @author Buddhini
 */

@Stateless
@Local(UserServiceBeanLocal.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@LocalBinding(jndiBinding = "UserServiceBean/local")
public class UserServiceBean implements UserServiceBeanLocal {

    @Resource
    private SessionContext context;
    private IUserDAO userDAO = HibernateDAOFactory.getInstance().getUserDAO();

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public UserProfileDTO getUserProfile(String userName, String password) throws BusinessException {
        try {
            return userDAO.getUser(userName, password);
        } catch (Exception e) {
            ExceptionHelper.handleError("Error in UserServiceBean -> getUserProfile", context, e);
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Long> getUserFunctionDids(Long userRoleDid) throws BusinessException {
    	List<Long> functionDids = null; 
    	try {
             List<UserFunction> userFunctions = userDAO.getUserFunctions(userRoleDid);
             if(userFunctions != null && userFunctions.size() > 0) {
            	functionDids = new ArrayList<Long>();
                for(UserFunction f : userFunctions) {
                	functionDids.add(f.getFunctionDid());
                }
             }
             
             return functionDids;

         } catch (Exception e) {
             ExceptionHelper.handleError("Error in UserServiceBean -> getUserFunctionDids", context, e);
             return null;
         }
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<UserMenuDTO> getUserMenus(Long userRoleDid) throws BusinessException {
    	try {
             return userDAO.getUserMenus(userRoleDid);
         } catch (Exception e) {
             ExceptionHelper.handleError("Error in UserServiceBean -> getUserMenus", context, e);
             return null;
         }
    }
    
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public UserLogin getUserByRoleAndName(Long userRoleDid, String userName) throws BusinessException {
		try {			
			return userDAO.getUserByRoleAndName(userRoleDid,userName);
		}catch (Exception e) {
			ExceptionHelper.handleError("Error in UserServiceBean -> getUserByRoleAndName",context, e);
			return null;
		}
	}
	
}
