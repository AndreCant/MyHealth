package it.univaq.mwt.myhealth.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.dao.DaoException;
import it.univaq.mwt.myhealth.dao.ExpenseDao;
import it.univaq.mwt.myhealth.domain.Expense;

@Repository
public class ExpenseDaoImpl implements ExpenseDao{
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Expense> findAll() throws DaoException {
		return (List<Expense>) entityManager.createQuery("FROM Expense").getResultList();
	}

	@Override
	public void saveAll(List<Expense> expenses) throws DaoException {
		for (Expense expense : expenses) {
			entityManager.persist(expense);
		}
	}

}
