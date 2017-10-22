/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.business;

import com.ha.entity.model.custom.StudentDTO;
import javax.ejb.Local;
import com.ha.entity.model.custom.MemberDTO;
import com.ha.entity.model.custom.UserProfileDTO;
import com.ha.exceptions.BusinessException;
import java.util.List;

/**
 *
 * @author Buddhini
 */
@Local
public interface HorseServiceBeanLocal {
   public String getWelcomeMessage();
   public void saveStudent(StudentDTO studentDTO) throws BusinessException;
   public void saveMember(MemberDTO memberDTO) throws BusinessException;
   public UserProfileDTO getUserProfile(String userName, String password) throws BusinessException;
   public List<MemberDTO> findPendingRegistrations() throws BusinessException;
}
