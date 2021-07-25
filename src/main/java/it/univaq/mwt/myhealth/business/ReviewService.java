package it.univaq.mwt.myhealth.business;

import java.util.List;
import java.util.Set;

import it.univaq.mwt.myhealth.business.exceptions.BusinessException;
import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.domain.Review;

public interface ReviewService {
	
	void save(Review review) throws BusinessException;

	List<Review> findReviewsByExamIds(Set<Long> examIds) throws BusinessException;

}
