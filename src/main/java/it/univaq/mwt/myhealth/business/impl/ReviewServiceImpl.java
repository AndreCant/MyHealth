package it.univaq.mwt.myhealth.business.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.mwt.myhealth.business.ReviewService;
import it.univaq.mwt.myhealth.business.exceptions.BusinessException;
import it.univaq.mwt.myhealth.business.exceptions.DaoException;
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
}
