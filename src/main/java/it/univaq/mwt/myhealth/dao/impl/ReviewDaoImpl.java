package it.univaq.mwt.myhealth.dao.impl;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.dao.DaoException;
import it.univaq.mwt.myhealth.dao.ReviewDao;
import it.univaq.mwt.myhealth.domain.Review;

@Repository
public class ReviewDaoImpl implements ReviewDao{
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Review> findAll() {
		return (List<Review>) entityManager.createQuery("FROM Review WHERE isRemoved = false").getResultList();
	}

	@Override
	public Review findById(Long uid) {
		return (Review) entityManager.find(Review.class, uid);	
	}

	@Override
	public void save(Review review) throws DaoException {
		entityManager.persist(review);
	}

	@Override
	public void update(Review review) throws DaoException {
		entityManager.merge(review);
	}

	@Override
	public void delete(Long uid) {
		entityManager.remove(this.findById(uid));		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Review> findReviewsByExamIds(Set<Long> examIds) throws DaoException {
		return (List<Review>) entityManager
				.createQuery("FROM Review rev WHERE rev.visit.reservation.exam.id IN (:examIds) AND isRemoved = false")
				.setParameter("examIds", examIds).getResultList();
	}

	@Override
	public void saveAll(List<Review> reviews) throws DaoException {
		for (Review review : reviews) {
			entityManager.persist(review);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Review> findReviewsByDoctor(Long doctorId) throws DaoException {
		return (List<Review>) entityManager
				.createQuery("FROM Review rev WHERE rev.visit.doctor.id = :doctorId AND isRemoved = false")
				.setParameter("doctorId", doctorId).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Review> findReviewsByExam(Long examId) throws DaoException {
		return (List<Review>) entityManager
				.createQuery("FROM Review rev WHERE rev.visit.exam.id = :examId AND isRemoved = false")
				.setParameter("examId", examId).getResultList();
	}

}
