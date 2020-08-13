package com.networthcalculator.calculator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.networthcalculator.calculator.model.Assets;
import com.networthcalculator.calculator.model.Liabilities;
import com.networthcalculator.calculator.repository.AssetsRepo;
import com.networthcalculator.calculator.repository.LiabilitiesRepo;

@Service
public class NetWorthServiceImpl implements NetWorthService{

	@Autowired
	AssetsRepo assetRepo;
	
	@Autowired
	LiabilitiesRepo liabilitiesRepo;

	@Override
	public List<Assets> getAllAssets() {
		return (List<Assets>) assetRepo.findAll();
	}

	@Override
	public Double getTotalAssets() {
		if( assetRepo.getTotalAssets() != null) {
			return  (Double) assetRepo.getTotalAssets();
		}else {
			return (double) 0;
		}
		 
	}

	@Override
	public List<Liabilities> getAllLiabilities() {
		return (List<Liabilities>) liabilitiesRepo.findAll();
	}

	@Override
	public Double getTotalLiabilities() {
		if(liabilitiesRepo.getTotalLiabilities() != null) {
		return liabilitiesRepo.getTotalLiabilities();
		}else {
			return (double) 0;
			}
		}
	

	@Override
	public Double getNetWorth() {
		Double totalAssets = (double) 0;
		Double totalLiabilities = (double) 0;
		
		if(assetRepo.getTotalAssets() != null) {
			totalAssets = assetRepo.getTotalAssets();
		}
		
		if(liabilitiesRepo.getTotalLiabilities() != null) {
			totalLiabilities = liabilitiesRepo.getTotalLiabilities();
		}
				
		double networth = totalAssets-totalLiabilities;
		
		return (double) networth;
	}

	@Override
	public void saveAsset(Assets asset) {
		assetRepo.save(asset);
		
	}


	@Override
	public void updateAsset(Assets asset) {
		assetRepo.save(asset);
		
	}

	@Override
	public void saveLiability(Liabilities liability) {
		liabilitiesRepo.save(liability);
		
	}

	@Override
	public void updateLiability(Liabilities liability) {
		liabilitiesRepo.save(liability);
		
	}

	@Override
	public void deleteLiability(String LiabilityName) {
		liabilitiesRepo.deleteByLiabilityName(LiabilityName);
		
	}

	@Override
	public void deleteAsset(String assetName) {
		assetRepo.deleteByAssetName(assetName);
		
	}
	
	
}
