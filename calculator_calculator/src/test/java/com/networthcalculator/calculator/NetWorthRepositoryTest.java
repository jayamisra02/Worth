package com.networthcalculator.calculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.networthcalculator.calculator.model.Assets;
import com.networthcalculator.calculator.model.Liabilities;
import com.networthcalculator.calculator.repository.AssetsRepo;
import com.networthcalculator.calculator.repository.LiabilitiesRepo;

@DataJpaTest
public class NetWorthRepositoryTest {


	   @Autowired
	   private TestEntityManager entityManager;
	   
	   @Autowired
	   private AssetsRepo assetsRepo;
	   
	   @Autowired
	   private LiabilitiesRepo liabilityRepo;
	   
	   @Test
	   public void whenFindAll() {
	       //given
	       Assets asset1 = new Assets();
	       asset1.setAssetName("asset1");
	       asset1.setAssetValue(new BigDecimal("1234.0"));
	       entityManager.persist(asset1);
	       entityManager.flush();
	       
	       Liabilities liability1 = new Liabilities();
	       liability1.setLiabilityName("liab1");
	       liability1.setLiabilityValue(new BigDecimal("1234.0"));
	       entityManager.persist(liability1);
	       entityManager.flush();
	       //when
	       List<Assets> assets = assetsRepo.findAll();
	       List<Liabilities> liabilities = liabilityRepo.findAll();
	       //then
	       assertThat(assets.size()).isGreaterThan(0);
	       assertThat(liabilities.size()).isGreaterThan(0);
	       assertThat(assets.contains(asset1));
	       assertThat(liabilities.contains(liability1));
	   }
	   
	   @Test
	   public void deleteByRepo() {
	       //given
	       Assets asset1 = new Assets();
	       asset1.setAssetName("asset1");
	       asset1.setAssetValue(new BigDecimal("1234.0"));
	       entityManager.persist(asset1);
	       entityManager.flush();
	       
	       Liabilities liability1 = new Liabilities();
	       liability1.setLiabilityName("liab1");
	       liability1.setLiabilityValue(new BigDecimal("1234.0"));
	       entityManager.persist(liability1);
	       entityManager.flush();
	       //when
	       assetsRepo.delete(asset1);
	       liabilityRepo.delete(liability1);
	       
	       List<Assets> assets = assetsRepo.findAll();
	       List<Liabilities> liabilities = liabilityRepo.findAll();
	       //then
	       
	       assertFalse(assets.contains(asset1));
	       assertFalse(liabilities.contains(liability1));
	   }
	
}
