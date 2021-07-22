package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.domain.Paycheck;

public interface PaycheckDao {
	
	public List<Paycheck> findAll() throws DaoException;
	
	public Paycheck findById(Long uid) throws DaoException;
   
	public void save(Paycheck paycheck) throws DaoException;
 
	public void update(Paycheck paycheck) throws DaoException;
  
	public void delete(Long uid) throws DaoException;
	
	public void saveAll(List<Paycheck> paychecks) throws DaoException;
}
