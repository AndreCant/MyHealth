package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.domain.Expense;

public interface ExpenseDao {
	
	public List<Expense> findAll() throws DaoException;
	
	public void saveAll(List<Expense> expenses) throws DaoException;
}
