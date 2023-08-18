package com.insurance.report.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.insurance.report.entity.Citizens;
import com.insurance.report.model.SearchRequest;
import com.insurance.report.service.CitizenReportGenerationService;

@Controller
public class InsuranceReportController {

	@Autowired
	private CitizenReportGenerationService service;
	
	@GetMapping("/")
	public String showHomePage(@ModelAttribute("searchRequest") SearchRequest searchRequest, Map<String,Object> map) {
		
		init(map);
		
		System.out.println("=============== Controller : showHomePage() method =================");
		System.out.println(searchRequest);
		
		//show LVN
		return "index";
	}
	
	
	@PostMapping("/search")
	public String handleSearch(@ModelAttribute("searchRequest") SearchRequest searchRequest, Map<String,Object> map) {
		
		System.out.println("=============== Controller : handleSearch() method =================");
		System.out.println(searchRequest);
		
		
		//send data from controller to index.jsp
		init(map);
		
		//in Map<String,Object> map object, SearchRequest object will go back to index.jsp with key "search"
		
		//get all records as per search data
		List<Citizens> citizens = service.search(searchRequest);
		map.put("listCitizens", citizens);
		
		return "index";
		
	}
	
	@GetMapping("/export")
	public void handleExcelExport(HttpServletResponse response) throws Exception {
		
		File file = new File("plans.xls");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;fileName=plans.xls");
		service.exportToExcel(response, file);
		
		//delete the locally created file so that duplicate file will not get create
		file.delete();
		
	}
	
	@GetMapping("/pdf")
	public void handlePdfExport(HttpServletResponse response) throws Exception {
		
		File file = new File("plans.pdf");
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment;fileName=plans.pdf");
		service.exportToPdf(response, file);
		
		//delete the locally created file so that duplicate file will not get create
		file.delete();
		
	}
	
	
	private void init(Map<String,Object> map) {
		map.put("planNameList", service.getAllUniquePlanNames());
		map.put("planStatusList", service.getAllUniquePlanStatus());
		map.put("genderList", service.getAllUniqueGender());
	}
	
	
}
