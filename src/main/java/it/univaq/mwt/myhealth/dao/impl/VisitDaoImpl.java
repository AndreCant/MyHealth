package it.univaq.mwt.myhealth.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.dao.VisitDao;
import it.univaq.mwt.myhealth.domain.Visit;

@Repository
public class VisitDaoImpl implements VisitDao{
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;

	@Override
	public List<Visit> findAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Visit findById(Long uid) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Visit visit) throws DaoException {
		entityManager.persist(visit);
	}

	@Override
	public void update(Visit visit) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long uid) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveAll(List<Visit> visits) throws DaoException {
		for (Visit visit : visits) {
			entityManager.persist(visit);
		}
	}

}
