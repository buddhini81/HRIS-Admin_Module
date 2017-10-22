/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.presentation.employee;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ha.entity.model.custom.CompanyDTO;
import com.ha.entity.model.custom.EmployeeSearchResultsDTO;
import com.ha.exceptions.BusinessException;
import com.ha.presentation.util.PresentationConstants;
import com.ha.services.EmployeeDelegate;

/**
 *
 * @author Buddhini
 */
public class EmployeeSearchAction extends EmployeeBaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Log log = LogFactory.getLog(EmployeeSearchAction.class);
		
	private String searchDobRangeCheckedValue = "no";
    private String searchJoinDateRangeCheckedValue = "no";
    private String searchConfDateRangeCheckedValue = "no";
    private String searchDueconfDateRangeCheckedValue = "no";
    	
	public String searchEmployee() {
		String forward = "";
        List<Long> functionDids = (List<Long>)sessionMap.get("userFunctions");
		try {

			populatePageData();
			populateCompanyDids();
            List<EmployeeSearchResultsDTO> results = new EmployeeDelegate().searchEmployee(this.employeeSearchDTO);
            sessionMap.put("empSearchResults", results);
            

  		  	

            //
//            int page = 0;
//            String name = "";
//
//            Enumeration paramNames = request.getParameterNames();
//            while (paramNames.hasMoreElements()) {
//                name = (String) paramNames.nextElement();
//                if (name != null && name.startsWith("d-") && name.endsWith("-p")) {
//                    String pageValue = request.getParameter(name);
//                    if (pageValue != null) {
//                        page = Integer.parseInt(pageValue);
//                        reportForm.setPageNumberParamName(name);
//                        reportForm.setPageNumber(page);
//                    }
//                }
//            }
            //
    	    
    	
    	 forward = functionDids.contains(PresentationConstants.FUNC_DID_ADD_NEW_EMPLOYEE) ? "success" : "basic";
			
		 return forward;
        } catch (BusinessException be) {
        	log.error(be);
			forward = functionDids.contains(PresentationConstants.FUNC_DID_ADD_NEW_EMPLOYEE) ? "error" : "basic_error";
			return forward;
        }
    }
	
	public String resetSearchPage() {
        List<Long> functionDids = (List<Long>)sessionMap.get("userFunctions");
		String forward = functionDids.contains(PresentationConstants.FUNC_DID_ADD_NEW_EMPLOYEE) ? "success" : "basic";
		
		try {
			this.employeeSearchDTO.reset();
			sessionMap.remove("empSearchResults");
			populatePageData();
			return forward;
		} catch (BusinessException be) {
        	log.error(be);
			forward = functionDids.contains(PresentationConstants.FUNC_DID_ADD_NEW_EMPLOYEE) ? "error" : "basic_error";
			return forward;
        }
	}
	
	private void populateCompanyDids() {
		List<CompanyDTO> companyData = (List<CompanyDTO>)sessionMap.get("companyData");
		
		for(CompanyDTO dto : companyData) {
			this.employeeSearchDTO.getCompanyDidList().add(dto.getCompanyDid());
		}
		
	}

	public String getSearchDobRangeCheckedValue() {
		return searchDobRangeCheckedValue;
	}

	public void setSearchDobRangeCheckedValue(String searchDobRangeCheckedValue) {
		this.searchDobRangeCheckedValue = searchDobRangeCheckedValue;
	}

	public String getSearchJoinDateRangeCheckedValue() {
		return searchJoinDateRangeCheckedValue;
	}

	public void setSearchJoinDateRangeCheckedValue(
			String searchJoinDateRangeCheckedValue) {
		this.searchJoinDateRangeCheckedValue = searchJoinDateRangeCheckedValue;
	}

	public String getSearchConfDateRangeCheckedValue() {
		return searchConfDateRangeCheckedValue;
	}

	public void setSearchConfDateRangeCheckedValue(
			String searchConfDateRangeCheckedValue) {
		this.searchConfDateRangeCheckedValue = searchConfDateRangeCheckedValue;
	}

	public String getSearchDueconfDateRangeCheckedValue() {
		return searchDueconfDateRangeCheckedValue;
	}

	public void setSearchDueconfDateRangeCheckedValue(
			String searchDueconfDateRangeCheckedValue) {
		this.searchDueconfDateRangeCheckedValue = searchDueconfDateRangeCheckedValue;
	}

}
