package com.cook.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cook.entity.CookPick;


public interface CookPickRepository extends JpaRepository<CookPick, Integer> {
	
	@Query("SELECT t FROM CookPick t WHERE t.productName=?1 AND t.productVendor=?2")
	List<CookPick> fetchCallDetails(String productName, String productVendor);

}
