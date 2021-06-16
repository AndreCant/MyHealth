package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.domain.MedicineDiagnosis;

public interface MedicineDiagnosisDao {
	
	public List<MedicineDiagnosis> findAll();
	
	public MedicineDiagnosis findById(Long uid);
   
	public void save(MedicineDiagnosis medicineDiagnosis);
 
	public void update(MedicineDiagnosis medicineDiagnosis);
  
	public void delete(Long uid);
}
