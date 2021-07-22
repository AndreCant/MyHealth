package it.univaq.mwt.myhealth.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.dao.ExpenseDao;
import it.univaq.mwt.myhealth.domain.Expense;

@Repository
public class ExpenseDaoImpl implements ExpenseDao{
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;

	@Override
	public List<Expense> findAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expense findById(Long uid) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Expense expense) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Expense expense) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long uid) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveAll(List<Expense> expenses) throws DaoException {
		for (Expense expense : expenses) {
			entityManager.persist(expense);
		}
	}

}
