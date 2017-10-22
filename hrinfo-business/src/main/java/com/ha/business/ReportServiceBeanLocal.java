/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.business;

import com.ha.entity.model.custom.ContractTypeDTO;
import com.ha.entity.model.custom.ReportResultsDTO;
import com.ha.entity.model.custom.ReportSearchDTO;
import javax.ejb.Local;
import com.ha.exceptions.BusinessException;
import com.ha.entity.model.custom.SearchCols;
import java.util.List;

/**
 *
 * @author Buddhini
 */

@Local
public interface ReportServiceBeanLocal {
     public List<SearchCols> findAllReportFields() throws BusinessException;
     public List<ReportResultsDTO> searchReport(ReportSearchDTO searchDTO) throws BusinessException;
}
