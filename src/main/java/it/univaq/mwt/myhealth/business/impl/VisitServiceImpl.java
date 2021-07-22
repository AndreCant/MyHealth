package it.univaq.mwt.myhealth.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.mwt.myhealth.business.VisitService;
import it.univaq.mwt.myhealth.business.exceptions.BusinessException;
import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.dao.DiagnosisDao;
import it.univaq.mwt.myhealth.dao.MedicineDao;
import it.univaq.mwt.myhealth.dao.MedicineDiagnosisDao;
import it.univaq.mwt.myhealth.dao.VisitDao;
import it.univaq.mwt.myhealth.domain.Diagnosis;
import it.univaq.mwt.myhealth.domain.Medicine;
import it.univaq.mwt.myhealth.domain.MedicineDiagnosis;
import it.univaq.mwt.myhealth.domain.Visit;

@Service
@Transactional
public class VisitServiceImpl implements VisitService{
	
	@Autowired
	private VisitDao visitDao;
	
	@Autowired
	private DiagnosisDao diagnosisDao;
	
	@Autowired
	private MedicineDao medicineDao;
	
	@Autowired
	private MedicineDiagnosisDao medicineDiagnosisDao;

	@Override
	public List<Visit> findAllUsers() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveVisit(Visit visit) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Visit findById(Long id) throws BusinessException {
		try {
			return visitDao.findById(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void updateVisit(Visit visit) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteVisit(Long uid) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveDiagnosis(List<Diagnosis> diagnosis) throws BusinessException {
		try {
			diagnosisDao.saveAll(diagnosis);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void saveVisits(List<Visit> visits) throws BusinessException {
		try {
			visitDao.saveAll(visits);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void saveMedicines(List<Medicine> medicines) throws BusinessException {
		try {
			medicineDao.saveAll(medicines);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void saveMedicineDiagnosis(List<MedicineDiagnosis> medicineDiagnosis) throws BusinessException {
		try {
			medicineDiagnosisDao.saveAll(medicineDiagnosis);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		
	}

}
