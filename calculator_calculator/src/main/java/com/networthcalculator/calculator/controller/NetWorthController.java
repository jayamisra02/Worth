package com.networthcalculator.calculator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.networthcalculator.calculator.model.Assets;
import com.networthcalculator.calculator.model.Liabilities;
import com.networthcalculator.calculator.service.NetWorthService;

@RestController
public class NetWorthController {
	
	@Autowired
	NetWorthService netWorthService;
	
	@GetMapping("/assets")
	public List<Assets> getAssets() {
		return netWorthService.getAllAssets();
	}
	
	@RequestMapping(value = "/assets", method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> saveAsset(@RequestBody Assets asset) throws Exception {
		netWorthService.saveAsset(asset);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestMapping(value = "/assets/{assetName}", method = RequestMethod.PUT)
	public ResponseEntity<HttpStatus> updateAsset(@PathVariable(value = "assetName") String assetName, @RequestBody Assets asset) throws Exception {
		netWorthService.updateAsset(asset);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestMapping(value = "/assets/{assetName}", method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> deleteAsset(@PathVariable(value = "assetName") String assetName) throws Exception {
		netWorthService.deleteAsset(assetName);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@GetMapping("/liabilities")
	public List<Liabilities> getLiabilities() {
		return netWorthService.getAllLiabilities();
	}
	
	@RequestMapping(value = "/liabilities", method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> saveLiability(@RequestBody Liabilities liability) throws Exception {
		netWorthService.saveLiability(liability);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/liabilities/{liabilityName}", method = RequestMethod.PUT)
	public ResponseEntity<HttpStatus> updateLiability(@PathVariable(value = "liabilityName") String liabilityName, @RequestBody Liabilities liability) throws Exception {
		netWorthService.updateLiability(liability);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestMapping(value = "/liabilities/{liabilityName}", method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> deleteLiability(@PathVariable(value = "liabilityName") String liabilityName) throws Exception {
		netWorthService.deleteLiability(liabilityName);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@GetMapping("/assets/total")
	public Double getTotalAssets() {
		return netWorthService.getTotalAssets();
	}
	
	@GetMapping("/liabilities/total")
	public Double getTotalLiabilities() {
		return netWorthService.getTotalLiabilities();
	}
	
	@GetMapping("/networth")
	public Double getNetWorth() {
		return netWorthService.getNetWorth();
	}

}
