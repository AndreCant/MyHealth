package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.domain.Review;

public interface ReviewDao {
	
	public List<Review> findAll();
	
	public Review findById(Long uid);
   
	public void save(Review review);
 
	public void update(Review review);
  
	public void delete(Long uid);
}
