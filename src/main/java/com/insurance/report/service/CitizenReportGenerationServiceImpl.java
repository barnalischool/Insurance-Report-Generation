package com.insurance.report.service;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.insurance.report.entity.Citizens;
import com.insurance.report.model.SearchRequest;
import com.insurance.report.repo.CitizenPlansRepo;
import com.insurance.report.utility.EmailUtils;
import com.insurance.report.utility.ExcelGenerator;
import com.insurance.report.utility.PdfGenerator;
import com.insurance.report.utility.StringDateFormatter;

@Service
public class CitizenReportGenerationServiceImpl implements CitizenReportGenerationService{

	@Autowired
	private CitizenPlansRepo repo;
	
	@Autowired
	private ExcelGenerator excelGenerator;
	
	@Autowired
	private PdfGenerator pdfGenerator;
	
	@Autowired
	private EmailUtils emailUtils; 
	
	
	@Override
	public List<String> getAllUniquePlanNames() {
		return repo.fetchDistinctPlanNames();
	}

	@Override
	public List<String> getAllUniquePlanStatus() {
		return repo.fetchDistinctPlanStatus();
	}

	@Override
	public List<String> getAllUniqueGender() {
		return repo.fetchDistinctGender();
	}

	@Override
	public List<Citizens> search(SearchRequest searchRequest) {
		
		/**
		 * if entity property is null then record should fetch
		 */
		Citizens entity = new Citizens();
		
		if(searchRequest.getPlanName()!=null && !searchRequest.getPlanName().isEmpty())
			entity.setPlanName(searchRequest.getPlanName());
		
		if(searchRequest.getPlanStatus()!=null && !searchRequest.getPlanStatus().isEmpty())
			entity.setPlanStatus(searchRequest.getPlanStatus());
		
		if(searchRequest.getGender()!=null && !searchRequest.getGender().isEmpty())
			entity.setGender(searchRequest.getGender());
		
		if(searchRequest.getStartDate()!=null && !searchRequest.getStartDate().isBlank()) {
			LocalDate startDate = StringDateFormatter.dateConverter(searchRequest.getStartDate());
			entity.setPlanStartDate(startDate);
		}
		
		if(searchRequest.getEndDate()!=null && !searchRequest.getEndDate().isBlank()) {
			LocalDate endDate = StringDateFormatter.dateConverter(searchRequest.getEndDate());
			entity.setPlanEndDate(endDate);
		}
		
		System.out.println("================Entity ====="+entity);
			
		return repo.findAll(Example.of(entity));
		
	}

	@Override
	public void exportToExcel(HttpServletResponse response, File file) throws Exception{
		
		List<Citizens> citizenList = repo.findAll();
		excelGenerator.generate(response, citizenList, file);
		emailUtils.sendEmail(file);
		
	}
	
	

	@Override
	public void exportToPdf(HttpServletResponse response, File file) throws Exception {
		
		List<Citizens> citizenList = repo.findAll();
		pdfGenerator.generate(response, citizenList, file);
		emailUtils.sendEmail(file);
		
	}

}
