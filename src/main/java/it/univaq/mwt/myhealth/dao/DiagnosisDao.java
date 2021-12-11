package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.domain.Diagnosis;

public interface DiagnosisDao {
	
	public List<Diagnosis> findAll() throws DaoException;
	
	public Diagnosis findById(Long uid) throws DaoException;
  
	public void delete(Long uid) throws DaoException;
	
	public void saveAll(List<Diagnosis> diagnosis) throws DaoException;
}
