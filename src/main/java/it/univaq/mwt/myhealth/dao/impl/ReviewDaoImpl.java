package it.univaq.mwt.myhealth.dao.impl;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.dao.ReviewDao;
import it.univaq.mwt.myhealth.domain.Review;
import it.univaq.mwt.myhealth.domain.User;

@Repository
public class ReviewDaoImpl implements ReviewDao{
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;

	@Override
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
		// TODO Auto-generated method stub
		
	}

	@Override
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
	}

}
