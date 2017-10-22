package decorators;

import org.displaytag.decorator.TableDecorator;

import com.ha.entity.model.custom.EmployeeSearchResultsDTO;
import com.ha.entity.model.custom.ReportResultsDTO;
import com.ha.util.Util;

public class ReportResultsDecorator extends TableDecorator {
	
	public String getConfDate() {
		ReportResultsDTO dto = (ReportResultsDTO)getCurrentRowObject();
        String confDate =  dto.getConfDate() != null ? Util.formatDate(dto.getConfDate()) : "";
        return confDate;
	}

	public String getDateJoined() {
		ReportResultsDTO dto = (ReportResultsDTO)getCurrentRowObject();
        String dateJoined =  dto.getDateJoined() != null ? Util.formatDate(dto.getDateJoined()) : "";
	    return dateJoined;
	}

	public String getDob() {
		ReportResultsDTO dto = (ReportResultsDTO)getCurrentRowObject();
        String dob =  dto.getDob() != null ? Util.formatDate(dto.getDob()) : "";
	    return dob;
	}

	public String getDueConfDate() {
		ReportResultsDTO dto = (ReportResultsDTO)getCurrentRowObject();
        String dueConfDate =  dto.getDueConfDate() != null ? Util.formatDate(dto.getDueConfDate()) : "";
	    return dueConfDate;
	}

	 public String getResigDate() {
		 ReportResultsDTO dto = (ReportResultsDTO)getCurrentRowObject();
	     String resigDate =  dto.getResigDate() != null ? Util.formatDate(dto.getResigDate()) : "";
	     return resigDate;
	 }
}
