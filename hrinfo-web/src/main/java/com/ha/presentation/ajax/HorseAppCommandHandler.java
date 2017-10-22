/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ha.presentation.ajax;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;

/**
 *
 * @author Buddhini
 */
public class HorseAppCommandHandler implements AjaxCommandHandler {

    private static final String COMMAND_REGISTER_MEMBER = "registerMember";
    private static final String COMMAND_USER_LOGIN = "userLogin";
    private static final String COMMAND_CHECK_USER_AUTHORIZATION = "checkUserAuthorization";
    private static final String COMMAND_LOGOUT_USER = "logoutUser";
    private static final String COMMAND_LOAD_REGISTRATION_DATA = "loadRegistrationData";
    private static final String COMMAND_ADD_ITEMS_TO_SELECTED_FIELDS="addItemsToSelectedFields";
    private static final String COMMAND_REMOVE_ITEMS_FROM_SELECTED_FIELDS="removeItemsFromSelectedFields";
    private static final String COMMAND_LOAD_COMPANY_SPECIFIC_DATA = "loadCompanySpecificData";
    private static final String COMMAND_LOAD_DEPARTMENTS = "loadDepartments";
    private static final String COMMAND_RESET_EMPLOYEE_ADD = "resetEmployeeAdd";
    private static final String COMMAND_LOAD_USERS = "loadUsers";
    private static final String COMMAND_CHECK_EXISTING_EPFNO = "checkEPFNumber";
    private static final String COMMAND_CHECK_USERNAME = "checkUserName";
    private static final String COMMAND_CHECK_EXISTING_NICNO = "checkNICNumber";
    
    private static final String COMMAND_TEST_JQ_DATA_TABLE = "testJQDataTable";



    public String[] getValidCommands() {
        return new String[]{
                    COMMAND_REGISTER_MEMBER,
                    COMMAND_USER_LOGIN,
                    COMMAND_CHECK_USER_AUTHORIZATION,
                    COMMAND_LOGOUT_USER,
                    COMMAND_LOAD_REGISTRATION_DATA,
                    COMMAND_ADD_ITEMS_TO_SELECTED_FIELDS,
                    COMMAND_REMOVE_ITEMS_FROM_SELECTED_FIELDS,
                    COMMAND_LOAD_COMPANY_SPECIFIC_DATA,
                    COMMAND_LOAD_DEPARTMENTS,
                    COMMAND_RESET_EMPLOYEE_ADD,
                    COMMAND_LOAD_USERS,
                    COMMAND_CHECK_EXISTING_EPFNO,
                    COMMAND_CHECK_USERNAME,
                    COMMAND_CHECK_EXISTING_NICNO,
                    COMMAND_TEST_JQ_DATA_TABLE
                };
    }

    public AjaxResponse executeCommand(HttpServletRequest request, String commandName, Map parameters) {
        try {
            
            if(commandName.equals(COMMAND_ADD_ITEMS_TO_SELECTED_FIELDS)) {
               
            }else if(commandName.equals(COMMAND_REMOVE_ITEMS_FROM_SELECTED_FIELDS)) {
               
            } else if(commandName.equals(COMMAND_LOAD_DEPARTMENTS)) {
                HorseAppAjaxController controller = new HorseAppAjaxController();
                JSONObject response = controller.loadDepartments(request);
                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
            } else if(commandName.equals(COMMAND_LOAD_COMPANY_SPECIFIC_DATA)) {
                HorseAppAjaxController controller = new HorseAppAjaxController();
                JSONObject response = controller.loadCompanySpecificData(request);
                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
            } else if(commandName.equals(COMMAND_RESET_EMPLOYEE_ADD)) {
               
            } else if(commandName.equals(COMMAND_LOAD_USERS)) {
                HorseAppAjaxController controller = new HorseAppAjaxController();
                JSONObject response = controller.loadUsers(request);
                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
            } else if(commandName.equals(COMMAND_CHECK_EXISTING_EPFNO)) {
                HorseAppAjaxController controller = new HorseAppAjaxController();
                JSONObject response = controller.checkEPFNumber(request);
                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
            } else if(commandName.equals(COMMAND_CHECK_USERNAME)) {
                HorseAppAjaxController controller = new HorseAppAjaxController();
                JSONObject response = controller.checkUserName(request);
                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
            } else if(commandName.equals(COMMAND_CHECK_EXISTING_NICNO)) {
                HorseAppAjaxController controller = new HorseAppAjaxController();
                JSONObject response = controller.checkNICNumber(request);
                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
            } else if(commandName.equals(COMMAND_CHECK_EXISTING_NICNO)) {
                HorseAppAjaxController controller = new HorseAppAjaxController();
                JSONObject response = controller.testJQDataTable(request);
                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
            }

        } catch (Exception e) {

            return null;
        }

        return null;
    }
}
