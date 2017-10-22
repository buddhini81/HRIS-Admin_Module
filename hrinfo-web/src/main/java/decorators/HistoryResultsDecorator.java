package decorators;

import org.displaytag.decorator.TableDecorator;

import com.ha.entity.model.custom.HistoryDTO;
import com.ha.util.Util;

public class HistoryResultsDecorator extends TableDecorator {

	 public String getDate() {
	      HistoryDTO dto = (HistoryDTO)getCurrentRowObject();
	      String date =  dto.getDate() != null ? Util.formatDate(dto.getDate()) : "";
	      return date;
	 }
}
