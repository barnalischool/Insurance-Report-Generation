package com.insurance.report.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.insurance.report.entity.Citizens;
import com.insurance.report.model.SearchRequest;

public interface CitizenReportGenerationService {
	
	public List<String> getAllUniquePlanNames();
	
	public List<String> getAllUniquePlanStatus();
	
	public List<String> getAllUniqueGender();
	
	public List<Citizens> search(SearchRequest data);
	
	public void exportToExcel(HttpServletResponse response, File file) throws Exception;
	
	public void exportToPdf(HttpServletResponse response, File file) throws Exception;

}
