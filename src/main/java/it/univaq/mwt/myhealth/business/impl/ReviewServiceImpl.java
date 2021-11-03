package it.univaq.mwt.myhealth.business.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.business.ReviewService;
import it.univaq.mwt.myhealth.dao.DaoException;
import it.univaq.mwt.myhealth.dao.ReviewDao;
import it.univaq.mwt.myhealth.domain.Review;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	private ReviewDao reviewDao;
	
	@Override
	public List<Review> findReviewsByExamIds(Set<Long> examIds) throws BusinessException{
		try {
		    return reviewDao.findReviewsByExamIds(examIds);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		 
	} 

	@Transactional
	@Override
	public void save(Review review) throws BusinessException {
		try {
			reviewDao.save(review);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
				
	}

	@Override
	public List<Review> findAllReviews() throws BusinessException {
		try {
			return reviewDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public Review findReviewById(Long id) throws BusinessException {
		try {
			return reviewDao.findById(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void updateReview(Review review) throws BusinessException {
		try {
			reviewDao.update(review);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Review> findReviewsByDoctor(Long doctorId) throws BusinessException {
		try {
			return reviewDao.findReviewsByDoctor(doctorId);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Review> findReviewsByExam(Long examId) throws BusinessException {
		try {
			return reviewDao.findReviewsByExam(examId);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}
}
