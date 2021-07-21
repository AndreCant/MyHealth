package it.univaq.mwt.myhealth.dao;

import java.util.List;
import java.util.Set;

<<<<<<< HEAD
import it.univaq.mwt.myhealth.business.exceptions.BusinessException;
=======
>>>>>>> a26c0e56d9b307c6f8db5422b9cf7752ccb2b507
import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.domain.Review;

public interface ReviewDao {
	
	public List<Review> findAll() throws DaoException;
	
	public Review findById(Long uid) throws DaoException;
   
<<<<<<< HEAD
	public void save(Review review)  throws DaoException;
=======
	public void save(Review review) throws DaoException;
>>>>>>> a26c0e56d9b307c6f8db5422b9cf7752ccb2b507
 
	public void update(Review review) throws DaoException;
  
	public void delete(Long uid) throws DaoException;
<<<<<<< HEAD
	
	List<Review> findReviewsByExam(long id) throws DaoException;

=======

	public List<Review> findReviewsByExamIds(Set<Long> examIds) throws DaoException;
	
	public void saveAll(List<Review> reviews) throws DaoException;
>>>>>>> a26c0e56d9b307c6f8db5422b9cf7752ccb2b507
}
