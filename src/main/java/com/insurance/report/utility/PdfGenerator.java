package com.insurance.report.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.insurance.report.entity.Citizens;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component
public class PdfGenerator {

	public void generate(HttpServletResponse response, List<Citizens> citizens, File file ) throws Exception {
		
		// step 1: creation of a document-object
        Document document = new Document(PageSize.A4,  10, 10, 10, 10);
        
        //write file to browser
        PdfWriter.getInstance(document, response.getOutputStream());
        
        //write file to local file-system
        PdfWriter.getInstance(document, new FileOutputStream(file));
        
        document.open();
            
        Paragraph p1 = new Paragraph("Citizens Plan");
        p1.getFont().setStyle(Font.BOLD | Font.UNDERLINE);
        p1.setAlignment(Paragraph.ALIGN_CENTER);
        
        
        // we add the content
        document.add(p1);
        
        //create table for records to display
        PdfPTable table = new PdfPTable(7);
        table.addCell("ID");
        table.addCell("Citizen Name");
        table.addCell("Citizen Plan");
        table.addCell("Plan Status");
        table.addCell("Start Date");
        table.addCell("End Date");
        table.addCell("Benefit Amount");
        
        
        for(Citizens citizen : citizens) {
        	table.addCell(citizen.getCitizenId().toString());
            table.addCell(citizen.getCitizenName());
            table.addCell(citizen.getPlanName());
            table.addCell(citizen.getPlanStatus());
            
            if(citizen.getPlanStartDate() != null)
            	table.addCell(citizen.getPlanStartDate().toString());
            else
            	table.addCell("N/A");
            
            if(citizen.getPlanEndDate() != null)
            	table.addCell(citizen.getPlanEndDate().toString());
            else
            	table.addCell("N/A");
            
            if(citizen.getBenefitAmount() != null)
            	table.addCell(citizen.getBenefitAmount().toString());
            else
            	table.addCell("N/A");
            
        }
           
        document.add(table);
        document.close();
		
	}
}
