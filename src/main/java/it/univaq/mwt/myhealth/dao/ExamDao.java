package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.domain.Exam;

public interface ExamDao {
	
	public List<Exam> findAll() throws DaoException;
	
	public List<Exam> findByType(String type) throws DaoException;
	
	public List<Exam> findBySpecialization(String specialization) throws DaoException;
	
	public Exam findById(Long uid) throws DaoException;
   
	public void save(Exam exam) throws DaoException;
 
	public void update(Exam exam) throws DaoException;
  
	public void delete(Long uid) throws DaoException;
}
