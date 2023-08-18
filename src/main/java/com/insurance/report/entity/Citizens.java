package com.insurance.report.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name="CITIZEN_PLANS_INFO")
public class Citizens {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CITIZEN_ID")
	private Integer citizenId;
	
	@Column(name = "CITIZEN_NAME")
	private String citizenName;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "PLAN_NAME")
	private String planName;
	
	@Column(name = "PLAN_STATUS")
	private String planStatus;
	
	@Column(name = "PLAN_START_DATE")
	private LocalDate planStartDate;
	
	@Column(name = "PLAN_END_DATE")
	private LocalDate planEndDate;
	
	@Column(name = "BENEFIT_AMOUNT")
	private Float benefitAmount;
	
	@Column(name ="DENIAL_REASON")
	private String denialReason;
	
	@Column(name = "TERMINATION_REASON")
	private String terminationReason;
	
	@Column(name = "TERMINATION_DATE")
	private LocalDate terminationDate;
	
	
	
}
