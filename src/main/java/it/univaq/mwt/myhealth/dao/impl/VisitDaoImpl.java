package it.univaq.mwt.myhealth.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.dao.VisitDao;
import it.univaq.mwt.myhealth.domain.User;
import it.univaq.mwt.myhealth.domain.Visit;

@Repository
public class VisitDaoImpl implements VisitDao{
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;


	@Override
	public List<Visit> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Visit findById(Long uid) {
		return (Visit) entityManager.find(Visit.class, uid);
	}

	@Override
	public void save(Visit visit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Visit visit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long uid) {
		// TODO Auto-generated method stub
		
	}

}
