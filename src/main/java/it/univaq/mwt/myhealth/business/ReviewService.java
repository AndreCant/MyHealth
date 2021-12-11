package it.univaq.mwt.myhealth.business;

import java.util.List;
import java.util.Set;

import it.univaq.mwt.myhealth.domain.Review;

public interface ReviewService {
	
	void save(Review review) throws BusinessException;

	List<Review> findReviewsByExamIds(Set<Long> examIds) throws BusinessException;
	
	List<Review> findAllReviews() throws BusinessException;
	
	Review findReviewById(Long id) throws BusinessException;
	
	void updateReview(Review review) throws BusinessException;
	
	List<Review> findReviewsByDoctor(Long doctorId) throws BusinessException;
	
	List<Review> findReviewsByExam(Long examId) throws BusinessException;

}
