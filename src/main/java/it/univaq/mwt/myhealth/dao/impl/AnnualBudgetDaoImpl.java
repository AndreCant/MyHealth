package it.univaq.mwt.myhealth.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.dao.AnnualBudgetDao;
import it.univaq.mwt.myhealth.domain.AnnualBudget;

@Repository
public class AnnualBudgetDaoImpl implements AnnualBudgetDao{
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;

	@Override
	public List<AnnualBudget> findAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnnualBudget findById(Long uid) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(AnnualBudget annualBudget) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(AnnualBudget annualBudget) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long uid) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveAll(List<AnnualBudget> annualBudgets) throws DaoException {
		for (AnnualBudget annualBudget : annualBudgets) {
			entityManager.persist(annualBudget);
		}
	}

}
