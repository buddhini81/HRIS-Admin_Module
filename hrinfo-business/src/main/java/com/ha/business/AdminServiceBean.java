package com.ha.business;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.ejb3.annotation.LocalBinding;

import com.ha.entity.dao.hibernate.HibernateDAOFactory;
import com.ha.entity.dao.hibernate.IAdminDAO;
import com.ha.entity.dao.hibernate.ICommonDAO;
import com.ha.entity.model.custom.UserDTO;
import com.ha.entity.model.domain.Company;
import com.ha.entity.model.domain.UserLogin;
import com.ha.exceptions.BusinessException;
import com.ha.exceptions.ExceptionHelper;

@Stateless
@Local(EmployeeServicBeaneLocal.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@LocalBinding(jndiBinding = "AdminServiceBean/local")
public class AdminServiceBean implements AdminServiceBeanLocal {

	private Log log = LogFactory.getLog(ExceptionHelper.class);
	@Resource
	private SessionContext context;
	private ICommonDAO commonDAO = HibernateDAOFactory.getInstance().getCommonDAO();
	private IAdminDAO adminDAO = HibernateDAOFactory.getInstance().getAdminDAO();
	
	public void saveUser(UserDTO userDTO) throws BusinessException {
		try {
			UserLogin user = new UserLogin();
			Company company = new Company();
			company.setCompanyDid(userDTO.getCompanyDid());
			user.setCompany(company);
			user.setUserRoleDid(userDTO.getUserRoleDid());
			user.setUserName(userDTO.getUserName());
			user.setPassword(userDTO.getPassword());
			user.setUserLoginDid(userDTO.getUserDid());
			
			
			commonDAO.saveAnyObject(user);
		} catch (Exception e) {
			log.info("Before handleError ", e);
			ExceptionHelper.handleError("Error in AdminServiceBean -> saveUser",context, e);
		}
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<UserDTO> getUserListByCompanyAndRole(Long companyDid,Long roleDid) throws BusinessException {
		try {
			return adminDAO.getUserListByCompanyAndRole(companyDid, roleDid);

		} catch (Exception e) {
			log.info("Before handleError ", e);
			ExceptionHelper.handleError("Error in AdminServiceBean -> getUserListByCompanyAndRole",context, e);
			return null;
		}
	}
}
