package it.univaq.mwt.myhealth.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.dao.DaoException;
import it.univaq.mwt.myhealth.dao.PaymentDao;
import it.univaq.mwt.myhealth.domain.Payment;

@Repository
public class PaymentDaoImpl implements PaymentDao {
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Payment> findAll() throws DaoException {
		return (List<Payment>) entityManager.createQuery("FROM Payment").getResultList();
	}

	@Override
	public void save(Payment payment) throws DaoException {
		entityManager.persist(payment);
	}

	@Override
	public void saveAll(List<Payment> payments) throws DaoException {
		for (Payment payment : payments) {
			entityManager.persist(payment);
		}
	}
}
