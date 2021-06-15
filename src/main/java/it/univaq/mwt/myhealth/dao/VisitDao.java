package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.domain.Visit;

public interface VisitDao {
	
	public List<Visit> findAll();
	
	public Visit findById(Long uid);
   
	public void save(Visit visit);
 
	public void update(Visit visit);
  
	public void delete(Long uid);
}
