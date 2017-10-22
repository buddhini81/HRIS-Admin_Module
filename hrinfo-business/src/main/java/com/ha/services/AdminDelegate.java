package com.ha.services;

import java.util.List;

import com.ha.business.AdminServiceBeanLocal;
import com.ha.business.ServiceLocator;
import com.ha.business.ServiceLocatorException;
import com.ha.entity.model.custom.EmployeeDTO;
import com.ha.entity.model.custom.UserDTO;
import com.ha.exceptions.BusinessException;

public class AdminDelegate {
	private transient AdminServiceBeanLocal localSession;

    public AdminDelegate() {
         try {

            localSession = (AdminServiceBeanLocal) ServiceLocator.getInstance().getEjbLocalHome("AdminServiceBean/local");


        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        }
    }
    
    public void saveUser(UserDTO userDTO) throws BusinessException {
        try {
           localSession.saveUser(userDTO);
       } catch (Exception e){
          throw new BusinessException();
       }
   }
    
    public List<UserDTO> getUserListByCompanyAndRole(Long companyDid,Long roleDid) throws BusinessException {
    	 try {
             return localSession.getUserListByCompanyAndRole(companyDid,roleDid);
         } catch (Exception e){
            throw new BusinessException();
         }
    }
}
