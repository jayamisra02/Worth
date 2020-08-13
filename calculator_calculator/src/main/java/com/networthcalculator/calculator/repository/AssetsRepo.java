package com.networthcalculator.calculator.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.networthcalculator.calculator.model.Assets;

@Repository
public interface AssetsRepo extends JpaRepository<Assets, Long> {

	
	@Query("Select sum(assetValue) from Assets")
	  Double getTotalAssets();

	@Transactional
	@Modifying
	@Query("Delete from Assets c where c.assetName =:assetName ")
	void deleteByAssetName(@Param("assetName") String assetName);
	
}
