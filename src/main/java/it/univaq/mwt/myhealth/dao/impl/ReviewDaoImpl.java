package it.univaq.mwt.myhealth.dao.impl;

import java.util.List;
<<<<<<< HEAD
=======
import java.util.Set;
>>>>>>> a26c0e56d9b307c6f8db5422b9cf7752ccb2b507

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

<<<<<<< HEAD
=======
import it.univaq.mwt.myhealth.business.exceptions.DaoException;
>>>>>>> a26c0e56d9b307c6f8db5422b9cf7752ccb2b507
import it.univaq.mwt.myhealth.dao.ReviewDao;
import it.univaq.mwt.myhealth.domain.Review;
import it.univaq.mwt.myhealth.domain.User;

@Repository
public class ReviewDaoImpl implements ReviewDao{
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;

	@Override
<<<<<<< HEAD
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
=======
	public List<Review> findAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Review findById(Long uid) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Review review) throws DaoException {
		entityManager.persist(review);
	}

	@Override
	public void update(Review review) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long uid) throws DaoException {
>>>>>>> a26c0e56d9b307c6f8db5422b9cf7752ccb2b507
		// TODO Auto-generated method stub
		
	}

	@Override
<<<<<<< HEAD
	public void delete(Long uid) {
		entityManager.remove(this.findById(uid));		
	}

	@Override
	public List<Review> findReviewsByExam(long id) {
		List<Review> result = (List<Review>) entityManager.createQuery("from User as u INNER JOIN Review as Review where Review.visit = :id ").setParameter("id", id).getResultList();
		return result;
=======
	public List<Review> findReviewsByExamIds(Set<Long> examIds) throws DaoException {
		return (List<Review>) entityManager
				.createQuery("FROM Review rev WHERE rev.visit.reservation.exam.id IN (:examIds)")
				.setParameter("examIds", examIds).getResultList();
	}

	@Override
	public void saveAll(List<Review> reviews) throws DaoException {
		for (Review review : reviews) {
			entityManager.persist(review);
		}
>>>>>>> a26c0e56d9b307c6f8db5422b9cf7752ccb2b507
	}

}
