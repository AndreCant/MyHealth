package it.univaq.mwt.myhealth.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.business.VisitService;
import it.univaq.mwt.myhealth.dao.DaoException;
import it.univaq.mwt.myhealth.dao.DiagnosisDao;
import it.univaq.mwt.myhealth.dao.MedicineDao;
import it.univaq.mwt.myhealth.dao.VisitDao;
import it.univaq.mwt.myhealth.domain.Diagnosis;
import it.univaq.mwt.myhealth.domain.Medicine;
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

	@Override
	public void saveVisit(Visit visit) throws BusinessException {
		try {
			visitDao.save(visit);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
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
		try {
			visitDao.update(visit);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void deleteVisit(Long uid) throws BusinessException {
		try {
			visitDao.delete(uid);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
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
	public List<Visit> findByReservation(Long id) throws BusinessException {
		try {
			return visitDao.findByReservation(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Visit> findByDoctor(Long id) throws BusinessException {
		try {
			return visitDao.findByDoctor(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Diagnosis> findAllDiagnosis() throws BusinessException {
		try {
			return diagnosisDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void deleteDiagnosis(Long id) throws BusinessException {
		try {
			diagnosisDao.delete(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		
	}

	@Override
	public List<Visit> findAll() throws BusinessException {
		try {
			return visitDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Visit> findVisitsByDiagnosis(Long diagnosisId) throws BusinessException {
		try {
			return visitDao.findByDiagnosis(diagnosisId);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Visit> findByPatient(Long id) throws BusinessException {
		try {
			return visitDao.findByPatient(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}
}
