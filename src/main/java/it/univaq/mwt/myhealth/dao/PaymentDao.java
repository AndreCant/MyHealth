package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.domain.Payment;

public interface PaymentDao {
	
	public List<Payment> findAll() throws DaoException;
	
	public Payment findById(Long uid) throws DaoException;
   
	public void save(Payment payment) throws DaoException;
 
	public void update(Payment payment) throws DaoException;
  
	public void delete(Long uid) throws DaoException;
	
	public void saveAll(List<Payment> payments) throws DaoException;
}
