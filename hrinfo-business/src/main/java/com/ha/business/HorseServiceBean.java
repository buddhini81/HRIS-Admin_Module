/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ha.business;

import com.ha.entity.dao.hibernate.HibernateDAOFactory;
import com.ha.entity.dao.hibernate.IHorseDAO;
import com.ha.entity.model.custom.MemberDTO;
import com.ha.entity.model.custom.StudentDTO;
import com.ha.entity.model.custom.UserProfileDTO;
import com.ha.entity.model.domain.SiteMember;
import com.ha.entity.model.domain.SiteMemberLogin;
import com.ha.entity.model.domain.Student;
import com.ha.entity.model.domain.UserLogin;
import com.ha.exceptions.BusinessException;
import com.ha.exceptions.ExceptionHelper;
import com.ha.util.Constants;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import org.jboss.ejb3.annotation.RemoteBinding;
import org.jboss.ejb3.annotation.LocalBinding;
import javax.persistence.PersistenceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Buddhini
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class HorseServiceBean implements HorseServiceBeanLocal {

    private Log log = LogFactory.getLog(ExceptionHelper.class);
    @Resource
    private SessionContext context;
    private IHorseDAO horseDAO = HibernateDAOFactory.getInstance().getHorseDAO();

    public String getWelcomeMessage() {
        return "Hello World!!!";
    }

    public void saveStudent(StudentDTO studentDTO) throws BusinessException {
        try {
            Student student = new Student();

            student.setFirstName(studentDTO.getFirstName());
            student.setLastName(studentDTO.getLastName());
            student.setAddress(studentDTO.getAddress());

            horseDAO.saveAnyObject(student);

        } catch (Exception e) {
            ExceptionHelper.handleError("Error in HorseServiceBean -> saveStudent", context, e);
        }
    }

    public void saveMember(MemberDTO memberDTO) throws BusinessException {
        try {
            SiteMember member = new SiteMember();
            UserLogin userLogin = new UserLogin();
            SiteMemberLogin memberLogin = new SiteMemberLogin();

            if (memberDTO.getFirstName() != null) {
                member.setFirstName(memberDTO.getFirstName());
            }
            if (memberDTO.getLastName() != null) {
                member.setLastName(memberDTO.getLastName());
            }
            if (memberDTO.getCompany() != null) {
                member.setCompany(memberDTO.getCompany());
            }
            if (memberDTO.getEmail() != null) {
                member.setEmail(memberDTO.getEmail());
                userLogin.setUserName(memberDTO.getEmail());
            }
            if (memberDTO.getPhoneNumber() != null) {
                member.setPhoneNumber(memberDTO.getPhoneNumber());
            }
            if (memberDTO.getMobileNumber() != null) {
                member.setMobileNumber(memberDTO.getMobileNumber());
            }
            if (memberDTO.getFaxNumber() != null) {
                member.setFaxNumber(memberDTO.getFaxNumber());
            }
            if (memberDTO.getApprovalStatus() != null) {
                member.setApprovalStatus(memberDTO.getApprovalStatus());
            }
            if (memberDTO.getPassword() != null) {
                userLogin.setPassword(memberDTO.getPassword());
            }

            userLogin.setUserRoleDid(new Long(Constants.USER_ROLE_DID_MEMBER));

            horseDAO.saveAnyObject(member);
            horseDAO.saveAnyObject(userLogin);

            memberLogin.setSiteMemberDid(member.getSiteMemberDid());
            memberLogin.setUserRoleDid(userLogin.getUserLoginDid());

            horseDAO.saveAnyObject(memberLogin);

        } catch (Exception e) {
            log.info("Before handleError ", e);
            ExceptionHelper.handleError("Error in HorseServiceBean -> saveMember", context, e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public UserProfileDTO getUserProfile(String userName, String password) throws BusinessException {
        UserProfileDTO userProfileDTO = null;
        try {
            UserLogin userLogin = horseDAO.getUser(userName, password);
            if (userLogin != null) {
                if (userLogin.getUserRoleDid().intValue() == Constants.USER_ROLE_DID_ADMIN) {
                    userProfileDTO = new UserProfileDTO();
                    userProfileDTO.setUserName(userLogin.getUserName());
                    userProfileDTO.setPassword(userLogin.getPassword());
                    userProfileDTO.setUserRoleDid(userLogin.getUserRoleDid());
                    //userProfileDTO.setLastLoginDate(userLogin.getLastSucessfulLoginDate());
                } else if (userLogin.getUserRoleDid().intValue() == Constants.USER_ROLE_DID_MEMBER) {
                    userProfileDTO = horseDAO.getUserProfile(userLogin.getUserLoginDid());
                    if (userProfileDTO == null) {
                        userProfileDTO = new UserProfileDTO();
                        //userProfileDTO.setErrorCode(Constants.LoginErrorCodes.MEMBER_NOT_APPROVED);
                    }
                }
            } else {
                userProfileDTO = new UserProfileDTO();
                //userProfileDTO.setErrorCode(Constants.LoginErrorCodes.INVALID_LOGIN_DETAILS);
            }
        } catch (Exception e) {
            ExceptionHelper.handleError("Error in HorseServiceBean -> getUserProfile", context, e);
        }
        return userProfileDTO;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public List<MemberDTO> findPendingRegistrations() throws BusinessException {
        List<MemberDTO> memberDTOList = null;
        try {
            List<SiteMember> memberList = horseDAO.findPendingRegistrations();
            if (memberList != null && memberList.size() > 0) {
                memberDTOList = new ArrayList<MemberDTO>();
                for (SiteMember sm : memberList) {
                    MemberDTO memberDTO = new MemberDTO();

                    memberDTO.setDid(sm.getSiteMemberDid());
                    memberDTO.setFirstName(sm.getFirstName());
                    memberDTO.setLastName(sm.getLastName());
                    memberDTO.setCompany(sm.getCompany());
                    memberDTO.setPhoneNumber(sm.getPhoneNumber());
                    memberDTO.setFaxNumber(sm.getFaxNumber());
                    memberDTO.setEmail(sm.getEmail());

                    memberDTOList.add(memberDTO);
                }
            }
            return memberDTOList;
        } catch (Exception e) {
            ExceptionHelper.handleError("Error in HorseServiceBean -> findPendingRegistrations", context, e);
            return null;
        }
    }
}
