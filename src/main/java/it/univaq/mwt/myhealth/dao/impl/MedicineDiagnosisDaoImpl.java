package it.univaq.mwt.myhealth.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.dao.MedicineDiagnosisDao;
import it.univaq.mwt.myhealth.domain.MedicineDiagnosis;

@Repository
public class MedicineDiagnosisDaoImpl implements MedicineDiagnosisDao{
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;

	@Override
	public List<MedicineDiagnosis> findAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MedicineDiagnosis findById(Long uid) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(MedicineDiagnosis medicineDiagnosis) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(MedicineDiagnosis medicineDiagnosis) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long uid) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveAll(List<MedicineDiagnosis> medicineDiagnosis) throws DaoException {
		for (MedicineDiagnosis currMedicineDiagnosis : medicineDiagnosis) {
			entityManager.persist(currMedicineDiagnosis);
		}
	}

}
