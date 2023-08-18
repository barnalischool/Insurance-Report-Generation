package com.insurance.report.model;

import lombok.Data;

/**
 * It is a DTO/command/form binding object
 * @author 91939
 *
 */
@Data
public class SearchRequest{
	
	private String planName;
	private String planStatus;
	private String gender;
	private String startDate;		//in LocalDate, the default format is yyyy-mm-dd ,  but in html page date format is dd-mm-yyyy
	private String endDate;         //in LocalDate, the default format is yyyy-mm-dd,  but in html page date format is dd-mm-yyyy

}
