package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.business.exceptions.BusinessException;
import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.domain.Review;

public interface ReviewDao {
	
	public List<Review> findAll() throws DaoException;
	
	public Review findById(Long uid) throws DaoException;
   
	public void save(Review review)  throws DaoException;
 
	public void update(Review review) throws DaoException;
  
	public void delete(Long uid) throws DaoException;
	
	List<Review> findReviewsByExam(long id) throws DaoException;

}
