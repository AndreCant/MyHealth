package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.domain.MedicineDiagnosis;

public interface MedicineDiagnosisDao {
	
	public List<MedicineDiagnosis> findAll() throws DaoException;
	
	public MedicineDiagnosis findById(Long uid) throws DaoException;
   
	public void save(MedicineDiagnosis medicineDiagnosis) throws DaoException;
 
	public void update(MedicineDiagnosis medicineDiagnosis) throws DaoException;
  
	public void delete(Long uid) throws DaoException;
	
	public void saveAll(List<MedicineDiagnosis> medicineDiagnosis) throws DaoException;
}
