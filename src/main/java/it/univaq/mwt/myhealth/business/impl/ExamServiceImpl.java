package it.univaq.mwt.myhealth.business.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.mwt.myhealth.business.ExamService;
import it.univaq.mwt.myhealth.business.exceptions.BusinessException;
import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.dao.ExamDao;
import it.univaq.mwt.myhealth.dao.ReviewDao;
import it.univaq.mwt.myhealth.domain.Exam;
import it.univaq.mwt.myhealth.domain.Review;

@Service
@Transactional
public class ExamServiceImpl implements ExamService{
	
	@Autowired
	private ExamDao examDao;
	
	@Autowired
	private ReviewDao reviewDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<Exam> findAllExams() throws BusinessException{
		try {
			return examDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}
	
	@Override
	public List<Exam> findExamsByType(String type) throws BusinessException {
		try {
			return examDao.findByType(type);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Exam> findExamsBySpecialization(String specialization) throws BusinessException {
		try {
			return examDao.findBySpecialization(specialization);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public Exam findById(Long uid) throws BusinessException {
		try {
			return examDao.findById(uid);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}
	
	@Override
	public Exam findByName(String name) throws BusinessException {
		try {
			return examDao.findByName(name);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void save(Exam exam) throws BusinessException {
		try {
			examDao.save(exam);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void update(Exam exam) throws BusinessException {
		try {
			examDao.update(exam);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void delete(Long uid) throws BusinessException {
		try {
			examDao.delete(uid);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void saveExams(List<Exam> exams) throws BusinessException {
		try {
			examDao.saveAll(exams);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Review> findExamReview(Set<Long> examIds) throws BusinessException {
		try {
			return reviewDao.findReviewsByExamIds(examIds);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}
}
