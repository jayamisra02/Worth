package com.networthcalculator.calculator.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;


@Entity
@Table(name = "liabilities")
public class Liabilities {
		
	@Id
	@Column(name = "liabilityName", nullable = false)
	private String liabilityName;
	
	@Column(name = "liabilityValue", nullable = false)
	private BigDecimal liabilityValue;
	
	@Version
    private Long version;
	
	public String getLiabilityName() {
		return liabilityName;
	}
	public void setLiabilityName(String liabilityName) {
		this.liabilityName = liabilityName;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public BigDecimal getLiabilityValue() {
		return liabilityValue;
	}
	public void setLiabilityValue(BigDecimal liabilityValue) {
		this.liabilityValue = liabilityValue;
	}
}
