package it.univaq.mwt.myhealth.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.dao.ReviewDao;
import it.univaq.mwt.myhealth.domain.Review;
import it.univaq.mwt.myhealth.domain.User;

@Repository
public class ReviewDaoImpl implements ReviewDao{
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;

	@Override
	public List<Review> findAll() {
		return (List<Review>) entityManager.createQuery("FROM Review").getResultList();
	}

	@Override
	public Review findById(Long uid) {
		return (Review) entityManager.find(Review.class, uid);	
	}

	@Override
	public void save(Review review) {
		 entityManager.persist(review);		
	}

	@Override
	public void update(Review review) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long uid) {
		entityManager.remove(this.findById(uid));		
	}

	@Override
	public List<Review> findReviewsByExam(long id) {
		List<Review> result = (List<Review>) entityManager.createQuery("from User as u INNER JOIN Review as Review where Review.visit = :id ").setParameter("id", id).getResultList();
		return result;
	}

}
