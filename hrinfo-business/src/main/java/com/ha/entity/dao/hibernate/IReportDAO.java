/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.entity.dao.hibernate;

import com.ha.entity.model.custom.ReportResultsDTO;
import com.ha.entity.model.custom.ReportSearchDTO;
import java.util.List;
import javax.persistence.PersistenceException;
import com.ha.entity.model.custom.SearchCols;
import com.ha.entity.model.domain.ContractType;

/**
 *
 * @author Buddhini
 */
public interface IReportDAO {
     public List<SearchCols> findAllReportFields() throws PersistenceException;
     public List<ReportResultsDTO> searchReport(ReportSearchDTO searchDTO) throws PersistenceException;
}
