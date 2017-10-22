package com.ha.business;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import com.ha.entity.model.custom.UserDTO;
import com.ha.exceptions.BusinessException;

@Local
public interface AdminServiceBeanLocal {

	public void saveUser(UserDTO userDTO) throws BusinessException;
	public List<UserDTO> getUserListByCompanyAndRole(Long companyDid, Long roleDid) throws BusinessException;
}
