/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.business;

import com.ha.entity.dao.hibernate.HibernateDAOFactory;
import com.ha.entity.dao.hibernate.IReportDAO;
import com.ha.entity.model.custom.ContractTypeDTO;
import com.ha.entity.model.custom.SearchCols;
import com.ha.entity.model.domain.ContractType;
import com.ha.exceptions.BusinessException;
import com.ha.exceptions.ExceptionHelper;
import com.ha.util.Constants;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Remote;
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
import com.ha.entity.model.custom.ReportResultsDTO;
import com.ha.entity.model.custom.ReportSearchDTO;

/**
 *
 * @author Buddhini
 */
@Stateless
@Local(ReportServiceBeanLocal.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@LocalBinding(jndiBinding = "ReportServiceBean/local")
public class ReportServiceBean implements ReportServiceBeanLocal{

     private Log log = LogFactory.getLog(ExceptionHelper.class);
    @Resource
    private SessionContext context;
    private IReportDAO reportDAO = HibernateDAOFactory.getInstance().getReportDAO();

    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<SearchCols> findAllReportFields() throws BusinessException {

        try {
            return reportDAO.findAllReportFields();
        } catch (Exception e) {
            ExceptionHelper.handleError("Error in ReportServiceBean -> findAllReportFields", context, e);
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<ReportResultsDTO> searchReport(ReportSearchDTO searchDTO) throws BusinessException {
         try {
            return reportDAO.searchReport(searchDTO);
        } catch (Exception e) {
            ExceptionHelper.handleError("Error in ReportServiceBean -> searchReport", context, e);
            return null;
        }
    }
}
