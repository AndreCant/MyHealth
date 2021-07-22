package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.domain.Expense;

public interface ExpenseDao {
	
	public List<Expense> findAll() throws DaoException;
	
	public Expense findById(Long uid) throws DaoException;
   
	public void save(Expense expense) throws DaoException;
 
	public void update(Expense expense) throws DaoException;
  
	public void delete(Long uid) throws DaoException;
	
	public void saveAll(List<Expense> expenses) throws DaoException;
}
