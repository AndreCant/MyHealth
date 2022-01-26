package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.domain.Visit;

public interface VisitDao {
	
	public List<Visit> findAll() throws DaoException;
	
	public List<Visit> findByReservation(Long id) throws DaoException;
	
	public Visit findById(Long uid) throws DaoException;
   
	public void save(Visit visit) throws DaoException;
 
	public void update(Visit visit) throws DaoException;
  
	public void delete(Long uid) throws DaoException;
	
	public void saveAll(List<Visit> visits) throws DaoException;

	public List<Visit> findByDoctor(Long id)  throws DaoException;
	
	public List<Visit> findByDiagnosis(Long diagnosisId)  throws DaoException;
	
	public List<Visit> findByPatient(Long id) throws DaoException;
}
