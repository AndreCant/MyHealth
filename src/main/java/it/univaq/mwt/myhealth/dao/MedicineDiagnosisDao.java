package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.domain.MedicineDiagnosis;

public interface MedicineDiagnosisDao {
	
	public void saveAll(List<MedicineDiagnosis> medicineDiagnosis) throws DaoException;
}
