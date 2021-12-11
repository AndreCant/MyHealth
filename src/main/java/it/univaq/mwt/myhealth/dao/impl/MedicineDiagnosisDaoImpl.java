package it.univaq.mwt.myhealth.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.dao.DaoException;
import it.univaq.mwt.myhealth.dao.MedicineDiagnosisDao;
import it.univaq.mwt.myhealth.domain.MedicineDiagnosis;

@Repository
public class MedicineDiagnosisDaoImpl implements MedicineDiagnosisDao{
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;

	@Override
	public void saveAll(List<MedicineDiagnosis> medicineDiagnosis) throws DaoException {
		for (MedicineDiagnosis currMedicineDiagnosis : medicineDiagnosis) {
			entityManager.persist(currMedicineDiagnosis);
		}
	}

}
