package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.domain.AnnualBudget;

public interface AnnualBudgetDao {
	
	public List<AnnualBudget> findAll();
	
	public AnnualBudget findById(Long uid);
   
	public void save(AnnualBudget annualBudget);
 
	public void update(AnnualBudget annualBudget);
  
	public void delete(Long uid);
}
