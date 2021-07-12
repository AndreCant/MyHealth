package it.univaq.mwt.myhealth.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.dao.ExamDao;
import it.univaq.mwt.myhealth.domain.Exam;
import it.univaq.mwt.myhealth.domain.Role;
import it.univaq.mwt.myhealth.domain.User;
@Repository
public class ExamDaoImpl implements ExamDao{
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;

	@Override
	public List<Exam> findAll() throws DaoException {
		
		List<Exam> resultList = (List<Exam>) entityManager.createQuery("FROM Exam").getResultList();
		return resultList;
	}

	@Override
	public List<Exam> findByType(String type) throws DaoException {
		return (List<Exam>) entityManager.createQuery("FROM Exam WHERE type = :type", Exam.class)
		        .setParameter("type", type)
		        .getResultList();
	}

	@Override
	public List<Exam> findBySpecialization(String specialization) throws DaoException {
		return (List<Exam>) entityManager.createQuery("FROM Exam WHERE specialization = :specialization", Exam.class)
		        .setParameter("specialization", specialization)
		        .getResultList();
	}
	

	@Override
	public Exam findByName(String name) throws DaoException {
		return (Exam) entityManager.find(Exam.class, name);
	}


	@Override
	public Exam findById(Long uid) throws DaoException {
		return (Exam) entityManager.find(Exam.class, uid);
	}

	@Override
	public void save(Exam exam) throws DaoException {
		entityManager.persist(exam);
	}

	@Override
	public void update(Exam exam) throws DaoException {
		entityManager.merge(exam);
	}

	@Override
	public void delete(Long uid) throws DaoException {
		entityManager.remove(this.findById(uid));
	}

}
