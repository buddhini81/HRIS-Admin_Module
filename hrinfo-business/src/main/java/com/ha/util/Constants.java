/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ha.util;

/**
 *
 * @author Buddhini
 */
public class Constants {

    public static final String TOBJECT_EMPLOYEE = "EMPLOYEE";

    public static final int USER_ROLE_DID_MEMBER = 100;
    public static final int USER_ROLE_DID_ADMIN = 101;
    
    public static final int EMPLOYEE_SEARCH_CONTEXT_VIEW = 1; 
    public static final int EMPLOYEE_SEARCH_CONTEXT_EDIT = 2; 
     
    public enum ReportOrderByColumns {
    	
    	ORDER_BY_COLUMN_COMPANY(1,"company"),ORDER_BY_COLUMN_DEPARTMENT(2,"department"),ORDER_BY_COLUMN_SENIORITY(3,"seniority"),ORDER_BY_COLUMN_AGE(4,"age"),ORDER_BY_COLUMN_EPFNO(5,"epfno");
    	private int colId;
    	private String colName;
    	
    	
		private ReportOrderByColumns(int colId, String colName) {
			this.colId = colId;
			this.colName = colName;
		}
		public int getColId() {
			return colId;
		}
		public void setColId(int colId) {
			this.colId = colId;
		}
		public String getColName() {
			return colName;
		}
		public void setColName(String colName) {
			this.colName = colName;
		}
    	
    }

    public enum LoginErrorCodes {

        INVALID_LOGIN_DETAILS(200, "Invalid Username or Password"), MEMBER_NOT_APPROVED(201, "Your membership is not yet approved please try again later");
        private long errorId;
        private String errorCode;

        LoginErrorCodes(long errorId, String errorCode) {
            this.errorId = errorId;
            this.errorCode = errorCode;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public long getErrorId() {
            return errorId;
        }

        public void setErrorId(long errorId) {
            this.errorId = errorId;
        }

        public static String getErrorCodeByErrorId(Long errorId) {
            if (errorId == null) {
                return "";
            }
            for (LoginErrorCodes type : LoginErrorCodes.values()) {
                if (errorId.longValue() == type.getErrorId()) {
                    return type.getErrorCode();
                }
            }
            return "";
        }
    }

    public static final Long ATTRIBUTE_GROUP_DID_EMPLOYEE = 100L;
    
    //Attribute subgroups 200 - 250
    public static final Long ATTRIBUTE_SUBGROUP_DID_EMPLOYEE_SPOUSE_INFO = 200L;
    public static final Long ATTRIBUTE_SUBGROUP_DID_EMPLOYEE_CHILDREN_INFO = 201L;
    public static final Long ATTRIBUTE_SUBGROUP_DID_EMPLOYEE_EMERGENCY_CONTACT_INFO = 202L;
    
    //Attribute distributions
    public static final Long ATTRIBUTE_DISTRIBUTION_DID_EMPLOYEE_SPOUSE_NAME = 1000L;
    public static final Long ATTRIBUTE_DISTRIBUTION_DID_EMPLOYEE_SPOUSE_GENDER = 1001L;
    public static final Long ATTRIBUTE_DISTRIBUTION_DID_EMPLOYEE_SPOUSE_DOB = 1002L;
    
    public static final Long ATTRIBUTE_DISTRIBUTION_DID_EMPLOYEE_CHILD_NAME = 2000L;
    public static final Long ATTRIBUTE_DISTRIBUTION_DID_EMPLOYEE_CHILD_GENDER = 2001L;
    public static final Long ATTRIBUTE_DISTRIBUTION_DID_EMPLOYEE_CHILD_DOB = 2002L;
    
    public static final Long ATTRIBUTE_DISTRIBUTION_DID_EMPLOYEE_ECI_CONTACTNAME = 3000L;
    public static final Long ATTRIBUTE_DISTRIBUTION_DID_EMPLOYEE_ECI_CONTACTNUMBER = 3001L;
    public static final Long ATTRIBUTE_DISTRIBUTION_DID_EMPLOYEE_ECI_RELATIONSHIP = 3002L;
    
    //Attribute Identifier
    public static final Long ATTRIBUTE_IDNTIFIER_SPOUSEINFO = 1000L;
    public static final Long ATTRIBUTE_IDNTIFIER_CHILDINFO = 1001L;
    public static final Long ATTRIBUTE_IDNTIFIER_CONTACTINFO = 1002L;


    public static final Long ATTRIBUTE_VALUE_TYPE_STRING = 200L;
    public static final Long ATTRIBUTE_VALUE_TYPE_DATE = 201L;

    public static final Long IDENTIFIER_TYPE_EMPLOYEE_NUMBER = 1L;
    

    //Company Policy Dids
    public static final Long COMPANY_POLICY_DID_SHOW_ALL_COMPANIES = 100L;
    
    //JBoss properties
    public static final String UI_VALIDATION_BYPASS = "hr.hradmin.property.uivalidationbypass";
}
