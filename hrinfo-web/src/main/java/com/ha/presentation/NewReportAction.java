package com.ha.presentation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;   
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.ha.entity.model.custom.ContractTypeDTO;
import com.ha.entity.model.custom.ReportResultsDTO;
import com.ha.entity.model.custom.ReportSearchDTO;
import com.ha.entity.model.custom.SearchCols;
import com.ha.exceptions.BusinessException;
import com.ha.presentation.util.SelectBoxDataHolder;
import com.ha.services.GlobalDelegate;
import com.ha.services.ReportDelegate;
import com.ha.util.Constants;
import com.ha.util.Util;
import com.opensymphony.xwork2.ActionSupport;


public class NewReportAction extends ActionSupport implements SessionAware, ServletRequestAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	
	private Map<String, Object> sessionMap;
	
	private String firstName;
    private String lastName;
    private Date dateOfBirthFrom;
    private Date dateOfBirthTo;
    private String empNumber;
    private String designation;
    private String department;
    private String company;
    private String epfNumber;
    private String nicNumber;
    private Date joinDateFrom;
    private Date joinDateTo;
    private Date confDateFrom;
    private Date confDateTo;
    private Date dueConfDateFrom;
    private Date dueConfDateTo;
    private Long contractTypeDid;
    private String gender;
    private String sortBy;
    private String sortOrder;
    private String searchDobRangeCheckedValue = "no";
    private String searchJoinDateRangeCheckedValue = "no";
    private String searchConfDateRangeCheckedValue = "no";
    private String searchDueconfDateRangeCheckedValue = "no";
    private List<SelectBoxDataHolder> genderData = new ArrayList<SelectBoxDataHolder>();
    private List<SelectBoxDataHolder> contractTypeData = new ArrayList<SelectBoxDataHolder>();
    private List<SelectBoxDataHolder> sortByData = new ArrayList<SelectBoxDataHolder>();
    private List<SelectBoxDataHolder> sortingOrderData = new ArrayList<SelectBoxDataHolder>();



    private String boxOneSelected;
    private String boxTwoSelected;
    private List<SearchCols> boxOneData = new ArrayList<SearchCols>();
    private List<SearchCols> boxTwoData = new ArrayList<SearchCols>();
    private Map<String,SearchCols> searchColsMap = new HashMap<String,SearchCols>();
    private List<ReportResultsDTO> results = new ArrayList<ReportResultsDTO>();
    
    public String testAjax() {
    	String selectedValuesInBoxOne = request.getParameter("boxOneSelectedValues");
    	String selectedValuesInBoxTwo = request.getParameter("boxTwoSelectedValues");
    	
    	sessionMap.put("selectedValuesInBoxOne", selectedValuesInBoxOne);
    	sessionMap.put("selectedValuesInBoxTwo", selectedValuesInBoxTwo);
    	
        return "success";
    }

	
    public String loadReport() {

        try {
        	sessionMap.remove("selectedValuesInBoxOne");
        	sessionMap.remove("selectedValuesInBoxTwo");
        	sessionMap.remove("results");
        	
        	populatePageData();
        } catch (BusinessException be) {
            return "error";
        }

        return "success";
    }
    
    public String searchReport() {
        try {
            
        	populatePageData();
        	populateSelectedSearchResultColumns();  		  
  		  	
	  		ReportSearchDTO searchDTO = new ReportSearchDTO();
	        populateReportSearchDTO(searchDTO);
	        List<ReportResultsDTO> results = new ReportDelegate().searchReport(searchDTO);
	            
	        sessionMap.put("results", results);
  		  	

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
        } catch (BusinessException be) {
            return "error";
        }
        return "success";
    }
    
    public String resetPage() {
 	   reset();
 	   return loadReport();
 	}

    
	private void populatePageData() throws BusinessException {

		String selectedValuesInBoxOne = sessionMap.get("selectedValuesInBoxOne") != null ? (String) sessionMap.get("selectedValuesInBoxOne") : null;
		String selectedValuesInBoxTwo = sessionMap.get("selectedValuesInBoxTwo") != null ? (String) sessionMap.get("selectedValuesInBoxTwo") : null;
		
		if (Util.isNotEmpty(selectedValuesInBoxOne)) {
			this.boxOneSelected = selectedValuesInBoxOne;
		}
		
		if (Util.isNotEmpty(selectedValuesInBoxTwo)) {
			this.boxTwoSelected = selectedValuesInBoxTwo;
		}

		List<SearchCols> data = new ReportDelegate().findAllReportFields();

		if (data != null && data.size() > 0) {
			for (SearchCols s : data) {
				this.searchColsMap.put(s.getFieldName(), s);
			}

			if (Util.isEmpty(this.boxTwoSelected)) {
				// Populate only Box1 data when there are no selected items in Box2
				this.setBoxOneData(data);
			} else {
				// Populate Box1 & Box2 data when there are selected items in Box2
				String[] arrBoxOneSelected = Util.isNotEmpty(this.boxOneSelected) ? this.boxOneSelected.split(",") : null;
				String[] arrBoxTwoSelected = Util.isNotEmpty(this.boxTwoSelected) ? this.boxTwoSelected.split(",") : null;

				if (arrBoxOneSelected != null && arrBoxOneSelected.length > 0) {
					for (String ele : arrBoxOneSelected) {
						this.boxOneData.add(this.searchColsMap.get(ele.trim()));
					}
				}
				if (arrBoxTwoSelected != null && arrBoxTwoSelected.length > 0) {
					for (String ele : arrBoxTwoSelected) {
						this.boxTwoData.add(this.searchColsMap.get(ele.trim()));
					}
				}
			}
		}

		if (this.genderData.size() == 0) {
			List<String> genderData = new ArrayList<String>();
			genderData.add("Male");
			genderData.add("Female");
			this.setGenderData(genderData);
		}
		if (this.contractTypeData.size() == 0) {
			List<ContractTypeDTO> contractTypeData = new GlobalDelegate()
					.findAllContractTypes();
			if (contractTypeData != null && contractTypeData.size() > 0) {
				this.setContractTypeData(contractTypeData);
			}
		}
		if(this.sortByData.size() == 0) {
			setSortByData();
		}
		if(this.sortingOrderData.size() == 0) {
			setSortingOrderData();
		}
	}
    
    private void populateSelectedSearchResultColumns() {
    	String[] arrSelectedCols = Util.isNotEmpty(this.boxTwoSelected) ? this.boxTwoSelected.split(",") : this.boxOneSelected.split(",");
    	List<SearchCols> selectedColList = null;
    	
    	if(arrSelectedCols != null && arrSelectedCols.length > 0) {
    		selectedColList = new ArrayList<SearchCols>();
	    	for(String ele : arrSelectedCols) {
	    		selectedColList.add(this.searchColsMap.get(ele.trim()));
	    	}
	    	
	        
			request.setAttribute("selectedColList", selectedColList);
    	}
    }
    
    private void populateReportSearchDTO(ReportSearchDTO reportSearchDTO) {
        if(Util.isNotEmpty(this.firstName)) {
            reportSearchDTO.setFirstName(this.firstName);
        }

        if(Util.isNotEmpty(this.lastName)) {
            reportSearchDTO.setLastName(this.lastName);
        }

        if(Util.isNotEmpty(this.gender) && !this.gender.equals("-1")) {
            reportSearchDTO.setGender(this.gender);
        }

        if(Util.isNotEmpty(this.empNumber)) {
            reportSearchDTO.setEmployeeNo(this.empNumber);
        }

        if(Util.isNotEmpty(this.epfNumber)) {
            reportSearchDTO.setEpfNo(this.epfNumber);
        }

        if(Util.isNotEmpty(this.nicNumber)) {
            reportSearchDTO.setNicNo(this.nicNumber);
        }

        if(Util.isNotEmpty(this.designation)) {
            reportSearchDTO.setDesignation(this.designation);
        }

        if(Util.isNotEmpty(this.company)) {
            reportSearchDTO.setCompanyName(this.company);
        }

        if(Util.isNotEmpty(this.department)) {
            reportSearchDTO.setDepartmentCode(this.department);
        }
        if(Util.isNotEmpty(this.sortBy)) {
        	reportSearchDTO.setSortBy(this.sortBy);
        } else {
        	reportSearchDTO.setSortBy(String.valueOf(Constants.ReportOrderByColumns.ORDER_BY_COLUMN_EPFNO.getColId()));
        }

        if(Util.isNotEmpty(this.sortOrder)) {
        	reportSearchDTO.setSortOrder(this.sortOrder);
        } else{
        	reportSearchDTO.setSortOrder("ASC");
        }
        

        if(this.contractTypeDid != null && this.contractTypeDid.longValue() != -1L) {
            reportSearchDTO.setContractTypeDid(this.contractTypeDid);
        }

        if(this.searchDobRangeCheckedValue.equalsIgnoreCase("yes")) {
            if(Util.isNotEmpty(this.dateOfBirthFrom)) {
                reportSearchDTO.setDobFrom(this.dateOfBirthFrom);
            }
            if(Util.isNotEmpty(this.dateOfBirthTo)) {
                reportSearchDTO.setDobTo(this.dateOfBirthTo);
            }
        } else if(this.searchDobRangeCheckedValue.equalsIgnoreCase("no")) {
            if(Util.isNotEmpty(this.dateOfBirthFrom)) {
                reportSearchDTO.setDobFrom(this.dateOfBirthFrom);
            }
        }

        if(this.searchJoinDateRangeCheckedValue.equalsIgnoreCase("yes")) {
            if(Util.isNotEmpty(this.joinDateFrom)) {
                reportSearchDTO.setDateJoinedFrom(this.joinDateFrom);
            }
            if(Util.isNotEmpty(this.joinDateTo)) {
                reportSearchDTO.setDateJoinedTo(this.joinDateTo);
            }
        } else if(this.searchJoinDateRangeCheckedValue.equalsIgnoreCase("no")) {
             if(Util.isNotEmpty(this.joinDateFrom)) {
                reportSearchDTO.setDateJoinedFrom(this.joinDateFrom);
            }
        }

        if(this.searchDueconfDateRangeCheckedValue.equals("yes")) {
            if(Util.isNotEmpty(this.dueConfDateFrom)) {
                reportSearchDTO.setDueConfirmDateFrom(this.dueConfDateFrom);
            }
            if(Util.isNotEmpty(this.dueConfDateTo)) {
                reportSearchDTO.setDueConfirmDateTo(this.dueConfDateTo);
            }
        } else if(this.searchDueconfDateRangeCheckedValue.equals("no")) {
            if(Util.isNotEmpty(this.dueConfDateFrom)) {
                reportSearchDTO.setDueConfirmDateFrom(this.dueConfDateFrom);
            }
        }

        if(this.searchConfDateRangeCheckedValue.equals("yes")) {
            if(Util.isNotEmpty(this.confDateFrom)) {
                reportSearchDTO.setConfirmDateFrom(this.confDateFrom);
            }
            if(Util.isNotEmpty(this.confDateTo)) {
                reportSearchDTO.setConfirmDateTo(this.confDateTo);
            }
        } else if(this.searchConfDateRangeCheckedValue.equals("no")) {
            if(Util.isNotEmpty(this.confDateFrom)) {
                reportSearchDTO.setConfirmDateFrom(this.confDateFrom);
            }
        }

    }
    
    private void validateReportSearchData() {
    	if(searchDobRangeCheckedValue.equalsIgnoreCase("yes")) {
            if(Util.isEmpty(dateOfBirthFrom)) {
              addFieldError("dateOfBirthFrom", "Date of Birth From is required");
            }
            if(Util.isEmpty(dateOfBirthTo)) {
              addFieldError("dateOfBirthTo", "Date of Birth To is required");        
            }
          }

          if(searchJoinDateRangeCheckedValue.equalsIgnoreCase("yes")) {
            if(Util.isEmpty(joinDateFrom)) {
              addFieldError("joinDateFrom", "Date Joined From is required");
            }
            if(Util.isEmpty(joinDateTo)) {
              addFieldError("joinDateTo", "Date Joined To is required");
            }
          }
          
          if(searchConfDateRangeCheckedValue.equalsIgnoreCase("yes")) {
            if(Util.isEmpty(confDateFrom)) {
              addFieldError("confDateFrom", "Confirmation Date From is required");
            }
            if(Util.isEmpty(confDateTo)) {
              addFieldError("confDateTo", "Confirmation Date To is required");
            }
          }

          if(searchDueconfDateRangeCheckedValue.equalsIgnoreCase("yes")) {
            if(Util.isEmpty(dueConfDateFrom)) {
              addFieldError("dueConfDateFrom", "Due Confirmation Date From is required");
            }
            if(Util.isEmpty(dueConfDateTo)) {
              addFieldError("dueConfDateTo", "Due Confirmation Date From is required");             
            }
          }

          if(Util.isNotEmpty(dateOfBirthFrom)) {
              if(!Util.isValidDate(dateOfBirthFrom,"dd-MM-yyyy")) {
            	 addFieldError("dateOfBirthFrom", "Date of Birth From is invalid"); 
              }
          }
          if(Util.isNotEmpty(dateOfBirthTo)) {
              if(!Util.isValidDate(dateOfBirthTo,"dd-MM-yyyy")) {
            	 addFieldError("dateOfBirthTo", "Date of Birth To is invalid");
              }
          }
          if(Util.isNotEmpty(joinDateFrom)) {
              if(!Util.isValidDate(joinDateFrom,"dd-MM-yyyy")) {
            	 addFieldError("joinDateFrom", "Date Joined From is invalid");
              }
          }
          if(Util.isNotEmpty(joinDateTo)) {
              if(!Util.isValidDate(joinDateTo,"dd-MM-yyyy")) {
            	 addFieldError("joinDateTo", "Date Joined To is invalid");
              }
          }
          if(Util.isNotEmpty(dueConfDateFrom)) {
              if(!Util.isValidDate(dueConfDateFrom,"dd-MM-yyyy")) {
            	 addFieldError("dueConfDateFrom", "Due Confirmation Date From is invalid");
              }
          }
          if(Util.isNotEmpty(dueConfDateTo)) {
              if(!Util.isValidDate(dueConfDateTo,"dd-MM-yyyy")) {
            	 addFieldError("dueConfDateTo", "Due Confirmation Date To is invalid");
              }
          }
          if(Util.isNotEmpty(confDateFrom)) {
              if(!Util.isValidDate(confDateFrom,"dd-MM-yyyy")) {
            	 addFieldError("confDateFrom", "Confirmation Date From is invalid");
              }
          }
          if(Util.isNotEmpty(confDateTo)) {
              if(!Util.isValidDate(confDateTo,"dd-MM-yyyy")) {
            	 addFieldError("confDateTo", "Confirmation Date To is invalid");
              }
          }

          if(Util.isNotEmpty(nicNumber)) {
              if(!Util.isValidNIC(nicNumber)) {
            	 addFieldError("nicNumber", "NIC is invalid");
              }
          }

          if(Util.isNotEmpty(epfNumber)) {
              if(!Util.isNumeric(epfNumber)) {
            	 addFieldError("epfNumber", "EPF Number is invalid");
              }
          }

    }
    
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getServletRequest() {
		return request;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirthFrom() {
		return dateOfBirthFrom;
	}

	public void setDateOfBirthFrom(Date dateOfBirthFrom) {
		this.dateOfBirthFrom = dateOfBirthFrom;
	}

	public Date getDateOfBirthTo() {
		return dateOfBirthTo;
	}

	public void setDateOfBirthTo(Date dateOfBirthTo) {
		this.dateOfBirthTo = dateOfBirthTo;
	}

	public String getEmpNumber() {
		return empNumber;
	}

	public void setEmpNumber(String empNumber) {
		this.empNumber = empNumber;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEpfNumber() {
		return epfNumber;
	}

	public void setEpfNumber(String epfNumber) {
		this.epfNumber = epfNumber;
	}

	public String getNicNumber() {
		return nicNumber;
	}

	public void setNicNumber(String nicNumber) {
		this.nicNumber = nicNumber;
	}

	public Date getJoinDateFrom() {
		return joinDateFrom;
	}

	public void setJoinDateFrom(Date joinDateFrom) {
		this.joinDateFrom = joinDateFrom;
	}

	public Date getJoinDateTo() {
		return joinDateTo;
	}

	public void setJoinDateTo(Date joinDateTo) {
		this.joinDateTo = joinDateTo;
	}

	public Date getConfDateFrom() {
		return confDateFrom;
	}

	public void setConfDateFrom(Date confDateFrom) {
		this.confDateFrom = confDateFrom;
	}

	public Date getConfDateTo() {
		return confDateTo;
	}

	public void setConfDateTo(Date confDateTo) {
		this.confDateTo = confDateTo;
	}

	public Date getDueConfDateFrom() {
		return dueConfDateFrom;
	}

	public void setDueConfDateFrom(Date dueConfDateFrom) {
		this.dueConfDateFrom = dueConfDateFrom;
	}

	public Date getDueConfDateTo() {
		return dueConfDateTo;
	}

	public void setDueConfDateTo(Date dueConfDateTo) {
		this.dueConfDateTo = dueConfDateTo;
	}

	public Long getContractTypeDid() {
		return contractTypeDid;
	}

	public void setContractTypeDid(Long contractTypeDid) {
		this.contractTypeDid = contractTypeDid;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getSortBy() {
		return sortBy;
	}


	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}


	public String getSortOrder() {
		return sortOrder;
	}


	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
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

	public List<SelectBoxDataHolder> getGenderData() {
		return genderData;
	}

	public void setGenderData(List<String> genderData) {
		this.genderData.add(new SelectBoxDataHolder("-1","---Select---"));
        for(String s : genderData) {
            this.genderData.add(new SelectBoxDataHolder(s.substring(0, 1),s));
        }
	}

	public List<SelectBoxDataHolder> getContractTypeData() {
		return contractTypeData;
	}

	public void setContractTypeData(List<ContractTypeDTO> contractTypeData) {
	        this.contractTypeData.add(new SelectBoxDataHolder(-1,"---Select---"));
	        for(ContractTypeDTO dto : contractTypeData) {
	            this.contractTypeData.add(new SelectBoxDataHolder(dto.getContractTypeDid(),dto.getName()));
	        }
	}
	

	public List<SelectBoxDataHolder> getSortByData() {
		return sortByData;
	}


	public void setSortByData() {
		this.sortByData.add(new SelectBoxDataHolder(Constants.ReportOrderByColumns.ORDER_BY_COLUMN_COMPANY.getColId(),"Company"));
		this.sortByData.add(new SelectBoxDataHolder(Constants.ReportOrderByColumns.ORDER_BY_COLUMN_DEPARTMENT.getColId(),"Department"));
		this.sortByData.add(new SelectBoxDataHolder(Constants.ReportOrderByColumns.ORDER_BY_COLUMN_SENIORITY.getColId(),"Seniority"));
		this.sortByData.add(new SelectBoxDataHolder(Constants.ReportOrderByColumns.ORDER_BY_COLUMN_AGE.getColId(),"Age"));
	}

	
	public List<SelectBoxDataHolder> getSortingOrderData() {
		return sortingOrderData;
	}


	public void setSortingOrderData() {
		this.sortingOrderData.add(new SelectBoxDataHolder("ASC","Ascending"));
		this.sortingOrderData.add(new SelectBoxDataHolder("DESC","Descending"));
	}


	public String getBoxOneSelected() {
		return boxOneSelected;
	}

	public void setBoxOneSelected(String boxOneSelected) {
		this.boxOneSelected = boxOneSelected;
	}

	public String getBoxTwoSelected() {
		return boxTwoSelected;
	}

	public void setBoxTwoSelected(String boxTwoSelected) {
		this.boxTwoSelected = boxTwoSelected;
	}

	public List<SearchCols> getBoxOneData() {
		return boxOneData;
	}

	public void setBoxOneData(List<SearchCols> boxOneData) {
		this.boxOneData = boxOneData;
	}

	public List<SearchCols> getBoxTwoData() {
		return boxTwoData;
	}

	public void setBoxTwoData(List<SearchCols> boxTwoData) {
		this.boxTwoData = boxTwoData;
	}
	
	public Map<String, SearchCols> getSearchColsMap() {
		return searchColsMap;
	}

	public void setSearchColsMap(Map<String, SearchCols> searchColsMap) {
		this.searchColsMap = searchColsMap;
	}
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;		
	}

	public List<ReportResultsDTO> getResults() {
		return results;
	}

	public void setResults(List<ReportResultsDTO> results) {
		this.results = results;
	}
	
	public void reset() {
		this.firstName = null;
	    this.lastName = null;
	    this.dateOfBirthFrom = null;
	    this.dateOfBirthTo = null;
	    this.empNumber = null;
	    this.designation = null;
	    this.department = null;
	    this.company = null;
	    this.epfNumber = null;
	    this.nicNumber = null;
	    this.joinDateFrom = null;
	    this.joinDateTo = null;
	    this.confDateFrom = null;
	    this.confDateTo = null;
	    this.dueConfDateFrom = null;
	    this.dueConfDateTo = null;
	    this.contractTypeDid = null;
	    this.gender = null;
	    this.sortBy = null;
	    this.sortOrder = null;
	    this.searchDobRangeCheckedValue = "no";
	    this.searchJoinDateRangeCheckedValue = "no";
	    this.searchConfDateRangeCheckedValue = "no";
	    this.searchDueconfDateRangeCheckedValue = "no";
	    this.boxOneSelected = null;
	    this.boxTwoSelected = null;
	    this.results.clear();
	}
	
}
