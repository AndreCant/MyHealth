package it.univaq.mwt.myhealth.business;

import java.util.List;

import it.univaq.mwt.myhealth.domain.Diagnosis;
import it.univaq.mwt.myhealth.domain.Medicine;
import it.univaq.mwt.myhealth.domain.MedicineDiagnosis;
import it.univaq.mwt.myhealth.domain.Visit;

public interface VisitService {
	
	List<Visit> findAll() throws BusinessException;
	
	List<Visit> findByReservation(Long id) throws BusinessException;
	
	List<Visit> findByDoctor(Long id) throws BusinessException;
	   
	void saveVisit(Visit visit) throws BusinessException;
	
	void saveVisits(List<Visit> visits) throws BusinessException;
	 
	void updateVisit(Visit visit) throws BusinessException;
  
	void deleteVisit(Long uid) throws BusinessException;

	Visit findById(Long id) throws BusinessException;
	
	void saveDiagnosis(List<Diagnosis> diagnosis) throws BusinessException;
	
	void saveMedicines(List<Medicine> medicines) throws BusinessException;
	
	void saveMedicineDiagnosis(List<MedicineDiagnosis> medicineDiagnosis) throws BusinessException;
	
	List<Diagnosis> findAllDiagnosis() throws BusinessException;
	
	void deleteDiagnosis(Long id) throws BusinessException;
	
	List<Visit> findVisitsByDiagnosis(Long diagnosisId) throws BusinessException;
}
