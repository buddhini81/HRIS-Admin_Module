/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.services;

import com.ha.business.ReportServiceBeanLocal;
import com.ha.business.ServiceLocator;
import com.ha.business.ServiceLocatorException;
import com.ha.entity.model.custom.ContractTypeDTO;
import com.ha.entity.model.custom.MemberDTO;
import com.ha.entity.model.custom.SearchCols;
import com.ha.entity.model.custom.StudentDTO;
import com.ha.entity.model.custom.UserProfileDTO;
import com.ha.exceptions.BusinessException;
import java.util.List;
import com.ha.entity.model.custom.ReportResultsDTO;
import com.ha.entity.model.custom.ReportSearchDTO;

/**
 *
 * @author Buddhini
 */
public class ReportDelegate {
 private transient ReportServiceBeanLocal localSession;

    public ReportDelegate() {
        try {

            localSession = (ReportServiceBeanLocal) ServiceLocator.getInstance().getEjbLocalHome("ReportServiceBean/local");


        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        }
    }


    public List<SearchCols> findAllReportFields() throws BusinessException {
        try {
            return localSession.findAllReportFields();
        } catch (Exception e){
           throw new BusinessException();
        }
    }

    public List<ReportResultsDTO> searchReport(ReportSearchDTO searchDTO) throws BusinessException {
        try {
            return localSession.searchReport(searchDTO);
        } catch (Exception e){
           throw new BusinessException();
        }
    }
}
