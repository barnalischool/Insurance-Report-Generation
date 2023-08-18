package com.insurance.report.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class StringDateFormatter {
	
	public static LocalDate dateConverter(String dateInString) {
		
		System.out.println("Date in string format :: "+dateInString);
		
		//date format ::  2023-07-21
		
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		//LocalDate date = LocalDate.parse(dateInString, formatter);
		
		LocalDate date = LocalDate.parse(dateInString);
		return date;
	}

}
