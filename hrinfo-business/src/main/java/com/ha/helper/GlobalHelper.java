package com.ha.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ha.entity.model.custom.CompanyDTO;
import com.ha.entity.model.custom.CompanyPolicyDTO;
import com.ha.exceptions.BusinessException;
import com.ha.services.GlobalDelegate;
import com.ha.util.Constants;



public class GlobalHelper {
	
	public static CompanyDTO loadParentCompany(CompanyDTO loggedUserCompany) throws BusinessException {
		GlobalDelegate globalDelegate = new GlobalDelegate();
		
		CompanyDTO parentCompany = null;
    	if(!loggedUserCompany.getIsSingleCompany()) {
    		if(!loggedUserCompany.getIsParent()) {
    			if(loggedUserCompany.getParentCompanyDid() != null) {
    				parentCompany = globalDelegate.getCompanyByDid(loggedUserCompany.getParentCompanyDid());
    			}
    		}
    	}
    	
    	return parentCompany;
	}
	
	public static List<CompanyDTO> loadChildCompanies(CompanyDTO loggedUserCompany) throws BusinessException {
		GlobalDelegate globalDelegate = new GlobalDelegate();
		
		List<CompanyDTO> childCompanyData = new ArrayList<CompanyDTO>();
    	if(loggedUserCompany.getIsSingleCompany()) {
    		childCompanyData.add(loggedUserCompany);
    	} else {
    		if(loggedUserCompany.getIsParent()) {
    			childCompanyData = globalDelegate.getParentAndChildCompanies(loggedUserCompany.getCompanyDid());	
    		} else {
    			if(loggedUserCompany.getParentCompanyDid() != null) {
    				childCompanyData.add(loggedUserCompany);
    			} else {
    				//check company policy value
    				Map<Long,CompanyPolicyDTO> policies = globalDelegate.getCompanyPoliciesByCompanyDid(loggedUserCompany.getCompanyDid());
    				if(policies != null && policies.size() > 0 && policies.containsKey(Constants.COMPANY_POLICY_DID_SHOW_ALL_COMPANIES)) {
    					CompanyPolicyDTO policy = policies.get(Constants.COMPANY_POLICY_DID_SHOW_ALL_COMPANIES);
    					if(policy.getPolicyValue().equalsIgnoreCase("YES")) {
    						childCompanyData = globalDelegate.findAllCompanies();
    					} else {
    						childCompanyData.add(loggedUserCompany);
    					}
    				} else {
    					childCompanyData.add(loggedUserCompany);
    				}
    			}
    		}
    	}
    	
    	return childCompanyData;
	}
}
