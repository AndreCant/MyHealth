package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.domain.PatientConvention;

public interface PatientConventionDao {
	
	public List<PatientConvention> findAll();
	
	public PatientConvention findById(Long uid);
   
	public void save(PatientConvention patientConvention);
 
	public void update(PatientConvention patientConvention);
  
	public void delete(Long uid);
}
