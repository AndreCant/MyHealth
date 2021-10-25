package it.univaq.mwt.myhealth.dao;

import java.util.List;
import java.util.Set;

import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.domain.Review;

public interface ReviewDao {
	
	public List<Review> findAll() throws DaoException;
	
	public Review findById(Long uid) throws DaoException;
   
	public void save(Review review) throws DaoException;
 
	public void update(Review review) throws DaoException;
  
	public void delete(Long uid) throws DaoException;

	public List<Review> findReviewsByExamIds(Set<Long> examIds) throws DaoException;
	
	public void saveAll(List<Review> reviews) throws DaoException;
	
	public List<Review> findReviewsByDoctor(Long doctorId) throws DaoException;
}
