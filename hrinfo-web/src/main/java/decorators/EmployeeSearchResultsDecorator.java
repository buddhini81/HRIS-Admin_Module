/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package decorators;

import com.ha.entity.model.custom.EmployeeSearchResultsDTO;
import com.ha.util.Util;

import org.displaytag.decorator.TableDecorator;

/**
 *
 * @author Buddhini
 */
public class EmployeeSearchResultsDecorator extends TableDecorator {


    public String getEmail() {
      EmployeeSearchResultsDTO dto = (EmployeeSearchResultsDTO)getCurrentRowObject();
      String emailId = "<a href=\"mailto:"+dto.getEmail()+"\">"+dto.getEmail()+ "</a>";
      return emailId;
    }

    public String getDob() {
        EmployeeSearchResultsDTO dto = (EmployeeSearchResultsDTO)getCurrentRowObject();
        String dob =  dto.getDob() != null ? Util.formatDate(dto.getDob()) : "";
        return dob;
    }
}
