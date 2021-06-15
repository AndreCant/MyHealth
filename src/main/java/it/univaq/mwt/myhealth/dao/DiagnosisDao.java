package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.domain.Diagnosis;

public interface DiagnosisDao {
	
	public List<Diagnosis> findAll();
	
	public Diagnosis findById(Long uid);
   
	public void save(Diagnosis diagnosis);
 
	public void update(Diagnosis diagnosis);
  
	public void delete(Long uid);
}
