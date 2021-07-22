package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.domain.AnnualBudget;

public interface AnnualBudgetDao {
	
	public List<AnnualBudget> findAll() throws DaoException;
	
	public AnnualBudget findById(Long uid) throws DaoException;
   
	public void save(AnnualBudget annualBudget) throws DaoException;
 
	public void update(AnnualBudget annualBudget) throws DaoException;
  
	public void delete(Long uid) throws DaoException;
	
	public void saveAll(List<AnnualBudget> annualBudgets) throws DaoException;
}
