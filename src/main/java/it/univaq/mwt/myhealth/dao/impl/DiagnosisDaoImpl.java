package it.univaq.mwt.myhealth.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.dao.DaoException;
import it.univaq.mwt.myhealth.dao.DiagnosisDao;
import it.univaq.mwt.myhealth.domain.Diagnosis;
import it.univaq.mwt.myhealth.domain.Exam;

@Repository
public class DiagnosisDaoImpl implements DiagnosisDao{
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;

	@Override
	public List<Diagnosis> findAll() throws DaoException {
		return (List<Diagnosis>) entityManager.createQuery("FROM Diagnosis").getResultList();
	}

	@Override
	public Diagnosis findById(Long uid) throws DaoException {
		return (Diagnosis) entityManager.find(Diagnosis.class, uid);
	}

	@Override
	public void save(Diagnosis diagnosis) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Diagnosis diagnosis) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long uid) throws DaoException {
		entityManager.remove(this.findById(uid));
	}

	@Override
	public void saveAll(List<Diagnosis> diagnosis) throws DaoException {
		for (Diagnosis currDiagnosis : diagnosis) {
			entityManager.persist(currDiagnosis);
		}
	}

}
