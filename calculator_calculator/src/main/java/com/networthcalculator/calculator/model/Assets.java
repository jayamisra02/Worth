package com.networthcalculator.calculator.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "assets")
public class Assets {
	
	@Id
	@Column(name = "assetName", nullable = false)
	private String assetName;
	
	@Column(name = "assetValue", nullable = false)
	private BigDecimal assetValue;
	
	@Version
    private Long version;
	
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public BigDecimal getAssetValue() {
		return assetValue;
	}
	public void setAssetValue(BigDecimal assetValue) {
		this.assetValue = assetValue;
	}

}
