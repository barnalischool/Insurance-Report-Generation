package com.insurance.report.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import com.insurance.report.entity.Citizens;

@Component
public class ExcelGenerator {
	
	public void generate(HttpServletResponse response, List<Citizens> citizens, File file ) throws Exception {
		
		//create new workbook
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("Sheet1");
		
		//create Header row and its column values
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Citizen Name");
		headerRow.createCell(2).setCellValue("Plan Name");
		headerRow.createCell(3).setCellValue("Plan Status");
		headerRow.createCell(4).setCellValue("Start Date");
		headerRow.createCell(5).setCellValue("End Date");
		headerRow.createCell(6).setCellValue("Benefit Amount");
			
			
		int rowIndex=1;
		for(Citizens citizen : citizens) {
			Row dataRow = sheet.createRow(rowIndex);
			dataRow.createCell(0).setCellValue(citizen.getCitizenId());
			dataRow.createCell(1).setCellValue(citizen.getCitizenName());
			dataRow.createCell(2).setCellValue(citizen.getPlanName());
			dataRow.createCell(3).setCellValue(citizen.getPlanStatus());
			
			
			 if(citizen.getPlanStartDate() == null)
			     dataRow.createCell(4).setCellValue("N/A"); 
			 else
				 dataRow.createCell(4).setCellValue(citizen.getPlanStartDate().toString());
			  
			  if(citizen.getPlanEndDate() == null)
				  dataRow.createCell(5).setCellValue("N/A"); 
			  else
			     dataRow.createCell(5).setCellValue(citizen.getPlanEndDate().toString());
			  
			 if(citizen.getBenefitAmount() == null)
			     dataRow.createCell(6).setCellValue("N/A"); 
			 else
				 dataRow.createCell(6).setCellValue(citizen.getBenefitAmount());
			 
			
			rowIndex++;
		}
			
		//if you want to write in a file of server then below code
		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
		
		//if you want to send the file in response
		ServletOutputStream responseOutputStream = response.getOutputStream();
		workbook.write(responseOutputStream);
		
		
		workbook.close();
		
		
	}
	
	

}
