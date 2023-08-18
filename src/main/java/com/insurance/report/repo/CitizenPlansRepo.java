package com.insurance.report.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.insurance.report.entity.Citizens;

public interface CitizenPlansRepo extends JpaRepository<Citizens, Integer>{
	
	//find all unique plan names
	@Query("select distinct planName from Citizens")
	List<String> fetchDistinctPlanNames();												
	
	//find all unique plan status
	@Query("select distinct planStatus from Citizens")
	List<String> fetchDistinctPlanStatus();
	
	//find all unique citizen gender
	@Query("select distinct gender from Citizens")
	List<String> fetchDistinctGender();
	
	
}
