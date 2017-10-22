/**
/**
 * Licensed under the Artistic License; you may not use this file
 * except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://displaytag.sourceforge.net/license.html
 *
 * THIS PACKAGE IS PROVIDED "AS IS" AND WITHOUT ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE IMPLIED
 * WARRANTIES OF MERCHANTIBILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 */
package decorators;

import java.awt.Color;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.displaytag.Messages;
import org.displaytag.exception.BaseNestableJspTagException;
import org.displaytag.exception.SeverityEnum;
import org.displaytag.export.BinaryExportView;
import org.displaytag.export.PdfView;
import org.displaytag.model.Column;
import org.displaytag.model.ColumnIterator;
import org.displaytag.model.HeaderCell;
import org.displaytag.model.Row;
import org.displaytag.model.RowIterator;
import org.displaytag.model.TableModel;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;


/**
 * PDF exporter using IText. This class is provided more as an example than as a "production ready" class: users
 * probably will need to write a custom export class with a specific layout.
 * 
 * Added some nice eye candy's and prevent OutOfMemoryExceptions
 * 
 * @author Ivan Markov
 * @author Fabrizio Giustina
 * @author Michael Vogt
 * @version $Revision: 1.2 
 */
public class NicePdfExport implements BinaryExportView
{
	private static final Logger log = Logger.getLogger(NicePdfExport.class);
	
	/* split up the large pdf report into smaller parts */
	private static final int FRAGMENT_SIZE = 512;
	
	//i18n reference to the displaytag properties file
	private static final String HEADER_TEXT = "pdf.export.header";
	private static final String FOOTER_TEXT = "pdf.export.footer";
	
    /**
     * TableModel to render.
     */
    private TableModel model;

    /**
     * export full list?
     */
    private boolean exportFull;

    /**
     * include header in export?
     */
    private boolean header;

    /**
     * decorate export?
     */
    private boolean decorated;

    /**
     * This is the table, added as an Element to the PDF document. It contains all the data, needed to represent the
     * visible table into the PDF
     */
    private Table tablePDF;

    /**
     * The default font used in the document.
     */
    private Font smallFont = null;
        
    private Font headerFont = null;
    
    private ResourceBundle bundle = null;


    /**
     * @see org.displaytag.export.ExportView#setParameters(TableModel, boolean, boolean, boolean)
     */
    public void setParameters(TableModel tableModel, boolean exportFullList, boolean includeHeader,
        boolean decorateValues)
    {
        this.model = tableModel;
        this.exportFull = exportFullList;
        this.header = includeHeader;
        this.decorated = decorateValues;
    }

    /**
     * Initialize the main info holder table.
     * @throws BadElementException for errors during table initialization
     */
    protected void initTable() throws BadElementException
    {
        tablePDF = new Table(this.model.getNumberOfColumns());        
        tablePDF.setDefaultVerticalAlignment(Element.ALIGN_TOP);
        tablePDF.setCellsFitPage(true);
        tablePDF.setWidth(100);

        tablePDF.setPadding(2);
        tablePDF.setSpacing(0);        
        
        if (bundle==null) {
        	//get displaytag ressource bundle
        	bundle = ResourceBundle.getBundle(org.displaytag.properties.TableProperties.LOCAL_PROPERTIES, Locale.getDefault());	
        }        
        
        if (smallFont==null) {
        	smallFont = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL, new Color(0, 0, 0));
        }
        if (headerFont==null) {
        	headerFont = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD, new Color(192, 20, 25));	
        }        

    }

    /**
     * @see org.displaytag.export.BaseExportView#getMimeType()
     * @return "application/pdf"
     */
    public String getMimeType()
    {
        return "application/pdf"; //$NON-NLS-1$
    }

    /**
     * The overall PDF table generator.
     * @throws JspException for errors during value retrieving from the table model
     * @throws BadElementException IText exception
     */
    protected void generatePDFTable(Document document) throws JspException, BadElementException, DocumentException
    {
        if (this.header)
        {
            generateHeaders();
        }
        tablePDF.endHeaders();
        generateRows(document);
    }

    /**
     * @see org.displaytag.export.BinaryExportView#doExport(OutputStream)
     */
    public void doExport(OutputStream out) throws JspException
    {
    	log.debug("EXPORT PDF START");
    	
        try
        {
            // Initialize the table with the appropriate number of columns
            initTable();

            // Initialize the Document and register it with PdfWriter listener and the OutputStream
            Document document = new Document(PageSize.A4.rotate(), 60, 60, 40, 40);
            document.addCreationDate();
            
            String txt = bundle.getString(FOOTER_TEXT);
            HeaderFooter footer = new HeaderFooter(new Phrase(txt, smallFont), true);
            footer.setBorder(Rectangle.NO_BORDER);            
            footer.setAlignment(Element.ALIGN_CENTER);   

            txt = bundle.getString(HEADER_TEXT);
            HeaderFooter header = new HeaderFooter(new Phrase(txt, headerFont), false);
            header.setBorder(Rectangle.NO_BORDER);            
            header.setAlignment(Element.ALIGN_RIGHT);   
          
            PdfWriter.getInstance(document, out);
            document.setFooter(footer);
            document.setHeader(header);
            
            // Fill the virtual PDF table with the necessary data
            document.open();
            generatePDFTable(document);            
            document.close();
            log.debug("EXPORT PDF END");
        }
        catch (Exception e)
        {
        	log.debug("failed to export data as pdf");
            throw new PdfGenerationException(e);
        }
    }

    /**
     * Generates the header cells, which persist on every page of the PDF document.
     * @throws BadElementException IText exception
     */
    protected void generateHeaders() throws BadElementException
    {
        Iterator<HeaderCell> iterator = this.model.getHeaderCellList().iterator();

        while (iterator.hasNext())
        {
            HeaderCell headerCell = iterator.next();
            String columnHeader = headerCell.getTitle();
            if (columnHeader == null)
            {
                columnHeader = StringUtils.capitalize(headerCell.getBeanPropertyName());
            }

            Cell hdrCell = getCell(columnHeader);
            hdrCell.setBackgroundColor(new Color(210, 221, 231));
            hdrCell.setHeader(true);
            tablePDF.addCell(hdrCell);

        }
    }

    /**
     * Generates all the row cells.
     * @throws JspException for errors during value retrieving from the table model
     * @throws BadElementException errors while generating content
     */
    protected void generateRows(Document document) throws JspException, BadElementException, DocumentException
    {
    	log.debug("columns: "+this.model.getNumberOfColumns());
    	log.debug("row: "+this.model.getRowListFull().size());
        // get the correct iterator (full or partial list according to the exportFull field)
        RowIterator rowIterator = this.model.getRowIterator(this.exportFull);                
        int rowCnt = 0;
        Column column = null;
        Object value = null;
        Cell cell = null;
        while (rowIterator.hasNext())
        {
            Row row = rowIterator.next();

            // iterator on columns
            ColumnIterator columnIterator = row.getColumnIterator(this.model.getHeaderCellList());

            while (columnIterator.hasNext())
            {
                column = columnIterator.nextColumn();

                // Get the value to be displayed for the column
                value = column.getValue(this.decorated);
                cell = getCell(ObjectUtils.toString(value));
                
                /* some eyecandy stuff */
                if (rowCnt%2 == 1) {
                	cell.setBackgroundColor(new Color(220, 223, 225));
                	
                }

                tablePDF.addCell(cell);
            }            
                
            /* split up the pdf document to prevent OutOfMemoryExceptions */
            if (rowCnt % FRAGMENT_SIZE == FRAGMENT_SIZE-1) {
            	log.debug("add fragmented table part");
            	document.add(this.tablePDF);
            	initTable();
            	generateHeaders();
            	this.tablePDF.endHeaders();
            }
            
            rowCnt++;
        }
               
        /* add the rest of the table */ 
        document.add(this.tablePDF);
    }

    /**
     * Returns a formatted cell for the given value.
     * @param value cell value
     * @return Cell
     * @throws BadElementException errors while generating content
     */
    private Cell getCell(String value) throws BadElementException
    {
        Cell cell = new Cell(new Chunk(StringUtils.trimToEmpty(value), smallFont));
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setLeading(8);
        cell.setUseAscender(true);
        
        return cell;
    }

    /**
     * Wraps IText-generated exceptions.
     * @author Fabrizio Giustina
     * @version $Revision: 1.2 $ ($Author: mvo $)
     */
    static class PdfGenerationException extends BaseNestableJspTagException
    {

        /**
         * D1597A17A6.
         */
        private static final long serialVersionUID = 899149338534L;

        /**
         * Instantiate a new PdfGenerationException with a fixed message and the given cause.
         * @param cause Previous exception
         */
        public PdfGenerationException(Throwable cause)
        {
            super(PdfView.class, Messages.getString("PdfView.errorexporting"), cause); //$NON-NLS-1$
        }

        /**
         * @see org.displaytag.exception.BaseNestableJspTagException#getSeverity()
         */
        public SeverityEnum getSeverity()
        {
            return SeverityEnum.ERROR;
        }
    }
}