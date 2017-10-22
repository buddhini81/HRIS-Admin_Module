package com.ha.presentation.util;

import com.ha.util.Constants.LoginErrorCodes;

public class PresentationConstants {
	
    public static final int LOGIN_STATUS_SUCCESS = 1;
    public static final int LOGIN_STATUS_INVALID_CREDENTIALS = 3;
    public static final int LOGIN_STATUS_INTERNAL_ERROR = 5;
    
    public static final Long LIST_DEFAULT_VALUE = -1L;
    
    public static final int PAGE_STATUS_SUCCESS = 1;
    public static final int PAGE_STATUS_HAS_VALIDATION_ERRORS = 3;
    public static final int PAGE_STATUS_INTERNAL_ERROR = 5;
    
    public static final Long FUNC_DID_ADD_NEW_EMPLOYEE = 100L;
    public static final Long FUNC_DID_EDIT_EMPLOYEE = 101L;
    public static final Long FUNC_DID_VIEW_EMPLOYEE = 102L;
    public static final Long FUNC_DID_SEARCH_EMPLOYEE = 103L;
    public static final Long FUNC_DID_VIEW_EMPLOYEE_HISTORY = 104L;
    public static final Long FUNC_DID_ADD_EMPLOYEE_HISTORY = 105L;
    public static final Long FUNC_DID_DELETE_EMPLOYEE_HISTORY = 106L;
    public static final Long FUNC_DID_ADD_NEW_USER = 107L;
    public static final Long FUNC_DID_CHANGE_USER_PASSWORD = 108L;
    public static final Long FUNC_DID_ADD_EMPLOYEE_QUALIFICATIONS = 109L;
    public static final Long FUNC_DID_ADD_EMPLOYEE_SKILLS = 110L;
    public static final Long FUNC_DID_ADD_EMPLOYEE_PROPERTY_ASSIGNMENT = 111L;
    public static final Long FUNC_DID_ADD_EMPLOYEE_RESIGNATION_AND_CLEARENCE = 112L;
    
    public static final Long USER_ROLE_DID_ADMIN = 100L;
    public static final Long USER_ROLE_DID_NON_ADMIN = 200L;
    
    public static final Long QUALIFICATION_TYPE_DID_ACADEMIC = 100L; 
    public static final Long QUALIFICATION_TYPE_DID_PROFESSIONAL = 101L; 
    
    public static final Long SKILL_TYPE_DID_MANAGEMENT = 200L;
    public static final Long SKILL_TYPE_DID_COMPUTER = 201L;
    public static final Long SKILL_TYPE_DID_OTHER = 202L;
}
