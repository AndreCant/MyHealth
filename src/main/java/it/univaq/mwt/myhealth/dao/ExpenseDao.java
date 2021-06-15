package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.domain.Expense;

public interface ExpenseDao {
	
	public List<Expense> findAll();
	
	public Expense findById(Long uid);
   
	public void save(Expense expense);
 
	public void update(Expense expense);
  
	public void delete(Long uid);
}
