package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.domain.AnnualBudget;

public interface AnnualBudgetDao {
	
	public List<AnnualBudget> findAll() throws DaoException;
	
	public void saveAll(List<AnnualBudget> annualBudgets) throws DaoException;
}
