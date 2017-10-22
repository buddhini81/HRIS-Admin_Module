package com.ha.presentation.employee;

import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ha.entity.model.custom.HistoryDTO;
import com.ha.exceptions.BusinessException;
import com.ha.services.EmployeeDelegate;
import com.ha.util.Util;

public class EmployeeHistoryAction extends EmployeeBaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(EmployeeHistoryAction.class);
	
	
	public String loadEmployeeHistoryPage() {
		try {
			Long employeeDid = Long.valueOf(request.getParameter("did"));
				
			historyDTO.setEmployeeDid(employeeDid);
			return "success";
		}  catch (Exception e) {
			log.error(e);
        	e.printStackTrace();
			return "error";
		}
	}
	
	public String saveEmployeeHistory() {
		String forward = "success";
		validateEmpHistoryData();
		try {
			if (!hasFieldErrors()) {
				EmployeeDelegate employeeDelegate = new EmployeeDelegate();
				employeeDelegate.saveEmployeeHistory(historyDTO);
				
				this.message = "Details saved successfully!";
				forward = "success";
			}else {
				this.message = "Failed to save details!";
				forward = "error";
			}
		} catch (BusinessException be) {
			log.error(be);
        	be.printStackTrace();
        	this.message = "Failed to save details!";
			return "error";
		} catch (Exception e) {
			log.error(e);
        	e.printStackTrace();
        	this.message = "Failed to save details!";
			return "error";
		}
		return forward;
	}
	
	public String deleteEmployeeHistory() {
		try {
			Long hisDid = Long.valueOf(request.getParameter("hisDid"));
			EmployeeDelegate employeeDelegate = new EmployeeDelegate();
			employeeDelegate.deleteEmployeeHistory(hisDid);
			return searchEmployeeHistory();
			
		} catch (BusinessException be) {
			log.error(be);
        	be.printStackTrace();
			return "error";
		} catch (Exception e) {
			log.error(e);
        	e.printStackTrace();
			return "error";
		}
	}
	
	public String searchEmployeeHistory() {
		try{
			Long employeeDid = Long.valueOf(request.getParameter("did"));
  		  	
            List<HistoryDTO> results = new EmployeeDelegate().getEmployeeHistory(employeeDid);
    	    sessionMap.put("empHistory", results);
    	    
		} catch (BusinessException be) {
			log.error(be);
			be.printStackTrace();
            return "error";
        }
		return "success";
	}
	
    private void validateEmpHistoryData() {
    	if(Util.isEmpty(historyDTO.getDate())) {
    		addFieldError("historyDTO.date", "Date is required");
    	}
    	if(Util.isEmpty(historyDTO.getDescription())) {
    		addFieldError("historyDTO.description", "Description is required");
    	}
    	if (Util.isNotEmpty(historyDTO.getDate())) {
            if (!Util.isValidDate(historyDTO.getDate(), "dd-MM-yyyy")) {
            	addFieldError("historyDTO.date.invalid", "The preffered date format is dd-MM-yyyy");
            }
        }
    }

}
