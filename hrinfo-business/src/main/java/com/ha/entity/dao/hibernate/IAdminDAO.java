package com.ha.entity.dao.hibernate;

import java.util.List;

import javax.persistence.PersistenceException;

import com.ha.entity.model.custom.EmployeeDTO;
import com.ha.entity.model.custom.EmployeeSearchDTO;
import com.ha.entity.model.custom.UserDTO;

public interface IAdminDAO {

	public List<UserDTO> getUserListByCompanyAndRole(Long companyDid, Long roleDid) throws PersistenceException;
}
