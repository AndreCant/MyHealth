package it.univaq.mwt.myhealth.business;

import java.util.List;
import it.univaq.mwt.myhealth.business.exceptions.BusinessException;
import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.domain.Review;

public interface ReviewService {
	
	List<Review> findReviewsByExam(long id) throws BusinessException, DaoException;

	void save(Review review) throws BusinessException;

}
