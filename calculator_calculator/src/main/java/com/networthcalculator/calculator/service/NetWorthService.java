package com.networthcalculator.calculator.service;

import java.util.List;

import com.networthcalculator.calculator.model.Assets;
import com.networthcalculator.calculator.model.Liabilities;

public interface NetWorthService {
	
List<Assets> getAllAssets();

Double getTotalAssets();

List<Liabilities> getAllLiabilities();

Double getTotalLiabilities();

Double getNetWorth();

void saveAsset(Assets asset);

void deleteAsset(String assetName);

void updateAsset(Assets asset);

void saveLiability(Liabilities liability);

void updateLiability(Liabilities liability);

void deleteLiability(String LiabilityName);

}
