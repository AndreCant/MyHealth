package it.univaq.mwt.myhealth.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.dao.DaoException;
import it.univaq.mwt.myhealth.dao.VisitDao;
import it.univaq.mwt.myhealth.domain.Exam;
import it.univaq.mwt.myhealth.domain.Reservation;
import it.univaq.mwt.myhealth.domain.User;
import it.univaq.mwt.myhealth.dao.VisitDao;
import it.univaq.mwt.myhealth.domain.Visit;

@Repository
public class VisitDaoImpl implements VisitDao{
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;

	@Override
	public List<Visit> findAll() throws DaoException {
<<<<<<< Updated upstream
		return (List<Visit>) entityManager.createQuery("FROM Visit").getResultList();
=======
		return  (List<Visit>) entityManager.createQuery("FROM Visit").getResultList();
>>>>>>> Stashed changes
	}

	@Override
	public Visit findById(Long uid) {
		return (Visit) entityManager.find(Visit.class, uid);
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
		
	}
		
	public void saveAll(List<Visit> visits) throws DaoException {
		for (Visit visit : visits) {
			entityManager.persist(visit);
		}
	}

	@Override
	public List<Visit> findByReservation(Long id) throws DaoException {
		return (List<Visit>) entityManager.createQuery("FROM Visit WHERE reservation_id = :id", Visit.class)
		        .setParameter("id", id)
		        .getResultList();
	}

	@Override
	public List<Visit> findByDoctor(Long id) throws DaoException {
		return (List<Visit>) entityManager.createQuery("FROM Visit WHERE doctor_id = :id", Visit.class)
		        .setParameter("id", id)
		        .getResultList();
	}

}
