package com.networthcalculator.calculator.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.networthcalculator.calculator.model.Liabilities;

@Repository
public interface LiabilitiesRepo extends JpaRepository<Liabilities, Long> {

	
	@Query("Select sum(liabilityValue) from Liabilities")
	 Double getTotalLiabilities();
	
	@Transactional
	 @Modifying
		@Query("Delete from Liabilities c where c.liabilityName =:liabilityName ")
		void deleteByLiabilityName(@Param("liabilityName") String liabilityName);
}
