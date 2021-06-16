package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.domain.Payment;

public interface PaymentDao {
	
	public List<Payment> findAll();
	
	public Payment findById(Long uid);
   
	public void save(Payment payment);
 
	public void update(Payment payment);
  
	public void delete(Long uid);
}
