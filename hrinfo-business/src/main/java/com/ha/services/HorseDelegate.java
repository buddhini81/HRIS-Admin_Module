/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ha.services;

import com.ha.business.ServiceLocator;
import com.ha.business.HorseServiceBeanLocal;
import com.ha.business.ServiceLocatorException;
import com.ha.entity.model.custom.MemberDTO;
import com.ha.entity.model.custom.StudentDTO;
import com.ha.entity.model.custom.UserProfileDTO;
import com.ha.exceptions.BusinessException;
import java.util.List;

/**
 *
 * @author Buddhini
 */
public class HorseDelegate {

    private transient HorseServiceBeanLocal localSession;

    public HorseDelegate() {
        try {

            localSession = (HorseServiceBeanLocal) ServiceLocator.getInstance().getEjbLocalHome("HorseProject/HorseServiceBean/local");


        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        }
    }

    public String getWelcomeMessage() {
        try {
            return localSession.getWelcomeMessage();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void saveStudent(StudentDTO studentDTO) throws BusinessException {
        try {
            localSession.saveStudent(studentDTO);
        } catch (Exception e){
            throw new BusinessException(e);
        }
    }

    public void saveMember(MemberDTO memberDTO) throws BusinessException {
        try{
            localSession.saveMember(memberDTO);
        } catch (Exception e){
            throw new BusinessException();
        }
    }

    public UserProfileDTO getUserProfile(String userName, String password) throws BusinessException {
        try{
           return localSession.getUserProfile(userName, password);
        } catch (Exception e){
           throw new BusinessException();
        }        
    }
    
    public List<MemberDTO> findPendingRegistrations() throws BusinessException {
        try {
            return localSession.findPendingRegistrations();
        } catch (Exception e){
           throw new BusinessException();
        }
    }
}
