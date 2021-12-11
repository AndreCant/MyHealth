package it.univaq.mwt.myhealth.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.dao.AnnualBudgetDao;
import it.univaq.mwt.myhealth.dao.DaoException;
import it.univaq.mwt.myhealth.domain.AnnualBudget;

@Repository
public class AnnualBudgetDaoImpl implements AnnualBudgetDao{
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<AnnualBudget> findAll() throws DaoException {
		return (List<AnnualBudget>) entityManager.createQuery("FROM AnnualBudget").getResultList();
	}

	@Override
	public void saveAll(List<AnnualBudget> annualBudgets) throws DaoException {
		for (AnnualBudget annualBudget : annualBudgets) {
			entityManager.persist(annualBudget);
		}
	}

}
