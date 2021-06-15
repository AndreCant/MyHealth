package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.domain.Paycheck;

public interface PaycheckDao {
	
	public List<Paycheck> findAll();
	
	public Paycheck findById(Long uid);
   
	public void save(Paycheck paycheck);
 
	public void update(Paycheck paycheck);
  
	public void delete(Long uid);
}
