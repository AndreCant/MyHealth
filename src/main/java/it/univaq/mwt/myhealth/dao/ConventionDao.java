package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.domain.Convention;

public interface ConventionDao {
	
	public List<Convention> findAll();
	
	public Convention findById(Long uid);
   
	public void save(Convention convention);
 
	public void update(Convention convention);
  
	public void delete(Long uid);
}
