package it.univaq.mwt.myhealth.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import it.univaq.mwt.myhealth.dao.VisitDao;
import it.univaq.mwt.myhealth.domain.User;
=======
import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.dao.VisitDao;
>>>>>>> a26c0e56d9b307c6f8db5422b9cf7752ccb2b507
import it.univaq.mwt.myhealth.domain.Visit;

@Repository
public class VisitDaoImpl implements VisitDao{
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;

<<<<<<< HEAD

	@Override
	public List<Visit> findAll() {
=======
	@Override
	public List<Visit> findAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Visit findById(Long uid) throws DaoException {
>>>>>>> a26c0e56d9b307c6f8db5422b9cf7752ccb2b507
		// TODO Auto-generated method stub
		return null;
	}

	@Override
<<<<<<< HEAD
	public Visit findById(Long uid) {
		return (Visit) entityManager.find(Visit.class, uid);
	}

	@Override
	public void save(Visit visit) {
=======
	public void save(Visit visit) throws DaoException {
		entityManager.persist(visit);
	}

	@Override
	public void update(Visit visit) throws DaoException {
>>>>>>> a26c0e56d9b307c6f8db5422b9cf7752ccb2b507
		// TODO Auto-generated method stub
		
	}

	@Override
<<<<<<< HEAD
	public void update(Visit visit) {
=======
	public void delete(Long uid) throws DaoException {
>>>>>>> a26c0e56d9b307c6f8db5422b9cf7752ccb2b507
		// TODO Auto-generated method stub
		
	}

	@Override
<<<<<<< HEAD
	public void delete(Long uid) {
		// TODO Auto-generated method stub
		
=======
	public void saveAll(List<Visit> visits) throws DaoException {
		for (Visit visit : visits) {
			entityManager.persist(visit);
		}
>>>>>>> a26c0e56d9b307c6f8db5422b9cf7752ccb2b507
	}

}
